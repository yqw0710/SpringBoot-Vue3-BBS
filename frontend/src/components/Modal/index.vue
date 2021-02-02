<template>
  <teleport to="#app" :disabled="false">
    <div v-if="show" class="modal">
      <div class="modal-wrapper">
        <div class="modal-header">
          <button @click="$emit('update:show', false)" class="modal-close-btn">
            <i class="el-icon-close" />
          </button>
        </div>
        <div class="modal-body">
          <slot />
        </div>
      </div>
    </div>
  </teleport>
</template>

<script>
// 自定义模态框 use  <modal v-model:show="true/false"> xxxx </modal>
export default {
  name: 'Modal',
  props: {
    show: {
      type: Boolean,
      default: false,
    },
  },
  emits: ['update:show'],
}
</script>

<style lang="scss" scoped>
.modal {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 2002;
  background-color: rgba(0, 0, 0, 0.3);
}

.modal-wrapper {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  min-width: 250px;
  max-width: 100%;
  border-radius: 5px;
  background-color: #ececec;
  padding: 10px;
}

.modal-close-btn {
  position: absolute;
  top: -30px;
  right: 0;
  border: none;
  outline: none;
  background-color: transparent;
  cursor: pointer;
  font-size: 1.5rem;
  font-weight: bold;
  color: #5c5959;

  &:after {
    content: '';
    position: absolute;
    left: 11px;
    top: 13px;
    border-top-left-radius: 50%;
    border-top-right-radius: 50%;
    height: 17px;
    width: 2px;
    background-color: #5c5959;
  }
}
</style>
