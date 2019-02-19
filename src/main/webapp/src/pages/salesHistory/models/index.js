import {queryChartListByType} from '@/services/history';

export default {
  namespace: 'history',

  state: {
    showDetail: false,
    spCode: '',
    lineData: [],
    barData1: [],
    barData2: [],
    xAxisData: []
  },
  effects: {
    * fetchChange({payload}, {call, put}) {
      const response = yield call(queryChartListByType, payload);
      yield put({
        type: 'getChange',
        payload: response,
      });
    },
  },
  reducers: {
    showDetail(state, action) {
      return {
        ...state,
        showDetail: action.payload.showDetail,
        spCode: action.payload.spCode
      };
    },
    getChange(state,action){
      return{
        ...state,
        lineData: action.payload.data.rateList,
        barData1: action.payload.data.saleList,
        barData2: action.payload.data.taskList,
        xAxisData:action.payload.data.monthList,
      }
    }
  },
};
