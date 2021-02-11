<template>
  <div class="PostPreview-item">
    <div class="item-header">
      <router-link :to="'/space/' + model.uid">
        <img
          :src="model.avatar"
          class="item-avatar"
          v-default-img="'avatar'"
          alt="avatar"
        />
      </router-link>
      <div class="item-header-content">
        <router-link :to="'/post/' + model.id" class="item-title">
          <span class="item-user">{{ model.nickname }}:</span>
          {{ model.title }}
        </router-link>
        <el-tag size="mini" effect="dark">{{ model.category }}</el-tag>
      </div>
    </div>
    <div class="item-content">{{ model.content }}</div>
    <div class="item-footer">
      <span>👁{{ model.pv }}</span>
      <span>👍{{ model.likes }}</span>
      <span>💬{{ model.comments }}</span>
      <span class="menu">...</span>
      <span class="time">{{ model.gmtCreate.split(' ')[0] }}</span>
    </div>
  </div>
</template>

<script>
export default {
  name: 'PostPreviewItem',
  props: {
    model: { type: Object, required: true },
  },
  setup() {
    return {}
  },
}
</script>

<style lang="scss" scoped>
@import '~@/styles/_mixins';
.PostPreview-item {
  padding: 10px 15px;
  transition: 0.3s;
  border-bottom: 1px solid #efefef;
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
  > .menu {
    cursor: pointer;
    color: #7b7b7b;
    font-size: 1.1rem;
    height: 20px;
    line-height: 10px;
    float: right;
  }
}
</style>
