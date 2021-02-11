export default {
  // 是否登录
  logined: (state) => !!state.user.token,
  // 用户头像
  avatar: (state) => state.user.avatar,
  // token
  token: (state) => state.user.token,
  // uid
  uid: (state) => state.user.uid,
  // 用户昵称
  nickname: (state) => state.user.nickname,
  // 当前主题
  theme: (state) => state.settings.theme,
}
