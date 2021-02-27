<template>
  <div v-if="loaded" class="author-info">
    <router-link :to="'/space/' + uid">
      <img :src="digest.avatar" class="avatar" alt="" />
    </router-link>
    <div class="user">
      <span>
        <span class="nickname">
          <a :href="'/space/' + digest.uid">{{ digest.nickname }}</a>
        </span>
        <span class="sex"></span>
        <span class="talk">📧 发送消息</span>
      </span>
      <span class="sign">{{ digest.sign }}</span>
    </div>
  </div>
</template>

<script>
import { getUserInfoDigest } from '@/api/user'
import { reactive, ref } from 'vue'

export default {
  name: 'AuthorInfo',
  props: {
    uid: Number,
  },
  setup(props) {
    const digest = reactive({})
    const loaded = ref(false)
    getUserInfoDigest(props.uid).then((res) => {
      digest.avatar = res.data.avatar
      digest.coverImg = res.data.avatar
      digest.nickname = res.data.nickname
      digest.sign = res.data.sign
      loaded.value = true
    })
    return { digest, loaded }
  },
}
</script>

<style lang="scss" scoped>
.author-info {
  height: 50px;
  margin-bottom: 10px;
  display: flex;
  align-items: center;
  .user {
    margin: 0 8px;
    display: flex;
    flex-direction: column;
    > span {
      line-height: 25px;
    }
    .nickname {
      font-weight: bold;
      margin-right: 10px;
    }
    .sex {
      margin-right: 5px;
    }
  }
}
.talk {
  cursor: pointer;
  padding: 3px;
  border-radius: 3px;
  &:hover {
    background-color: #bababa;
  }
}
</style>
