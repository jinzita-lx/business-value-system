import { douyinRequest } from '@/api/predict/util'

/**
 * 登录第三方平台
 * @param data
 * @returns {*}
 */
export function loginPredictSystem(data) {
  return douyinRequest({
      url: '/auth/login',
      method: 'POST',
      data,
    })
}

/**
 * 每日签到
 * @param cookie
 * @returns {AxiosPromise}
 */
export function everyDayLoginSystem(cookie) {
  return douyinRequest({
    url: '/auth/daily_check_in',
    method: 'GET',
    cookie,
  })
}
