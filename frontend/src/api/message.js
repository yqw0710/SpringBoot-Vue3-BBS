import http from './http'

//留言相关请求
export function postMessage(data) {
  return http.post('/message', data)
}

export function getMessage(num, size) {
  return http.get(`/message?num=${num}&size=${size}`)
}

/*
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "records": [
      {
        "id": 26,
        "nickname": "jojo999",
        "content": "test1",
        "avatar": "1",
        "parentId": 0,
        "userId": 14,
        "toId": 0,
        "toName": null,
        "status": 1,
        "browser": "Chrome87.0.4280.141",
        "os": "Windows",
        "ip": null,
        "gmtCreate": "2021-01-27 18:36:31",
        "children": [],
        "childrenCount": 0
      },
      {
        "id": 25,
        "nickname": "jojo999",
        "content": "test",
        "avatar": "1",
        "parentId": 0,
        "userId": 14,
        "toId": 0,
        "toName": null,
        "status": 1,
        "browser": "Chrome87.0.4280.141",
        "os": "Windows",
        "ip": null,
        "gmtCreate": "2021-01-27 14:05:49",
        "children": [],
        "childrenCount": 0
      },
      {
        "id": 24,
        "nickname": "test",
        "content": "test",
        "avatar": null,
        "parentId": 0,
        "userId": null,
        "toId": 0,
        "toName": null,
        "status": 1,
        "browser": "Chrome87.0.4280.141",
        "os": "Windows",
        "ip": null,
        "gmtCreate": "2021-01-27 14:04:50",
        "children": [],
        "childrenCount": 0
      }
    ],
    "total": 18,
    "size": 3,
    "current": 1,
    "orders": [],
    "optimizeCountSql": true,
    "hitCount": false,
    "countId": null,
    "maxLimit": null,
    "searchCount": true,
    "pages": 6
  }
}
 */
