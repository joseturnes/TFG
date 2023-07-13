<template>
  <div class="stellarium">
    <iframe src="http://192.168.0.128:8081/" width="100%" height="100%"></iframe>
  </div>

</template>

<script>
export default {
  // Configuración del componente
  name: 'StellariumWeb'
}

</script>

<style scoped>
.stellarium{
  height: 100vh;
}

iframe{
  border: none;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
}
</style>