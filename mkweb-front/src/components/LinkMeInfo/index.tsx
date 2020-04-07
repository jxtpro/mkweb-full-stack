import { WechatOutlined } from '@ant-design/icons';
import React from 'react';
import classNames from 'classnames';
import HeaderDropdown from '../HeaderDropdown';
import { Typography, Card } from 'antd';
const { Title, Paragraph, Text } = Typography;
import styles from './index.less';
import { useIntl } from 'umi';

interface LinkMeProps {
  className?: string;
}

const LinkMeInfo: React.FC<LinkMeProps> = (props) => {
  const { className } = props;

  const { formatMessage } = useIntl();

  const copy = (text: string) => {
    return <code>
      <Typography.Text copyable>{text}</Typography.Text>
    </code>
  }
  const linkmeMenu = (
    <Card>
      <Typography className={styles.introduction}>
        <Title level={4}>{formatMessage({ id: 'link.me.title' })}</Title>
        <Paragraph>
          {formatMessage({ id: 'link.me.time' })} <Text code>{formatMessage({ id: 'link.me.time.am' })}</Text>{formatMessage({ id: 'link.me.time.arrv' })}<Text code>{formatMessage({ id: 'link.me.time.pm' })}</Text>{formatMessage({ id: 'link.me.fu' })}{formatMessage({ id: 'link.me.time.call' })}
        </Paragraph>
        <Paragraph>
          <ul>
            <li>
              <div>
                {formatMessage({ id: 'link.me.qq' })}<Text strong>{copy(`${formatMessage({ id: 'link.me.qq.number' })}`)}</Text>
                <br></br>
                <div className={styles.link_me}>
                  <img src='/qq.jpg' />
                </div>
              </div>
            </li>
            <li>
              <div>
                {formatMessage({ id: 'link.me.weixin' })}<Text strong>{copy(`${formatMessage({ id: 'link.me.weixin.number' })}`)}</Text>
                <br></br>
                <div className={styles.link_me}>
                  <img src='/weixin.png' />
                </div>
              </div>
            </li>
            <li>
              {formatMessage({ id: 'link.me.phone' })}<Text strong>{copy(`${formatMessage({ id: 'link.me.phone.number' })}`)}</Text>
            </li>
          </ul>
        </Paragraph>
      </Typography>
    </Card>
  );
  return (
    <HeaderDropdown overlay={linkmeMenu} placement="bottomRight">
      <span className={classNames(styles.dropDown, className)}>
        <WechatOutlined title="联系人" />
      </span>
    </HeaderDropdown>
  );
};

export default LinkMeInfo;
