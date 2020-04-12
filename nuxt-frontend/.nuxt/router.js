import Vue from 'vue'
import Router from 'vue-router'
import { interopDefault } from './utils'
import scrollBehavior from './router.scrollBehavior.js'

const _2377d2f3 = () => interopDefault(import('../pages/dashboard.vue' /* webpackChunkName: "pages/dashboard" */))
const _6fa6f80e = () => interopDefault(import('../pages/icons.vue' /* webpackChunkName: "pages/icons" */))
const _14bff588 = () => interopDefault(import('../pages/maps.vue' /* webpackChunkName: "pages/maps" */))
const _198af7c7 = () => interopDefault(import('../pages/notifications.vue' /* webpackChunkName: "pages/notifications" */))
const _d5762724 = () => interopDefault(import('../pages/table-list.vue' /* webpackChunkName: "pages/table-list" */))
const _2c40849e = () => interopDefault(import('../pages/index.vue' /* webpackChunkName: "pages/index" */))

// TODO: remove in Nuxt 3
const emptyFn = () => {}
const originalPush = Router.prototype.push
Router.prototype.push = function push (location, onComplete = emptyFn, onAbort) {
  return originalPush.call(this, location, onComplete, onAbort)
}

Vue.use(Router)

export const routerOptions = {
  mode: 'history',
  base: decodeURI('/'),
  linkActiveClass: 'nuxt-link-active',
  linkExactActiveClass: 'nuxt-link-exact-active',
  scrollBehavior,

  routes: [{
    path: "/dashboard",
    component: _2377d2f3,
    name: "dashboard"
  }, {
    path: "/icons",
    component: _6fa6f80e,
    name: "icons"
  }, {
    path: "/maps",
    component: _14bff588,
    name: "maps"
  }, {
    path: "/notifications",
    component: _198af7c7,
    name: "notifications"
  }, {
    path: "/table-list",
    component: _d5762724,
    name: "table-list"
  }, {
    path: "/",
    component: _2c40849e,
    name: "index"
  }],

  fallback: false
}

export function createRouter () {
  return new Router(routerOptions)
}
