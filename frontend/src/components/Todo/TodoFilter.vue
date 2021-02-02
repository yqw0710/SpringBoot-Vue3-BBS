<template>
  <!-- 过滤选项 -->
  <div class="filters">
    <span
      v-for="filter in filters"
      :key="filter.value"
      @click="$emit('change-filter', filter.value)"
      class="filter"
      :class="{ active: selected === filter.value }"
    >
      {{ filter.label }}
    </span>
  </div>
</template>

<script>
export default {
  name: 'TodoFilter',
  props: ['selected'],
  setup() {
    const filters = [
      { label: '全部', value: 'all' },
      { label: '已完成', value: 'done' },
      { label: '未完成', value: 'todo' },
    ]

    return { filters }
  },
}
</script>

<style scoped>
.filters {
  display: flex;
  margin: 24px 2px;
  padding-bottom: 5px;
  border-bottom: 1px solid rgb(218, 215, 215);

  color: #c0c2ce;
  font-size: 14px;
}

.filters .filter {
  margin-right: 14px;
  transition: 0.8s;
  cursor: pointer;
}

/* 选中状态 让字体颜色更深,字体缩放1.2倍*/
.filters .filter.active {
  color: #6b729c;
  transform: scale(1.2);
}
</style>
