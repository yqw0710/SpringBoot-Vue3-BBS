<template>
  <main class="app-main">
    <router-view class="app-container" />
  </main>
</template>

<script>
export default {
  name: 'AppMain',
}
</script>

<style lang="scss" scoped>
.app-main {
  position: relative;
  width: 100%;
  // why 70px: header 60px and .app-container margin-top:10px
  min-height: calc(100vh - 70px);
}

.app-container {
  padding: 0 15px;
  margin: 10px auto;
  width: 1000px;
  // box-shadow: $box-shadow-base;
}
</style>
