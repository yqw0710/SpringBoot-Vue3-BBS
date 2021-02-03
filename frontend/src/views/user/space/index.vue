<template>
  <div>
    <space-head :uid="uid" />
    <!--    TODO 用户动态-->
  </div>
</template>

<script>
import { useRoute } from 'vue-router'
import SpaceHead from './components/SpaceHead'
export default {
  name: 'Space',
  components: { SpaceHead },
  setup() {
    let route = useRoute()
    let uid = route.params.uid
    console.log(route.params.uid)
    return { uid }
  },
}
</script>

<style scoped></style>
