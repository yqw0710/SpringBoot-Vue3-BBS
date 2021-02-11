// ESLint配置文件
module.exports = {
  root: true,
  // 预定义的全局变量
  env: {
    browser: true,
    node: true,
    es6: true,
  },
  extends: ['plugin:vue/vue3-essential', 'eslint:recommended', '@vue/prettier'],
  rules: {
    // 等级分为三级： 0-不显示；1-显示警告warning; 2-显示错误error
    'vue/singleline-html-element-content-newline': 0, // 在单行元素的内容前后需要换行符
    'vue/multiline-html-element-content-newline': 0, // 在多行元素的内容之前和之后需要换行符
    'vue/prop-name-casing': [1, 'camelCase'], // 在声明prop的时候，其命名应该始终使用驼峰命名
    'vue/require-v-for-key': 1, // 给v-for设置键值，与key结合使用，可以高效的更新虚拟DOM
    'vue/no-use-v-if-with-v-for': [2, { allowUsingIterationVar: false }], // 不要把 v-if 和 v-for 用在同一个元素上——因为v-for 比 v-if 具有更高的优先级
    'vue/no-v-html': 0,
    'space-before-function-paren': 0,
    'no-unused-vars': 0,
    camelcase: 2, //强制驼峰法命名
  },
}
