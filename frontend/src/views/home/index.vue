<template>
  <div id="index">
    <!--    帖子列表展示-->
    <el-tabs style="width: 65%" v-model="activeTab" :stretch="false">
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
    <div class="right" style="position: relative"><todo /></div>
  </div>
</template>

<script>
import PostPreview from '@/views/home/components/PostPreview'
import Todo from '@/components/Todo'
import { postCategory } from '@/views/home/components/usePost'
import { ref } from 'vue'

export default {
  name: 'Home',
  components: { PostPreview, Todo },
  setup() {
    const activeTab = ref('default')
    return { activeTab, postCategory }
  },
}
</script>

<style lang="scss" scoped>
#index {
  display: flex;
}
.right {
  flex: 1;
}
</style>
