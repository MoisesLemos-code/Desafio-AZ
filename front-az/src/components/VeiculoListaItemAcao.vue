<template>
  <div>
    <h2 v-if="tipo === `editar`">Editar veiculo</h2>
    <h2 v-if="tipo === `cadastrar`">Cadastrar veiculo</h2>
    <div class="form-group">
      <v-dialog v-model="dialog" max-width="400">
        <v-card>
          <v-card-title>Atenção!</v-card-title>
          <v-card-text>{{ message }}</v-card-text>
          <v-card-actions>
            <v-btn @click="dialog = false">Cancelar</v-btn>
            <v-btn @click="acaoVeiculo" :color="color">{{acaoDialog}}</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
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
      <v-btn color="success" @click="vendaAcao">{{switchValue ? `vendido!` : `vender` }}</v-btn>
    </div>
    <v-btn @click="salvarVeiculo" color="primary">Salvar</v-btn>
    <v-btn v-if="tipo !== 'cadastrar'" id="btn-excluir" @click="excluirAcao" color="error">Excluir</v-btn>
    <v-btn @click="cancelarVeiculo">Cancelar</v-btn>
  </div>
</template>

<script>
import { eventBus } from "./../main";

export default {
  props: {
    veiculo: {
      type: Object,
      required: true
    },
    tipo: String
  },
  data() {
    return {
      dialog: false,
      message: "",
      color: "",
      acaoDialog: "",
      switchValue: this.veiculo.vendido,
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
  methods: {
    salvarVeiculo() {
      if (this.tipo === "editar") {
        eventBus.atualizarVeiculo(this.veiculoLocal);
      } else if (this.tipo === "cadastrar") {
        console.log(this.veiculoLocal);
        eventBus.cadastrarVeiculo(this.veiculoLocal);
      }
    },
    vendaAcao() {
      this.dialog = true;
      if (this.switchValue) {
        this.message = "Deseja alterar o status para à venda?";
      } else {
        this.message = "Deseja vender este veículo?";
      }
      this.color = "success";
      this.acaoDialog = "Confirmar";
    },
    excluirAcao() {
      this.dialog = true;
      this.message = "Deseja excluir este veículo?";
      this.color = "error";
      this.acaoDialog = "Excluir";
    },
    acaoVeiculo() {
      this.dialog = false;
      if (this.acaoDialog === "Excluir") {
        eventBus.excluirVeiculo(this.veiculoLocal);
        eventBus.cancelarAcao();
      } else if (this.acaoDialog === "Confirmar") {
        this.switchValue = !this.switchValue;
        this.veiculoSelecionado = {
          propriedade: "vendido",
          valor: this.switchValue
        };
      }
    },
    cancelarVeiculo() {
      eventBus.cancelarAcao();
    }
  }
};
</script>

<style>
#btn-excluir {
  margin-left: 5;
}
</style>