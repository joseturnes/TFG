<template>
  <div style="color: white;" class="container mt-5">
    <h2 class="mb-4">Curiosidades</h2>
    <div v-if="isLoggedIn">
      <!-- Formulario para crear una nueva publicación -->
      <form @submit.prevent="crearPublicacion">
        <div class="mb-3">
          <label for="titulo" class="form-label">Título : </label>
          <input v-model="titulo" id="titulo" class="form-control" required />
        </div>
        <div class="mb-3">
          <label for="contenido" class="form-label">Contido : </label>
          <textarea v-model="contenido" id="contenido" class="form-control" required></textarea>
        </div>
        <div class="mb-3">
          <label for="imagen1" class="form-label">Imaxes relacionadas : </label>
          <input type="file" @change="onFileChange" multiple class="form-control" />
        </div>
        <button type="submit" class="btn btn-outline-light">Crear Publicación</button>
      </form>
      <div v-if="postCreated" style="padding-top: 10px" class="success-message">
        Post creado correctamente!
      </div>
    </div>
    <div v-else>
      <!-- Lista de publicaciones -->
      <ul class="list-group">
        <li v-for="publicacion in publicaciones" :key="publicacion.id" class="list-group-item">
          <h3 class="mb-3">{{ publicacion.titulo }}</h3>
          <p>{{ publicacion.contenido }}</p>
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';

const isLoggedIn = ref(localStorage.getItem('userToken') != null);

const postCreated = ref(false);

const titulo = ref('');
const contenido = ref('');

const post = ref({
  name: '',
  description: '',
  type: 'CURIOSIDADES',
  relatedImages: [],
});

const onFileChange = (event) => {
  const files = event.target.files;
  post.value.relatedImages = Array.from(files);
};

const crearPublicacion = async () => {
  try {
    const imageDataPromises = post.value.relatedImages.map((file) => {
      return new Promise((resolve) => {
        const reader = new FileReader();
        reader.onload = () => resolve(reader.result);
        reader.readAsDataURL(file);
      });
    });

    const imageDataList = await Promise.all(imageDataPromises);
    const sanitizedImageDataList = imageDataList.map((dataURL) => {
      return dataURL.replace(/^data:image\/(png|jpeg|jpg);base64,/, ''); // Elimina la cabecera
    });
    post.value.name = titulo.value;
    post.value.description = contenido.value;
    post.value.relatedImages = sanitizedImageDataList;

    const response = await axios.post('http://localhost:8080/stellarium/posts', post.value);
    postCreated.value = true;
    setTimeout(() => {
      postCreated.value = false;
    }, 3000);
    titulo.value = '';
    contenido.value = '';

  } catch (error) {
    console.error('Error al crear el post:', error);
  }
};
</script>

<style>
.curiosidades {
  color: white;
  max-width: 600px;
  margin: 0 auto;
}

.curiosidades h2 {
  margin-bottom: 20px;
}

.curiosidades form,
.curiosidades ul {
  padding: 0;
  margin: 0;
  list-style: none;
}

.curiosidades form div,
.curiosidades ul li {
  margin-bottom: 10px;
}

.curiosidades input,
.curiosidades textarea {
  width: 100%;
  padding: 5px;
}

.success-message {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100px;
  background-color: #1c5944;
  color: white;
  font-size: 20px;
  font-weight: bold;
  border-radius: 5px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
  margin-top: 10px;
}
</style>

