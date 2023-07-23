<template>
  <div style="color: black;" class="container mt-5">
    <h2 style="color:white;" class="mb-4">Curiosidades</h2>
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
        <div class="mb-3">
          <label for="imagen1" class="form-label">Imaxes relacionadas : </label>
          <div class="custom-file">
            <input type="file" @change="onFileChange" multiple class="custom-file-input" id="imagen1" />
            <label class="custom-file-label" for="imagen1">Escolla ficheiros</label>
          </div>
        </div>
        <button type="submit" class="btn btn-outline-light">Crear Publicación</button>
      </form>
      <div v-if="postCreated" style="padding-top: 10px" class="success-message">
        Post creado correctamente!
      </div>
      <div style="margin-top: 20px">
        <ul class="list-group">
          <li v-for="(publication, index) in publicacionesInvertidas" :key="publication.id" class="list-group-item publication-item">
            <div class="publication-header">
              <h3 class="mb-3">{{ publication.name }}</h3>
              <button v-if="isLoggedIn" @click="eliminarPublicacion(publication.id)" class="btn btn-outline-danger btn-sm mt-2 red-button">Eliminar</button>
            </div>
            <p v-if="!publication.showFullContent" >{{ getTrimmedContent(publication) }}</p>
            <a v-if="!publication.showFullContent && publication.description.length > 300" @click="showFullContent(publication)" class="show-more-btn link-black">Amosar mais</a>
            <p v-if="publication.showFullContent">{{ publication.description }}</p>
            <a v-if="publication.showFullContent" @click="showSmallContent(publication)" class="show-less-btn link-black">Amosar menos</a>
            <div v-if="publication.relatedImages.length > 0">
              <div :class="`image-container ${publication.relatedImages.length <= 3 ? 'image-container-flex' : ''}`">
                <div v-for="(image, imgIndex) in publication.relatedImages" :key="imgIndex" class="related-image">
                  <img :src="arrayToImage(image)" alt="Imagen asociada" @click="openModal(arrayToImage(image))" style="max-width: 200px; margin-right: 10px;" />
                </div>
              </div>
            </div>
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
          <div v-if="publication.relatedImages.length > 0">
            <div :class="`image-container ${publication.relatedImages.length <= 3 ? 'image-container-flex' : ''}`">
              <div v-for="(image, imgIndex) in publication.relatedImages" :key="imgIndex" class="related-image">
                <img :src="arrayToImage(image)" alt="Imagen asociada" @click="openModal(arrayToImage(image))" style="max-width: 200px; margin-right: 10px;" />
              </div>
            </div>
          </div>
        </li>
      </ul>
    </div>
    <div v-if="showModal" class="modal" @click="closeModal">
      <img :src="selectedImage" class="modal-content" />
    </div>
  </div>
</template>

<script setup>
import {onMounted, ref} from 'vue';
import axios from 'axios';

const isLoggedIn = ref(localStorage.getItem('userToken') != null);

const postCreated = ref(false);

const titulo = ref('');
const contenido = ref('');
const publicaciones = ref([]);
const publicacionesInvertidas = ref([]);

const post = ref({
  name: '',
  description: '',
  type: 'CURIOSIDADES',
  relatedImages: [],
});

const fetchPosts = async () => {
  try {
    const response = await axios.get('http://localhost:8080/stellarium/posts/search?type=CURIOSIDADES');
    publicaciones.value = response.data;
    console.log(publicaciones.value);
    publicacionesInvertidas.value = publicaciones.value.reverse();
    console.log(publicacionesInvertidas);

  } catch (error) {
    console.error('Error al obtener los posts:', error);
  }
};

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
      fetchPosts();
    }, 3000);
    titulo.value = '';
    contenido.value = '';

  } catch (error) {
    console.error('Error al crear el post:', error);
  }
};

const getTrimmedContent = (publication) => {
  if (publication.description.length <= 300) {
    return publication.description;
  } else {
    return publication.showFullContent ? publication.description : publication.description.slice(0, 300) + "...";
  }
};

// Función para mostrar el contenido completo cuando el usuario hace clic en el enlace
const showFullContent = (publication) => {
  publication.showFullContent = true;
};

const showSmallContent = (publication) => {
  publication.showFullContent = false;
};

const arrayToImage = (bytes) => {
  return `data:image/jpeg;base64,${bytes}`;
};

const showModal = ref(false);
const selectedImage = ref('');

const openModal = (image) => {
  selectedImage.value = image;
  showModal.value = true;
};

const closeModal = () => {
  selectedImage.value = '';
  showModal.value = false;
};

const eliminarPublicacion = async (id) => {
  try {
    const response = await axios.delete(`http://localhost:8080/stellarium/posts/${id}`);
    if (response.status === 200) {
      fetchPosts();
    } else {
      console.error('Error al eliminar la publicación:', response);
    }
  } catch (error) {
    console.error('Error al eliminar la publicación:', error);
  }
};

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

.publication-item {
  margin-bottom: 20px;
}

.show-more-btn,
.show-less-btn {
  cursor: pointer;
}

.show-more-btn:hover,
.show-less-btn:hover {
  /* Estilos para el feedback visual al pasar el cursor sobre los botones */
  background-color: rgba(0, 0, 0, 0.6);
  color: white;
  border-radius: 3px;
}
.link-black {
  color: black;
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
  display: flex; /* Usamos flexbox para ajustar las imágenes */
  flex-wrap: wrap; /* Para que las imágenes estén en la misma línea */
}

.image-container-flex {
  display: flex; /* Usamos flexbox para ajustar las imágenes */
  flex-wrap: wrap; /* Envuelve las imágenes a la siguiente línea si no hay suficiente espacio */
}

.related-image {
  margin-right: 10px;
  margin-bottom: 10px; /* Ajusta el margen entre las imágenes */
}

.publication-header {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
}

.red-button {
  margin-top: 0;
}

.custom-file-label::after {
  content: "Explorar"; /* Texto que se mostrará en el botón "Examinar" */
}

/* Estilo adicional para resaltar el botón "Examinar" al pasar el cursor */
.custom-file-label:hover {
  cursor: pointer;
  background-color: #e9ecef;
}

.list-group .list-group-item{
  background-color: #9c9c9c;
  border-radius: 10px;
}
</style>

