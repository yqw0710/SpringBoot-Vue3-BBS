<template>
  <el-form ref="loginForm" :rules="rules" :model="form">
    <el-form-item required size="small" prop="username">
      <el-input
        required
        v-model="form.username"
        prefix-icon="el-icon-user"
        placeholder="请输入用户名"
      />
    </el-form-item>
    <el-form-item required size="small" prop="password">
      <el-input
        required
        prefix-icon="el-icon-lock"
        v-model="form.password"
        show-password
        placeholder="请输入密码"
      />
    </el-form-item>
    <div class="login-group">
      <div>
        <a class="login-email" href="#">邮箱登录</a>
        <a class="forget-password" href="#">忘记密码？</a>
      </div>
      <button class="btn login-btn" @click.prevent="handleSubmit()">
        登录
      </button>
    </div>
    <third-party-login />
    <p style="color: gray; padding: 5px">
      登录注册即遵守该网站协议
      <a href="#">《balabala》</a>
    </p>
  </el-form>
</template>

<script>
import { reactive, ref } from 'vue'
import { useStore } from 'vuex'
import ThirdPartyLogin from '@/components/Login/ThirdPartyLogin'

export default {
  name: 'LoginForm',
  components: {
    ThirdPartyLogin,
  },
  setup() {
    const store = useStore()
    const form = reactive({
      username: '',
      password: '',
    })
    const loginForm = ref(null)
    const rules = {
      username: [{ required: true, message: null }],
      password: [{ required: true, message: null }],
    }
    const handleSubmit = () => {
      loginForm.value.validate(async (valid) => {
        if (valid && (await store.dispatch('user/login', form))) {
          setTimeout(() => location.reload(), 100)
        }
      })
    }
    return { form, handleSubmit, rules, loginForm }
  },
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
}
</style>
