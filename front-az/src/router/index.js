import Vue from 'vue'
import VueRouter from 'vue-router'

import Menu from '../views/Menu';
import VeiculoCadastros from '../views/VeiculoCadastros';

Vue.use(VueRouter)

const routes = [
  { path: '/', component: Menu },
  { path: '/VeiculoCadastros', component: VeiculoCadastros },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
