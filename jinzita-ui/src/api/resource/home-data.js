import request from '@/utils/request'

export function GetHomeCount() {
  return request({
    url: 'home/count',
    method: 'GET',
  })
}

export function GetLineChart() {
  return request({
    url: 'home/line_chart',
    method: 'get',
  })
}
