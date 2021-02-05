<template>
  <article>
    <h1 class="title">{{ article.title }}</h1>
    <meta-info
      v-if="showInfo"
      :date="article.gmtCreate"
      :tags="article.tags"
      :pv="article.pv"
      :comments="article.comments"
      :likes="article.likes"
    />
    <author-info v-if="showInfo" :uid="article.uid" />
    <div class="markdown-body" v-html="renderedContent"></div>
  </article>
</template>

<script>
import AuthorInfo from './AuthorInfo'
import MetaInfo from './MetaInfo'
import marked from 'marked'
export default {
  name: 'ContentDisplay',
  components: { AuthorInfo, MetaInfo },
  props: {
    article: {
      type: Object,
      required: true,
    },
    showInfo: {
      type: Boolean,
      default: true,
    },
  },
  setup(props) {
    const renderedContent = marked(props.article.value.content)
    return { renderedContent }
  },
}
</script>

<style lang="scss" scoped>
.markdown-body {
  img {
    max-width: 100%;
  }
}
</style>
