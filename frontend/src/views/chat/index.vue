<template>
  <div>
    <h1>chat!</h1>
    <div class="chat-body">
      <ChatRecord />
      <div class="chat-history">
        <ChatDialog />
      </div>
    </div>
  </div>
</template>

<script>
import useChat from '@/hooks/Chat/useChat'
import ChatRecord from './components/ChatRecord'
import ChatDialog from './components/ChatDialog'
import { ref } from 'vue'

export default {
  name: 'chat',
  components: { ChatRecord, ChatDialog },
  setup() {
    const { sendMessageTo, subscribeTalk } = useChat()
    const msg = ref('')
    const content = ref(null)
    const rec = ref(null)
    const sendTo = () => {
      sendMessageTo(rec.value, content.value)
    }
    subscribeTalk((content) => {
      msg.value = content
    })
    return { sendTo, msg, rec, content }
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
