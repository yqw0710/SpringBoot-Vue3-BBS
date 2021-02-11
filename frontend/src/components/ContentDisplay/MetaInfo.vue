<template>
  <div class="meta-info">
    <div v-if="tagList" class="tags">
      标签🏷:
      <el-tag
        v-for="(i, index) in tagList"
        :key="i + 'tag' + index + 1"
        :color="i.color"
        effect="dark"
      >
        {{ i.tag }}
      </el-tag>
    </div>
    <div class="other">
      <span class="date">{{ date }}</span>
      <span class="val">👁{{ pv }}</span>
      <span class="likes">👍{{ likes }}</span>
      <span class="val">💬{{ comments }}</span>
    </div>
  </div>
</template>

<script>
import { computed } from 'vue'
import { getStrColor } from '@/utils/process'

export default {
  name: 'MetaInfo',
  props: {
    tags: String || Array,
    date: String,
    pv: Number,
    comments: Number,
    likes: Number,
  },
  setup(props) {
    // eslint-disable-next-line vue/no-setup-props-destructure
    const tagList = computed(() => {
      if (!props.tags) return null
      return props.tags
        .split('-')
        .filter((element, index, self) => {
          return element.length > 1 && self.indexOf(element) === index
        })
        .map((x) => {
          return {
            tag: x,
            color: getStrColor(x),
          }
        })
    })
    return { tagList }
  },
}
</script>

<style lang="scss" scoped>
.meta-info {
  // border: 1px solid red;
  height: 40px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  .other {
    > * {
      margin-right: 8px;
    }
  }
  .el-tag--dark {
    height: 24px;
    line-height: 24px;
    border: none;
    margin-right: 10px;
  }

  .date {
    margin-right: 5px;
  }
}
</style>
