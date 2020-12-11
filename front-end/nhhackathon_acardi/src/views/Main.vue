<template>
  <div>
    <Header></Header>
    <router-link to="/main/calendar">Calendar</router-link> |
    <router-link to="/main/list">List</router-link> |
    <router-view></router-view>
    <Profile class="profile-fixed" style=""></Profile>
  </div>
</template>

<script>
import Header from '@/components/main/Header.vue';
import Profile from '@/components/main/Profile.vue';

export default {
  components: { Header, Profile },
};
</script>

<style scoped>
.profile-fixed {
  position: fixed;
  bottom: 0;
  width: 100%;
}
</style>
