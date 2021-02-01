<template>
  <div class="MessageBoard-list-item">
    <div class="user">
      <img :src="model.avatar" class="avatar" v-default-img="'avatar'" />
      <span class="nickname">{{ model.nickname }}</span>
      <el-tag effect="dark" :type="isUser ? '' : 'success'" size="mini">{{
        isUser ? "用户" : "游客"
      }}</el-tag>
      <span class="os">{{ model.os }}</span>
      <span class="browser">{{ model.browser }}</span>
    </div>
    <div class="content">
      <span class="aite" v-if="model.toName">@{{ model.toName }}:</span>
      <span v-html="content"></span>
    </div>
    <div class="footer">
      <span class="reply-btn">回复TA</span>
      <span class="time">{{ model.gmtCreate }}</span>
      <span class="reply-count" v-if="model.childrenCount > 0"
        >共{{ model.childrenCount }}条回复</span
      >
    </div>
    <div class="children" v-if="parent">
      <MessageListItem
        v-for="item in model.children"
        :model="item"
        :key="'c' + item.id"
        :parent="false"
      />
    </div>
  </div>
</template>

<script>
import { computed } from "vue";
import { useProcessEmotion } from "@/hooks/Emotion/useEmotions";
export default {
  name: "MessageListItem",
  props: {
    model: { type: Object, required: true },
    parent: { type: Boolean, default: false },
  },
  setup(props) {
    const model = props.model;
    const isParent = props.parent;
    const isUser = computed(() => {
      return model.userId > 0;
    });
    const content = computed(() => {
      return useProcessEmotion(model.content);
    });
    return { model, isParent, isUser, content };
  },
};
</script>

<style lang="scss" scoped>
.MessageBoard-list-item {
  min-height: 90px;
  position: relative;
  margin-top: 10px;
}
.user {
  display: flex;
  overflow: hidden;
  white-space: nowrap; /*强制span不换行*/
  // border:1px solid green;
  font-size: 0.9rem;
  > * {
    margin-right: 10px;
    font-size: 12px;
  }
  .nickname {
    margin-left: 10px;
    font-size: 14px;
  }
}
.content {
  margin-left: 60px; /* img 40px margin total 20px 8*/
  margin-top: -10px;
  margin-bottom: 10px;
  > .aite {
    color: #4949fc;
    margin-right: 5px;
  }
}
.footer {
  height: 30px;
  line-height: 30px;
  margin-left: 60px;
  > * {
    margin-right: 10px;
    font-size: 12px;
  }
  > .reply-btn {
    padding: 3px;
    &:hover {
      cursor: pointer;
      border-radius: 5px;
      color: gray;
      background-color: #cecee0;
    }
  }
  > .reply-count {
    float: right;
    // color: blueviolet;
    margin-right: 10px;
  }
}
.children {
  margin-top: 10px;
  margin-left: 60px;
  transition: 0.5s;
  border-bottom: 1px solid #d1d1d1;
}
</style>
