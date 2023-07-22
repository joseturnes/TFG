<template>
  <div class="login-container">
    <h2>Iniciar sesión</h2>
    <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
    <form @submit.prevent="loginUser">
      <div class="form-group">
        <label for="username">Nome de usuario: </label>
        <input type="text" id="username" v-model="username" required />
      </div>
      <div class="form-group">
        <label for="password">Contrasinal: </label>
        <input type="password" id="password" v-model="password" required />
      </div>
      <button type="submit">Iniciar sesión</button>
    </form>
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
        // Almacenar el token en el localStorage o Vuex para futuras solicitudes
        const token = response.data.serviceToken;
        const userName = response.data.user.login;
        console.log(response);
        localStorage.setItem('userToken', token);
        localStorage.setItem('userName', userName);
        menuItems.setProfileName(userName);
        this.$emit('profileNameChanged', userName);
        // Redirigir a la página deseada después del inicio de sesión exitoso

        this.$router.push('/galeria-imaxes'); // Cambia '/home' por la ruta que desees
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

button {
  background-color: #007bff;
  color: #fff;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
}
</style>