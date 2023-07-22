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
        <button @click="editMode = true" class="btn btn-outline-light" style="margin-right: 10px;">Edit Profile</button>
        <button @click="showSignUpModal" class="btn btn-outline-light">Crear novo usuario</button>
      </div>
      <div v-else>
        <p>Loading user data... <font-awesome-icon icon="spinner" spin /></p>
      </div>
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
    <div class="modal" tabindex="-1" role="dialog" :class="{ 'd-block': showModal }">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Create New User</h5>
            <button type="button" class="btn-close" @click="closeSignUpModal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <div>
              <h3>Crear novo usuario</h3>
              <form @submit.prevent="createUser">
                <div class="mb-3">
                  <label for="username" class="form-label">Nome de usuario : </label>
                  <input v-model="userData.login" id="username" class="form-control" required />
                </div>
                <div class="mb-3">
                  <label for="firstName" class="form-label">Nome : </label>
                  <input v-model="userData.firstName" id="firstName" class="form-control" required />
                </div>
                <div class="mb-3">
                  <label for="lastName" class="form-label">Apelidos : </label>
                  <input v-model="userData.lastName" id="lastName" class="form-control" required />
                </div>
                <div class="mb-3">
                  <label for="email" class="form-label">Correo electrónico : </label>
                  <input v-model="userData.email" id="email" class="form-control" required />
                </div>
                <div class="mb-3">
                  <label for="password" class="form-label">Contrasinal : </label>
                  <input type="password" v-model="userData.password" id="password" class="form-control" required />
                </div>
                <button type="submit" class="btn btn-primary">Crear usuario</button>
              </form>
            </div>
            <div v-if="userCreated" style="padding-top: 10px" class="success-message">
              Novo usuario creado correctamente!
            </div>
          </div>
        </div>
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
const showModal = ref(false);

// Function to show the modal
const showSignUpModal = () => {
  showModal.value = true;
};

// Function to close the modal
const closeSignUpModal = () => {
  showModal.value = false;
};

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

const userData = ref({
  login: '',
  firstName: '',
  lastName: '',
  email: '',
  password: '',
  role: 'ADMIN',
});

const userCreated = ref(false);

const createUser = async () => {
  try {
    await axios.post('http://localhost:8080/stellarium/users/signUp', userData.value);
    // Clear form fields after successful creation
    userData.value = {
      login: '',
      firstName: '',
      lastName: '',
      email: '',
      password: '',
    };
    userCreated.value = true;

    setTimeout(() => {
      userCreated.value = false;
      closeSignUpModal();
    }, 3000);

    // Optionally, emit an event to notify the parent component (UserProfile.vue) about the new user creation
    // This allows you to update the user list, if necessary
    // $emit('userCreated', newUser);
  } catch (error) {
    console.error('Error creating user:', error);
  }
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

.signup-form h3 {
  margin-bottom: 20px;
}

.signup-form form {
  display: flex;
  flex-direction: column;
}

.signup-form label {
  margin-bottom: 10px;
}

.signup-form input {
  padding: 5px;
  margin-bottom: 10px;
}

.signup-form button {
  padding: 10px;
  margin-top: 10px;
  cursor: pointer;
}

.success-message {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100px;
  background-color: #00cc00;
  color: white;
  font-size: 20px;
  font-weight: bold;
  border-radius: 5px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
  margin-top: 10px;
}
</style>