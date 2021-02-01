<template>
  <el-form ref="registerForm" status-icon :rules="rules" :model="form">
    <el-form-item required size="small" label="用户名" prop="username">
      <el-input prefix-icon="el-icon-user"
                required v-model="form.username"
                placeholder="请输入用户名"/>
    </el-form-item>
    <el-form-item required size="small" label="密码" prop="password">
      <el-input required prefix-icon="el-icon-lock"
                v-model="form.password" show-password
                placeholder="请输入密码"/>
    </el-form-item>
    <el-form-item required size="small" label="确认密码" prop="checkPassword">
      <el-input required prefix-icon="el-icon-lock"
                v-model="form.checkPassword" show-password
                placeholder="请再次输入密码"/>
    </el-form-item>
    <label>
      <p style="font-size: 0.85rem">我已同意协议 <a href="#">《balabal》</a> <input
          required type="checkbox"></p>
    </label>
    <button @click="handleSubmit" class="btn login-btn">立即注册</button>
  </el-form>
</template>

<script>
import {reactive, ref} from "vue"
import {useStore} from 'vuex'
import useFormValidator from '@/hooks/Form/useFormValidator'
// TODO 表单校验密码长度和强度以及是否重复
export default {
  name: "RegisterForm",
  setup() {
    const store = useStore()
    const form = reactive({
      username: '',
      password: '',
      checkPassword: '',
    })
    const registerForm = ref(null);
    const rules = {
      username: [{required: true, message: "用户名不能为空"}],
      password: [{required: true, message: "密码不能为空"}],
      checkPassword: [{
        validator: useFormValidator(form, 'PasswordSame'),
        trigger: 'blur'
      }]
    }
    const handleSubmit = () => {
      registerForm.value.validate(async valid => {
        if (valid && await store.dispatch("user/register", form)) {
          setTimeout(() => location.reload(), 100)
        }
      })
    }
    return {form, handleSubmit, rules, registerForm}
  }
}
</script>

<style scoped>

</style>
