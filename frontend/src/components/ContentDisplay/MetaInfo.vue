<template>
  <div className="meta-info">
    <!--    <div v-if="tagList" class="tags">
      标签
      <a-icon type="tags"/>
      :
      <el-tag v-for="(i,index) in tagList" :key="i+'tag'+index+1" :color="i.color">{{ i.tag }}</el-tag>
    </div>-->
    <div className="other">
      <span className="date">2021-1-1</span>
      <span className="pv">
        <i className="el-icon-view"></i>
        <span className="val">11</span>
      </span>
      <span v-if="true" className="likes">
        <i className="el-icon-thumb"></i>
        22
      </span>
      <span v-if="true">
        <i className="el-icon-message"></i>
        <span className="val">{{ comments }}</span>
      </span>
      <span>
        <i className="el-icon-headset"></i>
        <span className="val">13</span>
      </span>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MetaInfo',
  props: {
    tags: String || Array,
    date: String,
    pv: Number,
    comments: Number,
    likes: Number,
  },
}
</script>

<style lang="scss" scoped>
.meta-info {
  // border: 1px solid red;
  height: 40px;
  display: flex;
  justify-content: space-between;
  align-items: center;

  .val {
    margin: 0 6px;
  }

  .date {
    margin-right: 5px;
  }
}
</style>
