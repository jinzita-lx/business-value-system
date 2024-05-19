import request from '@/utils/request'

// 查询榜单类型列表
export function listList_type(query) {
  return request({
    url: '/resource/list_type/list',
    method: 'get',
    params: query
  })
}

// 查询榜单类型详细
export function getList_type(id) {
  return request({
    url: '/resource/list_type/' + id,
    method: 'get'
  })
}

// 新增榜单类型
export function addList_type(data) {
  return request({
    url: '/resource/list_type',
    method: 'post',
    data: data
  })
}

// 修改榜单类型
export function updateList_type(data) {
  return request({
    url: '/resource/list_type',
    method: 'put',
    data: data
  })
}

// 删除榜单类型
export function delList_type(id) {
  return request({
    url: '/resource/list_type/' + id,
    method: 'delete'
  })
}

export function reloadListType() {
  return request({
    url: '/resource/list_type/reload',
    method: 'get',
  })
}
