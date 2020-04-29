
import Taro, { Component, Config } from '@tarojs/taro'
import { View, Image } from '@tarojs/components'
// import { connect } from '@tarojs/redux'
// import Api from '../../utils/request'
// import Tips from '../../utils/tips'
import { MaineProps, MaineState } from './maine.interface'
import './maine.scss'
// import { } from '../../components'

// @connect(({ maine }) => ({
//     ...maine,
// }))

class Maine extends Component<MaineProps, MaineState> {
  config: Config = {
    navigationBarTitleText: '刀马客建站'
  }
  constructor(props: MaineProps) {
    super(props)
    this.state = {}
  }

  componentDidMount() {

  }

  render() {
    return (
      <View className='maine-wrap'>
        <View className='at-article'>
          <View className='at-article__h1'> 在线服务 </View>
          <View className='at-article__info'> 2018年4月28日-至今 刀马客 </View>
          <View className='at-article__content'>
            <View className='at-article__section'>
              <View className='at-article__h2'>时间 早9:30到晚6:30, 均可随时致电与我沟通。</View>
              <View className='at-article__h3'>QQ</View>
              <View className='at-article__p'>
                <Image
                  className='at-article__img'
                  src='../../assets/qq.jpg'
                  mode='widthFix' />
              </View>
              <View className='at-article__h3'>微信</View>
              <View className='at-article__p'>
                <Image
                  className='at-article__img'
                  src='../../assets/weixin.png'
                  mode='widthFix' />
              </View>
            </View>
          </View>
        </View>
      </View>
    )
  }
}

export default Maine
