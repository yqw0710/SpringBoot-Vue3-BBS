<template>
  <div>
    <h1>chat!</h1>
    <div contenteditable>
      {{ msg }}
    </div>
    <input type="text" v-model="rec" />
    <input type="text" v-model="content" />
    <button @click="sendTo">sendTo</button>
  </div>
</template>

<script>
import useChat from '@/hooks/Chat/useChat'
import { ref } from 'vue'

export default {
  name: 'chat',
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

<style scoped></style>
