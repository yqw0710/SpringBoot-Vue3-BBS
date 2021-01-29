<template>
  <div>
    <h2>message</h2>
    <modal v-model:show="dialogVisible"><h1>2</h1></modal>
    <button @click="dialogVisible=true">打开对话框</button>
  </div>
</template>

<script>
import {ref} from "vue";
import Modal from "@/components/Modal";

export default {
  name: "messageBoard",
  components:{Modal},
  setup() {
    const dialogVisible = ref(false);
    return {dialogVisible}
  }
}
</script>

<style scoped>

</style>
