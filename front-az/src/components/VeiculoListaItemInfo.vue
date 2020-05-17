<template>
  <div>
    <h2>Veiculo selecionado</h2>

    <div class="card" v-if="veiculo">
      <div class="card-body">
        <h4 class="card-title">{{ veiculo.marca}} {{ veiculo.modelo }}</h4>
        <div id="card-info">
          <h5>R${{ veiculo.valor }}</h5>
          <p>{{ veiculo.vendido ? 'vendido! (não pode ser editado)' : 'à venda!' }}</p>
        </div>
        <v-btn @click="cancelarVeiculo">Cancelar</v-btn>
        <v-btn :disabled="veiculo.vendido" @click="editar" color="secondary">Editar</v-btn>
      </div>
    </div>
    <p v-else>Nenhum veiculo selecionado</p>
  </div>
</template>

<script>
import { eventBus } from "./../main";

export default {
  data() {
    return {
      veiculo: undefined
    };
  },
  watch: {
    veiculo(novoVeiculo) {
      this.veiculo = novoVeiculo;
    }
  },
  created() {
    eventBus.$on("selecionarVeiculo", veiculoSelecionado => {
      this.veiculo = veiculoSelecionado;
    });
    eventBus.$on("cancelarAcao", () => {
      this.veiculo = undefined;
    });
  },
  methods: {
    editar() {
      this.$emit("editarVeiculo", this.veiculo);
    },
    cancelarVeiculo() {
      eventBus.cancelarAcao();
    }
  }
};
</script>

<style scoped>
.v-btn {
  float: right;
}
</style>