module.exports = {
  assetsDir: 'static',
  publicPath: process.env.NODE_ENV === 'production' ? '/vue3/' : '/',
  productionSourceMap: false,
  devServer: {
    port: 8008, // 端口号
  },
  lintOnSave: process.env.NODE_ENV !== 'production',
  css: {
    loaderOptions: {
      scss: {
        // 配置sass-resources定义全局css变量  大概每个scss部分都自动帮你加了这一句话
        additionalData: '@import "@/styles/_variables";',
      },
    },
  },
}
