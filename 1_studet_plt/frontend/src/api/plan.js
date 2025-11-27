import request from '@/utils/request'

// 创建学习计划
export function createPlan(data) {
  return request({
    url: '/plan/create',
    method: 'post',
    data
  })
}

// 获取学习计划列表
export function getPlanList(params) {
  return request({
    url: '/plan/list',
    method: 'get',
    params
  })
}

// 获取学习计划详情
export function getPlanById(id) {
  return request({
    url: `/plan/${id}`,
    method: 'get'
  })
}

// 更新学习计划
export function updatePlan(data) {
  return request({
    url: '/plan/update',
    method: 'put',
    data
  })
}

// 删除学习计划
export function deletePlan(id) {
  return request({
    url: `/plan/${id}`,
    method: 'delete'
  })
}

