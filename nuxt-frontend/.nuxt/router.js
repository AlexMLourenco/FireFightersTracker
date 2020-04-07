import Vue from 'vue'
import Router from 'vue-router'
import { interopDefault } from './utils'
import scrollBehavior from './router.scrollBehavior.js'

const _728898be = () => interopDefault(import('../pages/dashboard.vue' /* webpackChunkName: "pages/dashboard" */))
const _7485e744 = () => interopDefault(import('../pages/icons.vue' /* webpackChunkName: "pages/icons" */))
const _3b577cdd = () => interopDefault(import('../pages/maps.vue' /* webpackChunkName: "pages/maps" */))
const _43dde012 = () => interopDefault(import('../pages/notifications.vue' /* webpackChunkName: "pages/notifications" */))
const _284ce003 = () => interopDefault(import('../pages/table-list.vue' /* webpackChunkName: "pages/table-list" */))
const _d38dbe08 = () => interopDefault(import('../pages/index.vue' /* webpackChunkName: "pages/index" */))

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
    component: _728898be,
    name: "dashboard"
  }, {
    path: "/icons",
    component: _7485e744,
    name: "icons"
  }, {
    path: "/maps",
    component: _3b577cdd,
    name: "maps"
  }, {
    path: "/notifications",
    component: _43dde012,
    name: "notifications"
  }, {
    path: "/table-list",
    component: _284ce003,
    name: "table-list"
  }, {
    path: "/",
    component: _d38dbe08,
    name: "index"
  }],

  fallback: false
}

export function createRouter () {
  return new Router(routerOptions)
}
