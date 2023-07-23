
<template>
  <div :class="['menu',collapsed ? 'collapsed' : 'expanded']">
    <div class=" header" >
      <button class="menu-button" @click="menuButtonClick">
        <font-awesome-icon icon="fa-solid fa-bars" size="2x" />
      </button>
    </div>
    <div class="profile">
      <img :src="'../assets/' + menuItems.profile.img" alt="profile image">
      <div class="profile-name" v-if="!collapsed">
        <a href="#" @click.prevent="menuItems.profile.onClick(router)">
          {{menuItems.profile.name}} <font-awesome-icon icon="fa-solid fa-user" />
        </a>
        <a v-if="hasUserToken" href="#" @click.prevent="menuItems.logout.onClick(router)">
          {{menuItems.logout.name}} <font-awesome-icon :icon="['fas', 'arrow-right-from-bracket']" />
        </a>
      </div>
    </div>
    <div class="menu-items">
      <ul>
        <MenuItem v-for="menuItem in menuItems.items" :key="menuItem.id" :item="menuItem" :collapsed = "collapsed"/>
      </ul>
    </div>

  </div>
</template>

<script setup>
import {onMounted, ref, watch} from 'vue';
import menuItems from "@/components/MenuItems";
import MenuItem from "@/components/MenuItem.vue";
import { useRouter } from 'vue-router';
import { onBeforeUnmount } from 'vue';

const emit = defineEmits(['menuCollapsed'])

let collapsed = ref(JSON.parse(localStorage.getItem('menuCollapsed')) || false);

console.log(collapsed.value);

const menuButtonClick = () => {
  collapsed.value = JSON.parse(localStorage.getItem('menuCollapsed')) || false;
  collapsed.value = !collapsed.value;
  console.log("Menu collapsed", collapsed.value);
  emit("menuCollapsed");

};

const router = useRouter();

const clearLocalStorage = () => {
  localStorage.clear();
};

const hasUserToken = ref(false);

onMounted(() => {
  console.log('Página recargada');
  hasUserToken.value = localStorage.getItem('userToken') !== null;
});

// Registra el evento beforeunload para limpiar el localStorage al recargar
onBeforeUnmount(() => {
  window.removeEventListener('beforeunload', clearLocalStorage);
});

window.addEventListener('beforeunload', clearLocalStorage);

const checkUrlChange = () => {
  console.log('URL cambió:', router.currentRoute.value.fullPath);
};

watch(
    () => router.currentRoute.value.fullPath,
    (newVal, oldVal) => {
      if (newVal !== oldVal) {
        checkUrlChange();
      }
    }
);

</script>


<style scoped>
  .menu {
  color: white;
  height: 100vh;
  transition: width 0.3s;
  font-family: Verdana, Helvetica, sans-serif;
  position: fixed;
  left: 0;
  top: 0;
}

.profile-name a {
  display: inline-block;
  align-items: center;
  margin-right: 5px; /* Ajusta el espaciado entre los enlaces si es necesario */
  position: relative; /* Agrega esta propiedad para que los pseudo-elementos sean relativos a este enlace */
}

  /* Utiliza :first-child para excluir el separador del primer enlace */
.profile-name a:not(:first-child)::before {
  content: '|'; /* Texto o símbolo que se utilizará como separador */
  margin-right: 5px; /* Espaciado entre el separador y el texto del enlace */
  color: #ccc; /* Color del separador, puedes ajustarlo según tus necesidades */
}

.header {
  display: flex;
  justify-content: start;
  padding-left: 19px;
}

.menu-button {
  border: none;
  background-color: transparent;
  color: white;
  padding: 10px;
}

.collapsed {
  width: 80px;
}

.expanded {
  width: 350px;
}

.profile {
  padding: 10px;
  text-align: center;
}

.profile img {
  border-radius: 50%;
  min-width: 50px;
  max-width: 300px;
  width: 100%;
}

.profile-name {
  padding: 10px 0;
  font-size: 18px;
}

.menu-items ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

a{
  color: white;
  text-decoration: none;
  display: block;
}
a:hover{
  color: black;
}
</style>

