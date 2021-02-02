<template>
  <div id="index">
    <el-tabs v-model="activeTab" :stretch="false">
      <el-tab-pane
        v-for="item in postCategory"
        :key="item.value"
        :label="item.name"
        :name="item.value"
        lazy
      >
        <post-preview :category="activeTab" />
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import PostPreview from '@/views/home/components/PostPreview'
import { postCategory } from '@/views/home/components/usePost'
import { ref } from 'vue'
export default {
  name: 'Home',
  components: { PostPreview },
  setup() {
    const activeTab = ref('default')
    return { activeTab, postCategory }
  },
}
</script>

<style></style>
