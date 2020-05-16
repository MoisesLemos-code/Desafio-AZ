<template>
  <div class="row">
    <!-- coluna 1 -->
    <div class="col-8">
      <h2>Cadastros de Veículos</h2>
      <VeiculosListaItemInfo v-if="!editar" @editarVeiculo="editarVeiculo" />
      <VeiculosListaItemEditar v-else :veiculo="veiculoSelecionado" />
    </div>

    <!-- coluna 2 -->
    <div class="col-4">
      <ul class="list-group list-group-flush">
        <VeiculosListaItem
          v-for="veiculo in veiculos"
          :key="veiculo.codigo"
          :veiculo="veiculo"
          :class="aplicarClasseAtiva(veiculo)"
        />
      </ul>
      <v-pagination
        v-model="pageConfig.page"
        :length="pageConfig.length"
        :next-icon="pageConfig.nextIcon"
        :prev-icon="pageConfig.prevIcon"
        :page="pageNumber"
      ></v-pagination>
    </div>
  </div>
</template>

<script>
import { eventBus } from "./../main";
import Metodos from "../services/metodos";
import VeiculosListaItem from "../components/VeiculoListaItem";
import VeiculosListaItemInfo from "../components/VeiculoListaItemInfo";
import VeiculosListaItemEditar from "../components/VeiculoListaItemAcao";

export default {
  components: {
    VeiculosListaItem,
    VeiculosListaItemInfo,
    VeiculosListaItemEditar
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
      pageConfig: {
        circle: false,
        disabled: false,
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
  mounted() {
    this.listarTodos();
  },
  computed: {
    pageNumber: function() {
      this.listarTodos();
      return this.pageConfig.page;
    }
  },
  methods: {
    listarTodos() {
      let page = this.pageConfig.page;
      Metodos.buscaPersonalizada(4, --page, "ASC").then(resposta => {
        console.log("teste: " + page);
        this.veiculos = resposta.data.content;
        this.pageConfig.length = resposta.data.totalPages;
      });
    },
    aplicarClasseAtiva(veiculoIterado) {
      return {
        active:
          this.veiculoSelecionado &&
          this.veiculoSelecionado.codigo === veiculoIterado.codigo
      };
    },
    editarVeiculo(veiculo) {
      this.editar = true;
      this.veiculoSelecionado = veiculo;
    },
    atualizarVeiculo(veiculoAtualizado) {
      const indice = this.veiculos.findIndex(
        veiculo => veiculo.id === veiculoAtualizado.id
      );
      this.veiculos.splice(indice, 1, veiculoAtualizado);
      this.veiculoSelecionado = undefined;
      this.editar = false;
    }
  },
  created() {
    eventBus.$on("selecionarVeiculo", veiculoSelecionado => {
      this.veiculoSelecionado = veiculoSelecionado;
    });
    eventBus.$on("atualizarVeiculo", this.atualizarVeiculo);
  }
};
</script>


