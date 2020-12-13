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
      <ul v-for="(baby, index) in getBabyInfos" :key="baby.key">
        <li @click="getBaby({ no: baby.no, index })">{{ baby.nickname }}</li>
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
      <div class="text-right">
        <b-form-group class="pr-3 pb-1 m-0">
          <b-form-radio-group
            v-model="selected"
            :options="options"
            buttons
            button-variant="outline-secondary"
            size="sm"
            name="radio-btn-outline"
          ></b-form-radio-group>
        </b-form-group>
      </div>
      <router-view :attributes="attributes" class="px-3"></router-view>
    </div>
    <Profile
      ref="Profile"
      class="profile-fixed"
      style=""
      :baby="selectedBabyInfo"
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
      bDay: 0,
      selected: '',
      options: [
        { text: '캘린더', value: 'Calendar' },
        { text: '리스트', value: 'List' },
      ],
      attributes: [],
      selectedBabyInfo: {},
    };
  },
  computed: {
    ...mapGetters([
      'getBabyInfos',
      'getBabyNo',
      'getBabyIdx',
      'getMainState',
      'getCurDate',
    ]),
  },
  watch: {
    selected: function(val) {
      console.log(val);
      this.CHANGE_MAIN_STATE(val);
      this.$router.push({ name: val });
    },
    getCurDate: function(val) {
      this.monthChange(val);
    },
    getBabyNo: function() {
      this.monthChange(this.getCurDate);
    },
    getBabyIdx: function(val) {
      console.log('getbabyidx ' + val);
      console.log('getbabyidx ' + JSON.stringify(this.getBabyInfos[val].no));
      this.selectedBabyInfo = this.getBabyInfos[val];
      console.log(this.selectedBabyInfo);
    },
  },
  created() {
    console.log('main created - ' + this.getMainState);
    console.log('cur date - ' + this.getCurDate);
    var id = this.$session.get('userID');
    if (!this.getBabyNo) this.GET_BABYNO(id);
    if (this.getBabyNo) {
      this.monthChange(this.getCurDate);
      this.selectedBabyInfo = this.getBabyInfos[this.getBabyIdx];
    }
    if (this.getMainState) {
      this.$router.push({ name: this.getMainState });
      this.selected = this.getMainState;
    } else {
      this.$router.push({ name: 'Calendar' });
      this.selected = 'Calendar';
    }
  },
  mounted() {
    console.log('main mounted');
    console.log(this.getMainState);
    // this.$refs.Profile.setValue(this.$store.state.babyno);
  },
  methods: {
    ...mapActions([
      'GET_BABYNO',
      'RESET_STATE',
      'CHANGE_MAIN_STATE',
      'CHANGE_BABY',
    ]),
    openSidebar() {
      console.log('open sidebar');
      this.isSidebarOpen = true;
      // this.$refs.Profile.setValue(this.getBabyNo); //임시 처리.
    },
    closeSidebar() {
      console.log('close sidebar');
      this.isSidebarOpen = false;
    },
    getBaby(info) {
      // this.babyno = no;
      // console.log('baby click = ' + this.babyno);
      // this.$refs.Profile.setValue(this.babyno);
      this.CHANGE_BABY(info);
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
    monthChange(date) {
      console.log(date);
      console.log(
        `month chagne - date:${date.year}-${date.month} , babyno:${this.getBabyNo}`
      );
      axios
        .get(
          `http://localhost/diary/${this.getBabyNo}/${date.year}/${date.month}`
        )
        .then((response) => {
          console.log(response.data);
          this.updateAttributes(response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getBirthday(bDate) {
      var day = new Date(bDate);
      var now = new Date();
      var gap = now.getTime() - day.getTime();
      return 365 - Math.floor((gap / (1000 * 60 * 60 * 24)) % 365);
    },
    updateAttributes(diaries) {
      console.log(diaries);
      // diary 순회해서 push
      var tmp = [];
      for (let index = 0; index < diaries.length; index++) {
        const d = diaries[index];
        tmp.push({
          key: index,
          customData: {
            title: d.title,
            content: d.content,
            cost: d.cost,
            member_id: d.member_id,
            no: d.no,
            imgsrc: d.imgsrc,
            date: d.registered_at,
          },
          dates: new Date(d.registered_at),
        }); //baby_no, content, cost, member_id, no, registered_at, title
      }
      this.attributes = tmp;
      console.log(this.attributes);
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
