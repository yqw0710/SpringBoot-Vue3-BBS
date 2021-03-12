<template>
  <div>
    <comment-input />
    <comment-item v-for="item in commentList" :key="item.id" :model="item" />
  </div>
</template>

<script>
import CommentInput from './CommentInput'
import CommentItem from '@/components/CommentArea/CommentItem'

export default {
  name: 'CommentArea',
  components: { CommentInput, CommentItem },
  setup() {
    const commentList = [
      {
        id: 1,
        parentId: 0,
        itemId: 1,
        itemType: 4,
        fromId: 1,
        fromName: 'JoJo',
        toId: null,
        toName: null,
        likes: 0,
        content: 'heh',
        status: 1,
        gmtCreate: '2020-09-23 22:07:15',
        gmtModified: '2020-09-23 22:07:15',
        children: [],
        childrenCount: 0,
        fromAvatar: 'http://res7.fanyibar.top/1612353510311-shy.jpg',
      },
      {
        id: 2,
        parentId: 0,
        itemId: 1,
        itemType: 4,
        fromId: 1,
        fromName: 'jojo',
        toId: null,
        toName: null,
        likes: 0,
        content: 'haha',
        status: 1,
        gmtCreate: '2020-09-23 22:07:15',
        gmtModified: '2020-09-23 22:07:15',
        children: [],
        childrenCount: 0,
        fromAvatar: 'http://res7.fanyibar.top/1612353510311-shy.jpg',
      },
    ]
    console.log(commentList)
    return { commentList }
  },
}
</script>


