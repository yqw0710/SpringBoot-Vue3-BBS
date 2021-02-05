<template>
  <div id="notice">
    <el-tabs v-if="show" type="card" :tabBarGutter="10">
      <el-tab-pane key="4" tab="评论">
        <div
          class="notice-card"
          v-for="(item, index) in commentNoticeList"
          :key="item.id"
          :id="'notice:' + item.id"
        >
          <a class="title" :href="item.link" target="_blank">
            {{ item.title }}
          </a>
          <p class="content">{{ item.content }}</p>
          <span class="date">
            2021-1-1
          </span>
          <span class="opt" @click.prevent="readNotice(item.id, index)">
            确认
          </span>
        </div>
        <el-empty v-if="commentNoticeList.length === 0" />
      </el-tab-pane>
      <el-tab-pane key="2" tab="点赞">
        <el-empty />
      </el-tab-pane>
      <el-tab-pane key="1" tab="系统通知">
        <el-empty />
      </el-tab-pane>
      <button @click="readNotice(0)">
        全部已读
      </button>
    </el-tabs>
  </div>
</template>
<script>
import { reactive, ref } from 'vue'
import { getNotice, readNotice as readUserNotice } from '@/api/user'

export default {
  name: 'notice',
  setup() {
    const show = ref(false)
    const commentNoticeList = reactive([])
    const likeNoticeList = reactive([])
    const systemNoticeList = reactive([])
    const readNotice = (id) => {
      readUserNotice(id).then((res) => {
        if (res.code !== 200) {
          alert('error')
        } else {
          if (id !== 0) {
            commentNoticeList.splice(
              commentNoticeList.findIndex((e) => e.id === id),
              1
            )
          } else {
            // 全部已读情况
            this.commentNoticeList = []
          }
        }
      })
    }
    getNotice().then((res) => {
      res.data.forEach((element) => {
        element.link = makeLink(element.itemType, element.itemId)
        if (element.type === 4) {
          commentNoticeList.push(element)
        } else if (element.type === 2) {
          likeNoticeList.push(element)
        } else if (element.type === 1) {
          systemNoticeList.push(element)
        } else console.log('unknown')
      })
      show.value = true
    })
    return {
      show,
      readNotice,
      commentNoticeList,
      likeNoticeList,
      systemNoticeList,
    }
  },
}
// 根据资源的类型和id生成一个链接，不带域名
function makeLink(itemType, itemId) {
  switch (itemType) {
    // case 1:
    case 4:
      return '/post/' + itemId
    case 3:
      return '/article/' + itemId
    default:
      return 'https://www.baidu.com'
  }
}
</script>

<style lang="scss" scoped>
#notice {
  max-width: 1000px;
  margin: auto;
  height: 100%;
}
.notice-card {
  display: block;
  min-height: 40px;
  margin-bottom: 10px;
  padding: 5px 10px;
  border-radius: 4px;
  background-color: #a8d8ea;
  &:hover {
    background-color: #a8d8ea;
    color: #40514e;
    > .opt {
      display: inline-block;
    }
  }
  > .title {
    font-size: 1.05rem;
    font-weight: bold;
  }
  > .date {
    margin-right: 10px;
  }
  > .opt {
    display: none;
    &:hover {
      font-weight: bold;
      cursor: pointer;
    }
  }
}
@import '~@/styles/_mixins';
.content {
  @include ellipsis(2);
}
</style>
