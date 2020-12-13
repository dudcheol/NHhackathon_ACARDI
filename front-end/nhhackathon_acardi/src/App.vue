<template>
  <div id="app">
    <!-- <div id="nav">
      <router-link to="/main">Main</router-link> |
      <router-link to="/login">Login</router-link> |
      <router-link to="/join">Join</router-link> |
      <router-link to="/register">Register</router-link> |
      <router-link to="/write">Write</router-link> |
      <router-link to="/detail">Detail</router-link>
    </div> -->
    <transition name="fade" mode="out-in">
      <router-view />
    </transition>
  </div>
</template>

<style>
#app {
  min-height: 100vh;
}

.footer-fixed {
  position: fixed;
  height: 58px;
  bottom: 0;
  width: 100%;
}

.footer-spacing {
  padding-bottom: 58px;
}
.fade-enter-active,
.fade-leave-active {
  transition-property: opacity;
  transition-duration: 0.25s;
}

.fade-enter-active {
  transition-duration: 0.25s;
}

.fade-enter,
.fade-leave-active {
  opacity: 0.5;
}

#nav {
  position: fixed;
  z-index: 1000;
}
</style>
