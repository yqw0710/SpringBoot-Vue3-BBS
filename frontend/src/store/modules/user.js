import { login, register } from '@/api/user'
import { getToken, setToken } from '@/utils/auth'
import { ElMessage } from 'element-plus'

const SUCCESS_CODE = 200
const user = {
  namespaced: true,
  state: {
    token: getToken(),
    avatar: '',
    uid: '',
    nickname: '',
    username: '',
    point: '',
  },
  mutations: {
    // 将登录成功返回的用户信息(res.data)保存到state和cookie中
    saveInfo(state, data) {
      for (let key in data) {
        state[key] = data[key]
      }
      setToken(data.token)
      localStorage.setItem('info', JSON.stringify(state))
    },
    loadInfo(state) {
      let temp = JSON.parse(localStorage.getItem('info'))
      for (let key in temp) {
        state[key] = temp[key]
      }
    },
  },
  actions: {
    //用户登录
    login({ state, commit }, payload) {
      return login(payload).then((res) => {
        if (res.code === SUCCESS_CODE) {
          ElMessage({
            type: 'success',
            message: '登录成功，' + '欢迎回来:🎉' + res.data.nickname,
          })
          commit('saveInfo', res.data)
          return true
        } else {
          ElMessage({
            type: 'error',
            message: res.msg,
          })
          return false
        }
      })
    },
    //用户注册
    register({ commit }, payload) {
      return register(payload).then((res) => {
        if (res.code === SUCCESS_CODE) {
          ElMessage({
            type: 'success',
            message: '注册成功，正在跳转...',
          })
          commit('saveInfo', res.data)
          return true
        } else {
          ElMessage({
            type: 'error',
            message: res.msg,
          })
          return false
        }
      })
    },
  },
  getters: {},
}
export default user
