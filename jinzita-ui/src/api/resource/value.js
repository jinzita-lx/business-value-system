import request from '@/utils/request'

// 查询价值榜单列表
export function listValue(query) {
  return request({
    url: '/resource/value/list',
    method: 'get',
    params: query
  })
}

// 查询价值榜单详细
export function getValue(id) {
  return request({
    url: '/resource/value/' + id,
    method: 'get'
  })
}

// 新增价值榜单
export function addValue(data) {
  return request({
    url: '/resource/value',
    method: 'post',
    data: data
  })
}

// 修改价值榜单
export function updateValue(data) {
  return request({
    url: '/resource/value',
    method: 'put',
    data: data
  })
}

// 删除价值榜单
export function delValue(id) {
  return request({
    url: '/resource/value/' + id,
    method: 'delete'
  })
}
