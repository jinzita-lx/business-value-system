import { douyinRequest } from '@/api/predict/util'
import request from '@/utils/request'

/**
 * 获取用户信息
 * @param cookie
 * @param data
 * @returns {AxiosPromise}
 */
export function getUserData(cookie, data) {
  return douyinRequest({
    method: 'POST',
    url: '/douyin/user_data',
    cookie,
    data,
  })
}

/**
 * 获取用户视频
 * @param cookie
 * @param data
 * @returns {AxiosPromise}
 */
export function getUserVideo(cookie, data) {
  return douyinRequest({
    method: 'POST',
    url: '/douyin/user_post',
    cookie,
    data,
  })
}

export function predictUserData(data) {
  return request({
    method: 'post',
    url: window.location.protocol+ '//' + window.location.hostname + ':' + '8088/predict',
    data,
  })
}
