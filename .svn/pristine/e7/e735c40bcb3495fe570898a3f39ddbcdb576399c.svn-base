import { queryTaskList, updateTask, sendMessage, importTask } from '@/services/salesTask';
import { notification } from "antd/lib/index";

export default {
  namespace: 'salesTask',
  state: {
    list: [],
    total: 0,
    filters: {},
    publishStatus: 0,
    currentPage: 1
  },

  effects: {
    * fetchTaskList({ payload }, { call, put }) {
      const response = yield call(queryTaskList, payload);
      yield put({
        type: 'getTaskList',
        payload: response
      });
    },

    * fetchUpdateTask({ payload }, { call, put }) {
      const response = yield call(updateTask, payload);
      yield put({
        type: 'getUpdateTask',
        payload: response
      });
    },

    * fetchSendMessage({ payload }, { call, put }) {
      const response = yield call(sendMessage, payload);
      yield put({
        type: 'getSendMessage',
        payload: response
      })
    },
    * fetchImportTask({ payload }, { call, put }) {
      const response = yield call(importTask, payload);
      yield put({
        type: 'getImportTask',
        payload: response
      });
    }
  },
  reducers: {
    getTaskList(state, action) {
      if (action.payload.code) {
        if (action.payload.code === 1000) {
          return {
            ...state,
            total: action.payload.pageTotal,
            list: action.payload.data,
            publishStatus: action.payload.publishStatus,
            currentPage: action.payload.pageNum
          }
        }
        notification.error({
          message: action.payload.code,
          description: action.payload.message,
        });
      }

    },
    getUpdateTask(state, action) {
      if (action.payload.code) {
        if (action.payload.code === 1000) {
          return {
            ...state,
            total: action.payload.pageTotal,
            list: action.payload.data
          }
        }
        notification.error({
          message: action.payload.code,
          description: action.payload.message,
        });
      }
    },

    getSendMessage(state, action) {
      if (action.payload.code) {
        if (action.payload.code === 1000) {
          return {
            ...state,
            publishStatus: 1
          }
        }
      }

      notification.error({
        message: action.payload.code,
        description: action.payload.message,
      })
    },

    getImportTask(state, action) {
      if (action.payload.code) {
        if (action.payload.code === 1000) {
          return {
            ...state,
          }
        }
        notification.error({
          message: action.payload.code,
          description: action.payload.message,
        });
      }
    }
  }
}


