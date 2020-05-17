<template>
  <v-card max-width="344" outlined>
    <v-card-title>{{ veiculo.marca }} {{ veiculo.modelo }}</v-card-title>
    <v-card-text>{{ veiculo.vendido ? "vendido" : "à venda" }}</v-card-text>
    <v-card-actions>
      <v-btn :disabled="selecionarAtivo" @click="selecionar" color="primary">Selecionar</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import { eventBus } from "./../main";

export default {
  props: {
    veiculo: {
      type: Object,
      required: true
    },
    selecionarAtivo: Boolean
  },
  methods: {
    selecionar() {
      eventBus.selecionarVeiculo(this.veiculo);
    }
  }
};
</script>



