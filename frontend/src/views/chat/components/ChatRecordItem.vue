<template>
  <div @click="handleItemClick" class="chat-list-item">
    <img :src="sender.avatar" v-default-img="'avatar'" class="avatar" alt="" />
    <div class="chat-info">
      <p class="nickname">{{ sender.nickname }}</p>
      <p class="last-one">{{ sender.content }}</p>
      <p v-if="unreadCount" class="unread">{{ unreadCount }}</p>
    </div>
  </div>
</template>

<script>
import useChat from '@/hooks/Chat/useChat'
import { computed } from 'vue'

export default {
  name: 'ChatRecordItem',
  props: ['sender'],
  setup(props) {
    const { currentChatTarget, cleanUnreadCount } = useChat()
    const unreadCount = computed(() => {
      if (props.sender.unread > 100) {
        return '99'
      } else if (props.sender.unread > 0) {
        return props.sender.unread + ''
      } else return null
    })
    const handleItemClick = () => {
      currentChatTarget.value = props.sender.fromId
      cleanUnreadCount(props.sender.fromId)
    }
    return { currentChatTarget, unreadCount, handleItemClick }
  },
}
</script>

<style lang="scss" scoped>
@import '~@/styles/_mixins';
.chat-list-item {
  display: flex;
  position: relative;
  width: 100%;
  padding: 5px;
  cursor: pointer;
  overflow: hidden;
  font-size: 14px;
  &:hover {
    background-color: #dcdad8;
  }
  > .chat-info {
    margin-left: 15px;
    width: 180px;
    > .last-one {
      margin-top: 4px;
      font-size: 12px;
      color: #616161;
    }
    > * {
      @include ellipsis();
    }
    > .unread {
      position: absolute;
      transition: 0.5s;
      padding: 0.5px;
      top: 2px;
      left: 2px;
      //transform: translateX(150px);
      width: 16px;
      //height: 15px;
      border-radius: 50%;
      text-align: center;
      font-size: 0.5rem;
      color: white;
      background-color: #e34545;
    }
  }
}
</style>
