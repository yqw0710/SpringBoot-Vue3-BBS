import http from './http'

export function getPost(num, size, category) {
  if (!category) return http.get(`/post/page?num=${num}&size=${size}`)
  else
    return http.get(`/post/page?category=${category}&num=${num}&size=${size}`)
}
export function getPostDetail(pid) {
  return http.get('/post/' + pid)
}
