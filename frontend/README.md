# study-vue3-elementplus

**main**:vue3.0 & vue-router4.0 & vuex4.0 & created by vue-cli4.5

**minor**:element-plus,axios,dayjs,lodash,sass

## 1. 聊天功能

聊天功能是此时重构的重点，通过此次聊天功能，能掌握websocket的使用以及？？

### 基础流程与相关功能

1. 当用户登录网站后，页面会向服务端发起建立websocket连接的请求

2. 连接成功后，拉取未查阅消息的数量显示在聊天按钮上（按人头算）

3. 当收到新的消息时，聊天按钮晃动 （X）

4. 点击聊天按钮会进入聊天页面，而非知乎那样的Modal。（使用hooks共享建立后的对象）

5. 在聊天页面，类似于微信网页版，左侧侧边栏有消息发送者及头像。（需从数据库中**拉取**消息列表[最近联络人]）

   1. 获取所有的聊天并保存本地之后的就再次追加，根据sid分组（之后可限制每组最多5条）

   2. 如果某组有未读的消息。则标红提醒 

      --------------

   3. ==分组，获取每组最后一条==

   4. 并记录当前时间。当以后有新消息过来时，要获取数据时，就获取after这个时间的数据

6. 右侧是聊天内容主题，点击侧边栏头像会先**加载**最近的消息记录。（用回调函数**标记**所有相关消息为已读）

7. 在这个页面会订阅用户消息的topic。有**新**消息过来直接push到聊天记录。(push后标记该消息为已读）

   1. 先判断该消息的发送者是否在消息列表中（有就放入数组中，没有就下一步）
   2. 如果不在，需要后端获取该消息发送者的头像以及昵称 等

8. 发送消息给指定用户时带上uid和内容。后端先**保存**一条记录到数据库中，然后再**中转**到目的用户上

### 数据库相关字段（单聊）

id，发送用户sid，接收用户rid，消息内容content，发送时间created，是否已读read

````mysql
CREATE TABLE y_chat (
  `id` INT NOT NULL AUTO_INCREMENT,
  `sid` INT NOT NULL COMMENT '发送者id(send_uid)',
  `rid` INT NOT NULL COMMENT '接收者id(receive_uid)',
  `content` TEXT NOT NULL COMMENT '消息的内容',
  `read` TINYINT(1) NULL DEFAULT 0 COMMENT '是否已读,默认0未读',
  `created` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)) COMMENT = '聊天记录表';
````



