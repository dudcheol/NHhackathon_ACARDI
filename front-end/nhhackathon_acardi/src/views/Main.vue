<template>
  <div>
    <b-sidebar
      id="sidebar"
      v-model="isSidebarOpen"
      title="title"
      shadow=""
      bg-variant="white"
    >
      <h1>sidebar area</h1>
    </b-sidebar>
    <Header
      class="header-fixed"
      style="height:56px"
      @open-sidebar="openSidebar"
    ></Header>
    <div style="padding-top:73px;padding-bottom:110px">
      <router-link to="/main/calendar">Calendar</router-link> |
      <router-link to="/main/list">List</router-link> |
      <router-view></router-view>
    </div>
    <Profile class="profile-fixed" style=""></Profile>
  </div>
</template>

<script>
import Header from '@/components/main/Header.vue';
import Profile from '@/components/main/Profile.vue';

export default {
  components: { Header, Profile },
  data() {
    return {
      isSidebarOpen: false,
    };
  },
  methods: {
    openSidebar() {
      console.log('open sidebar');
      this.isSidebarOpen = true;
    },
    closeSidebar() {
      console.log('close sidebar');
      this.isSidebarOpen = false;
    },
  },
};
</script>

<style scoped>
.header-fixed {
  position: fixed;
  top: 0;
  width: 100%;
  z-index: 999;
}
.profile-fixed {
  position: fixed;
  bottom: 0;
  width: 100%;
}
</style>
