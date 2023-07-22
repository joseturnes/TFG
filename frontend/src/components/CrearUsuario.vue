<template>
  <div>
    <h3>Create New User</h3>
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
    <div v-if="userCreated" class="checkmark-wrapper">
      <div class="checkmark"></div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';

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
      closeModal();
    }, 5000);

    // Optionally, emit an event to notify the parent component (UserProfile.vue) about the new user creation
    // This allows you to update the user list, if necessary
    // $emit('userCreated', newUser);
  } catch (error) {
    console.error('Error creating user:', error);
  }
};

const closeModal = () => {
  this.$emit('closeModal');
};
</script>

<style>
.signup-form {
  max-width: 400px;
  margin: 10px auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
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

.checkmark-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100px;
}

.checkmark {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  background-color: #00cc00;
  display: inline-block;
  transform: rotate(45deg);
  box-shadow: 0 0 0 2px white;
}

.checkmark::after {
  content: '';
  width: 25px;
  height: 50px;
  background-color: #00cc00;
  display: inline-block;
  position: absolute;
  transform: rotate(-45deg);
}
</style>