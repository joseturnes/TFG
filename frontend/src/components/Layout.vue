<template>
  <div class="app-container">
    <Menu></Menu>
  </div>
</template>

<script>
import Menu from './Menu.vue';

export default {
  components: {
    Menu,
  },
};
</script>

<style scoped>

.app-container {
  display: flex;
  height: 100%;
}

.menu-container {
  flex-grow: 1;
  padding: 20px;
}

.collapsed {
  /* Estilos para el contenedor de contenido cuando el menú está colapsado */
}

.expanded {
  /* Estilos para el contenedor de contenido cuando el menú está expandido */
}




</style>