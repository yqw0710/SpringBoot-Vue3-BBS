<template>
  <header>
    <div class="navbar">
      <a style="height: 60px" href="#">
        <img height="60"
             src="~@/assets/images/logo.png"
             alt=""></a>
      <el-menu
          background-color="transparent"
          :default-active="activeIndex"
          mode="horizontal"
          :router="true"
      >
        <el-menu-item v-for="menu in menus" :key="menu.path"
                      :disabled="!menu.enable" :index="menu.path">
          {{ menu.value }}
        </el-menu-item>
      </el-menu>
      <div class="search">
        <input
            type="text"
            name="search"
            placeholder="search"
        >
        <button>
          <i class="el-icon-search"/>
        </button>
      </div>
      <button class="btn btn-post">发帖</button>
      <div class="self">
        <el-menu
            v-if="true"
            background-color="transparent"
            :default-active="activeIndex"
            mode="horizontal"
        >
          <el-menu-item index="8"><i class="el-icon-bell"/></el-menu-item>
          <el-menu-item index="9"><i class="el-icon-chat-dot-round"/>
          </el-menu-item>

          <el-submenu :popper-class="'self-menu-pop'" index="2">
            <template #title>
              <img class="avatar"
                   src="~@/assets/images/dio.jpg"
                   alt="avatar">
            </template>
            <el-menu-item index="2-1">选项1</el-menu-item>
            <el-menu-item index="2-2">选项2</el-menu-item>
            <el-menu-item index="2-3">选项3</el-menu-item>
          </el-submenu>
        </el-menu>
        <a href="#" v-else>登录/注册</a>
      </div>
    </div>
  </header>
</template>

<script>
import {ref} from "vue";
import useMenus from "@/hooks/layout/useMenus"

export default {
  name: "NavBar",
  setup() {
    const activeIndex = ref("1");
    const {menus} = useMenus();
    return {activeIndex, menus};
  },
}
</script>

<style lang="scss" scoped>

header {
  z-index: 100;
  position: relative;
  overflow: hidden;

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

.search {
  width: 150px;
  position: relative;

  > input {
    width: 100%;
    height: 40px;
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
    width: 40px;
    height: 40px;

    border: none;
    outline: none;
    border-radius: 50%;
    font-size: 1.05rem;
    cursor: pointer;
    background-color: $primary-color;
    transition: .3s;

    &:hover {
      background: linear-gradient($primary-color, #036EB8);
    }
  }
}

.btn-post {
  color: #f6f6f6;
  font-weight: 500;
  padding: 0 20px;
}

</style>
