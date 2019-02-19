import request from '@/utils/request';
import { stringify } from 'qs';

export async function queryList(params) {
  return request(`/salesHistory/getListByPage?${stringify(params)}`);
}

export async function queryMonthlyDetailList(params) {
  return request(`/salesHistory/getListByPage?${stringify(params)}`);
}

export async function queryMonthSalesHistory(params) {
  return request(`/salesHistory/exportMonthSalesHistory?${stringify(params)}`);
}

export async function queryAreaList(){
  return request(`/salesHistory/getAreaList`);
}

export async function queryLeaderList(){
  return request(`/salesHistory/getLeaderList`);
}

export async function queryChartList(){
  return request(`/salesHistory/getSumSalesByMonth`);
}

export async function queryChartListByType(params){
  return request(`/salesHistory/getSumSalesHistoryByType?${stringify(params)}`);
}

export async function queryChartListBySp(params){
  return request(`/salesHistory/getChartListBySp?${stringify(params)}`);
}


