<template>
  <div class="container mt-5">
    <div class="login-container">
      <h2 class="mb-3">Iniciar sesión</h2>
      <div v-if="errorMessage" class="error-message mb-3">{{ errorMessage }}</div>
      <form @submit.prevent="loginUser">
        <div class="mb-3">
          <label for="username" class="form-label">Nome de usuario:</label>
          <input type="text" id="username" v-model="username" class="form-control" required />
        </div>
        <div class="mb-3">
          <label for="password" class="form-label">Contrasinal:</label>
          <input type="password" id="password" v-model="password" class="form-control" required />
        </div>
        <button type="submit" class="btn btn-outline-light">Iniciar sesión</button>
      </form>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import menuItems  from "@/components/MenuItems";
import router from "@/router";

export default {
  data() {
    return {
      username: '',
      password: '',
      errorMessage: '',
    };
  },
  methods: {
    async loginUser() {
      try {
        const response = await axios.post('http://localhost:8080/stellarium/users/login', {
          userName: this.username,
          password: this.password,
        });
        const token = response.data.serviceToken;
        const userName = response.data.user.login;
        console.log(response.data.user);
        localStorage.setItem('userId',response.data.user.id )
        localStorage.setItem('userToken', token);
        localStorage.setItem('userName', userName);
        menuItems.setProfileName(userName);
        this.$emit('profileNameChanged', userName);
        await this.$router.push('/');
      } catch (error) {
        this.errorMessage = 'Credenciales incorrectas. Inténtalo de nuevo.';
        console.error(error);
      }
    },
  },
};
</script>

<style scoped>
h2{
  color: white;
}

.form-label{
  color: white;
}

.login-container {
  max-width: 400px;
  margin: auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.form-group {
  margin-bottom: 10px;
  color: white;
}

.error-message {
  color: red;
  margin-bottom: 10px;
}
</style>