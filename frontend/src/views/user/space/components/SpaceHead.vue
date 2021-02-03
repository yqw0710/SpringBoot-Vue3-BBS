<template>
  <div v-if="userinfo !== {}" class="ProfileHeader">
    <!-- 封面图 -->
    <div class="cover">
      <img
        :src="userinfo.coverImg"
        v-default-img="'background'"
        alt="1000*250"
      />
    </div>
    <!-- 用户信息 头像 昵称&性别&个人签名-->
    <div class="userinfo">
      <img :src="userinfo.avatar" v-default-img="'avatar'" alt="avatar" />
      <div class="detail">
        <div>
          <span class="nickname">
            {{ userinfo.nickname }}
          </span>
          <span class="sex" v-if="userinfo.sex === 1 || userinfo.sex === 2">
            <i
              :class="userinfo.sex === 1 ? 'el-icon-male' : 'el-icon-female'"
            />
          </span>
          <span class="point">{{ null }}</span>
        </div>
        <div>{{ userinfo.sign }}</div>
      </div>
    </div>
  </div>
</template>

<script>
import { getUserInfoDigest } from '@/api/user'
import { reactive } from 'vue'
//TODO  错误处理 如用户uid不存在
export default {
  name: 'SpaceHead',
  props: {
    uid: { type: String, default: '-1' },
  },
  // 需要头像
  setup(props) {
    let userinfo = reactive({})
    getUserInfoDigest(props.uid).then((res) => {
      for (let key in res.data) userinfo[key] = res.data[key]
    })
    return { userinfo }
  },
}
</script>

<style lang="scss" scoped>
.ProfileHeader {
  > .cover {
    position: relative;
    max-height: 250px;
    box-shadow: $box-shadow-base;
    > img {
      max-height: 250px;
      width: 100%;
    }
  }
  .userinfo {
    position: relative;
    > img {
      border: 3px solid #ebe0e0;
      position: absolute;
      border-radius: 5px;
      height: 100px;
      left: 10px;
      top: -25px;
    }
    > .detail {
      position: absolute;
      left: 125px;
      top: 5px;
      * {
        margin-right: 10px;
        margin-bottom: 10px;
      }
    }
  }
}
</style>
