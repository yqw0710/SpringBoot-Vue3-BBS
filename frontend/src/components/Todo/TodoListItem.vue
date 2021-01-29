<template>
  <div
    class="todo-item"
    :style="
      todoItem.completed ? 'font-style:italic;text-decoration:line-through' : ''
    "
  >
    <label>
      <!-- @click可以直接todoItem.completed!=$event.target.checked -->
      <input
        type="checkbox"
        :checked="todoItem.completed"
        @click="$emit('change-state', $event)"
      />
      {{ todoItem.content }}
      <span class="check-button"></span>
    </label>
  </div>
</template>

<script>
export default {
  name: 'TodoListItem',
  props: ['todoItem'],
}
</script>

<style scoped>
.todo-item {
  padding: 16px;
  border-radius: 8px;
  color: #626262;
  background-color: white;
}

.todo-item label {
  position: relative;
  display: flex;
  align-items: center;
}

.todo-item label span.check-button {
  position: absolute;
  top: 0;
}

.todo-item label span.check-button::before,
.todo-item label span.check-button::after {
  content: '';
  display: block;
  position: absolute;
  width: 18px;
  height: 18px;
  border-radius: 50%;
}
/** 展示外圈细紫色的border */
.todo-item label span.check-button::before {
  border: 1px solid #b382f9;
}
/* 展示内圈实心圆 主要就是缩小*/
.todo-item label span.check-button::after {
  transition: 0.4s;
  background: #b382f9;
  transform: translate(1px, 1px) scale(0.8);
  opacity: 0;
}
.todo-item input {
  margin-right: 16px;
  opacity: 0;
}
/*用兄弟选择器，让复选框选中后改变旁边的span的after，让他显示 */
.todo-item input:checked + span.check-button::after {
  opacity: 1;
}
</style>