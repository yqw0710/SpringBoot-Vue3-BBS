<template>
  <div class="chat-record">
    <div class="chat-search">
      <i class="el-icon-search" />
      <input type="text" placeholder="搜索" />
    </div>
    <div class="chat-list">
      <ChatRecordItem />
      <ChatRecordItem />
    </div>
  </div>
</template>

<script>
import ChatRecordItem from './ChatRecordItem'
export default {
  name: 'ChatRecord',
  components: { ChatRecordItem },
}
</script>

<style lang="scss" scoped>
.chat-record {
  background-color: #eceae8;
  width: 250px;
  color: #333;
  > .chat-search {
    position: relative;
    height: 30px;

    i {
      position: absolute;
      top: 4px;
      font-size: 0.8rem;
      left: 15px;
    }

    input {
      display: block;
      width: 90%;
      margin: 5px auto;
      padding: 2px 2px 2px 16px;

      border: none;
      border-radius: 4px;
      height: 22px;
      line-height: 22px;
      background-color: #dbd8d8;

      &:focus {
        outline: none;
      }
    }
  }

  > .chat-list {
    height: calc(100% - 35px);
    position: relative;
    overflow: hidden;

    &:hover {
      overflow-y: scroll;
    }
  }
}
</style>
