
import Taro, { Component, Config } from '@tarojs/taro'
import { View, ScrollView, Image, Text, RichText } from '@tarojs/components'
import { connect } from '@tarojs/redux'
// import Api from '../../utils/request'
import Tips from '../../utils/tips'
import { AtList, AtListItem, AtDrawer } from "taro-ui"
import TagPage from '../../components/TagPage/TagPage'

import { IndexProps, IndexState } from './index.interface'
import './index.scss'
// import { } from '../../components'

@connect(({ index }) => ({
  ...index,
}))

class Index extends Component<IndexProps, IndexState> {
  config: Config = {
    navigationBarTitleText: '刀马客建站'
  }
  state = {
    showContent: false,
    detail: {},
  }
  async getArticleAll() {
    const { dispatch } = this.props;
    await dispatch({
      type: 'index/getArticleAll'
    })
  }

  constructor(props: IndexProps) {
    super(props)
  }

  componentDidMount() {
    this.getArticleAll()

  }

  showDrawer = (article: any) => {
    this.setState({
      showContent: true,
      detail: article,
    }, () => {
      // this.browse(article)
    });
  };

  onClose = () => {
    this.setState({
      showContent: false,
    });
  };


  render() {
    const { showContent } = this.state;
    const { data } = this.props;
    return (
      <View className='index-wrap'>
        <ScrollView>
          <TagPage >
            <Image
              style='width:100%;height:250px'
              src="../../assets/home-hd.png"
            />
          </TagPage>
          <View className="logo">
            <Image
              style='width: 80px;height: 80px;background: #fff;'
              src='../../assets/logo.svg'
            />
            <Text className="logo_site_name">刀马客建站</Text>
          </View>

          <View className="share">
            <AtList>
              <View className='at-article__h1'>分享</View>
              {data && data.map(article => (
                < AtListItem
                  title={article.fileName}
                  note={article.fileDesc}
                  arrow='right'
                  thumb='https://www.3jxq.com/public/upload/714d0ddeb1a6085900b8c4744bd79e43.png'
                  onClick={() => { this.showDrawer(article) }}
                />
              ))}
            </AtList>
          </View>

          <View className="site_desc">
            <View className='at-article'>
              <View className='at-article__h1'>站长简介</View>
              <View className='at-article__info'>刀马客</View>
              <View className='at-article__content'>
                <View className='at-article__section'>
                  <View className='at-article__p'>
                    站长花名：刀马客。从事IT工作多年，掌握全栈技术开发，追求稳定，高可用。
                  </View>
                  <View className='at-article__p'>
                    极致优雅的分享，便是传播正能量。
                  </View>
                  <View className='at-article__p'>
                    建此站点一是想打造一个~个人IP。二是可以把自己所学和实践经验中得到一些技术分享给大家 。 我会保持简洁易懂的更新，希望大家能够喜欢,也可以添加站长的联系方式哦，随时与我联系。
                  </View>
                  <View className='at-article__h2'>开源地址</View>
                  <View className='at-article__p'>https://github.com/jxtpro/mkweb-full-stack</View>

                  <View className='at-article__p'>有钱的捧个钱场，^.^ . 后续还会进行源码层实现和深度解析设计,广泛分享所见即所得。
</View>
                  <Image
                    className='at-article__img'
                    src='../../assets/help_me.jpg'
                    mode='widthFix' />
                </View>
              </View>
            </View>
          </View>

          <AtDrawer
            width="90%"
            show={showContent}
            onClose={this.onClose}
            mask
          >
            <View className='at-article'>
              <View className='at-article__h1'>文档详情</View>
              <View className='at-article__info'> 刀马客</View>
              <View className='at-article__content'>
                <View className='at-article__section'>
                  <View className='at-article__p'>
                    <RichText className='text' nodes={this.state.detail.content} />
                  </View>
                </View>
              </View>
            </View>
          </AtDrawer>
        </ScrollView>
      </View >
    )
  }
}

export default Index
