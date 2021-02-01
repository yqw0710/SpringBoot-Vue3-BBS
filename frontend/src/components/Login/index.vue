<template>
  <div class="login">
    <el-tabs v-model="activeName" :stretch="true">
      <el-tab-pane name="login">
        <template #label>
          <span><i class="el-icon-user-solid"/>用户登录</span>
        </template>
        <LoginForm/>
      </el-tab-pane>
      <el-tab-pane name="register">
        <template #label>
          <span><i class="el-icon-plus"/>账号注册</span>
        </template>
        <RegisterForm/>
      </el-tab-pane>
      <el-tab-pane name="qr-code">
        <template #label>
          <span><i class="el-icon-camera"/>扫码登录</span>
        </template>
        扫码。。。
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import {ref} from "vue";
import LoginForm from "@/components/Login/LoginForm";
import RegisterForm from "@/components/Login/RegisterForm";

export default {
  name: "index",
  components: {
    LoginForm, RegisterForm
  },
  setup() {
    const activeName = ref('login')
    return {activeName}
  }
}
</script>

<style lang="scss">
.login {
  min-width: 350px;
  margin: 0 5px;
}

.login-btn {
  width: 100%;
  color: white;
  border-radius: 5px;
  margin-top: 10px;
  justify-content: center;
}
</style>
