<template>
  <div class="comment-item">
    <div class="user">
      <img
        :src="model.fromAvatar"
        class="avatar"
        alt=""
        v-default-img="'avatar'"
      />
      <span class="nickname">{{ model.fromName }}</span>
      <span class="os">{{ model.gmtCreate }}</span>
    </div>
    <div class="content">
      <span class="aite" v-if="model.toName">@{{ model.toName }}:</span>
      <span v-html="model.content"></span>
    </div>
    <div class="opts">
      <span class="opt opt-like">👍{{ model.likes }}</span>
      <span class="opt opt-unlike">👎</span>
    </div>
    <div class="children"></div>
  </div>
</template>

<script>
export default {
  name: 'CommentItem',
  props: { model: Object },
}
</script>

<style lang="scss" scoped>
.comment-item {
  min-height: 90px;
  position: relative;
  margin-top: 10px;
}
.user {
  display: flex;
  overflow: hidden;
  white-space: nowrap; /*强制span不换行*/
  font-size: 0.9rem;

  .nickname {
    margin-left: 20px;
    margin-right: 20px;
    font-weight: bold;
    font-size: 16px;
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

.opts{
  margin-left: 60px;
  font-size: 12px;
  >.opt{
    cursor: pointer;
    letter-spacing: 5px;
    margin-right: 12px;
  }
}
</style>
