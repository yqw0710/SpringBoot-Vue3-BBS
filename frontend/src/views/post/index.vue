<template>
  <div class="post">
    <suspended-panel :post="post" />
    <div class="view-area">
      <content-display v-if="loaded" :article="post" />
      <hr />
      <h1>11</h1>
      <h1>11</h1>
    </div>
    <div class="sidebar">
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
import { reactive, ref } from 'vue'
export default {
  name: 'PostDetail',
  components: { SuspendedPanel, ContentDisplay },
  setup() {
    let route = useRoute()
    let pid = route.params.pid
    const loaded = ref(false)
    let post = reactive({})
    getPostDetail(pid).then((res) => {
      post.value = res.data
      document.title = res.data.title
      loaded.value = true
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
  //border: 1px solid red;
}
.sidebar {
  width: 35%;
  //border: 1px solid #9bff24;
}
</style>
