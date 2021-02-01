<template>
  <div class="todo-list">
    <!-- todo 项目，外层容器用于显示背景、圆角边框 -->
    <todo-list-item
      v-for="todo in todos"
      :key="todo.id + ':item'"
      :todo-item="todo"
      @change-state="todo.completed = $event.target.checked"
    />
    <div class="" v-show="todos.length < 1">
      <el-empty :image-size="120" />
    </div>
  </div>
</template>

<script>
import TodoListItem from "./TodoListItem";
export default {
  name: "TodoList",
  components: { TodoListItem },
  props: ["todos"],
};
</script>

<style  scoped>
.todo-list {
  display: grid;
  row-gap: 14px;
  max-height: 250px;
  overflow: auto;
}

</style>
