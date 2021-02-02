<template>
  <div @click.stop="handleClick" class="emotion-box">
    <slot>
      <span class="open-btn">小表情</span>
    </slot>
    <div v-show="show" class="emotion-wrapper">
      <p style="font-size: 0.8rem">{{ currentEmotion }}</p>
      <div class="emotion-container" v-if="current === 'wechat'">
        <span
          v-for="(item, index) in wechat"
          :key="'w:' + index"
          class="emotion"
          v-html="item.url"
          @click.stop="$emit('emotion-add', item.name)"
        />
      </div>
      <div class="emotion-container" v-else-if="current === 'kaomoji'">
        <span
          v-for="(item, index) in kaomoji"
          :key="'k:' + index"
          class="emotion"
          v-html="item"
          @click.stop="$emit('emotion-add', item)"
        />
      </div>
      <div class="emotion-tabs">
        <a @click.stop="current = 'wechat'">微信表情</a>
        <a @click.stop="current = 'kaomoji'">颜文字</a>
      </div>
    </div>
  </div>
</template>

<script>
import { useEmotions } from '@/hooks/Emotion/useEmotions'
import { computed, ref } from 'vue'

export default {
  name: 'EmotionBox',
  emits: ['emotion-add'],
  setup() {
    let show = ref(false)
    let current = ref('wechat')
    let wechat = useEmotions('wechat')
    let kaomoji = useEmotions('kaomoji')
    const handleClick = () => {
      if (show.value) {
        document.onclick = null
      } else {
        document.onclick = () => {
          show.value = false
          document.onclick = null
        }
      }
      show.value = !show.value
    }
    const currentEmotion = computed(() => {
      if (current.value === 'wechat') return '微信表情'
      else if (current.value === 'kaomoji') return '颜文字'
      else return 'unknown'
    })
    return { show, wechat, kaomoji, current, currentEmotion, handleClick }
  },
}
</script>

<style lang="scss" scoped>
.emotion-box {
  display: inline-block;
  position: relative;
  //展开表情盒子的按钮
  .open-btn {
    cursor: pointer;
    height: 30px;
    user-select: none;
    line-height: 30px;
    padding: 2px 4px;
    border: 1px solid #9c9a9a;
    border-radius: 7px;
    margin-right: 5px;

    &:hover {
      background-color: $primary-color;
    }
  }

  //装有一堆表情的容器
  .emotion-container {
    height: 150px;
    overflow: scroll;
  }

  //单个表情
  .emotion {
    cursor: pointer;
    display: inline-block;
    margin: 2px;
    padding: 2px;

    &:hover {
      border-radius: 5px;
      background-color: #9c9a9a;
    }
  }

  // 切换表情的tab
  .emotion-tabs {
    font-size: 0.95rem;
    padding: 5px;

    > a {
      cursor: pointer;
      padding: 3px;

      &:hover {
        border-radius: 5px;
        background-color: #7b7b7b;
      }
    }
  }

  // 包含wrapper和tabs的最外层容器
  .emotion-wrapper {
    position: absolute;
    top: 30px;
    width: 343px;

    padding: 5px;
    border: 1px solid aqua;
    color: $primary-text-color;
    background-color: #e2e2e2;
    z-index: 10;
  }
}
</style>
