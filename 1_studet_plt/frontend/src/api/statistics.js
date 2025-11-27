import request from '@/utils/request'

export function getUserStatistics(userId) {
  return request({
    url: `/statistics/user/${userId}`,
    method: 'get'
  })
}

