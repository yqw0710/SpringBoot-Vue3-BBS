import { createApp } from 'vue'
import App from './App'
import store from './store'
import router from './router'
import _ from 'lodash'
import ElementPlus from 'element-plus'
import locale from 'element-plus/lib/locale/lang/zh-cn'
import 'element-plus/lib/theme-chalk/index.css'

import drag from '@/directives/drag'
import defaultImg from '@/directives/default-img'
import http from '@/api/http'
import '@/styles/common.scss'

const app = createApp(App)
app.directive('drag', drag).directive('default-img', defaultImg)
app.config.globalProperties.$_ = _
app.config.globalProperties.$http = http
app.use(ElementPlus, { locale }).use(store).use(router).mount('#app')
