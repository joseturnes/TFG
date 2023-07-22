<template>
  <div class="container">
    <h1 class="title">Galería de Imaxes</h1>
    <div v-if="showModal" class="modal" @click="closeModal">
      <img :src="selectedImage" class="modal-content" />
    </div>
    <div class="image-container">
      <div v-for="(imagen, index) in reversedImages" :key="imagen.id" class="image-item">
        <img :src="arrayToImage(imagen.datos)" @click="openModal(arrayToImage(imagen.datos))" alt="imagen" />
        <button @click="eliminarImagen(imagen.id)" class="btn btn-danger btn-sm mt-2">Eliminar</button>
      </div>
      <div v-if="errorModal" class="modal error-modal" @click="closeErrorModal">
        <div class="modal-content">
          <p class="error-message">{{ errorMessage }}</p>
        </div>
      </div>
    </div>
    <div class="uploadButton">
      <button @click="uploadImage" class="btn btn-outline-light btn-lg mt-5">Subir Imaxe</button>
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
      selectedImage: null,
      errorModal: false,
      errorMessage: '',
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
    },
    showErrorModal(message) {
      this.errorMessage = message;
      this.errorModal = true;
    },
    closeErrorModal() {
      this.errorMessage = '';
      this.errorModal = false;
    },
    async uploadImage() {
      try {
        const fileInput = document.createElement('input');
        fileInput.type = 'file';
        fileInput.accept = 'image/*';
        fileInput.addEventListener('change', async () => {
          try {
            const file = fileInput.files[0];
            const formData = new FormData();
            formData.append('archivo', file);
            await axios.post('http://localhost:8080/stellarium/imagenes/guardar', formData);
            // You may want to fetch the updated images after successful upload
            this.fetchImages();
          } catch (error) {
            // Mostrar la modal de error en caso de error
            this.showErrorModal('Error al subir la imagen');
            console.error(error);
          }
        });
        fileInput.click();
      } catch (error) {
        this.showErrorModal('Error al subir la imagen');
        console.error(error);
      }
    },
    async eliminarImagen(id) {
      try {
        await axios.delete(`http://localhost:8080/stellarium/imagenes/${id}`);
        // Actualizar la lista de imágenes después de eliminar
        this.fetchImages();
      } catch (error) {
        console.error(error);
      }
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
  margin: 40px;
  font-family: Verdana, Helvetica, sans-serif;
  color: white;
}

.uploadButton {
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

.error-modal {
  display: flex;
  position: fixed;
  z-index: 1;
  padding-top: 100px;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgba(0, 0, 0, 0.6);
}

.error-modal .modal-content {
  margin: auto;
  display: block;
  background-color: rgba(0,0,0,0.36);
  padding: 20px;
  border-radius: 4px;
  max-width: 50%;
}

.error-message {
  color: red;
  font-size: 20px;
}

.image-item img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  cursor: pointer;
}
</style>