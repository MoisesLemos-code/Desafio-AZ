import Vue from 'vue'
import VueRouter from 'vue-router'

import Menu from '../views/Menu';
import VeiculoCadastros from '../views/VeiculoCadastros';
import VeiculoVenda from '../views/VeiculoVenda';
import VeiculoVendido from '../views/VeiculoVendido'

Vue.use(VueRouter)

const routes = [
  { path: '/', component: Menu },
  { path: '/VeiculoCadastros', component: VeiculoCadastros },
  { path: '/VeiculoVenda', component: VeiculoVenda },
  { path: '/VeiculoVendido', component: VeiculoVendido },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
