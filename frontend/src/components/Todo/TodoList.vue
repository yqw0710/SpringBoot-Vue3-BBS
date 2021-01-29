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
.todo-list::-webkit-scrollbar {
  width: 10px; /*高宽分别对应横竖滚动条的尺寸*/
}
.todo-list::-webkit-scrollbar-thumb {
  /*滚动条里面小方块*/
  border-radius: 10px;
  box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
  background: #9099f6;
}
.todo-list::-webkit-scrollbar-track {
  /*滚动条里面轨道*/
  box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.05);
  border-radius: 5px;
  background: #ededed;
}
</style>