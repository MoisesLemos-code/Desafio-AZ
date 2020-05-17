import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify';
import router from './router'
import Metodos from "./services/metodos";


Vue.config.productionTip = false

export const eventBus = new Vue({
  methods: {
    selecionarVeiculo(veiculoSelecionado) {
      this.$emit('selecionarVeiculo', veiculoSelecionado)
    },
    atualizarVeiculo(veiculoAtualizado) {
      this.$emit('atualizarVeiculo', veiculoAtualizado)
      Metodos.atualizar(veiculoAtualizado)
    },
    excluirVeiculo(veiculoSelecionado) {
      this.$emit('excluirVeiculo')
      Metodos.apagar(veiculoSelecionado)
    },
    cancelarAcao() {
      this.$emit("cancelarAcao");
    }
  }
})

new Vue({
  vuetify,
  router,
  render: h => h(App)
}).$mount('#app')
