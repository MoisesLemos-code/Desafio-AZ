<template>
  <v-container fluid>
    <h2>Veiculo selecionado</h2>

    <v-card v-if="veiculo" outlined>
      <v-card-title>{{ veiculo.marca}} {{ veiculo.modelo }}</v-card-title>
      <v-card-text>
        <h3>R${{ veiculo.valor }}</h3>
        <h4>{{ veiculo.vendido ? 'vendido! (não pode ser editado)' : 'à venda!' }}</h4>
      </v-card-text>
      <v-card-actions>
        <v-btn :disabled="veiculo.vendido" @click="editar" color="secondary">Editar</v-btn>
        <v-btn @click="cancelarVeiculo">Cancelar</v-btn>
      </v-card-actions>
    </v-card>
    <p v-else>Nenhum veiculo selecionado</p>
  </v-container>
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
.v-card-actions {
  float: right;
}
</style>