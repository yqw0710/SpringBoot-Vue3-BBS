<template>
  <div ref="listRef" class="message-list">
    <div class="message-more">
      <p v-if="noMore" class="no-more">没有更多消息了</p>
      <p v-else class="have-more" @click="loadMore">加载更多消息!</p>
    </div>
    <ChatMessageItem
      v-for="item in current.historyArr"
      :key="item.id"
      :is-me="item.sid === uid"
      :content="item.content"
      :created="item.created"
    />
  </div>
</template>

<script>
import ChatMessageItem from './ChatMessageItem'
import { useStore } from 'vuex'
import { computed, nextTick, onMounted, ref, watch } from 'vue'
import useChat from '@/hooks/Chat/useChat'
export default {
  name: 'ChatMessageList',
  props: ['current'],
  components: { ChatMessageItem },
  setup(props) {
    const listRef = ref(null)
    const { loadMoreHistory } = useChat()
    const noMore = computed(
      () => props.current.pageInfo.current >= props.current.pageInfo.pages
    )
    const loadMore = () => {
      loadMoreHistory(props.current.pageInfo.current + 1)
    }
    // 监听数组变化，最后一条消息在保持在底部
    onMounted(() => (listRef.value.scrollTop = listRef.value.scrollHeight))
    watch(props.current, () => {
      // 判断如果current.pageInfo.current比之前的增加了就往上滚动
      nextTick(() => (listRef.value.scrollTop = listRef.value.scrollHeight))
    })

    return { uid: useStore().getters.uid, listRef, noMore, loadMore }
  },
}
</script>

<style lang="scss" scoped>
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
  .have-more {
    font-size: 12px;
    padding: 6px;
    text-align: center;
    cursor: pointer;
  }

  .message-time {
    text-align: center;
    color: #7b7b7b;
    font-size: 12px;
  }
}
</style>
