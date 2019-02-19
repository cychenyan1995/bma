import { stringify } from 'qs';
import request from '@/utils/request';

export async function querySendInfo(params){
  return request(`/salesTask/querySendInfo?${stringify(params)}`)
}