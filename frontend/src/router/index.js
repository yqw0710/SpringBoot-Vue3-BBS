import { createRouter, createWebHashHistory } from 'vue-router'
import About from '../views/about'
import Home from '../views/home'
import Layout from '@/layout'

const routes = [
  {
    path: '/',
    component: Layout,
    children: [
      {
        path: '/',
        name: 'index',
        component: Home,
      },

      {
        path: '/message-board',
        name: 'message-board',
        component: () => import('@/views/message-board'),
      },
      {
        path: '/read',
        name: 'read',
        component: () => import('@/views/article-preview'),
      },
      {
        path: '/notice',
        name: 'notice',
        component: () => import('@/views/notice'),
      },
      {
        path: '/chat',
        name: 'chat',
        component: () => import('@/views/chat'),
      },
      {
        path: '/post/:pid',
        name: 'post',
        component: () => import('@/views/post'),
      },
      {
        path: '/article/:aid',
        name: 'article',
        component: () => import('@/views/article'),
      },
      {
        path: '/space/:uid',
        name: 'space',
        component: () => import('@/views/user/space'),
      },
      {
        path: '/profile',
        name: 'profile',
        component: () => import('@/views/user/profile'),
      },
    ],
  },
  {
    path: '/about',
    name: 'About',
    component: About,
  },
  {
    path: '/publicity',
    name: 'Publicity',
    component: () => import('../views/other/Publicity.vue'),
  },
  {
    path: '/:catchAll(.*)',
    name: 'catch',
    component: Layout,
    children: [
      {
        path: '/:catchAll(.*)',
        name: '404',
        component: () => import('@/views/error/NotFound'),
      },
    ],
  },
]

// TODO 增加路由跳转钩子来改变Title
const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL),
  routes,
})
export default router
