<template>
  <div class="home">
    <!--    <h1 v-for="i in 33" :key="'key1'+i">{{ i }}</h1>-->
    <modal v-model:show="dialogVisible">
      <login/>
    </modal>

    <button @click="dialogVisible=true">打开对话框</button>
    <Todo/>
  </div>
</template>

<script>
import Todo from "@/components/Todo";
import Modal from "@/components/Modal";
import Login from "@/components/Login";


import {getCurrentInstance, ref} from "vue";

export default {
  name: "Home",
  components: {Todo, Modal, Login},
  setup(props, context) {
    const {ctx} = getCurrentInstance()
    console.log(ctx);
    const dialogVisible = ref(false);
    return {dialogVisible}
  }
};
</script>
