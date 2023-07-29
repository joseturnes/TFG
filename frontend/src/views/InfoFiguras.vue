<template>
  <div style="color: black;" class="container mt-5">
    <h2 style="color:white;" class="mb-4">Información de Corpos Celestes</h2>
    <div class="mb-3 position-relative align-right">
      <input
          v-model="searchTerm"
          class="search-form"
          placeholder="Buscar por nome..."
          :class="{ 'is-invalid': searchTerm.length > 30 }"
      />
      <div class="invalid-feedback">O término de búsqueda non pode exceder os 30 carácteres.</div>
    </div>
    <div v-if="isLoggedIn">
      <!-- Formulario para crear una nueva publicación -->
      <form style="color: white" @submit.prevent="crearPublicacion">
        <div class="mb-3">
          <label for="titulo" class="form-label">Título : </label>
          <input v-model="titulo" id="titulo" class="form-control" required />
        </div>
        <div class="mb-3">
          <label for="contenido" class="form-label">Contido : </label>
          <textarea v-model="contenido" id="contenido" class="form-control" required></textarea>
        </div>
        <button type="submit" class="btn btn-outline-light">Crear Publicación</button>
      </form>
      <div v-if="postCreated" style="padding-top: 10px" class="success-message">
        Información engadida correctamente!
      </div>
      <div style="margin-top: 20px">
        <ul class="list-group">
          <li v-for="(publication, index) in publicacionesInvertidas" :key="publication.id" class="list-group-item publication-item">
            <div class="publication-header">
              <h3 class="mb-3">{{ publication.name }}</h3>
              <button v-if="isLoggedIn" @click="eliminarPublicacion(publication.name)" class="btn btn-outline-danger btn-sm mt-2 red-button">Eliminar</button>
            </div>
            <p v-if="!publication.showFullContent" >{{ getTrimmedContent(publication) }}</p>
            <a v-if="!publication.showFullContent && publication.description.length > 300" @click="showFullContent(publication)" class="show-more-btn link-black">Amosar mais</a>
            <p v-if="publication.showFullContent">{{ publication.description }}</p>
            <a v-if="publication.showFullContent" @click="showSmallContent(publication)" class="show-less-btn link-black">Amosar menos</a>
          </li>
        </ul>
      </div>
    </div>
    <div v-else>
      <!-- Lista de publicaciones -->
      <ul class="list-group">
        <li v-for="(publication, index) in publicacionesInvertidas" :key="publication.id" class="list-group-item publication-item">
          <h3 class="mb-3">{{ publication.name }}</h3>
          <p v-if="!publication.showFullContent" >{{ getTrimmedContent(publication) }}</p>
          <a v-if="!publication.showFullContent && publication.description.length > 300" @click="showFullContent(publication)" class="show-more-btn link-black">Amosar mais</a>
          <p v-if="publication.showFullContent">{{ publication.description }}</p>
          <a v-if="publication.showFullContent" @click="showSmallContent(publication)" class="show-less-btn link-black">Amosar menos</a>
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import {onMounted, ref, watch} from 'vue';
import axios from 'axios';

const isLoggedIn = ref(localStorage.getItem('userToken') != null);

const postCreated = ref(false);

const titulo = ref('');
const contenido = ref('');
const publicaciones = ref([]);
const publicacionesInvertidas = ref([]);
const allFigures = ref([]);

const post = ref({
  name: '',
  description: ''
});

const fetchPosts = async () => {
  try {
    const response = await axios.get('http://localhost:8080/stellarium/astros');
    publicaciones.value = response.data;
    publicacionesInvertidas.value = publicaciones.value.reverse();
    allFigures.value = publicacionesInvertidas.value;
    localStorage.setItem('allFigures', JSON.stringify(allFigures.value));

  } catch (error) {
    console.error('Error al obtener los posts:', error);
  }
};

const crearPublicacion = async () => {
  try {
    post.value.name = titulo.value;
    post.value.description = contenido.value;

    const response = await axios.post('http://localhost:8080/stellarium/astros/guardar', post.value);
    postCreated.value = true;
    setTimeout(() => {
      postCreated.value = false;
      fetchPosts();
    }, 1500);
    titulo.value = '';
    contenido.value = '';

  } catch (error) {
    console.error('Error al crear el post:', error);
  }
};

const getTrimmedContent = (publication) => {
  const content = publication.description;
  if (content.length <= 300) {
    return content;
  } else {
    return publication.showFullContent ? content : content.slice(0, 300) + "...";
  }
};

// Función para mostrar el contenido completo cuando el usuario hace clic en el enlace
const showFullContent = (publication) => {
  publication.showFullContent = true;
};

const showSmallContent = (publication) => {
  publication.showFullContent = false;
};

const eliminarPublicacion = async (name) => {
  try {
    const response = await axios.delete(`http://localhost:8080/stellarium/astros/${name}`);
    if (response.status === 200) {
      await fetchPosts();
    } else {
      console.error('Error al eliminar la publicación:', response);
    }
  } catch (error) {
    console.error('Error al eliminar la publicación:', error);
  }
};

const searchTerm = ref('');

const filteredPublications = ref([]);

// Computed property to filter the publications based on the search term
watch([publicacionesInvertidas, searchTerm], () => {
  const term = searchTerm.value.toLowerCase();
  if (term === '') {
    publicacionesInvertidas.value = JSON.parse(localStorage.getItem('allFigures'));
  } else {
    filteredPublications.value = publicacionesInvertidas.value.filter(
        (publication) => publication.name.toLowerCase().includes(term)
    );
    publicacionesInvertidas.value = filteredPublications.value;
  }
});

// Inicializar la bandera showFullContent en falso para todas las publicaciones
onMounted(() => {
  publicaciones.value.forEach((publication) => {
    publication.showFullContent = false;
  });
});

onMounted(() => {
  fetchPosts();
});
</script>

<style>
.search-form {
  max-width: 300px;
  /* Adjust the padding and margin as needed */
  padding: 5px 10px;
  margin-left: auto;
  border-radius: 10px;
}

/* Vertically align the search input with the "h2" heading */
.align-right {
  display: flex;
  align-items: center;
}
</style>
