<script setup>
import Menu from '@/components/Menu.vue';
import { useRouter } from 'vue-router';

const router = useRouter();

import {ref, computed} from "vue";

const collapsed = ref(false);
localStorage.setItem('menuCollapsed', JSON.stringify(collapsed.value))

const handleToggleMenu = () => {
  collapsed.value = !collapsed.value;
  localStorage.setItem('menuCollapsed', JSON.stringify(collapsed.value))
};

const contentWidth = computed(() => {
  return collapsed.value ? 'calc(100% - 80px)' : 'calc(100% - 350px)';
});

const menuWidth = computed(() => {
  return collapsed.value ? '80px' : '350px';
});

const currentRoutePath = computed(() => router.currentRoute.value.path);

</script>

<template>
  <div class="app-container">
    <div class="menu-container" :style="{ width: menuWidth }">
        <Menu class="menu" :key="currentRoutePath" @menuCollapsed="handleToggleMenu"/>
    </div>
    <div class="content-container" :style="{ width: contentWidth }">
      <router-view />
    </div>
  </div>
</template>

<style scoped>

.app-container {
  display: flex;
  align-items: stretch;
  height: 100vh;
  font-family: "Ubuntu";
}

.menu-container {
  width: auto; /* Ajusta el ancho del menú según tus necesidades */
  transition: width 0.3s;
  height: 100vh;
}

.content-container {
  flex-grow: 1;
  height: 98vh;
  min-height: 0;
  margin: 1em;

}

router-view {
  min-height: 0; /* Lo mismo para router-view */
}

</style>
