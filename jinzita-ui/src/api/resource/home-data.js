import request from '@/utils/request'

export function GetHomeCount() {
  return request({
    url: 'home/count',
    method: 'GET',
  })
}
