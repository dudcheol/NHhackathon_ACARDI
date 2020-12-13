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
      <ul v-for="baby in getBabyInfos" :key="baby">
        <li @click="getBaby(baby.no)">{{ baby.nickname }}</li>
      </ul>
      <ul>
        <li @click="registerBaby">아이 추가하기</li>
      </ul>

      <!-- 가족 추가-->
      <ul>
        <li @click="registerFamily">가족 추가하기</li>
      </ul>

      <ul>
        <h6 class="text-danger" @click="logout">로그아웃</h6>
      </ul>
    </b-sidebar>
    <Header
      class="header-fixed"
      style="height:56px"
      @open-sidebar="openSidebar"
      @open-eventList="openEventList"
    ></Header>
    <b-modal v-model="modalShow" title="알림" ok-only
      ><p class="my-2">
        {{ baby.nickname }}이의 생일이 {{ this.bDay }}일 남았습니다.
      </p>
      <hr />
      <p class="my-2">
        <a
          href="https://www.nhlife.co.kr/ho/ig/HOIG0001M00.nhl?prodCd=N0000709"
          target="_blank"
          v-b-tooltip
          title="어린이보험"
          >어린이 보험에 가입해 보세요!</a
        >
      </p>
    </b-modal>
    <div style="padding-top:73px;padding-bottom:117px">
      <router-link to="/main/calendar">Calendar</router-link>
      |
      <router-link
        :to="{ name: 'List', params: { babyno: getBabyNo } }"
        ref="List"
        >List</router-link
      >
      |
      <router-view :babyno="getBabyNo"></router-view>
    </div>
    <Profile
      ref="Profile"
      class="profile-fixed"
      style=""
      :baby="getBabyInfos[getBabyIdx]"
    ></Profile>
  </div>
</template>

<script>
import Header from '@/components/main/Header.vue';
import Profile from '@/components/main/Profile.vue';
import { mapActions, mapGetters } from 'vuex';
import axios from 'axios';
export default {
  name: 'Main',
  props: ['params'],
  components: { Header, Profile },
  data() {
    return {
      isSidebarOpen: false,
      modalShow: false,
      baby: {},
      bDay: '',
    };
  },
  computed: {
    ...mapGetters(['getBabyInfos', 'getBabyNo', 'getBabyIdx']),
  },
  created() {
    var id = this.$session.get('userID');
    this.GET_BABYNO(id);
  },
  mounted() {
    this.$refs.Profile.setValue(this.$store.state.babyno);
  },
  methods: {
    ...mapActions(['GET_BABYNO', 'RESET_STATE']),
    openSidebar() {
      console.log('open sidebar');
      this.isSidebarOpen = true;
      this.$refs.Profile.setValue(this.getBabyNo); //임시 처리.
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
    registerFamily() {
      this.$router.push({
        name: 'RegisterFam',
      });
    },
    logout() {
      console.log('logout click');
      this.$session.set('userID', null);
      this.RESET_STATE();
      this.$router.push({ name: 'Login' });
    },
    // 이벤트 알림창.
    openEventList() {
      this.modalShow = true;
      //아이 정보를 얻어와야 함.
      axios
        .get('http://localhost/baby/' + this.$store.state.babyno)
        .then((response) => {
          console.log(response);
          this.baby = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
      this.bDay = this.getBirthday(this.baby.birthday);
    },
    getBirthday(bDate) {
      var day = new Date(bDate);
      var now = new Date();
      var gap = now.getTime() - day.getTime();
      return 365 - Math.floor((gap / (1000 * 60 * 60 * 24)) % 365);
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
