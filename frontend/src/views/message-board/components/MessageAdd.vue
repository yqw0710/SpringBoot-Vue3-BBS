<template>
  <div class="MessageBoard-add">
    <!-- 文本输入框-->
    <el-input
      type="textarea"
      :clearable="true"
      :autosize="{ minRows: 4, maxRows: 6 }"
      placeholder="请留下和谐友善的言论哦"
      v-model="form.content"
    />
    <!-- 表情功能，提交按钮-->
    <div class="toolbar">
      <EmotionBox
        class="emotions"
        @emotion-add="(val) => (form.content += val)"
      />
      <div class="ops">
        <el-input
          v-model="form.nickname"
          style="width: 140px"
          maxlength="10"
          prefix-icon="el-icon-user"
          size="small"
          show-word-limit
          placeholder="输入昵称"
        />
        <button
          @click="handleSubmit"
          :disabled="!form.content || !form.nickname"
          class="btn"
        >
          提交留言
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive } from 'vue'
import EmotionBox from '@/components/EmotionBox'
import { postMessage } from '@/api/message.js'
import { useStore } from 'vuex'

// 如果输入框的内容不够，让按钮禁用
export default {
  name: 'MessageAdd',
  components: { EmotionBox },
  props: {
    // 要对留言回复的那条留言的id
    toId: { type: Number, default: 0 },
    // 要对留言回复的那条留言的发表人昵称
    toName: { type: String, default: null },
    // 要对留言回复的那条留言所在的父级留言的id
    // 对父级留言则,2个相同
    parentId: { type: Number, default: 0 },
  },
  setup(props, { emit }) {
    let form = reactive({
      content: '',
      nickname: useStore().getters.nickname || '',
      parentId: props.parentId,
      toName: props.toName,
      toId: props.toId,
    })
    const handleSubmit = () => {
      postMessage(form).then((res) => {
        emit('message-add', true)
      })
    }
    return { handleSubmit, form }
  },
}
</script>

<style lang="scss" scoped>
.MessageBoard-add {
  .toolbar {
    margin-top: 15px;
    height: 40px;

    .ops {
      float: right;

      button {
        margin-left: 5px;
        border-radius: 5px;
        color: white;
        height: 33px;

        &:disabled {
          background-color: #87939a;
        }
      }
    }

    .emotions {
      float: left;
    }
  }
}
</style>
