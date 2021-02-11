<template>
  <article>
    <h1 class="title">{{ article.value.title }}</h1>
    <meta-info
      v-if="showInfo"
      :date="article.value.gmtCreate"
      :tags="article.value.tags"
      :pv="article.value.pv"
      :comments="article.value.comments"
      :likes="article.value.likes"
    />
    <author-info v-if="showInfo" :uid="article.value.uid" />
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
    console.log(props.article.value)
    return { renderedContent }
  },
}
</script>

<style lang="scss" scoped></style>
