<template>
  <div class="dialog">
    <div class="title">
      <span>{{ currentChatObj.nickname }}</span>
      <span class="dialog-menu">...</span>
    </div>
    <ChatMessageList :current="currentChatObj" />
    <div class="send">
      <div class="toolbar">
        <emotion-box @emotion-add="(val) => (userInput += val)" position="top">
          😃
        </emotion-box>
        <span>📷</span>
      </div>
      <textarea
        v-model="userInput"
        @keydown.enter="keyDown"
        class="input-box"
      />
      <button class="btn send-btn" @click="sendMessage">发送(S)</button>
    </div>
  </div>
</template>
<script>
import EmotionBox from '@/components/EmotionBox'
import ChatMessageList from './ChatMessageList'
import useChat from '@/hooks/Chat/useChat'
import { ref, watch } from 'vue'
export default {
  name: 'ChatDialog',
  components: { EmotionBox, ChatMessageList },
  setup() {
    const {
      senders,
      sendMessageTo,
      currentChatObj,
      currentChatTarget,
    } = useChat()
    // 监听选择聊天对象的变化，备份输入框内容，并恢复之前的暂存内容
    watch(currentChatTarget, (newVal, oldVal) => {
      senders[oldVal].userInput = userInput.value
      userInput.value = senders[newVal].userInput || ''
    })
    // 发送消息
    const sendMessage = () => {
      console.log('SendTo ' + currentChatTarget.value + ':' + userInput.value)
      sendMessageTo(currentChatTarget.value, userInput.value)
      userInput.value = ''
    }
    const userInput = ref('')
    const keyDown = (e) => {
      if (e.ctrlKey && e.keyCode === 13) {
        sendMessage()
      }
    }
    return {
      currentChatObj,
      userInput,
      sendMessage,
      keyDown,
    }
  },
}
</script>

<style lang="scss">
.item-active {
  background-color: #c5c5c5 !important;
}
</style>
<style lang="scss" scoped>
.dialog {
  display: flex;
  flex-direction: column;
  align-items: stretch;
  height: 100%;
}
.title {
  position: relative;
  text-align: center;
  font-size: 14px;
  height: 35px;
  line-height: 35px;
  border-bottom: 1px solid #c6c6c6;
  .dialog-menu {
    position: absolute;
    color: #7b7b7b;
    user-select: none;
    letter-spacing: 2px;
    font-size: 1.2rem;
    cursor: pointer;
    top: -5px;
    right: 8px;
  }
}
.send {
  position: relative;
  height: 120px;
  border-top: 1px solid #e8e8e8;
  > .toolbar {
    //position: absolute;
    height: 25px;
    padding-left: 10px;
    padding-top: 5px;
    width: 100%;
    > * {
      margin-right: 15px;
    }
  }
  > .input-box {
    height: 95px;
    width: 100%;
    border: none;
    resize: none;
    outline: none;
    padding: 5px 5px 0 5px;
    white-space: pre-wrap;
    overflow-x: hidden;
    overflow-y: auto;
    background-color: transparent;
    font-size: 14px;
    vertical-align: baseline;
    word-break: break-word;
  }
  > .send-btn {
    position: absolute;
    bottom: 10px;
    right: 20px;
    height: 25px;
    border-radius: 2px;
    padding: 2px 10px;
  }
}
</style>
