import { reactive, ref, computed } from 'vue'
import { getToken } from '@/utils/auth'
import http from '@/api/http'
import { getUserInfoDigest } from '@/api/user'
import Stomp from 'stompjs'

let stomp = null,
  currentUserId = null,
  token = getToken(),
  isConnection = false,
  senders = reactive({}),
  currentChatTarget = ref(null),
  // url = 'ws://127.0.0.1:8080/api/ws'
  url = 'ws://47.100.95.40:8080/api/ws'

let currentChatObj = computed(() => {
  //如果没有历史记录 则去后端加载
  if (senders[currentChatTarget.value].historyArr.length < 1) {
    loadMoreHistory(1)
  }
  return senders[currentChatTarget.value]
})

// 发送消息给receiver
function sendMessageTo(receiver, content) {
  stomp.send('/app/talk2', {}, JSON.stringify({ rid: receiver, content }))
  senders[receiver].historyArr.push({
    content,
    sid: currentUserId,
    rid: receiver,
    id: new Date().getMilliseconds(),
  })
}

// 将数据库的中的readed字段置为true，与该用户则无未读消息了
function readedMessage(sender) {
  // 接收者的id为int类型
  if (typeof sender !== 'number') return
  stomp.send('/app/readed.' + sender)
}

// 获取聊天记录列表
function getChatRecord() {
  return http.get('/chat/record').then((res) => {
    if (res.data && Array.isArray(res.data) && res.data.length > 0)
      res.data.forEach((value) => {
        value.pageInfo = {}
        value.historyArr = []
        senders[value.fromId] = value
      })
  })
}

function loadMoreHistory(page) {
  getChatHistoryPage(currentChatTarget.value, page, 5).then((res) => {
    senders[currentChatTarget.value].pageInfo.pages = res.data.pages
    senders[currentChatTarget.value].pageInfo.current = res.data.current
    res.data.records.forEach((value) => {
      senders[currentChatTarget.value].historyArr.unshift(value)
    })
  })
}

function getChatHistoryPage(sid, num, size) {
  return http.get('/chat/history', { params: { sid, num, size } })
}

function establishConnection() {
  if (!token) {
    alert('连接失败 没有令牌,请回到主页面重新登录')
    return
  }
  return new Promise((resolve) => {
    if (isConnection) resolve(true)
    if (!stomp) stomp = Stomp.client(url + '?token=' + token)
    stomp.debug = null
    stomp.connect({ token }, () => {
      resolve(true)
      isConnection = true
    })
  })
}

function handleReceiveMessage(body) {
  // 如果消息的发送者已在Record中展示，这push到历史数组里
  if (Object.prototype.hasOwnProperty.call(senders, body.sid)) {
    console.log(senders)
    senders[body.sid].historyArr.push(body)
  } else {
    getUserInfoDigest(body.sid).then((res) => {
      console.log(body)
      value.pageInfo = {}
      value.historyArr = []
      senders[body.sid].avatar = res.data.avatar
      senders[body.sid].nickname = res.data.nickname
      senders[body.sid].content = body.content
      senders[body.sid].id = body.id
      senders[body.sid].created = body.created
    })
  }
  // 当不是正在与该消息的发送者聊天 提醒用户有新消息
  if (currentChatTarget.value !== body.sid) {
    console.log('有新消息来了')
    senders[body.sid].unread++
  } else {
    cleanUnreadCount(body.sid)
  }
}

function cleanUnreadCount(uid) {
  stomp.send('/app/readed.' + uid)
  senders[uid].unread = 0
}

// 初始化方法，先加载聊天记录然后建立websocket连接 然后订阅话题以便能接收到用户消息
function init(uid) {
  currentUserId = uid
  getChatRecord().then(() => {
    establishConnection().then(() => {
      stomp.subscribe('/user/queue/chat', (frame) => {
        handleReceiveMessage(JSON.parse(frame.body))
      })
    })
  })
}

export default function useChat() {
  return {
    init,
    sendMessageTo,
    readedMessage,
    loadMoreHistory,
    cleanUnreadCount,
    senders,
    currentChatObj,
    currentChatTarget,
  }
}
