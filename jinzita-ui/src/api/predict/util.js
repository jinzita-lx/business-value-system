import request from '@/utils/request'

export function douyinRequest(option) {
  const url = 'https://pro.moreapi.wouldmissyou.com/api' + option.url;
  const headers = {
    isToken: false,
    'Authorization': 'Bearer ' + option.cookie,
  }
  delete option.url;
  delete option.cookie;
  return request({
    url,
    headers,
    ...option,
  })
}
