<template>
  <div class="stellarium">
    <iframe src="http://localhost:8081/" width="100%" height="100%"></iframe>
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
  height: 95vh;
}
</style>