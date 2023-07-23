<template>
  <div class="container">
    <h1 class="title">Galería de Imaxes</h1>
    <div v-if="showModal" class="modal" @click="closeModal">
      <div class="modal-content-container">
        <img :src="arrayToImage(selectedImage.datos)" class="modal-content" />
        <div class="button-container">
          <button v-if="loggedIn" @click="eliminarImagen(selectedImage.id)" class="btn btn-danger btn-sm mt-2 red-button">Eliminar</button>
        </div>
      </div>
    </div>
    <div class="image-container">
      <div v-for="(imagen, index) in reversedImages" :key="imagen.id" class="image-item">
        <img :src="arrayToImage(imagen.datos)" @click="openModal(imagen)" alt="imagen" />
      </div>
      <div v-if="errorModal" class="modal error-modal" @click="closeErrorModal">
        <div class="modal-content">
          <p class="error-message">{{ errorMessage }}</p>
        </div>
      </div>
    </div>
    <div class="uploadButton" v-if="loggedIn">
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
      loggedIn: false,
    };
  },
  computed: {
    reversedImages() {
      console.log([...this.imagenes].reverse())
      return [...this.imagenes].reverse();
    },
  },
  created() {
    this.fetchImages();
    this.isLoggedIn();
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
    isLoggedIn(){
      this.loggedIn = localStorage.getItem('userToken') != null ;
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
    async eliminarImagen(selectedImageId) {
      if (!confirm('¿Estás seguro de que deseas eliminar esta imagen?')) {
        return;
      }
      console.log(selectedImageId);
      try {
        await axios.delete(`http://localhost:8080/stellarium/imagenes/${selectedImageId}`);
        // Actualizar la lista de imágenes después de eliminar
        this.fetchImages();
        this.showModal = false;
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

.modal-container {
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.6);
}

.modal {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.modal-content-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
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
  gap: 2.5em;
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

.button-container {
  display: flex;
  justify-content: center;
}


.red-button {
  background-color: #ff0000; /* Color de fondo rojo (#ff0000) */
  color: #ffffff; /* Color del texto blanco (#ffffff) */
}
</style>