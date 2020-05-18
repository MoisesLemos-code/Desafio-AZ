<template>
  <v-container fluid>
    <v-col id="col-1" cols="12" sm="12" md="12">
      <v-row class="v-row" justify="center">
        <h2>Veiculos à venda</h2>
      </v-row>
      <hr />
      <v-row class="v-row" justify="center">
        <ul id="lista">
          <VeiculosListaItem v-for="veiculo in veiculos" :key="veiculo.codigo" :veiculo="veiculo" />
        </ul>
        <v-pagination
          v-model="pageConfig.page"
          :length="pageConfig.length"
          :next-icon="pageConfig.nextIcon"
          :prev-icon="pageConfig.prevIcon"
          :page="pageNumber"
        ></v-pagination>
      </v-row>
      <v-row id="v-row">
        <v-col id="col-1" cols="12" sm="6" md="6">
          <VeiculosListaItemInfo @editarVeiculo="editarVeiculo" />
        </v-col>
        <v-col id="col-1" cols="12" sm="6" md="6">
          <VeiculosListaItemAcao v-if="editar" :veiculo="veiculoSelecionado" :tipo="tipo" />
        </v-col>
      </v-row>
    </v-col>
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
      veiculos: [],
      veiculoSelecionado: undefined,
      editar: false,
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
        await Metodos.listarVenda(4, --page, "DESC").then(resposta => {
          this.veiculos = resposta.data.content;
          this.pageConfig.length = resposta.data.totalPages;
        });
      } catch (err) {
        console.log(err);
      }
    },
    editarVeiculo(veiculo) {
      this.editar = true;
      this.veiculoSelecionado = veiculo;
      this.tipo = "editar";
    }
  },
  created() {
    eventBus.$on("atualizarVeiculo", () => {
      this.listarTodos();
      eventBus.cancelarAcao();
    });

    eventBus.$on("selecionarVeiculo", veiculoSelecionado => {
      this.veiculoSelecionado = veiculoSelecionado;
    });
    eventBus.$on("cancelarAcao", () => {
      this.veiculoSelecionado = undefined;
      this.editar = false;
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
  -ms-flex-direction: row;
  flex-direction: row;
  padding-left: 0;
  margin-bottom: 0;
}
#v-row {
  margin-top: 20px;
}
</style>