<template>
  <div class="article">
    <content-display v-if="loaded" :article="article" />
  </div>
</template>

<script>
import { useRoute } from 'vue-router'
import { getArticle } from '@/api/article'
import { reactive, ref } from 'vue'
import ContentDisplay from '@/components/ContentDisplay'
export default {
  name: 'article',
  components: { ContentDisplay },
  setup() {
    const route = useRoute()
    const aid = route.params.aid
    const loaded = ref(false)
    const article = reactive({})
    getArticle(aid).then((res) => {
      article.value = res.data
      document.title = res.data.title
      loaded.value = true
    })
    return { article, loaded }
  },
}
</script>

<style scoped></style>
