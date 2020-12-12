<template>
  <div>
    <b-sidebar
      id="sidebar"
      v-model="isSidebarOpen"
      title="baby list"
      shadow=""
      bg-variant="white"
    >
      <br />
      <ul v-for="baby in babyList" :key="baby">
        <li @click="getBaby(baby.no)">{{ baby.nickname }}</li>
      </ul>
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
    <Profile class="profile-fixed" style="" :babyno="babyno"></Profile>
  </div>
</template>

<script>
import Header from '@/components/main/Header.vue';
import Profile from '@/components/main/Profile.vue';
import axios from 'axios';

export default {
  components: { Header, Profile },
  data() {
    return {
      isSidebarOpen: false,
      babyList: [],
      babyno: '',
    };
  },
  created() {
    var id = this.$session.get('userID');
    console.log(id);
    axios
      .get('http://localhost/baby/list/' + id)
      .then((response) => {
        console.log(response);
        this.babyList = response.data;
      })
      .catch((error) => {
        console.log(error);
      });
    console.log(this.$route.query.baby_no);
    this.babyno = this.$route.query.baby_no;
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
    getBaby(no) {
      console.log(no);
      this.babyno = no;
      this.$router.push({
        path: '/main',
        query: { baby_no: no },
      });
      this.isSidebarOpen = false;
      window.location.reload('/main?baby_no=' + no);
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
ul {
  list-style: none;
  font-size: 18px;
}
</style>
