<template>
  <div>
    <h2>Editar veiculo</h2>

    <div class="form-group">
      <label>marca:</label>
      <input
        type="text"
        class="form-control"
        placeholder="Digite a marca"
        :value="veiculoSelecionado.marca"
        @input="veiculoSelecionado = {propriedade: 'marca', valor: $event.target.value}"
      />
    </div>
    <div class="form-group">
      <label>Modelo:</label>
      <input
        type="text"
        class="form-control"
        placeholder="Digite o modelo"
        :value="veiculoSelecionado.modelo"
        @input="veiculoSelecionado = {propriedade: 'modelo', valor: $event.target.value}"
      />
    </div>
    <div class="form-group">
      <label>Valor:</label>
      <input
        type="number"
        class="form-control"
        placeholder="Digite o valor"
        :value="veiculoSelecionado.valor"
        @input="veiculoSelecionado = {propriedade: 'valor', valor: $event.target.value}"
      />
    </div>
    <v-btn @click="salvarVeiculo" color="primary">Salvar</v-btn>
  </div>
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
  data() {
    return {
      veiculoLocal: this.veiculo
    };
  },
  computed: {
    veiculoSelecionado: {
      set(dados) {
        this.veiculoLocal = Object.assign({}, this.veiculoLocal, {
          [dados.propriedade]: dados.valor
        });
      },
      get() {
        return this.veiculo;
      }
    }
  },
  watch: {
    veiculo(novoVeiculo) {
      this.veiculoLocal = Object.assign({}, novoVeiculo);
    }
  },
  methods: {
    salvarVeiculo() {
      eventBus.atualizarVeiculo(this.veiculoLocal);
    }
  }
};
</script>
