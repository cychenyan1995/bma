import request from '@/utils/request';
import { stringify } from 'qs';

export async function queryUser(params) {
  let response = request(`/api/demo/user?${stringify(params)}`);
  return response;
}

export async function removeUser(params) {
  return request('/api/demo/user', {
    method: 'POST',
    body: {
      keys: params,
      method: 'delete',
    },
  });
}

export async function addUser(params) {
  return request('/api/demo/user', {
    method: 'POST',
    body: {
      ...params,
      method: 'create',
    },
  });
}

export async function updateUser(params) {
  return request('/api/demo/user', {
    method: 'POST',
    body: {
      ...params,
      method: 'update',
    },
  });
}
