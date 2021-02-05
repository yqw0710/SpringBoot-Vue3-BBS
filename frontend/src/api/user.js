import http from './http'

export function login(data) {
  return http.post('/user/login', data)
}

export function register(data) {
  return http.post('/user/register', data)
}

export function getNewestInfo() {
  return http.get('/user/newest')
}

export function getUserInfo(uid) {
  return http.get('/user/info/' + uid)
}

export function getUserInfoDigest(uid) {
  return http.get('user/info/digest/' + uid)
}

export function isExist(type, value) {
  return http.get(`/user/exist?type=${type}&value=${value}`)
}

export function uploadAvatar(file) {
  let param = new FormData() // 创建form对象
  param.append('file', file) // 通过append向form对象添加数据
  let config = {
    headers: { 'Content-Type': 'multipart/form-data' },
  }
  return http.post('file/avatar', param, config)
}
// 获取用户的通知
export function getNotice() {
  return http.get('/notice')
}
// 读取通知
export function readNotice(id) {
  return http.put('/notice/' + id)
}
