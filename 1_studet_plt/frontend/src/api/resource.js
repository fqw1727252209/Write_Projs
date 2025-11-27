import request from '@/utils/request'

export function uploadResource(formData) {
  return request({
    url: '/resource/upload',
    method: 'post',
    data: formData,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
}

export function getResourceList(params) {
  return request({
    url: '/resource/list',
    method: 'get',
    params
  })
}

export function updateResource(data) {
  return request({
    url: '/resource/update',
    method: 'put',
    data
  })
}

export function deleteResource(id) {
  return request({
    url: `/resource/${id}`,
    method: 'delete'
  })
}

export function downloadResource(id) {
  return request({
    url: `/resource/download/${id}`,
    method: 'post'
  })
}

