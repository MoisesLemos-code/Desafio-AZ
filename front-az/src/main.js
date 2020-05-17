import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify';
import router from './router'
import Metodos from "./services/metodos";


Vue.config.productionTip = false

export const eventBus = new Vue({
  methods: {
    async cadastrarVeiculo(veiculo) {
      await Metodos.salvar(veiculo)
      this.$emit('atualizarLista')
    },
    selecionarVeiculo(veiculoSelecionado) {
      this.$emit('selecionarVeiculo', veiculoSelecionado)
    },
    async atualizarVeiculo(veiculoAtualizado) {
      await Metodos.atualizar(veiculoAtualizado)
      this.$emit('atualizarVeiculo', veiculoAtualizado)
    },
    async excluirVeiculo(veiculoSelecionado) {
      await Metodos.apagar(veiculoSelecionado)
      this.$emit('atualizarLista')
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
