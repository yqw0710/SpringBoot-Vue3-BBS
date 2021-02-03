import { login, register } from '@/api/user'
import { getToken, removeToken, setToken } from '@/utils/auth'
import { ElMessage } from 'element-plus'

const SUCCESS_CODE = 200
const updateLocalstorage = (field, val) => {
  let temp = JSON.parse(localStorage.getItem('info'))
  temp[field] = val
  localStorage.setItem('info', JSON.stringify(temp))
}
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
    // 从localStorage载入存储的用户信息
    loadInfo(state) {
      if (!getToken()) return
      let temp = JSON.parse(localStorage.getItem('info'))
      for (let key in temp) {
        state[key] = temp[key]
      }
    },
    // 更新头像
    updateAvatar(state, data) {
      state.avatar = data
      updateLocalstorage('avatar', data)
    },
    // 用户登出 清除保存的数据
    logout(state) {
      removeToken()
      state.token = null
      localStorage.clear()
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
