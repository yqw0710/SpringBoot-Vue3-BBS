<template>
  <!-- 添加全局样式 -->
  <div class="app-root" :class="themeClass">
    <div class="app-container">
      <div>
        <span>{{ msg }}</span>
        <button>{{ msg }}</button>
        <form action="">
          <input type="text" />
        </form>
        <el-button >el-button</el-button>
      </div>
      <select v-model="theme">
        <option value="light">Light</option>
        <option value="dark">dark</option>
      </select>
    </div>
  </div>
</template>

<script>
import { computed, ref } from "vue";
export default {
  name: "ThemePreview01",
  setup() {
    const theme = ref("light");
    const show = ref(true);
    const msg = "Dynamic Themes";
    const themeClass = computed(() => {
      return `theme-${theme.value}`;
    });
    return { theme, show, msg, themeClass };
  },
};
</script>

<style lang="scss" scoped>
@import "@/styles/theme";
@import "@/styles/_mixins";

.app-container {
  @include mytheme();
  @include xuanting();
  select{
    box-shadow: $box-shadow-base;
  }
}
</style>
