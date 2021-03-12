<template>
  <div>{{ 1 }}</div>
</template>

<script>

export default {
  name: 'CommentList',

  props: ['list'],
  setup(props) {
    console.log(props)
  },
}
</script>

<style scoped></style>
