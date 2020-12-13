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
    ></Header>
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
      selected: '',
      options: [
        { text: '캘린더', value: 'Calendar' },
        { text: '리스트', value: 'List' },
      ],
      attributes: [],
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
  },
  created() {
    console.log('main created - ' + this.getMainState);
    console.log('cur date - ' + this.getCurDate);
    var id = this.$session.get('userID');
    this.GET_BABYNO(id);

    // this.monthChange(this.getCurDate);

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
    this.$refs.Profile.setValue(this.$store.state.babyno);
  },
  methods: {
    ...mapActions(['GET_BABYNO', 'RESET_STATE', 'CHANGE_MAIN_STATE']),
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
