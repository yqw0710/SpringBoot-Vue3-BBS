<template>
  <div class="PostPreview-item">
    <div class="item-header">
      <a href="#">
        <img
          :src="model.avatar"
          class="item-avatar"
          v-default-img="'avatar'"
          alt="avatar"
        />
      </a>
      <div class="item-header-content">
        <a href="#" class="item-title">
          <span class="item-user">{{ model.nickname }}:</span>
          {{ model.title }}
        </a>
        <el-tag size="mini">标签</el-tag>
        |
        <el-tag size="mini">标签</el-tag>
      </div>
    </div>
    <div class="item-content">{{ model.content }}</div>
    <div class="item-footer">
      <span>👀{{ 1 }}</span>
      <span>👍{{ 11 }}</span>
      <span>💬{{ 0 }}</span>
      <span class="time">2020/2/3</span>
    </div>
  </div>
</template>

<script>
export default {
  name: 'PostPreviewItem',
  props: {
    model: { type: Object, required: true },
  },
  setup() {},
}
</script>

<style lang="scss" scoped>
@import '~@/styles/_mixins';
.PostPreview-item {
  padding: 10px 2px;
  transition: 0.3s;
  border-bottom: 1px solid #cbcbcb;
  &:hover {
    background-color: rgba(0, 0, 0, 0.02);
  }
}
.item-header {
  display: flex;
  align-items: center;
  .item-header-content {
    margin-left: 5px;
    .item-title {
      color: inherit;
      display: block;
      font-size: 1.05rem;
      font-weight: bold;
    }
    .item-user {
      margin: 0 5px;
    }
  }

  .item-avatar {
    height: 40px;
    width: 40px;
    border-radius: 2px;
  }
}

.item-content {
  font-size: 0.95rem;
  margin: 10px 0;
  //padding: 13px 0;
  @include ellipsis(3);
}
.item-footer {
  font-size: 0.9rem;
  > span {
    margin-right: 10px;
  }
  > .time {
    float: right;
  }
}
</style>
