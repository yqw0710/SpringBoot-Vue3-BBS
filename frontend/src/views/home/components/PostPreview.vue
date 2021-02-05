<template>
  <div
    v-infinite-scroll="load"
    infinite-scroll-disabled="disabled"
    v-if="loaded"
    id="PostPreview"
    class="preview"
  >
    <post-preview-item
      v-for="item in models.records"
      :key="'post:' + item.id"
      :model="item"
    />
    <!-- TODO 加载更多-->
    <p v-if="disabled" class="noMore">没有更多了😅</p>
  </div>
</template>

<script>
import PostPreviewItem from './PostPreviewItem'
import { getPostList } from './usePost'
import { computed, reactive, ref } from 'vue'
export default {
  name: 'PostPreview',
  components: { PostPreviewItem },
  props: { category: String },
  setup(props) {
    let loaded = ref(false)
    let models = reactive({})
    // 加载更多
    const load = () => {
      if (isNaN(models.current)) return
      getPostList(models.current + 1, 5, models, loaded, props.category)
    }
    getPostList(1, 10, models, loaded, props.category)
    // 是否没更多数据了
    const disabled = computed(() => models.current >= models.pages)
    return { models, loaded, load, disabled }
  },
}
</script>

<style lang="scss" scoped></style>
