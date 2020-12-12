<template>
  <div>
    <h1>register</h1>
    <router-link to="/register/profile">profile</router-link> |
    <router-link to="/register/account">account</router-link>
    <div class="temp">
      <router-link to="/register/profile">profile</router-link> |
      <router-link to="/register/account">account</router-link>
    </div>
    <router-view></router-view>
    <div class="footer-fixed">
      <b-button block squared style="height:58px" variant="warning"
        >다음</b-button
      >
    </div>
  </div>
</template>

<script>
export default {
  name: 'register',
  data() {
    return {};
  },
  methods: {},
};
</script>

<style scoped>
.temp {
  padding-top: 20px;
  position: fixed;
}
</style>
