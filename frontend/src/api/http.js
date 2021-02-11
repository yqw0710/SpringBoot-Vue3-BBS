import axios from 'axios'
import { getToken } from '@/utils/auth'
import { ElMessage } from 'element-plus'
// 根据环境来设置请求根路径
const BASEURL = 'http://127.0.0.1:8080/api'
// const BASEURL = 'http://47.100.95.40:8080/api'
// const BASEURL = process.env.NODE_ENV === "production" ? 'http://47.100.95.40:8080/api' : 'http://localhost:8080/api';

// 1. 请求根路径和超时时间
axios.defaults.timeout = 5000 //响应时间
axios.defaults.baseURL = BASEURL

// 2. 配置*请求*拦截器
axios.interceptors.request.use((request) => {
  let token = getToken()
  if (token) request.headers.token = token
  return request
})

// 3. 配置*响应*拦截器
axios.interceptors.response.use(
  (response) => {
    console.log('===in http.js===')
    console.log(response.data)
    console.log('===in http.js===')
    return response.data
  },
  (error) => {
    console.error(error)
    // let status=error.response.status TODO 根据状态码进行不同操作
    ElMessage({ message: error.response.data.msg })
    return Promise.reject(error.response)
  }
)

export default axios
