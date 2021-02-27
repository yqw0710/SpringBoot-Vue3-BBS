<template>
  <div class="chat-list-item">
    <img src="~@/assets/images/dio.jpg" class="avatar" alt="" />
    <div class="chat-info">
      <p class="name">迪奥·布兰德</p>
      <p class="last">你会记得你至今吃过多少片面包么？</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ChatRecordItem',
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

  > .chat-info {
    margin-left: 15px;
    width: 180px;
    > .last {
      margin-top: 4px;
      font-size: 12px;
      color: #616161;
    }
    > * {
      @include ellipsis();
    }
  }
  &:hover {
    background-color: #dcdad8;
  }
}
</style>
