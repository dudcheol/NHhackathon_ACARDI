<template>
  <div>
    <div style="padding-top:20px; position:fixed; z-index:999">
      <router-link to="/write/account">write-add account</router-link>
      |
      <router-link to="/write/content">write-content</router-link>
    </div>

    <div>
      <transition name="fade" mode="out-in">
        <router-view
          @finishTransfer="onFinishTransfer"
          @postPhotos="onPostPhotos"
          @update="onUpdateContent"
          :money="localMoney"
        ></router-view>
      </transition>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { SearchAccount } from '@/api/account.js';
export default {
  name: 'write',
  tuno: Math.floor(Math.random() * 99999999999999999999),
  data() {
    return {
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
      this.$router.push({
        name: 'WriteContent',
        params: { type: 'write', body: this.body },
      });
    },
    onFinishTransfer(todayDiary) {
      this.diary.title = this.body.content;
      this.diary.cost = this.body.sum;
      this.diary.baby_no = this.body.babyno;
      this.diary.member_id = this.$session.get('userID');
      this.diary.content = todayDiary;
      console.log('finish : ' + this.diary);
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
    onPostPhotos(photos) {
      if (photos == '') return;
      console.log(photos);
      photos.baby_no = this.body.babyno;
      console.log(photos);
      // 전송이 완료 되었으면 백에 일기 전달.
      var frm = new FormData();
      for (var i = 0; i < photos.file.length; i++) {
        frm.append('multipartFiles', photos.file[i].file);
      }
      axios
        .post(`http://localhost/upload/${photos.baby_no}/${photos.type}`, frm, {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        })
        .then((response) => {
          console.log(response);
          console.log('사진 저장 완료');
          //alert('저장되었습니다.');
          // this.$router.push({
          //   path: '/main',
          // });
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style scoped></style>
