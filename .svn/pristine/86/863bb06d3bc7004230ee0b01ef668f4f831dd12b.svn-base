import {notification} from "antd/lib/index";
import {queryMonthlyDetailList, queryChartListBySp} from '@/services/history';

export default {
  namespace: 'historyDetail',

  state: {
    list: [],
    total: 0,
    filters:{},
    detail:null,
    lineData: [],
    barData1: [],
    barData2: [],
    xAxisData: [],
    currentPage: 1
  },
  effects: {
    * fetchDetail({payload}, {call, put}) {
      const response = yield call(queryMonthlyDetailList, payload);
      yield put({
        type: 'getDetail',
        payload: response,
      });
    },
    * fetchChart({payload}, {call, put}) {
      const response = yield call(queryChartListBySp, payload);
      yield put({
        type: 'getChart',
        payload: response,
      });
    },
  },
  reducers: {
    getDetail(state, action) {
      if (action.payload.code) {
        if (action.payload.code === 1000) {
          return {
            ...state,
            detail: action.payload.data[0],
            list:action.payload.data,
            total: action.payload.pageTotal,
            currentPage: action.payload.pageNum
          };
        }
        notification.error({
          message: action.payload.code,
          description: action.payload.message,
        });
      }
      return state;
    },
    getFilters(state,action){
      return {
        ...state,
        filters: action.payload
      }
    },
    getChart(state,action){
      return{
        ...state,
        lineData: action.payload.data.rateList,
        barData1: action.payload.data.saleList,
        barData2: action.payload.data.taskList,
        xAxisData:action.payload.data.monthList,
      }
    },
  },
};
