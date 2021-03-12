<template>
  <div class="post">
    <suspended-panel :post="post" />
    <div class="view-area">
      <content-display v-if="loaded" :article="post" />
      <hr />
      <comment-area/>
    </div>
    <div class="sidebar" style="display: none">
      <h2>22</h2>
      <h2>22</h2>
    </div>
  </div>
</template>

<script>
import { useRoute } from 'vue-router'
import { getPostDetail } from '@/api/post'
import SuspendedPanel from './components/SuspendedPanel'
import ContentDisplay from '@/components/ContentDisplay'
import CommentArea from '@/components/CommentArea'
import { reactive, ref } from 'vue'
export default {
  name: 'PostDetail',
  components: { SuspendedPanel, ContentDisplay, CommentArea },
  setup() {
    let route = useRoute()
    let pid = route.params.pid
    const loaded = ref(false)
    let post = reactive({})
    getPostDetail(pid).then((res) => {
      console.log(post)
      console.log(loaded)
      // 暂不知如何拷贝对象给post这个响应式对象
      post.value = res.data
      document.title = res.data.title
      loaded.value = true
      console.log(post)
    })
    return { post, loaded }
  },
}
</script>

<style lang="scss" scoped>
.post {
  display: flex;
}
.view-area {
  width: 65%;
  margin: 0 auto;
  //border: 1px solid red;
}
.sidebar {
  width: 35%;
  //border: 1px solid #9bff24;
}
</style>
