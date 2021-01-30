<template>
  <el-form ref="login-form" :model="form">
    <el-form-item size="small">
      <el-input
          prefix-icon="el-icon-user"
          v-model="form.username"
          placeholder="请输入用户名"/>
    </el-form-item>
    <el-form-item size="small">
      <el-input prefix-icon="el-icon-lock"
                v-model="form.password" show-password
                placeholder="请输入密码"/>
    </el-form-item>
    <div class="login-group">
      <div>
        <a class="login-email" href="#">邮箱登录</a>
        <a class="forget-password" href="#">忘记密码？</a>
      </div>
      <button class="btn loginBtn">登录</button>
    </div>
    <third-party-login/>
    <p style="color: gray;padding: 5px">登录注册即遵守该网站协议 <a href="#">《balabala》</a>
    </p>
  </el-form>
</template>

<script>
import {reactive} from "vue"
import ThirdPartyLogin from "@/components/Login/ThirdPartyLogin";

export default {
  name: "LoginForm",
  components: {
    ThirdPartyLogin
  },
  setup() {
    const form = reactive({
      username: '',
      password: '',
    })
    return {form}
  }
}
</script>

<style lang="scss" scoped>
.login-group {
  .login-email {
    float: left;
    font-size: 0.85rem;
  }

  .forget-password {
    font-size: 0.85rem;
    float: right;
  }

  .loginBtn {
    width: 100%;
    color: white;
    border-radius: 5px;
    margin: 10px;
    justify-content: center;
  }
}
</style>
