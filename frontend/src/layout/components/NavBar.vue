<template>
  <header>
    <div class="navbar">
      <a style="height: 60px" href="#">
        <img height="60" alt="" src="~@/assets/images/logo.png" />
      </a>
      <!-- 动态渲染主页菜单-->
      <el-menu
        background-color="transparent"
        mode="horizontal"
        :default-active="activeIndex"
        :router="true"
      >
        <el-menu-item
          v-for="menu in menus"
          :key="menu.path"
          :disabled="!menu.enable"
          :index="menu.path"
        >
          {{ menu.value }}
        </el-menu-item>
      </el-menu>
      <div class="search">
        <input type="text" name="search" placeholder="search" />
        <button><i class="el-icon-search" /></button>
      </div>
      <button class="btn btn-post">
        <i class="el-icon-edit" />
        发帖
      </button>
      <div>
        <el-menu
          v-if="logined"
          :default-active="activeIndex"
          mode="horizontal"
          :router="true"
          background-color="transparent"
        >
          <el-menu-item index="notice"><i class="el-icon-bell" /></el-menu-item>
          <el-menu-item index="talk">
            <i class="el-icon-chat-dot-round" />
          </el-menu-item>
          <el-submenu :popper-class="'self-menu-pop'" index="2">
            <template #title>
              <img class="avatar" :src="avatarUrl" alt="avatar" />
            </template>
            <el-menu-item index="space">个人中心</el-menu-item>
            <el-menu-item index="profile">查看信息</el-menu-item>
            <el-menu-item index="todo">TODO</el-menu-item>
          </el-submenu>
        </el-menu>
        <a v-else href="#" @click="showLogin = true">登录/注册</a>
      </div>
    </div>
  </header>
</template>

<script>
import { inject, ref } from 'vue'
import useMenus from '@/hooks/layout/useMenus'
import { useStore } from 'vuex'
import { useRoute } from 'vue-router'

export default {
  name: 'NavBar',
  setup() {
    const { menus } = useMenus()
    const store = useStore()
    const activeIndex = ref(useRoute().path)
    const showLogin = inject('showLogin')
    const logined = store.getters.logined
    const avatarUrl = store.getters.avatar
    return { activeIndex, menus, logined, showLogin, avatarUrl }
  },
}
</script>

<style lang="scss" scoped>
header {
  z-index: 100;
  position: relative;

  min-width: 1000px;
  box-shadow: 0 1px 3px 0 #dccfcf;
}

.navbar {
  display: flex;
  align-items: center;
  justify-content: space-around;

  width: auto;
  height: 60px;
  margin: 0 auto;
  max-width: $container-max-width;
}

// 搜索功能
.search {
  width: 150px;
  position: relative;

  > input {
    width: 100%;
    height: 35px;
    padding: 0 20px 0 10px;

    border: none;
    outline: none;
    border-radius: 40px;
    background-color: #f1f1f1;

    &:focus + button {
      transform: rotate(90deg);
      right: -20px;
    }
  }

  > button {
    position: absolute;
    right: 0;
    width: 35px;
    height: 35px;

    border: none;
    outline: none;
    border-radius: 50%;
    font-size: 1.05rem;
    cursor: pointer;
    background-color: $primary-color;
    transition: 0.3s;

    &:hover {
      background: linear-gradient($primary-color, #036eb8);
    }
  }
}

// 发帖按钮
.btn-post {
  color: #f6f6f6;
  height: 35px;
  font-weight: 500;
  padding: 0 20px;
}
</style>
