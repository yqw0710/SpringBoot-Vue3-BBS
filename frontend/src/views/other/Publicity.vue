<template>
  <el-container id="PublicityPage">
    <el-header>
      <div class="logo">
        <img style="height: 60px" src="@/assets/images/logo.png" />
      </div>
      <div class="menu">
        <el-menu
          text-color="#fff"
          active-text-color="#fff"
          background-color="transparent"
          :default-active="activeIndex"
          mode="horizontal"
        >
          <el-menu-item index="1">最新活动</el-menu-item>
          <el-menu-item index="2">申请试用</el-menu-item>
          <el-menu-item index="3">售后工单</el-menu-item>
          <el-menu-item index="4">使用文档</el-menu-item>
        </el-menu>
        <div class="controller">控制台</div>
      </div>
    </el-header>
    <el-main>
      <div class="part1">
        <div class="part1-info">
          <h2>智能制造解决方案</h2>
          <h3>助力中小企业数字化升级</h3>
          <span>智慧科技 · 引领未来</span>
        </div>
        <div class="part1-list">
          <div v-for="i in 4" :key="i + ':item'" class="part1-list-item">
            <img src="@/assets/images/logo.png" alt="" />
            <span>一致</span>
          </div>
        </div>
      </div>
      <div class="part2">
        <h2>性能强大、安全、稳定的云产品</h2>
        <div class="part2-card-container">
          <div v-for="c in 20" :key="c + 'card'" class="part2-card">
            <img
              style="height: 60px; width: 60px"
              src="@/assets/images/logo.png"
            />
            <h3>Vue{{ c }}</h3>
            <p>
              换行换行换行换行换行换行换行换行换行换行换行换行换行换行换行换行换行换行换行换行换行换行换行换行
            </p>
          </div>
        </div>
      </div>
      <div class="part3"></div>
    </el-main>
    <el-footer>Footer</el-footer>
  </el-container>
</template>

<script>
import { ref } from 'vue'
export default {
  name: 'Publicity',
  setup() {
    const activeIndex = ref('1')

    return { activeIndex }
  },
}
</script>

<style lang="scss" scoped>
$mainBack: #060c40;
$mainBlud: #3366cf;
$mainWhite: #e7e4e4;

#PublicityPage {
  margin: 0;
  min-height: 100vh;
  min-width: 700px;
}
.el-header {
  display: flex;
  align-items: center;
  justify-content: space-between;

  margin: 0;
  padding: 0;

  border: none;
  color: #fff;
  background-color: $mainBack;
  > .logo {
    height: 100%;
    flex: 0 0 200px;
    padding-left: 20px;
  }
  > .menu {
    height: 100%;
    flex: 0 1 auto;
    display: flex;
    > .controller {
      width: 100px;
      line-height: 60px;
      text-align: center;
      background-color: $mainBlud;
      cursor: pointer;
    }
    /* 重写激活样式，用伪元素显示短的下划线 */
    .el-menu-item {
      border: none !important;
    }
    .el-menu.el-menu--horizontal {
      border: none !important;
    }
    .is-active:after {
      content: '';
      position: absolute;
      bottom: 10px;
      left: 30px;
      height: 2px;
      width: 35px;
      background-color: #fff;
    }
  }
}

.el-main {
  padding: 0;
  .part1 {
    height: 400px;
    position: relative;
    background-color: $mainBack;
    .part1-info {
      position: absolute;
      top: 100px;
      left: 150px;
      > h2 {
        color: $mainWhite;
        font-size: 2rem;
        font-weight: normal;
        letter-spacing: 4px;
      }
      > h3 {
        color: $mainWhite;
        font-size: 1.2rem;
        font-weight: normal;
        margin-top: 5px;
        margin-bottom: 50px;
      }
      > span {
        color: $mainWhite;
      }
      &::after {
        content: '';
        position: absolute;
        top: -15px;
        left: 0;
        height: 2px;
        width: 100px;
        background-color: $mainBlud;
      }
    }
    .part1-list {
      display: flex;
      justify-content: space-around;
      align-items: center;
      position: absolute;
      bottom: -50px;
      left: calc(50% - 350px);
      height: 100px;
      width: 700px;
      background-color: #f7f3f3;
      box-shadow: 2px 4px 1px $mainWhite;
      border-radius: 20px;
      .part1-list-item {
        display: flex;
        flex-direction: column;
        text-align: center;
        > img {
          width: 50px;
          height: 50px;
        }
      }
    }
  }
  .part2 {
    min-height: 400px;
    padding: 70px;
    background-color: #fff;

    > h2 {
      text-align: center;
    }
    > .part2-card-container {
      display: flex;
      justify-content: center;
      align-items: center;
      flex-flow: row wrap;
      > .part2-card {
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
        height: 150px;
        width: 50%;
        max-width: 200px;
        padding: 5px;
        > p {
          font-size: 12px;
        }
      }
    }
  }
  .part3 {
    height: 400px;
    background-color: #fff;
  }
}
</style>
