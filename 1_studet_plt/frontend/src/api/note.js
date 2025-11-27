import request from '@/utils/request'

export function createNote(data) {
  return request({
    url: '/note/create',
    method: 'post',
    data
  })
}

export function getNoteList(params) {
  return request({
    url: '/note/list',
    method: 'get',
    params
  })
}

export function searchNotes(params) {
  return request({
    url: '/note/search',
    method: 'get',
    params
  })
}

export function updateNote(data) {
  return request({
    url: '/note/update',
    method: 'put',
    data
  })
}

export function deleteNote(id) {
  return request({
    url: `/note/${id}`,
    method: 'delete'
  })
}

