<template>
  <div
    v-text="modelValue"
    @input="$emit('update:modelValue', $event.target.innerHTML)"
    @keydown.enter.prevent="$emit('send')"
    contenteditable="true"
  />
</template>

<script>
// from https://blog.csdn.net/qq_35649079/article/details/84138634
/*
      <!--      <EditableDiv
        class="input-box"
        @update:model-value="updateUserInput"
        @send="sendMessage"
        v-model="userInput"
      />-->
 */
export default {
  name: 'EditableDiv',
  props: ['modelValue'],
  emits: ['update:modelValue', 'send'],
}
</script>
