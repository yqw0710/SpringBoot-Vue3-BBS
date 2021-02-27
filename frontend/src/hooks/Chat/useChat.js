import { getToken } from '@/utils/auth'
import Stomp from 'stompjs'
import http from '@/api/http'

const url = 'ws://47.100.95.40:8080/api/ws'
let token = getToken(),
  stomp = null,
  isConnection = false

// 发送消息给receive
function sendMessageTo(receive, content) {
  stomp.send('/app/talkTo', {}, JSON.stringify({ rid: receive, content }))
}

// 将数据库的中的readed字段置为true(1)，与该用户则无未读消息了
function readedMessage(sender) {
  // 接收者的id为int类型
  if (typeof sender !== 'number') return
  stomp.send('/app/readed.' + sender)
}

// 订阅以便能接收到其他用户的消息，需要回调函数用于处理接受到的消息
function subscribeTalk(callback) {
  //订阅前确保当前状态是已链接
  conn().then(() => {
    stomp.subscribe('/user/queue/chat', (frame) =>
      callback(JSON.parse(frame.body).content)
    )
  })
}

// 获取聊天记录列表
function getChatRecord() {
  return http.get('/chat/record')
}

// 分页获取与消息发送者的历史记录
function getChatHistory(sid, num, size) {
  return http.get('/chat/history', { params: { sid, num, size } })
}

function conn() {
  if (!token) {
    alert('连接失败 没有令牌')
    return
  }
  return new Promise((resolve) => {
    if (isConnection) resolve(true)
    if (!stomp) stomp = Stomp.client(url + '?token=' + token)
    stomp.connect({ token }, () => {
      resolve(true)
      isConnection = true
    })
  })
}

export default function useChat() {
  return {
    sendMessageTo,
    subscribeTalk,
    getChatRecord,
    getChatHistory,
    readedMessage,
  }
}
