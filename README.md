# 论坛项目第二次重构

寒假实在是在家没事，学校的项目觉得能力不足，不好意思接，然后只好再次重构写过的项目， 加上大三上就没怎么碰这个项目了，但期间看了不少技术文章，又学了点新知识。

## 后端变化

后端变化不算大。

### 1. 由单个模块变成多个模块

这样就是看起来层次更明了，但是切换模块要在`src/main/java`间来回展开切换，加上14寸笔记本还小，重构老费劲了

### 2. 新的项目目录结构

````demo
├── bbs-common		-- 存放通用的代码和工具类 
├── bbs-security	-- SpringSecurity相关模块 
├── bbs-service		-- 业务相关代码以及代码生成器 
└── bbs-web		   	-- web层主要就请求处理和全局异常处理  
````

### 3. 相关依赖的升级和改动

springboot从2.3.2RELEASE升到2.4.2，其他依赖的版本也更换到了最新（截至2021.1.25）

然后在安全认证框架把shiro替换成了spring-security。(官方starter太棒了！又精简不少代码！！)

### 4. 对写的不好的代码进行重构

如变量名不规范，采纳部分IDE提出的警告，优化能优化的代码，如各种工具类，切面类，百度翻译api的调用等

### 5. 增加了一些枚举

如返回状态码的枚举，提高规范性和可阅读性。

### 6. 项目的打包排除不必要的依赖

如lombok，模板引擎和一些代码生成器

### 7. 重新使用spring-devtools(热部署)

自动觉得热部署不好用，原来是没设置好。。。

### 8. 技术说明补充

- Knife4j：接口的生成和在线调试，浏览器输入`host:port/doc.html`进行访问 官网 https://doc.xiaominfo.com/
- Mybatis-Plus：Mybatis的增强工具。同时使用它的代码生成器 官网 https://baomidou.com/
    - 自3.3.0开始,默认使用雪花算法+UUID(不含中划线)数据库的主键需要 `bigint(64)`格式
    - js无法处理java的长整型，所以可以实体类中id字段可以把`Long`改成`String` 或者对全局jackon进行配置
    - 代码生成器中配置的表前缀为`t_`，数据库的表名为`t_xxx`
    - `CodeGenerator`类运行main方法，输入需要的表名，即可生成Entity、Mapper、Mapper
      XML、Service、Controller 等各个模块的代码
    - 对面Mapper接口使用`@Autowired`自动注入报红，可以忽视直接运行，也可以去Mapper头顶手动加入`@Repository`
      或者用java自带的`@Resource`来替换`@Autowried`
- Hutool：国产的一个小而全的Java工具类库 官网 https://www.hutool.cn/docs/
- screw：简洁好用的数据库表结构文档生成工具 https://gitee.com/leshalv/screw
- spring security: 以过滤器为主的安全认证服务框架

### 9. TODO

- [x] 重构用户模块
- [ ] 重构签到功能
- [ ] 重构点赞功能
- [x] 重构文件上传
- [ ] 重构用户动态
- [ ] 添加聊天功能

## 前端变化

变化很大

### 1. Vue全家桶都用最新的，新特性也能用就用

组合式Api真香，但是Vuex和Vue-Router最新官方文档还没有中文。。

### 2. UI组件库从又换成了Element的ElementPlus

从element-ui到ant-design-vue到ElementPlus

### 3. 添加主题切换功能

就用SCSS生成多套主题 样式

### 4. 页面美化

审美果然会一直提高的

### 5. 参考别的项目规范了代码风格

特别是Vue-element-admin