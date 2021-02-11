import { getPost } from '@/api/post'

export const postCategory = [
  {
    name: '默认',
    value: 'default',
  },
  {
    name: '讨论',
    value: 'discuss',
  },
  {
    name: '提问',
    value: 'question',
  },
  {
    name: '分享',
    value: 'share',
  },
  {
    name: '公告',
    value: 'notice',
  },
]
export function getPostList(num, size, models, loaded, category) {
  getPost(num, size, category).then((res) => {
    // 总数据条数
    models.total = res.data.total
    // 总页码数量
    models.pages = res.data.pages
    // 当前页码
    models.current = res.data.current
    if (num === 1) {
      models.records = []
      loaded.value = true
    }
    for (const item of res.data.records) {
      models.records.push(item)
    }
  })
}
