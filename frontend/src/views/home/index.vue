<template>
  <div id="index">
    <!--    帖子列表展示-->
    <el-tabs style="width: 65%" v-model="activeTab" :stretch="false">
      <el-tab-pane
        v-for="item in postCategory"
        :key="item.value"
        :label="item.name"
        :name="item.value"
        lazy
      >
        <post-preview :category="activeTab" />
      </el-tab-pane>
    </el-tabs>
    <div class="right" style="position: relative">
      <ul class="icons" style="list-style: none">
        <li>
          <i class="el-icon-platform-eleme"></i>
          <span>el-icon</span>
        </li>
        <li>
          <i class="el-icon-eleme"></i>
          <span>el-icon</span>
        </li>
        <li>
          <i class="el-icon-user-solid"></i>
          <span>el-icon</span>
        </li>
        <li>
          <i class="el-icon-user"></i>
          <span>el-icon</span>
        </li>
        <li>
          <i class="el-icon-star-on"></i>
          <span>el-icon</span>
        </li>
        <li>
          <i class="el-icon-star-off"></i>
          <span>el-icon</span>
        </li>
        <li>
          <i class="el-icon-phone"></i>
          <span>el-icon</span>
        </li>
        <li>
          <i class="el-icon-phone-outline"></i>
          <span>el-icon</span>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import PostPreview from '@/views/home/components/PostPreview'
import { postCategory } from '@/views/home/components/usePost'
import { ref } from 'vue'
const url = 'ws://localhost:8080/api/ws'

export default {
  name: 'Home',
  components: { PostPreview },
  setup() {
    const activeTab = ref('default')
    return { activeTab, postCategory }
  },
}
</script>

<style lang="scss" scoped>
#index {
  display: flex;
}

.right {
  flex: 1;
  > h1 {
    text-align: center;
  }
}
.icons {
  padding-left: 5px;
  cursor: pointer;
  position: sticky;
  top: 0;
  > li {
    width: 25%;
    height: 100px;
    padding-top: 15px;
    border: 1px solid #e7e1e1;
    float: left;
    text-align: center;
    > i {
      font-size: 3rem;
    }
    > span {
      display: inline-block;
    }
    &:hover {
      background-color: #e3e3e3;
    }
  }
}
</style>
