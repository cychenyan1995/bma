import { queryList, queryMonthSalesHistory, queryChartListByType } from '@/services/history';

export default {
  namespace: 'historyTable',
  state: {
    list: [],
    total: 0,
    filters: {},
    search: {},
    showSearch: false,
    exportMonth: '',
    lineData: [],
    barData1: [],
    barData2: [],
    xAxisData: [],
    currentPage: 1
  },

  effects: {
    * fetchList({ payload }, { call, put }) {
      const response = yield call(queryList, payload);
      yield put({
        type: 'getList',
        payload: response
      })
    },
    * fetchListByMonth({ payload }, { call, put }) {
      const response = yield call(queryMonthSalesHistory, payload);
      yield put({
        type: 'getListByMonth',
        payload: response
      })
    },
    * fetchChange({ payload }, { call, put }) {
      const response = yield call(queryChartListByType, payload);
      yield put({
        type: 'getChange',
        payload: response,
      });
    },
  },

  reducers: {
    getList(state, action) {
      return {
        ...state,
        list: action.payload.data,
        total: action.payload.pageTotal,
        // current: action.payload.current,
        currentPage: action.payload.pageNum
      }
    },
    getFilters(state, action) {
      return {
        ...state,
        filters: action.payload
      }
    },
    search(state, action) {
      return {
        ...state,
        search: action.payload
      };
    },
    showSearch(state, action) {
      return {
        ...state,
        showSearch: action.payload
      };
    },
    setExportMonth(state, action) {
      return {
        ...state,
        exportMonth: state.filters.month,
        list: action.payload.data,
        total: action.payload.pageTotal,
        current: action.payload.current
      };
    },
    getListByMonth(state, action) {
      return {
        ...state,
        MonthlyExport: action.payload.data
      };
    },
    clearUrl(state, action) {
      return {
        ...state,
        MonthlyExport: action.payload
      }
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
  }
}
