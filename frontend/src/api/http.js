import axios from "axios";
// 根据环境来设置请求根路径
const BASEURL = process.env.NODE_ENV === "production" ? 'http://47.100.95.40:8086/' : 'http://localhost:8086/';

// 1. 请求根路径和超时时间
axios.defaults.timeout = 5000;   //响应时间
axios.defaults.baseURL = BASEURL;

// 2. 配置*请求*拦截器
axios.interceptors.request.use(
  request => {
    return request;
  }
)

// 3. 配置*响应*拦截器
axios.interceptors.response.use(response => {
  console.log(response.data);
  return response.data
}, error => {
  console.error(error);
  return Promise.reject(error.response)
})


export default axios;