<template>
  <div ref="rightPanel" class="show rightPanel">
    <button v-drag="'vertical'" class="rightPanel-button" @click="show = !show">
      <i :class="show ? 'el-icon-close' : 'el-icon-setting'" />
    </button>
    <div class="rightPanel-content">
      <slot />
    </div>
  </div>
</template>

<script>
import { onMounted, ref, watch } from 'vue'
// 或许该加个onUnmounted来移除这个dom
export default {
  name: 'RightPanel',
  setup() {
    const show = ref(false)
    const rightPanel = ref(null)
    watch(show, (value) => {
      if (value) {
        rightPanel.value.style.width = '250px'
      } else {
        rightPanel.value.style.width = 0
      }
    })
    onMounted(() => {
      const node = document.querySelector('#app')
      node.insertBefore(rightPanel.value, node.firstChild)
    })

    return { show, rightPanel }
  },
}
</script>

<style lang="scss" scoped>
.rightPanel {
  position: fixed;
  top: 0;
  right: 0;

  width: 0;
  max-width: 250px;
  height: 100vh;

  z-index: 40000;
  background: #838383;
  transition: 0.2s;
  box-shadow: -7px 0px 5px 0px rgb(0 0 0 / 15%);
}

.rightPanel-button {
  position: absolute;
  left: -30px;
  top: 150px;
  width: 30px;
  height: 30px;
  line-height: 30px;
  border-radius: 3px;
  text-align: center;
  pointer-events: auto;
  cursor: pointer;
  background-color: $primary-color;
}
</style>

<style>
.rightPanel-content {
  overflow: hidden;
  text-align: center;
}

.rightPanel-content > * {
  margin-bottom: 20px;
}
</style>
