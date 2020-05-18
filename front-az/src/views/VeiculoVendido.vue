<template>
  <v-container fluid>
    <v-col id="col-1" cols="12" sm="12" md="12">
      <v-row class="v-row" justify="center">
        <h2>Veiculos vendidos</h2>
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
      <v-row id="v-row" justify="center">
        <VeiculosListaItemInfo v-if="!editar" @editarVeiculo="editarVeiculo" />
      </v-row>
    </v-col>
  </v-container>
</template>
<script>
import Metodos from "../services/metodos";
import VeiculosListaItem from "../components/VeiculoListaItem";
import VeiculosListaItemInfo from "../components/VeiculoListaItemInfo";

export default {
  components: {
    VeiculosListaItem,
    VeiculosListaItemInfo
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
        await Metodos.listarVendidos(4, --page, "DESC").then(resposta => {
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