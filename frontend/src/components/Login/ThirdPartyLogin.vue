<template>
  <div class="third-party">
    <ul class="third-party-list">
      <li id="tp-qq"><img src="~@/assets/icon/QQ.svg">QQ</li>
      <li id="tp-wechat"><img src="~@/assets/icon/Wechat.svg">微信</li>
      <li id="tp-github"><img src="~@/assets/icon/github.svg">github</li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "ThirdPartyLogin"
}
</script>

<style lang="scss" scoped>
@import "@/styles/_mixins";

.third-party-list {
  display: flex;
  justify-content: space-around;
  margin-top: 20px;
  font-size: 0.9rem;

  &:before {
    margin-top: 3px;
    content: "第三方登录";
  }

  #tp-qq {
    @include hoverLine(5px, #1296DB);
  }

  #tp-wechat {
    @include hoverLine(5px, #1AFA29);
  }

  #tp-github {
    @include hoverLine(5px, #23282D);
  }

  li {
    cursor: pointer;
    display: flex;
    align-items: center;

    img {
      height: 25px;
      margin-right: 10px;
    }
  }
}
</style>
