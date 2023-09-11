import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'main',
    component: () => import('@/views/MainView.vue')
  },
  { //TODO : trip id path param으로
    path: '/trips/:id',
    name: 'trip',
    component: () => import('@/views/TripView.vue')
  },
  {
    path: '/trips',
    name: 'trips',
    component: () => import('@/views/TripListView.vue')
  },
  {
    path: '/trip/write',
    name: 'tripwrite',
    component: () => import('@/views/TripArticleWriteView.vue')
  },
  {
    path: '/attractions',
    name: 'attractions',
    component: () => import('@/views/AttractionListView.vue')
  },

  {
    path: '/signin',
    name: 'signin',
    component: () => import('@/views/SignInView.vue')
  },
  {
    path: '/signup',
    name: 'signup',
    component: () => import('@/views/SignUpView.vue')
  },
  {
    path: '/community',
    name: 'community',
    component: () => import('@/views/CommunityListView.vue')
  },
  {
    path: '/mytriparticle',
    name: 'mytriparticle',
    component: () => import('@/views/MyTripArticles.vue')
  },
  {
    path: '/mytriprequest/:id',
    name: 'mytriprequest',
    component: () => import('@/views/MyTripRequests.vue')
  }
  // {
  //   path: '/tripapply',
  //   name: 'tripapply',
  //   component: () => import('@/views/TripApply.vue')
  // },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
