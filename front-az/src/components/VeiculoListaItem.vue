<template>
  <v-card max-width="344" outlined>
    <v-card-title>{{ veiculo.marca }} {{ veiculo.modelo }}</v-card-title>
    <v-card-actions>
      <v-btn @click="selecionar" color="primary">Selecionar</v-btn>
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
    }
  },
  methods: {
    selecionar() {
      eventBus.selecionarVeiculo(this.veiculo);
    }
  }
};
</script>

<style scoped>
.v-card {
  float: right;
  margin-bottom: 15;
}
</style>

