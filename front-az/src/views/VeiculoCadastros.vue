<template>
  <v-container fluid>
    <v-row class="v-row">
      <!-- coluna 1 -->
      <v-col cols="12" sm="6" md="8">
        <h2>Cadastros de Veículos</h2>
        <hr />
        <div id="col-1">
          <VeiculosListaItemInfo v-if="!editar" @editarVeiculo="editarVeiculo" />
          <VeiculosListaItemAcao v-if="editar" :veiculo="veiculoSelecionado" :tipo="tipo" />
          <v-btn v-if="adicionar" @click="adicionarVeiculo" color="success">Adicionar</v-btn>
        </div>
      </v-col>

      <!-- coluna 2 -->
      <v-col cols="12" md="4">
        <ul id="lista">
          <VeiculosListaItem
            v-for="veiculo in veiculos"
            :key="veiculo.codigo"
            :veiculo="veiculo"
            :selecionarAtivo="editar"
          />
        </ul>
        <v-pagination
          v-model="pageConfig.page"
          :length="pageConfig.length"
          :next-icon="pageConfig.nextIcon"
          :prev-icon="pageConfig.prevIcon"
          :page="pageNumber"
        ></v-pagination>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { eventBus } from "./../main";
import Metodos from "../services/metodos";
import VeiculosListaItem from "../components/VeiculoListaItem";
import VeiculosListaItemInfo from "../components/VeiculoListaItemInfo";
import VeiculosListaItemAcao from "../components/VeiculoListaItemAcao";

export default {
  components: {
    VeiculosListaItem,
    VeiculosListaItemInfo,
    VeiculosListaItemAcao
  },
  data() {
    return {
      veiculo: {
        codigo: 0,
        marca: "",
        modelo: "",
        valor: "",
        vendido: false
      },
      veiculos: [],
      veiculoSelecionado: undefined,
      editar: false,
      adicionar: true,
      criar: false,
      tipo: "",
      pageConfig: {
        length: 10,
        nextIcon: "mdi-chevron-right",
        nextIcons: ["mdi-chevron-right", "mdi-arrow-right", "mdi-menu-right"],
        prevIcon: "mdi-chevron-left",
        prevIcons: ["mdi-chevron-left", "mdi-arrow-left", "mdi-menu-left"],
        page: 1,
        totalVisible: 5
      }
    };
  },
  computed: {
    pageNumber: function() {
      this.listarTodos();
      return this.pageConfig.page;
    }
  },
  methods: {
    async listarTodos() {
      try {
        let page = this.pageConfig.page;
        await Metodos.buscaPersonalizada(4, --page, "DESC").then(resposta => {
          this.veiculos = resposta.data.content;
          this.pageConfig.length = resposta.data.totalPages;
        });
      } catch (err) {
        console.log(err);
      }
    },
    adicionarVeiculo() {
      this.tipo = "cadastrar";
      this.adicionar = false;
      this.editar = true;
      this.veiculoSelecionado = {
        marca: "",
        modelo: "",
        valor: "",
        vendido: false
      };
    },
    editarVeiculo(veiculo) {
      this.editar = true;
      this.veiculoSelecionado = veiculo;
      this.tipo = "editar";
    },
    atualizarVeiculo(veiculoAtualizado) {
      const indice = this.veiculos.findIndex(
        veiculo => veiculo.codigo === veiculoAtualizado.codigo
      );
      this.veiculos.splice(indice, 1, veiculoAtualizado);
      this.veiculoSelecionado = undefined;
      this.editar = false;
      this.adicionar = true;
    }
  },
  created() {
    eventBus.$on("selecionarVeiculo", veiculoSelecionado => {
      if (this.veiculoSelecionado != undefined) {
        if (this.veiculoSelecionado.codigo != veiculoSelecionado.codigo) {
          this.editar = false;
        }
      }
      this.veiculoSelecionado = veiculoSelecionado;
      this.adicionar = false;
    });
    eventBus.$on("atualizarVeiculo", this.atualizarVeiculo);
    eventBus.$on("cancelarAcao", () => {
      this.editar = false;
      this.adicionar = true;
    });
    eventBus.$on("atualizarLista", () => {
      this.listarTodos();
    });
  }
};
</script>

<style scoped>
#lista {
  display: flex;
  -ms-flex-direction: column;
  flex-direction: column;
  padding-left: 0;
  margin-bottom: 0;
}
#col-1 {
  margin-top: 20px;
}
</style>

