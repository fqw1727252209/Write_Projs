import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('@/views/Register.vue')
  },
  {
    path: '/home',
    name: 'Home',
    component: () => import('@/views/Home.vue'),
    redirect: '/dashboard',
    children: [
      {
        path: '/dashboard',
        name: 'Dashboard',
        component: () => import('@/views/Dashboard.vue')
      },
      {
        path: '/plan',
        name: 'Plan',
        component: () => import('@/views/Plan.vue')
      },
      {
        path: '/task',
        name: 'Task',
        component: () => import('@/views/Task.vue')
      },
      {
        path: '/course',
        name: 'Course',
        component: () => import('@/views/Course.vue')
      },
      {
        path: '/note',
        name: 'Note',
        component: () => import('@/views/Note.vue')
      },
      {
        path: '/resource',
        name: 'Resource',
        component: () => import('@/views/Resource.vue')
      },
      {
        path: '/statistics',
        name: 'Statistics',
        component: () => import('@/views/Statistics.vue')
      },
      {
        path: '/profile',
        name: 'Profile',
        component: () => import('@/views/Profile.vue')
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// 路由守卫
router.beforeEach((to, from, next) => {
  const userInfo = localStorage.getItem('userInfo')
  if (to.path !== '/login' && to.path !== '/register' && !userInfo) {
    next('/login')
  } else {
    next()
  }
})

export default router

