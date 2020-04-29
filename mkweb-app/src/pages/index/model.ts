
// import Taro from '@tarojs/taro';
import * as indexApi from './service';

export default {
  namespace: 'index',
  state: {
  },

  effects: {
    * getArticleAll({ payload }, { select, call, put }) {
      const res = yield call(indexApi.getArticleAll)
      if (res && res.status) {
        console.log(res)
        yield put({
          type: 'updateState',
          payload: {
            data: res.list
          }
        })
      }
    }
  },

  reducers: {
    updateState(state, { payload: data }) {
      return { ...state, ...data }
    }
  }

}
