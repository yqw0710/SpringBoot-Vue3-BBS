<template>
  <div
    v-if="loaded"
    v-infinite-scroll="load"
    infinite-scroll-disabled="disabled"
    class="read"
  >
    <article-card
      v-for="item in models.records"
      :key="item.id"
      :article="item"
    />
    <p v-if="disabled" class="noMore">没有更多了😅</p>
  </div>
</template>

<script>
import ArticleCard from './components/ArticleCard'
import { getArticleList } from '@/api/article'
import { computed, reactive, ref } from 'vue'

export default {
  name: 'index',
  components: { ArticleCard },
  setup() {
    let loaded = ref(false)
    let models = reactive({})
    // 加载更多
    const load = () => {
      useArticleList(models.current + 1, 5, models)
    }
    // 是否没更多数据了
    const disabled = computed(() => models.current >= models.pages)
    // 首次加载
    useArticleList(1, 5, models, loaded)
    return { models, loaded, load, disabled }
  },
}
function useArticleList(num, size, models, loaded) {
  getArticleList(num, size).then((res) => {
    // 总数据条数
    models.total = res.data.total
    // 总页码数量
    models.pages = res.data.pages
    // 当前页码
    models.current = res.data.current
    if (num === 1) {
      models.records = []
      loaded.value = true
    }
    for (const item of res.data.records) {
      models.records.push(item)
    }
    console.log(models)
  })
}
</script>

<style scoped></style>
