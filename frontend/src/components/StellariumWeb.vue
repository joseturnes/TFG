<template>
  <div class="stellarium">
    <iframe src="https://stellariumtfgweb.onrender.com/" width="100%" height="100%"></iframe>
  </div>

</template>

<script>
export default {
  name: 'StellariumWeb'
}

</script>

<style scoped>
.stellarium{
  height: 100%;
}

iframe{
  border: none;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  height: 100%;
}
</style>