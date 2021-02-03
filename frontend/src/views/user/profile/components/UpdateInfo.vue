<template>
  <div class="update-info">
    <div class="info">
      <p class="key">签名</p>
      <span v-if="!edited[0]" class="value">{{ info.sign }}</span>
      <input v-else type="text" class="value" v-model="info.sign" />
      <a v-if="!edited[0]" @click="edited[0] = true" class="opt">修改</a>
      <a v-else @click="save(0, $event)" class="opt">保存</a>
    </div>
    <div class="info">
      <p class="key">学校</p>
      <span v-if="!edited[1]" class="value">{{ info.school }}</span>
      <input v-else type="text" class="value" v-model="info.school" />
      <a v-if="!edited[1]" @click="edited[1] = true" class="opt">修改</a>
      <a v-else @click="save(1, $event)" class="opt">保存</a>
    </div>
    <p>“只是做做样子的更改”</p>
  </div>
</template>

<script>
import { reactive } from 'vue'

export default {
  name: 'UpdateInfo',
  setup() {
    const edited = reactive([])
    const info = reactive({
      sign: '这个人很懒',
      school: '重庆科技学院',
    })
    const save = (index, e) => {
      edited[index] = false
      console.log(e.target)
    }
    return { edited, save, info }
  },
}
</script>

<style lang="scss" scoped>
.update-info {
  margin-top: 20px;
}
.info {
  margin-bottom: 15px;
  position: relative;
  > .key {
    font-weight: bold;
    margin-bottom: 5px;
  }
  > .value {
    padding: 2px;
    outline: none;
    border: none;
    font-size: 1rem;
    border-bottom: 1px solid gray;
    background-color: transparent;
  }
  > .opt {
    position: absolute;
    top: 10px;
    right: 5px;
    cursor: pointer;
    color: $primary-color;
  }
}
</style>
