import request from '@/utils/request'

// 查询主页信息列表
export function listHome(query) {
  return request({
    url: '/resource/home/list',
    method: 'get',
    params: query
  })
}

// 查询主页信息详细
export function getHome(id) {
  return request({
    url: '/resource/home/' + id,
    method: 'get'
  })
}

// 新增主页信息
export function addHome(data) {
  return request({
    url: '/resource/home',
    method: 'post',
    data: data
  })
}

// 修改主页信息
export function updateHome(data) {
  return request({
    url: '/resource/home',
    method: 'put',
    data: data
  })
}

// 删除主页信息
export function delHome(id) {
  return request({
    url: '/resource/home/' + id,
    method: 'delete'
  })
}
