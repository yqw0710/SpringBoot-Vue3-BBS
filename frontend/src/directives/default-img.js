import defaultAvatar from '@/assets/images/dio.jpg'
import defaultBackground from '@/assets/images/cute.jpg'
/**
 * 检测图片是否存在
 * @param url
 */
let imageIsExist = function (url) {
  return new Promise((resolve) => {
    let img = new Image()
    img.onload = function () {
      if (this.complete === true) {
        resolve(true)
        img = null
      }
    }
    img.onerror = function () {
      resolve(false)
      img = null
    }
    img.src = url
  })
}
/**
 * 当图片加载失败时，显示默认图片
 * 参数可选：avatar|暂时没了
 * <img :src="xxxx" v-default-img="'avatar'" />
 */
export default async function defaultImg(el, binding) {
  // 需要显示默认图片(当图片原本的src属性有错时)的类型
  let params = binding.value
  // 图片原本的src
  let realURL = el.src
  // 当原本图片不存在时，根据参数返回不同的图片url
  let exist = await imageIsExist(realURL)
  if (!exist) {
    if (params === 'avatar') {
      el.setAttribute('src', defaultAvatar)
    } else if (params === 'background') {
      el.setAttribute('src', defaultBackground)
    }
  }
}
