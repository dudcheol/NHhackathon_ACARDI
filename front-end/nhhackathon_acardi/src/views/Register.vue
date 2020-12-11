<template>
  <div>
    <router-link to="/register/profile">profile</router-link> |
    <router-link to="/register/account">account</router-link>
    <router-view></router-view>
    <div class="footer-fixed">
      <b-button block squared style="height:58px" variant="warning"
        >다음</b-button
      >
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped></style>
