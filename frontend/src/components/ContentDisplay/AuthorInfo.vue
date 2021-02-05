<template>
  <div class="author-info">11</div>
</template>

<script>
export default {
  name: 'AuthorInfo',
  props: {
    uid: Number,
  },
}
</script>

<style lang="scss" scoped>
.author-info {
  height: 50px;
  margin-bottom: 10px;
  display: flex;
  align-items: center;
  .user {
    margin: 0 8px;
    display: flex;
    flex-direction: column;
    > span {
      line-height: 25px;
    }
    .nickname {
      font-weight: bold;
      margin-right: 10px;
    }
    .sex {
      margin-right: 5px;
    }
  }
}
</style>
