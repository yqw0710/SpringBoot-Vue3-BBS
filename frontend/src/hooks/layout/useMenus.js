export const menus = [
  {
    value: '首页',
    path: '/',
    enable: true,
  },
  {
    value: '阅读',
    path: 'read',
    enable: true,
  },
  {
    value: '教程',
    path: 'tutorial',
    enable: true,
  },
  {
    value: '排行',
    path: 'rank',
    enable: true,
  },
  {
    value: '留言',
    path: 'message-board',
    enable: true,
  },
  {
    value: '活动',
    path: 'activity',
    enable: false,
  },
]
export default function useMenus() {
  return { menus }
}
