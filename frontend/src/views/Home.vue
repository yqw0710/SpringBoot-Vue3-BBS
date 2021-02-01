<template>
  <div class="home">
    <!--    <h1 v-for="i in 33" :key="'key1'+i">{{ i }}</h1>-->
        <Todo/>
  </div>
</template>

<script>
import Todo from "@/components/Todo";
import Modal from "@/components/Modal";
import Login from "@/components/Login";



export default {
  name: "Home",
  components: {Todo, Modal, Login},
  setup() {
    return {}
  }
};
</script>
