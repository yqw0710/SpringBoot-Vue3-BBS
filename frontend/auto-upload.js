const client = require('scp2')
client.scp(
  './dist/',
  {
    // 本地打包文件的位置
    host: '47.100.95.40', // 服务器的IP地址
    port: '22', // 服务器端口
    username: 'root', // 用户名
    password: '', // 密码
    path: '/bbs/front-end/vue3', // 项目部署的服务器目标位置
  },
  (err) => {
    if (!err) {
      console.log('项目发布完毕!')
    } else {
      console.log('err', err)
    }
  }
)
