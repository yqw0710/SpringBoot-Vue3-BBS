<template>
  <div class="home">
    <h1 v-for="i in 33" :key="'key1'+i">{{ i }}</h1>
    <modal v-model:show="dialogVisible"><h1>1</h1></modal>
    <button @click="dialogVisible=true">打开对话框</button>
    <!--    <el-dialog
            title="登录/注册"
            v-model="dialogVisible"
            width="30%"
            :before-close="handleClose">
          <span>这是一段信息</span>
          <template #footer>
            <span class="dialog-footer">
              <el-button @click="dialogVisible = false">取 消</el-button>
              <el-button type="primary"
                         @click="dialogVisible = false">确 定</el-button>
            </span>
          </template>
        </el-dialog>-->
    <!--    <Todo />-->
  </div>
</template>

<script>
import Todo from "@/components/Todo";
import Modal from "@/components/Modal";

import {getCurrentInstance, ref} from "vue";

export default {
  name: "Home",
  components: {Todo, Modal},
  setup(props,context) {
    const { ctx } = getCurrentInstance()
    console.log(props)
    console.log(context)
    console.log(ctx);
    const dialogVisible = ref(false);
    return {dialogVisible}
  }
};
</script>
