import { stringify } from 'qs';
import request from '@/utils/request';

export async function queryTaskList(params){
  return request(`/salesTask/getSalesTaskList?${stringify(params)}`)
}

export async function updateTask(params){
  return request('/salesTask/updateTask',{
    method: 'POST',
    body: {
      ...params
    }
  })
}

export async function importTask(params){
  return request('/salesTask/importSalesTask',{
    method: 'POST',
    body: {
      ...params
    },
    upload:true
  })
}


export async function sendMessage(params){
  return request(`/salesTask/sendMessage?${stringify(params)}`)
}

