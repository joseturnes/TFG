<template>
  <div class="container mt-5">
    <div class="user-profile">
      <h2>User Profile</h2>
      <div v-if="user">
        <div class="mb-3">
          <strong>Nome de usuario : </strong> {{ user.login }}
        </div>
        <div class="mb-3">
          <strong>Nome : </strong> {{ user.firstName }}
        </div>
        <div class="mb-3">
          <strong>Apelidos : </strong> {{ user.lastName }}
        </div>
        <div class="mb-3">
          <strong>Correo electrónico : </strong> {{ user.email }}
        </div>
        <button @click="editMode = true" class="btn btn-outline-light">Edit Profile</button>
      </div>
      <div v-else>
        <p>Loading user data... <font-awesome-icon icon="spinner" spin /></p>
      </div>
      <div style="padding-top: 10px" v-if="editMode">
        <h3>Edit Profile</h3>
        <form @submit.prevent="updateProfile">
          <div class="mb-3">
            <label for="firstName" class="form-label">First Name:</label>
            <input v-model="editUser.firstName" id="firstName" class="form-control" required />
          </div>
          <div class="mb-3">
            <label for="lastName" class="form-label">Last Name:</label>
            <input v-model="editUser.lastName" id="lastName" class="form-control" required />
          </div>
          <div class="mb-3">
            <label for="email" class="form-label">Email:</label>
            <input v-model="editUser.email" id="email" class="form-control" required />
          </div>
          <button type="submit" class="btn btn-dark">Save Changes</button>
          <button @click="cancelEdit" class="btn btn-secondary">Cancel</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

const userId = ref(null);
const serviceToken = ref(null);
const user = ref(null);
const editMode = ref(false);
const editUser = ref(null);

onMounted(() => {
  userId.value = localStorage.getItem('userId');
  serviceToken.value = localStorage.getItem('userToken');
  fetchUserProfile();
});

const fetchUserProfile = async () => {
  try {
    const response = await axios.get(`http://localhost:8080/stellarium/users/usuario-registrado?userId=${userId.value}&serviceToken=${serviceToken.value}`);
    user.value = response.data.user;
    editUser.value = { ...user.value };
    console.log(editUser.value)
  } catch (error) {
    console.error('Error fetching user profile:', error);
  }
};

const updateProfile = async () => {
  try {
    await axios.put(`http://localhost:8080/stellarium/users/${userId.value}`, {
      firstName: editUser.value.firstName,
      lastName: editUser.value.lastName,
      email: editUser.value.email,
    });
    user.value = { ...editUser };
    await fetchUserProfile()
    editMode.value = false;
  } catch (error) {
    console.error('Error updating user profile:', error);
  }
};

const cancelEdit = () => {
  editMode.value = false;
  // Restaurar los datos originales del usuario después de cancelar la edición
  editUser.value = { ...user.value };
};
</script>

<style>
.user-profile {
  color: white;
  max-width: 400px;
  margin: 10px auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.user-profile h2 {
  margin-bottom: 20px;
}

.user-profile form {
  display: flex;
  flex-direction: column;
}

.user-profile label {
  margin-bottom: 10px;
}

.user-profile input {
  padding: 5px;
  margin-bottom: 10px;
}

.user-profile button {
  padding: 10px;
  margin-top: 10px;
  cursor: pointer;
}
</style>