<template>
  <div class="update-avatar">
    <input
      style="display: none"
      type="file"
      ref="fileInput"
      name="image"
      @change="setImage"
      accept="image/*"
    />
    <div style="display: flex;margin-bottom: 20px">
      <div class="cropper">
        <div v-if="!isChoose" @click="fileInput.click" class="chooser">
          <span>📷</span>
          <p>选择图片</p>
        </div>
        <div v-else class="img-cropper">
          <vue-cropper
            ref="cropper"
            :aspect-ratio="1"
            :src="imgSrc"
            preview=".preview"
          />
        </div>
      </div>
      <div class="avatar-area">
        <img
          v-if="cropImg"
          :src="cropImg"
          class="avatar-preview"
          alt="Cropped Image"
        />
        <img :src="avatar" class="avatar-preview" v-else alt />
      </div>
    </div>
    <div class="btn-group">
      <button @click="fileInput.click" class="btn">重新选择</button>
      <button @click="cropImage" class="btn">裁剪图片</button>
      <button @click="uploadImage" class="btn">确认上传</button>
    </div>
  </div>
</template>
<script>
import VueCropper from 'vue-cropperjs'
import 'cropperjs/dist/cropper.css'
import { ElMessage } from 'element-plus'
import { ref } from 'vue'
import { uploadAvatar } from '@/api/user'
import { dataURLtoFile } from '@/utils'
export default {
  name: 'UpdateAvatar',
  props: ['avatar'],
  emit: ['update-avatar'],
  components: { VueCropper },
  setup(props, ctx) {
    const fileInput = ref(null)
    const isChoose = ref(false)
    const cropper = ref(null)
    const cropImg = ref(null)
    const imgSrc = ref(null)
    const uploadImage = () => {
      if (!cropImg.value) {
        alert('未裁剪图片!')
      } else {
        uploadAvatar(dataURLtoFile(cropImg.value)).then((res) => {
          ctx.emit('update-avatar', res.data)
          ElMessage({ type: 'success', message: res.msg })
        })
      }
    }
    const cropImage = () => {
      try {
        cropImg.value = cropper.value.getCroppedCanvas().toDataURL()
      } catch (e) {
        console.error(e)
      }
    }
    const setImage = (e) => {
      const file = e.target.files[0]
      if (file.type.indexOf('image/') === -1) {
        alert('Please select an image file')
        return
      }
      if (typeof FileReader === 'function') {
        const reader = new FileReader()
        reader.onload = (event) => {
          imgSrc.value = event.target.result
          cropper.value.replace(event.target.result)
        }
        reader.readAsDataURL(file)
        isChoose.value = true
      } else {
        alert('Sorry, FileReader API not supported')
      }
    }
    return {
      isChoose,
      imgSrc,
      cropImg,
      cropper,
      fileInput,
      setImage,
      cropImage,
      uploadImage,
    }
  },
}
</script>

<style lang="scss" scoped>
// 图片选择裁剪区域
.cropper {
  width: 50%;
  > .chooser {
    position: relative;
    width: 200px;
    min-height: 200px;
    margin: 0 auto;
    background-color: #e0e1e5;
    border-radius: 5px;
    cursor: pointer;
    > * {
      transition: 0.5s;
      position: absolute;
    }
    &:hover {
      background-color: #d3d5dc;
    }
    > span {
      font-size: 2rem;
      bottom: 100px;
      left: 40%;
    }
    > p {
      color: #444141;
      bottom: 70px;
      left: 35%;
    }
  }
  > .img-cropper {
  }
}
// 头像预览显示区域
.avatar-area {
  display: flex;
  align-items: center;
  width: 50%;
  .avatar-preview {
    width: 100px;
    height: 100px;
    border-radius: 50%;
    margin: 0 auto;
  }
}
.btn-group {
  display: flex;
  justify-content: space-around;
}
</style>
