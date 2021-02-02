<template>
  <div id="PostPreview">
    <post-preview-item
      v-for="item in postList.records"
      :key="'post:' + item.id"
      :model="item"
    />
    <!-- TODO 加载更多-->
  </div>
</template>

<script>
import PostPreviewItem from './PostPreviewItem'
import { getPostList } from './usePost'
export default {
  name: 'post-preview',
  components: { PostPreviewItem },
  props: { category: String },
  setup(props) {
    let postList = getPostList(props.category)
    return { postList }
  },
}
</script>

<style scoped></style>
