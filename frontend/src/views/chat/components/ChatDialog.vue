<template>
  <div class="dialog">
    <div class="title">
      <span>迪奥·布兰德X</span>
      <span class="dialog-menu">...</span>
    </div>
    <div class="message-list">
      <div class="message-more">
        <p class="no-more">没有更多消息了la</p>
        <!--              加载更多-->
      </div>
      <div class="message-item not-me">
        <a class="message-sender" href="#">
          <img src="~@/assets/images/dio.jpg" class="avatar-small" alt="" />
        </a>
        <div class="message-content">
          平角裤平角裤平角裤平角裤平角裤平角裤平角裤平角裤平角裤平角裤平角裤平角裤平角裤平角裤平角裤平角裤平角裤平角裤平角裤平角裤平角裤平角裤平角裤平角裤平角裤平角裤平角裤平角裤平角裤平角裤平角裤平角裤平角裤平角裤平角裤平角裤平角裤平角裤
        </div>
      </div>
      <div class="message-item not-me">
        <a class="message-sender" href="#">
          <img src="~@/assets/images/dio.jpg" class="avatar-small" alt="" />
        </a>
        <div class="message-content">平角裤</div>
      </div>
      <div class="message-time">2020-1-1 12:30</div>
      <div class="message-item is-me">
        <a class="message-sender" href="#">
          <img src="~@/assets/images/jojo.jpg" class="avatar-small" alt="" />
        </a>
        <div class="message-content">你到底想说什么</div>
      </div>
    </div>
    <div class="send">
      <div class="toolbar">
        <emotion-box position="top">😃</emotion-box>
        <span>📷</span>
      </div>
      <div class="input-box" contenteditable></div>
      <button class="btn send-btn">发送(S)</button>
    </div>
  </div>
</template>

<script>
import EmotionBox from '@/components/EmotionBox'

export default {
  name: 'ChatDialog',
  components: { EmotionBox },
}
</script>

<style lang="scss">
.item-active {
  background-color: #c5c5c5 !important;
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
.message-list {
  flex: 1;
  overflow-x: hidden;
  overflow-y: scroll;
  > * {
    margin-bottom: 10px;
  }
  .no-more {
    color: #9c9a9a;
    font-size: 12px;
    padding: 6px;
    text-align: center;
  }
  .message-item {
    min-height: 40px;
    padding: 0 14px;
    overflow: hidden;
    position: relative;
  }
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
  .message-time {
    text-align: center;
    color: #7b7b7b;
    font-size: 12px;
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
}
.send {
  position: relative;
  height: 120px;
  border-top: 1px solid #e2e2e2;
  > .toolbar {
    position: absolute;
    height: 22px;
    padding-left: 10px;
    padding-top: 5px;
    width: 100%;
    > * {
      cursor: pointer;
      margin-right: 15px;
    }
  }
  > .input-box {
    height: 100%;
    padding: 30px 5px 0 5px;
    white-space: pre-wrap;
    overflow-x: hidden;
    overflow-y: auto;
    background-color: transparent;
    font-size: 14px;
    vertical-align: baseline;
    word-break: break-word;
    outline: none;
    &:focus {
      background-color: #fff;
    }
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
