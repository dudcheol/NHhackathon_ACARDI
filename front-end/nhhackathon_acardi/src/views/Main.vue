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
      <ul>
        <li @click="registerBaby">아이 추가하기</li>
      </ul>
    </b-sidebar>
    <Header
      class="header-fixed"
      style="height:56px"
      @open-sidebar="openSidebar"
    ></Header>
    <div style="padding-top:73px;padding-bottom:117px">
      <router-link to="/main/calendar">Calendar</router-link> |
      <router-link
        :to="{ name: 'List', params: { babyno: this.babyno } }"
        ref="List"
        >List</router-link
      >
      |
      <router-view></router-view>
    </div>
    <Profile
      ref="Profile"
      class="profile-fixed"
      style=""
      babyno="babyno"
    ></Profile>
  </div>
</template>

<script>
import Header from '@/components/main/Header.vue';
import Profile from '@/components/main/Profile.vue';
import axios from 'axios';

export default {
  name: 'Main',
  props: ['params'],
  components: { Header, Profile },
  data() {
    return {
      isSidebarOpen: false,
      babyList: [],
      babyno: '',
      diaryList: [],
    };
  },
  created() {
    var id = this.$session.get('userID');
    this.babyno = this.$route.params.no;
    console.log(this.babyno);
    axios
      .get('http://localhost/baby/list/' + id)
      .then((response) => {
        console.log(response);
        this.babyList = response.data;
      })
      .catch((error) => {
        console.log(error);
      });
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
      this.babyno = no;
      console.log(this.babyno);
      this.$refs.Profile.setValue(this.babyno);
      this.isSidebarOpen = false;
    },
    registerBaby() {
      this.$router.push({
        path: '/register',
      });
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
  z-index: 999;
}
ul {
  list-style: none;
  font-size: 18px;
}
</style>
