<template>
  <div class="message-item" :class="isMe ? 'is-me' : 'not-me'">
    <a class="message-sender" href="#">
      <img
        v-default-img="'avatar'"
        :src="avatarUrl"
        class="avatar-small"
        alt=""
      />
    </a>
    <div v-html="processedContent" class="message-content" />
  </div>
</template>

<script>
import { computed } from 'vue'
import { useStore } from 'vuex'
import useChat from '@/hooks/Chat/useChat'
import { useProcessEmotion } from '@/hooks/Emotion/useEmotions'
export default {
  name: 'ChatMessageItem',
  props: {
    isMe: { type: Boolean, require: true },
    content: String,
    created: String,
  },
  setup(props) {
    const { currentChatObj } = useChat()
    const avatarUrl = computed(() =>
      props.isMe ? useStore().getters.avatar : currentChatObj.value.avatar
    )
    const processedContent = computed(() => useProcessEmotion(props.content))
    return { avatarUrl, processedContent }
  },
}
</script>

<style lang="scss" scoped>
.message-item {
  min-height: 40px;
  padding: 0 7px 0 14px;
  overflow: hidden;
  position: relative;
  .message-content {
    margin: 0 10px 0;
    padding: 10px;
    max-width: 80%;
    border-radius: 8px;
    border: 1px solid #ececec;
    word-break: break-word;
    overflow: hidden;
    font-size: 14px;
    background-color: #fff;
  }
}
.is-me {
  > * {
    float: right;
  }
  .message-content {
    color: #fff;
    border-radius: 14px 0 14px 14px;
    background-color: $primary-color;
  }
}
.not-me {
  > * {
    float: left;
  }
  .message-content:before {
    content: '';
    width: 0;
    height: 0;
    position: absolute;
    top: 12px;
    left: 54px;
    border-top: solid 6px transparent;
    border-right: solid 6px #fff;
    border-bottom: solid 6px transparent;
  }
}
</style>
