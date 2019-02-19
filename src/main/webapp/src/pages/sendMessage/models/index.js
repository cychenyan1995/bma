import {querySendInfo} from '@/services/sendMessage'

export default {
  namespace: 'sendMessage',
  state: {
    data: [],

  },
  effects: {
    * fetchQuerySendInfo({ payload }, { call, put }){
      const response = yield call(querySendInfo, payload);
      yield put({
        type: 'getSendInfo',
        payload: response
      })
    }
  },
  reducers: {
    getSendInfo(state,action){
      console.log(action.payload);
      return {
        ...state,
        data: action.payload.data
      }
    }
  }
}