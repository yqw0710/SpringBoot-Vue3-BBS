<template>
  <div class="article-card">
    <div class="article-main">
      <router-link :to="'/article/' + article.id">
        <h2>{{ article.title }}</h2>
      </router-link>
      <p>{{ article.description }}</p>
    </div>
    <img class="article-pic" :src="article.firstPic" alt="" />
    <div class="article-bar">
      <span class="date">2020-1-1</span>
      <el-tag class="difficulty" :color="difficultyColor">
        {{ difficultyText }}
      </el-tag>
      <span class="pv">
        <i class="el-icon-view" />
        {{ '  ' + article.pv }}
      </span>
    </div>
    <silk-ribbon
      v-if="article.uid === 1"
      :pattern="1"
      :content="'来自管理员'"
    />
  </div>
</template>

<script>
import SilkRibbon from '@/components/SilkRibbon'
import { computed } from 'vue'
export default {
  name: 'ArticleCard',
  components: { SilkRibbon },
  props: {
    article: {
      type: Object,
      required: true,
    },
  },
  setup(props) {
    const difficultyColor = computed(() => {
      switch (props.article.difficulty) {
        case 1:
          return '#E6FFFB'
        case 2:
          return '#F6FFED'
        case 3:
          return 'pink'
        default:
          return '#FFF1F0'
      }
    })
    const difficultyText = computed(() => {
      switch (props.article.difficulty) {
        case 1:
          return '小学水平'
        case 2:
          return '中学水平'
        case 3:
          return '大学水平'
        default:
          return '商务水平'
      }
    })
    return { difficultyColor, difficultyText }
  },
}
</script>
<style lang="scss" scoped>
@import '~@/styles/_mixins';

.article-card {
  border-bottom: 1px solid ghostwhite;
  border-radius: 5px;

  padding: 10px 5px;
  height: 130px;

  display: flex;
  position: relative;
}

.article-main {
  flex: 1;
  padding: 0 20px;

  h2 {
    font-size: 1.3rem;
    //color: $primary-text-color;
    @include ellipsis(1);
    font-weight: bold;
  }

  p {
    @include ellipsis(2);
  }
}

.article-pic {
  height: 100px;
}

.article-bar {
  position: absolute;
  bottom: 3px;
  left: 25px;

  color: gray;

  > * {
    margin-right: 15px;
  }
  .difficulty {
    color: $primary-text-color;
  }
}
</style>
