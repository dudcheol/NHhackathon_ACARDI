<template>
  <div style="padding-top:20px">
    <router-link to="/write/account">write-add account</router-link> |
    <router-link to="/write/content">write-content</router-link>
    <div style="padding-bottom:58px">
      <router-view></router-view>
    </div>
    <div class="footer-fixed">
      <b-button
        block
        squared
        style="height:58px"
        variant="warning"
        @click="signin"
        >다음</b-button
      >
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped></style>
