<template>
  <div
    v-if="loaded"
    v-infinite-scroll="load"
    infinite-scroll-disabled="disabled"
    class="MessageBoard-list"
  >
    <h3>总计{{ models.total }}条留言</h3>
    <MessageListItem
      v-for="item in models.records"
      :model="item"
      :key="item.id"
      :parent="true"
    />
    <p v-if="disabled" class="noMore">没有更多了😅</p>
  </div>
</template>

<script>
import MessageListItem from '@/views/message-board/components/MessageListItem'
import { getMessage } from '@/api/message'
import { computed, reactive, ref } from 'vue'
export default {
  name: 'MessageList',
  components: { MessageListItem },
  setup() {
    let loaded = ref(false)
    let models = reactive({})
    // 加载更多
    const load = () => {
      useMessageList(models.current + 1, 5, models)
    }
    // 是否没更多数据了
    const disabled = computed(() => models.current >= models.pages)
    // 首次加载
    useMessageList(1, 5, models, loaded)
    return { models, loaded, load, disabled }
  },
}

function useMessageList(num, size, models, loaded) {
  getMessage(num, size, models).then((res) => {
    // 总数据条数
    models.total = res.data.total
    // 总页码数量
    models.pages = res.data.pages
    // 当前页码
    models.current = res.data.current
    // 具体留言数据
    if (num === 1) {
      models.records = []
      loaded.value = true
    }
    for (const item of res.data.records) {
      models.records.push(item)
    }
  })
}
</script>

<style lang="scss" scoped>
.MessageBoard-list {
  margin-top: 10px;
  h3 {
    margin-bottom: 10px;
  }
  .noMore {
    text-align: center;
    margin-top: 10px;
  }
}
</style>
