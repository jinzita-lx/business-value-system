import request from '@/utils/request'

// 查询价值指标列表
export function listIndicators(query) {
  return request({
    url: '/resource/indicators/list',
    method: 'get',
    params: query
  })
}

// 查询价值指标详细
export function getIndicators(id) {
  return request({
    url: '/resource/indicators/' + id,
    method: 'get'
  })
}

// 新增价值指标
export function addIndicators(data) {
  return request({
    url: '/resource/indicators',
    method: 'post',
    data: data
  })
}

// 修改价值指标
export function updateIndicators(data) {
  return request({
    url: '/resource/indicators',
    method: 'put',
    data: data
  })
}

// 删除价值指标
export function delIndicators(id) {
  return request({
    url: '/resource/indicators/' + id,
    method: 'delete'
  })
}
