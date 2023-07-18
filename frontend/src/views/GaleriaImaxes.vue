<template>
  <div class="container">
    <h1 class="title">Galería de Imágenes</h1>
    <div v-if="showModal" class="modal" @click="closeModal">
      <img :src="selectedImage" class="modal-content" />
    </div>
    <div class="image-container">
      <div v-for="(imagen, index) in reversedImages" :key="imagen.id" class="image-item">
        <img :src="arrayToImage(imagen.datos)" @click="openModal(arrayToImage(imagen.datos))" alt="imagen" />
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      imagenes: [],
      showModal: false,
      selectedImage: null
    };
  },
  computed: {
    reversedImages() {
      return [...this.imagenes].reverse();
    },
  },
  created() {
    this.fetchImages();
  },
  methods: {
    async fetchImages() {
      try {
        const response = await axios.get('http://localhost:8080/stellarium/imagenes');
        this.imagenes = response.data;
      } catch (error) {
        console.error(error);
      }
    },
    arrayToImage(bytes) {
      return `data:image/jpeg;base64,${bytes}`;
    },
    openModal(src) {
      this.selectedImage = src;
      this.showModal = true;
    },
    closeModal() {
      this.showModal = false;
    }
  },
};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.title {
  margin: 40px;
  font-family: Verdana, Helvetica, sans-serif;
  color: white;
}

.modal {
  display: flex;
  position: fixed;
  z-index: 1;
  padding-top: 100px;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgba(0,0,0,0.6);
}

.modal-content {
  margin: auto;
  display: block;
  max-width: 80%;
  max-height: 80%;
}

.image-container {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(500px, 1fr));
  gap: 1.5em;
  width: 100%;
}

.image-item img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  cursor: pointer;
}
</style>
