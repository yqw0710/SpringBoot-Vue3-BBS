<template>
  <div id="MessageBoard">
    <div class="MessageBoard-top">
      <h2 style="text-align: center">🎉:欢迎给该网站留下建设性的意见❤</h2>
      <img class="pic" alt="noteTaking"
        src="~@/assets/images/note_taking.svg"/>
    </div>
    <message-add />
    <message-list />
  </div>
</template>

<script>
import MessageAdd from "@/views/message-board/components/MessageAdd";
import MessageList from "@/views/message-board/components/MessageList";
// TODO 加载更多
export default {
  name: "MessageBoard",
  components: { MessageAdd, MessageList },
  setup() {
    return {};
  },
};
</script>

<style lang="scss" scoped>
#MessageBoard {
  max-width: 800px;
  margin: 10px auto 0;

  .MessageBoard-top {
    .pic {
      display: block;
      margin: -45px auto -45px;
      max-width: 400px;
    }
  }
}
</style>
