import React from 'react';
import { Card, Row, Col, Typography, List, Avatar, Drawer, Button, message } from 'antd';
import { ConnectState } from '@/models/connect';
import { CurrentUser } from '@/models/user';
import { history, formatMessage, connect } from 'umi';
import { queryArticleAll, updateArticleLike, updateArticleBrowse } from './service';
import { LikeOutlined, RiseOutlined, EditOutlined } from '@ant-design/icons';
import styles from './Welcome.less';

const { Title, Paragraph, Text } = Typography;
const gotoAddShare = () => {
  history.push('/edit/0')
}

const IconText = ({ icon, text, onClick }) => (
  <span onClick={onClick}>
    {React.createElement(icon, { style: { marginRight: 8 } })}
    {text}
  </span>
);

interface WelcomeProps {
  currentUser?: CurrentUser;
};


class Welcome extends React.Component<WelcomeProps> {

  state = {
    renderListData: [],
    showContent: false,
    detail: {},

  }

  showDrawer = (article: any) => {
    this.setState({
      showContent: true,
      detail: article,
    }, () => {
      this.browse(article)
    });
  };

  onClose = () => {
    this.setState({
      showContent: false,
    });
  };

  componentDidMount() {
    queryArticleAll({ pageSize: 10, page: 1 }).then(res => {
      // console.log(res)
      if (res && res.status && res.list) {
        let listData: any[] = [];
        res.list.forEach((article: any) => {
          listData.push({
            id: article.id,
            likeCount: article.likeCount,
            browseCount: article.browseCount,
            title: <Button key={article.id} type="link" onClick={() => { this.showDrawer(article) }}>{article.fileName}</Button>,
            avatar: 'https://www.3jxq.com/public/upload/714d0ddeb1a6085900b8c4744bd79e43.png',
            description: `${article.fileDesc}`,
            tilteImg: (article.attachments && article.attachments.length > 0 ? article.attachments[0].url : '')
          });
        });
        this.setState({ renderListData: listData });
      }
    });
  }

  // 待优化
  checkAuth(auth: any[], params: string) {
    if (auth && auth.length > 0) {
      return auth.find(value => value === params)
    }
    return false;
  };

  edit(id: number) {
    history.push(`/edit/${id}`)
  }

  like(item: number) {
    updateArticleLike(item.id).then(res => {
      if (res && res.status) {
        message.info("你 good ! , like you !")
        let data = this.state.renderListData;
        this.setState({ renderListData: data.map((item: any) => ({ ...item, ...{ likeCount: item.likeCount + 1 } })) });
      }
    })
  }
  browse(item: number) {
    updateArticleBrowse(item.id).then(res => {
      if (res && res.status) {
        let data = this.state.renderListData;
        this.setState({ renderListData: data.map((item: any) => ({ ...item, ...{ browseCount: item.browseCount + 1 } })) });
      }
    })
  }

  render() {
    const { currentUser } = this.props;

    return (
      <Row>
        <Col span={16} offset={1} xs={24} sm={24} md={17} lg={16} xl={16}>
          <Card extra={this.checkAuth(currentUser?.auth, 'admin') ? <a onClick={gotoAddShare}>新增</a> : null}>
            <Title level={3}>{formatMessage({ id: 'welcome.site.content.share.title' })}</Title>
            <List
              itemLayout="vertical"
              size="large"
              pagination={{
                onChange: page => {
                  // console.log(page);
                },
                pageSize: 3,
              }}
              dataSource={this.state.renderListData}
              renderItem={(item: any) => (
                <List.Item
                  key={item.title}
                  actions={[
                    <IconText icon={RiseOutlined} text={item.browseCount ? item.browseCount : 0} key="list-vertical-star-o" onClick={null} />,
                    <IconText icon={LikeOutlined} text={item.likeCount ? item.likeCount : 0} key="list-vertical-like-o" onClick={() => { this.like(item) }} />,
                    (currentUser && this.checkAuth(currentUser?.auth, 'admin') && (<IconText icon={EditOutlined} text="编辑" onClick={() => { this.edit(item.id) }} key="list-vertical-edit-o" />))
                  ]}
                  extra={
                    <img
                      width={272}
                      alt="logo"
                      src={item.tilteImg ? item.tilteImg : "https://gw.alipayobjects.com/zos/rmsportal/mqaQswcyDLcXyDKnZfES.png"}
                    />
                  }
                >
                  <List.Item.Meta
                    avatar={<Avatar src={item.avatar} />}
                    title={<a href={item.href}>{item.title}</a>}
                    description={item.description}
                  />
                </List.Item>
              )}
            />
          </Card>
        </Col>
        <Col span={4} offset={1} xs={24} sm={24} md={5} lg={6} xl={5}>
          <Card>
            <Title level={3}>{formatMessage({ id: 'welcome.site.desc' })}</Title>
            <Paragraph>
              {formatMessage({ id: 'welcome.site.desc.title' })}<Text mark>{formatMessage({ id: 'welcome.site.desc.mark' })}</Text>{formatMessage({ id: 'welcome.site.desc.mark.dou' })}{formatMessage({ id: 'welcome.site.desc.it' })}
            </Paragraph>
            <Paragraph>
              {formatMessage({ id: 'welcome.site.desc.subtext' })}
            </Paragraph>
            <Paragraph>
              {formatMessage({ id: 'welcome.site.desc.content' })}
            </Paragraph>
            <Paragraph>
              <Title level={4}>{formatMessage({ id: 'welcome.site.open.code.title' })}</Title>
              <a href="https://github.com/jxtpro/mkweb-full-stack" target="_blank">https://github.com/jxtpro/mkweb-full-stack</a>
            </Paragraph>
            <Paragraph>
              {formatMessage({ id: 'welcome.site.help.me' })}
            </Paragraph>
            <Paragraph className={styles.help_me} >
              <img src='/help_me.jpg' />
            </Paragraph>
          </Card>
        </Col>
        <Drawer
          title="文档内容"
          placement="right"
          closable={false}
          onClose={this.onClose}
          width="40%"
          visible={this.state.showContent}
        >
          {this.state.detail && this.state.detail.content && (
            <div dangerouslySetInnerHTML={{ __html: this.state.detail.content }} ></div>
          )}
        </Drawer>
      </Row>
    )
  }
}

export default connect(({ user }: ConnectState) => ({
  currentUser: user.currentUser,
}))(Welcome);