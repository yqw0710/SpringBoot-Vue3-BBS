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

export function isExist(type, value) {
  return http.get(`/user/exist?type=${type}&value=${value}`)
}
