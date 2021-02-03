<template>
  <div>
    <el-tabs tab-position="left">
      <el-tab-pane label="我的头像">
        <update-avatar :avatar="avatar" @update-avatar="refreshAvatar" />
      </el-tab-pane>
      <el-tab-pane label="个人信息">
        <update-info />
      </el-tab-pane>
      <el-tab-pane label="账号设置">角色管理</el-tab-pane>
      <el-tab-pane label="新消息通知">null</el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import UpdateAvatar from './components/UpdateAvatar'
import UpdateInfo from './components/UpdateInfo'
import { useStore } from 'vuex'

export default {
  name: 'profile',
  components: { UpdateAvatar, UpdateInfo },
  setup() {
    let store = useStore()
    const avatar = store.getters.avatar
    const refreshAvatar = (data) => {
      store.commit('user/updateAvatar', data)
    }
    return { avatar, refreshAvatar }
  },
}
</script>

<style lang="scss" scoped></style>
