<script setup>
import Menu from '@/components/Menu.vue';
import { handleClick as handleMenuButtonClick } from "@/components/Menu.vue";

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
      <section class="contenido">
        Stellea
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus adipiscing commodo aliquet. Fusce bibendum orci magna, a pellentesque augue posuere sed. Ut bibendum magna tincidunt velit fermentum, eu laoreet arcu consectetur. Nullam nec enim sed justo fermentum sagittis. Nam varius dapibus risus, quis consectetur mauris. Praesent ut iaculis turpis. Phasellus congue tristique ligula et consequat.</p>
      </section>
    </div>
  </div>


</template>


<style scoped>
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
  border: 1px solid black;
  padding: 10px;
}

</style>
