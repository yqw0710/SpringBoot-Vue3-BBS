<template>
  <div v-drag class="todo-container">
    <!-- 标题 -->
    <h1>To Do 待办事项！</h1>
    <todo-add :tid="todos.length" @add-todo="addTodo" />
    <todo-filter :selected="filter" @change-filter="filter = $event" />
    <todo-list :todos="filteredTodos" />
  </div>
</template>

<script>
/*
学习资源：https://www.zxuqian.cn/docs/videos/vue/develop-a-todo-app-using-vue3
*/
import { defineComponent } from 'vue'
import TodoAdd from './TodoAdd'
import TodoFilter from './TodoFilter'
import TodoList from './TodoList'
import useTodos from '@/hooks/Todo/useTodos'
import useFilteredTodos from '@/hooks/Todo/useFilteredTodos'

export default defineComponent({
  name: 'Todo',
  components: { TodoAdd, TodoFilter, TodoList },
  props: ['bespread'],
  setup() {
    const { todos, addTodo } = useTodos()
    const { filter, filteredTodos } = useFilteredTodos(todos)
    return { todos, addTodo, filteredTodos, filter }
  },
})
</script>

<style scoped>
.todo-container {
  min-width: 80%;
  max-width: 350px;
  padding: 14px 28px;
  position: absolute;

  border-radius: 24px;
  box-shadow: 0 0 24px rgba(0, 0, 0, 0.15);
  background-color: rgb(245, 246, 252); /* 淡紫灰色*/
}

h1 {
  margin: 16px 0;
  font-size: 28px;
  color: #414873;
}
</style>
