import { createRouter, createWebHashHistory } from 'vue-router'
import About from '../views/About.vue'
import Index from '../views/index';
import Layout from '@/layout'

const routes = [
  {
    path: '/',
    component: Layout,
    children: [
      {
        path: '/',
        name: 'index',
        component: Index
      },
      {
        path: '/home',
        name: 'home',
        component: () => import('@/views/Home')
      }, {
        path: 'message-board',
        name: 'message-board',
        component: () => import('@/views/message-board')
      }, {
        path: 'space',
        name: 'space',
        component: () => import('@/views/user/space')
      }, {
        path: 'profile',
        name: 'profile',
        component: () => import('@/views/user/profile')
      }
    ]
  },
  {
    path: '/about',
    name: 'About',
    component: About
  },
  {
    path: '/publicity',
    name: 'Publicity',
    component: () => import('../views/Publicity.vue')
  },
  {
    path: '/:catchAll(.*)',
    name: 'catch',
    component: Layout,
    children: [
      {
        path: '/:catchAll(.*)',
        name: '404',
        component: () => import('@/views/NotFound')
      },
    ]
  }
]

// TODO 增加路由跳转钩子来改变Title

const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL), routes
})
export default router
