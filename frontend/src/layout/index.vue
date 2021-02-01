<template>
  <div id="app-root" :class="'theme-'+theme">
    <div class="app-wrapper">
      <el-backtop/>
      <nav-bar/>
      <app-main/>
      <right-panel>
        <h2>网页设置(test)</h2>
        <hr>
        <el-switch
            v-model="theme"
            inactive-text="日间模式"
            inactive-value="light"
            inactive-color="#D7E7E7"
            active-value="dark"
            active-text="夜间模式"
            active-color="#4D4D4D"
        >
        </el-switch>
        <hr>
      </right-panel>
      <modal v-model:show="dialogVisible">
        <login/><!-- 还不会全局注册，会了在来 -->
      </modal>
    </div>
  </div>
</template>

<script>
import NavBar from "@/layout/components/NavBar";
import AppMain from "@/layout/components/AppMain"
import RightPanel from "@/components/RightPanel"
import Modal from "@/components/Modal";
import Login from "@/components/Login";
import {computed, provide, ref} from "vue";
import {useStore} from "vuex";

export default {
  name: "index",
  components: {NavBar, AppMain, RightPanel, Modal, Login},
  setup() {
    const store = useStore();
    store.commit("user/loadInfo");
    const dialogVisible = ref(false);
    provide('showLogin', dialogVisible);
    const theme = computed({
      get: () => store.getters.theme,
      set: val => store.commit('settings/changeTheme', val)
    });
    return {theme, dialogVisible}
  }
}
</script>

<style lang="scss">
@import "@/styles/theme";

.app-wrapper {
  @include mytheme();
  position: relative;
  //height: 100%;
  min-height: 100vh;
  width: 100%;
  min-width: 1000px;
}
</style>
