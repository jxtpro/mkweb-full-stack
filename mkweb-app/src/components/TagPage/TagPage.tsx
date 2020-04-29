import Taro, { Component } from '@tarojs/taro'
import { View, Image } from '@tarojs/components'
import { AtCurtain, AtButton } from 'taro-ui'
import { TagPageProps, TagPageState } from './TagPage.interface'
import './TagPage.scss'

class TagPage extends Component<TagPageProps, TagPageState> {
  state = {
    isOpened: true,
  }

  constructor(props: TagPageProps) {
    super(props)
  }
  static options = {
    addGlobalClass: true
  }
  static defaultProps: TagPageProps = {}

  handleChange() {
    this.setState({
      isOpened: true
    })
  }
  onClose() {
    this.setState({
      isOpened: false
    })
  }
  render() {
    return (
      <View className='fx-TagPage-wrap'>
        <AtCurtain
          isOpened={this.state.isOpened}
          onClose={this.onClose.bind(this)}
        >
          {this.props.children}
        </AtCurtain>
      </View>
    )
  }
}

export default TagPage
