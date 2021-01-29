<template>
  <main class="app-main">
    <router-view class="app-container"/>
  </main>
</template>

<script>
export default {
  name: "AppMain"
}
</script>

<style lang="scss" scoped>
.app-main {
  position: relative;
  width: 100%;
  min-height: calc(100vh - 60px);
  overflow: hidden;
}

.app-container {
  padding: 0 15px;
  margin: 10px auto;
  width: 1000px;
  box-shadow: $box-shadow-base;
}

</style>
