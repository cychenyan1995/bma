import {notification} from "antd/lib/index";
import {queryAreaList, queryLeaderList} from '@/services/history';

export default {
  namespace: 'historyForm',

  state: {
    areaOptions:[],
    leaderOptions:[]
  },
  effects: {
    * fetchAreaOptions({payload}, {call, put}) {
      const response = yield call(queryAreaList, payload);
      yield put({
        type: 'getAreaOptions',
        payload: response,
      });
    },
    * fetchLeaderOptions({payload}, {call, put}) {
      const response = yield call(queryLeaderList, payload);
      yield put({
        type: 'getLeaderOptions',
        payload: response,
      });
    }
  },
  reducers: {
    getAreaOptions(state, action) {
      if (action.payload.code) {
        if (action.payload.code === 1000) {
          return {
            ...state,
            areaOptions: action.payload.data,
            total: action.payload.pageTotal,
          };
        }
        notification.error({
          message: action.payload.code,
          description: action.payload.message,
        });
      }
      return state;
    },
    getLeaderOptions(state, action) {
      if (action.payload.code) {
        if (action.payload.code === 1000) {
          return {
            ...state,
            leaderOptions: action.payload.data,
            total: action.payload.pageTotal,
          };
        }
        notification.error({
          message: action.payload.code,
          description: action.payload.message,
        });
      }
      return state;
    }
  },
};
