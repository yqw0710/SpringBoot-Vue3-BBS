import {createStore} from 'vuex'
import user from './modules/user'
import settings from './modules/settings'
import getters from './getters'
export default createStore({
  modules: {
    user: user,
    settings: settings
  },
  getters,
  strict: process.env.NODE_ENV !== 'production'  // 生产环境下别开严格模式
})
