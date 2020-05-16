<template>
  <main>
    <v-container fluid>
      <v-layout text-center wrap>
        <v-row class="v-row" align="center" justify="center">
          <v-col cols="12" sm="8" md="4">
            <h2>Gerenciar Veículos</h2>
            <v-card class="elevation-12">
              <v-toolbar color="primary" dark flat>
                <v-toolbar-title class="v-toolbar-text">Menu principal</v-toolbar-title>
              </v-toolbar>
              <v-card-text>
                <v-btn
                  to="/VeiculoCadastros"
                  color="primary"
                  dark
                  v-on="on"
                  block
                  outlined
                >Cadastros</v-btn>
                <v-btn
                  to="/VeiculoCadastros"
                  color="primary"
                  dark
                  v-on="on"
                  block
                  outlined
                >Relatórios</v-btn>
              </v-card-text>
            </v-card>
          </v-col>
        </v-row>
      </v-layout>
    </v-container>
  </main>
</template>


<style scoped>
main {
  background-image: linear-gradient(
    to right bottom,
    #610083,
    #5f36a2,
    #5a56be,
    #5674d6,
    #5791eb
  );
  height: 100%;
}
.v-card {
  width: 500px;
  margin-top: 30px;
  animation-name: moveYBack;
  animation-duration: 600ms;
  overflow: hidden;
}
h2 {
  font-size: 40px;
  color: white;
  text-align: center;
  animation-name: fade;
  animation-duration: 500ms;
}
.v-toolbar-text {
  margin: 0 auto;
}
.v-row {
  padding-top: 50px;
}
.v-btn {
  margin-top: 20px;
}
.v-btn:nth-child(1) {
  animation: moveX 500ms;
  animation-delay: 200ms;
  animation-fill-mode: backwards;
}
.v-btn:nth-child(2) {
  animation: moveX 500ms;
  animation-delay: 300ms;
  animation-fill-mode: backwards;
}
.v-btn:nth-child(3) {
  animation: moveX 500ms;
  animation-delay: 390ms;
  animation-fill-mode: backwards;
}
</style>