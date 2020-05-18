<template>
  <v-container fluid id="container">
    <v-col cols="12" sm="8" md="8">
      <v-row class="v-row">
        <h2 v-if="tipo === `editar`">Editar veiculo</h2>
        <h2 v-if="tipo === `cadastrar`">Cadastrar veiculo</h2>
      </v-row>
      <v-row>
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
        <v-text-field
          label="Marca"
          outlined
          dense
          placeholder="Digite a marca"
          :value="veiculoSelecionado.marca"
          @input="event => changeInput(`marca`,event)"
        />
        <v-text-field
          label="Modelo"
          outlined
          dense
          placeholder="Digite o modelo"
          :value="veiculoLocal.modelo"
          @input="event => changeInput(`modelo`,event)"
        />
        <v-text-field
          label="Valor"
          outlined
          dense
          type="number"
          placeholder="Digite o valor"
          :value="veiculoSelecionado.valor"
          @input="event => changeInput(`valor`,event)"
        />
      </v-row>
      <v-row id="linha-centro">
        <v-btn
          id="btn-vender"
          color="success"
          @click="vendaAcao"
        >{{switchValue ? `vendido!` : `vender` }}</v-btn>
      </v-row>
      <v-row>
        <v-btn @click="salvarVeiculo" color="primary">Salvar</v-btn>
        <v-btn
          v-if="tipo !== 'cadastrar'"
          id="btn-excluir"
          @click="excluirAcao"
          color="error"
        >Excluir</v-btn>
        <v-btn @click="cancelarVeiculo">Cancelar</v-btn>
      </v-row>
    </v-col>
  </v-container>
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
        return this.veiculoLocal;
      }
    }
  },
  watch: {
    veiculo(novoVeiculo) {
      this.veiculoLocal = Object.assign({}, novoVeiculo);
    }
  },
  methods: {
    changeInput(prop, valor) {
      this.veiculoSelecionado = {
        propriedade: prop,
        valor: valor
      };
    },
    salvarVeiculo() {
      if (this.tipo === "editar") {
        console.log(this.veiculoLocal);
        eventBus.atualizarVeiculo(this.veiculoLocal);
      } else if (this.tipo === "cadastrar") {
        eventBus.cadastrarVeiculo(this.veiculoLocal);
        this.veiculoLocal = {};
        eventBus.cancelarAcao();
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
.v-btn {
  margin-right: 5px;
}
.v-text-field {
  width: 300px;
}
#container {
  border-radius: 4px;
  border: thin solid rgba(0, 0, 0, 0.12);
}
#linha-centro {
  margin-bottom: 20px;
}
</style>