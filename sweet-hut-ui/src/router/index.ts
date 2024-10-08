import { createRouter, createWebHashHistory } from 'vue-router'

import Layout from '@/layout/index.vue'

// path和name尽量不要重名，即使在不同的模块中
export const constantRoutes: any[] = [
  {
    path: '/',
    redirect: '/home'
  },
  // 主页
  {
    path: '/home',
    component: () => import('@/views/home/index.vue'),
    hidden: true
  },
  {
    path: '/cookery-book',
    component: Layout,
    redirect: '/cookery-book/show',
    hidden: true,
    children: [
      {
        path: '/cookery-book/show',
        name: 'CookeryBookShow',
        component: () => import('@/views/cookery-book/show.vue'),
        meta: {
          title: 'CookeryBookShow'
        }
      },
      {
        path: '/cookery-book/list',
        name: 'CookeryBookList',
        component: () => import('@/views/cookery-book/index.vue'),
        meta: {
          title: 'CookeryBookList'
        }
      },
      {
        path: '/cookery-book/detail',
        name: 'CookeryBookDetail',
        component: () => import('@/views/cookery-book/detail.vue'),
        meta: {
          title: 'CookeryBookDetail'
        }
      }
    ]
  },
  {
    path: '/travel',
    component: Layout,
    redirect: '/travel/list',
    hidden: true,
    children: [
      {
        path: '/travel/list',
        name: 'TravelList',
        component: () => import('@/views/travel/index.vue'),
        meta: {
          title: 'TravelList'
        }
      },
      {
        path: '/travel/plan',
        name: 'TravelPlan',
        component: () => import('@/views/travel/plan.vue'),
        meta: {
          title: 'TravelPlan'
        }
      }
    ]
  },
  {
    path: '/property',
    component: Layout,
    redirect: '/property/list',
    hidden: true,
    children: [
      {
        path: '/property/list',
        name: 'PropertyList',
        component: () => import('@/views/property/index.vue'),
        meta: {
          title: 'PropertyList'
        }
      },
      {
        path: '/property/plan',
        name: 'PropertyPlan',
        component: () => import('@/views/property/plan.vue'),
        meta: {
          title: 'PropertyPlan'
        }
      }
    ]
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  scrollBehavior: () => ({ top: 0 }),
  routes: constantRoutes
})

export default router
