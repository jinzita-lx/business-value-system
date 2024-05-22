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

export function GetPieChart() {
  return request({
    url: 'home/pie_chart',
    method: 'get',
  })
}

export function GetRaddarChart() {
  return request({
    url: 'home/raddar_chart',
    method: 'get'
  })
}

export function GetBarChart() {
  return request({
    url: 'home/bar_chart',
    method: 'get'
  })
}

