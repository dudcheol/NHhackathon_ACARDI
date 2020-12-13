<template>
  <div style="padding-top:20px">
    <router-link to="/write/account">write-add account</router-link>
    |
    <router-link to="/write/content">write-content</router-link>

    <div style="padding-bottom:58px">
      <router-view
        @finishTransfer="onFinishTransfer"
        @update="onUpdateContent"
        :money="localMoney"
      ></router-view>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

import { SearchAccount } from '@/api/account.js';
import { mapGetters } from 'vuex';

export default {
  name: 'write',
  tuno: Math.floor(Math.random() * 99999999999999999999),

  data() {
    return {
      ...mapGetters(['getBabyNo']),
      body: {
        id: '', //사용할 아이디.
        pinAccount: '', //사용할 핀어카운트.
        sum: '',
        content: '',
        babyAccount: '',
        babyno: '',
      },
      diary: {
        title: '',
        cost: '',
        baby_no: '',
        member_id: '',
        content: '',
      },
      localMoney: '',
      AccessToken: '',
      Iscd: '',
    };
  },
  mounted() {
    this.AccessToken = this.$store.state.AccessToken;
    this.Iscd = this.$store.state.Iscd;
    console.log(this.body.id);
    axios
      .get(`http://localhost/member/${this.body.id}`)
      .then((response) => {
        console.log(response);
        this.body.pinAccount = response.data.fin_account;
        SearchAccount(
          {
            Header: {
              ApiNm: 'InquireBalance',
              Tsymd: today,
              Trtm: '112428',
              Iscd: this.Iscd,
              FintechApsno: '001',
              ApiSvcCd: 'DrawingTransferA',
              IsTuno: this.tuno,
              AccessToken: this.AccessToken,
            },
            FinAcno: this.body.pinAccount,
          },
          (response) => {
            console.log(response);
            this.localMoney = response.data.Ldbl;
          },
          (error) => {
            console.log(error);
          }
        );
      })
      .catch((error) => {
        console.log(error);
      });
    axios
      .get(`http://localhost/baby/${this.body.babyno}`)
      .then((response) => {
        console.log(response);
        this.body.babyAccount = response.data.account;
      })
      .catch((error) => {
        console.log(error);
      });
    var date = new Date();
    var today =
      String(date.getFullYear()) +
      String(date.getMonth() + 1) +
      String(date.getDate());
  },

  created() {
    var id = this.$session.get('userID');
    this.body.id = id;
    // 여기서 핀-어카운트 얻어와야 함.
    this.body.babyno = this.$store.state.babyno;
    console.log(this.$store.state.babyno);
    // 아이 계좌 얻어오는 구문.
  },
  methods: {
    onUpdateContent(body) {
      //이름 받아옴.
      this.body.sum = body.sum;
      this.body.content = body.content;
      //console.log(this.body);
      this.$router.push({ name: 'WriteContent', params: this.body });
    },

    onFinishTransfer(todayDiary) {
      this.diary.title = this.body.content;
      this.diary.cost = this.body.sum;
      this.diary.baby_no = this.$route.params.babyno;
      this.diary.member_id = this.$session.get('userID');
      this.diary.content = todayDiary;
      // 전송이 완료 되었으면 백에 일기 전달.
      axios
        .post(`http://localhost/diary`, this.diary)
        .then((response) => {
          console.log(response);
          alert('저장되었습니다.');
          this.$router.push({
            path: '/main',
          });
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style scoped></style>
