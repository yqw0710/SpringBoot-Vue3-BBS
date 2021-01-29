<template>
  <!-- 输入框和按钮 -->
  <div class="input-add">
    <input
      type="text"
      name="todo"
      v-model="todoContent"
      placeholder="添加任务"
      @keyup.enter="emitAddTodo"
    />
    <button @click="emitAddTodo">
      <!-- 按钮图标 -->
      <i class="plus" />
    </button>
  </div>
</template>

<script>
import { ref } from 'vue'
export default {
  name: 'TodoAdd',
  setup(props, context) {
    return useEmitAddTodo(props.tid, context.emit)
  },
}
// ref用来处理基本类型数据, reactive用来处理对象(递归深度响应式)
// ref的数据操作: 在js中要.value, 在模板中不需要(内部解析模板时会自动添加.value)
/* 就属于它的 就放在一起得了*/
function useEmitAddTodo(tid, emit) {
  const todoContent = ref('')
  const emitAddTodo = () => {
    const todo = {
      id: tid,
      content: todoContent.value,
      completed: false,
    }
    emit('add-todo', todo)
    todoContent.value = ''
  }
  return {
    todoContent,
    emitAddTodo,
  }
}
</script>

<style scoped>
.input-add {
  position: relative;
  display: flex;
  align-items: center;
}
.input-add input {
  width: 100%;
  height: 46px;
  padding: 0 16px;

  border: none;
  outline: none;
  box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.06);
  border-radius: 40px;

  font-size: 16px;
  color: #626262;
}
.input-add button {
  position: absolute;
  right: 0;
  width: 40px;
  height: 40px;

  border: none;
  outline: none;
  border-radius: 50%;

  color: white;
  cursor: pointer;
  background: linear-gradient(#c0a5f3, #7f95f7);
}
.plus {
  display: block;
  width: 100%;
  height: 100%;
  /* 生成横着的线和竖着的线:配合size来控制大小 */
  background: linear-gradient(#fff, #fff), linear-gradient(#fff, #fff);
  background-size: 50% 2px, 2px 50%;
  background-position: center;
  background-repeat: no-repeat;
}
</style>
