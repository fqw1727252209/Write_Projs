import request from '@/utils/request'

export function createCourse(data) {
  return request({
    url: '/course/create',
    method: 'post',
    data
  })
}

export function getCourseList(params) {
  return request({
    url: '/course/list',
    method: 'get',
    params
  })
}

export function updateCourse(data) {
  return request({
    url: '/course/update',
    method: 'put',
    data
  })
}

export function deleteCourse(id) {
  return request({
    url: `/course/${id}`,
    method: 'delete'
  })
}

