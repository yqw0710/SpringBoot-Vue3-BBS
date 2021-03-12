<template>
  <div class="chat-body">
    <ChatRecord :records="senders" />
    <div class="chat-history">
      <ChatDialog v-if="currentChatTarget" />
      <el-empty v-else description="在线聊天" />
    </div>
  </div>
</template>

<script>
import useChat from '@/hooks/Chat/useChat'
import ChatRecord from './components/ChatRecord'
import ChatDialog from './components/ChatDialog'
import { useStore } from 'vuex'

export default {
  name: 'chat',
  components: { ChatRecord, ChatDialog },
  setup() {
    const { currentChatTarget, senders, init } = useChat()
    init(useStore().getters.uid)
    return { currentChatTarget, senders }
  },
}
</script>

<style lang="scss" scoped>
.chat-body {
  display: flex;
  border-radius: 4px;
  height: 75vh;
  position: relative;
  border: 1px solid #d4d4d4;
}
.chat-history {
  flex: 1;
  background-color: #f5f5f5;
  color: #333;
}
</style>
