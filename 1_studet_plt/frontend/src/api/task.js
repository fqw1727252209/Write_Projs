import request from '@/utils/request'

export function createTask(data) {
  return request({
    url: '/task/create',
    method: 'post',
    data
  })
}

export function getTaskList(params) {
  return request({
    url: '/task/list',
    method: 'get',
    params
  })
}

export function getTaskById(id) {
  return request({
    url: `/task/${id}`,
    method: 'get'
  })
}

export function updateTask(data) {
  return request({
    url: '/task/update',
    method: 'put',
    data
  })
}

export function deleteTask(id) {
  return request({
    url: `/task/${id}`,
    method: 'delete'
  })
}

