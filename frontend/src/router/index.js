import {createRouter, createWebHashHistory} from 'vue-router'
import About from '../views/About.vue'
import Layout from '@/layout'

const routes = [
  {
    path: '/',
    component: Layout,
    redirect: '/home',
    children: [
      {
        path: '/home',
        component: () => import('@/views/Home')
      },
      {
        path: 'message-board',
        component: () => import('@/views/message-board')
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

const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL),
  routes
})
export default router
