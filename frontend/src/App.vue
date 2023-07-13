<script setup>
import Menu from '@/components/Menu.vue';
import { handleClick as handleMenuButtonClick } from "@/components/Menu.vue";
import StellariumWeb from './components/StellariumWeb.vue';

import {ref, computed} from "vue";

const collapsed = ref(false);

const handleToggleMenu = () => {
  collapsed.value = !collapsed.value;
  console.log("click", collapsed.value)
};

const contentWidth = computed(() => {
  return collapsed.value ? 'calc(100% - 80px)' : 'calc(100% - 350px)';
});

const menuWidth = computed(() => {
  return collapsed.value ? '80px' : '350px';
});



</script>


<template>
  <div class="app-container">
    <div class="menu-container" :style="{ width: menuWidth }">
        <Menu class="menu" @menuCollapsed="handleToggleMenu"/>
    </div>
    <div class="content-container" :style="{ width: contentWidth }">
        <StellariumWeb></StellariumWeb>
    </div>
  </div>


</template>


<style scoped>

#app{
  margin: 0;
  padding: 0;
}

.app-container {
  display: flex;
}

.menu-container {
  width: auto; /* Ajusta el ancho del menú según tus necesidades */
  transition: width 0.3s;
}

.content-container {
  flex-grow: 1;

}

.contenido {
  padding: 5px;
  border: none;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
}

</style>
