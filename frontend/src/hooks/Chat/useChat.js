import { getToken } from '@/utils/auth'
import Stomp from 'stompjs'
const url = 'ws://localhost:8080/api/ws'
let token = getToken(),
  stomp = null,
  isConnection = false

function sendMessageTo(receive, content) {
  stomp.send('/app/talkTo', {}, JSON.stringify({ rid: receive, content }))
}

function subscribeTalk(callback) {
  //订阅前确保当前状态是已链接
  conn().then(() => {
    stomp.subscribe('/user/queue/chat', (frame) =>
      callback(JSON.parse(frame.body).content)
    )
  })
}

function readMessage(){

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
  return { stomp, sendMessageTo, subscribeTalk }
}
