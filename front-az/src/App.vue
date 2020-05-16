<template>
  <v-app>
    <v-content>
      <router-view></router-view>
    </v-content>
  </v-app>
</template>

<style>
@keyframes fade {
  from {
    opacity: 0;
    transform: scale(0.5);
  }
  to {
    opacity: 1;
    transform: scale(1);
  }
}
@keyframes moveX {
  from {
    opacity: 0;
    transform: translateX(-35%);
  }
  to {
    opacity: 1;
    transform: translateX(0%);
  }
}
@keyframes moveXRight {
  from {
    opacity: 0;
    transform: translateX(95%);
  }
  to {
    opacity: 1;
    transform: translateX(0%);
  }
}

@keyframes moveY {
  from {
    opacity: 0;
    transform: translateY(-35%);
  }
  to {
    opacity: 1;
    transform: translateY(0%);
  }
}
@keyframes moveYBack {
  from {
    opacity: 0;
    transform: translateY(95%);
  }
  to {
    opacity: 1;
    transform: translateY(0%);
  }
}
</style>
