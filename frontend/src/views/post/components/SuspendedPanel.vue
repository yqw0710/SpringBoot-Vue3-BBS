<template>
  <div class="suspended">
    <button>qq</button>
    <span class="share">分享</span>
  </div>
</template>

<script>
export default {
  name: 'SuspendedPanel',
}
</script>

<style lang="scss" scoped>
.suspended {
  position: fixed;
  display: flex;
  flex-direction: column;

  top: 7rem;
  margin-left: -4rem;

  > * {
    margin-bottom: 7px;
  }

  > .share {
    color: #a8a8a8;
    user-select: none;
  }
}
</style>
