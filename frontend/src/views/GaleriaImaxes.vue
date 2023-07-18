<template>
  <div class="container">
    <h1 class="title">Galería de Imaxes</h1>
    <div class="image-container">
      <div v-for="(imagen, index) in reversedImages" :key="imagen.id" class="image-item">
        <img :src="arrayToImage(imagen.datos)" alt="imagen" />
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
  margin-bottom: 20px;
  font-family: Verdana, Helvetica, sans-serif;
  color: white;
}

.image-container {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(500px, 1fr));
  gap: 1em;
  width: 100%;
}

.image-item img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
</style>
