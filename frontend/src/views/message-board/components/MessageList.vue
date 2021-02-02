<template>
  <div v-if="loaded" class="MessageBoard-list">
    <h3>总计{{ models.total }}条留言</h3>
    <MessageListItem
      v-for="item in models.records"
      :model="item"
      :key="item.id"
      :parent="true"
    />
  </div>
</template>

<script>
import MessageListItem from '@/views/message-board/components/MessageListItem'
import { getMessage } from '@/api/message'
import { reactive, ref } from 'vue'
export default {
  name: 'MessageList',
  components: { MessageListItem },
  setup() {
    let loaded = ref(false)
    let models = reactive({})
    getMessage(1, 5).then((res) => {
      models.total = res.data.total
      models.records = res.data.records
      loaded.value = true
    })
    return { models, loaded }
  },
}

function useMessageList(num, size) {}
</script>

<style lang="scss" scoped>
.MessageBoard-list {
  margin-top: 10px;
  h3 {
    margin-bottom: 10px;
  }
}
</style>
