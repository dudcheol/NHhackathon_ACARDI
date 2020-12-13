<template
  ><div>
    <div class="header-fixed px-3">
      <b-row class="pt-2">
        <b-col align-self="center" class="p-0">
          <h2 v-if="type == 'write'">
            <b-icon icon="arrow-left" class="p-1 m-2" @click="back"></b-icon>
          </h2>
        </b-col>
        <b-col align-self="center" class="text-center">
          <h5>{{ pageName }}</h5>
        </b-col>
        <b-col class="p-0">
          <h2 v-if="type == 'modify'" class="text-right">
            <b-icon icon="x" class="m-2" @click="close"></b-icon>
          </h2>
        </b-col>
      </b-row>
    </div>
    <b-container style="padding-top:56px;padding-bottom:56px;" class="">
      <!-- <b-container>
      <b-button><b-icon icon="camera-fill"></b-icon></b-button>
      <b-textarea :readonly="true" :placeholder="body.content"> </b-textarea>
      <b-textarea
        v-model="todayDiary"
        placeholder="내용을 입력하세요"
      ></b-textarea>
    </b-container> -->
      <b-row v-if="!diary.imgsrc" class="pt-3 p-0 m-0">
        <b-col class="text-center">
          <b-button variant="info"
            ><b-icon icon="camera-fill"></b-icon> {{ cameraBtnText }}</b-button
          >
        </b-col>
      </b-row>
      <b-row align-v="center" class="p-0 m-0">
        <b-col>
          <b-card
            :title="diary.title"
            header-tag="header"
            :img-src="diary.imgsrc"
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 30rem;"
            class="mb-2 mx-auto"
            border-variant="info"
          >
            <template #header>
              <b-row>
                <b-col class="text-right"
                  ><b-icon icon="cash-stack"></b-icon>
                  <strong> {{ diary.cost }}</strong
                  >원</b-col
                >
              </b-row>
              <!-- <div class="mb-0 text-right">
              <strong>{{ diary.cost }}</strong
              >원
            </div> -->
            </template>
            <b-textarea
              v-model="diary.content"
              placeholder="내용을 입력하세요"
              rows="3"
              max-rows="20"
            ></b-textarea>

            <!-- <b-button href="#" variant="warning">Go somewhere</b-button> -->
          </b-card>
        </b-col>
      </b-row>
    </b-container>
    <div class="footer-fixed">
      <b-row class="p-0 m-0">
        <b-col class="p-0 m-0">
          <b-button
            block
            squared
            style="height:58px"
            variant="warning"
            @click="complete"
            >{{ btnText }}</b-button
          >
        </b-col>
      </b-row>
    </div>
  </div>
</template>

<script>
import { WithdrawalAccount, DepositAccount } from '../../api/account.js';
import axios from 'axios';

export default {
  name: 'writecontent',
  data() {
    return {
      type: '',
      diary: {
        date: '',
        title: '',
        imgsrc: '',
        content: '',
      },
      pageName: '다이어리 작성',
      btnText: '작성하기',
      cameraBtnText: '사진 추가',
      body: {},
      todayDiary: '',
      AccessToken: '',
      Iscd: '',
    };
  },
  created() {
    var params = this.$route.params;
    console.log('writecontent params - ' + JSON.stringify(params));
    this.type = params.type;
    if (params.type == 'write') {
      this.pageName = '다이어리 작성';
      this.btnText = '작성하기';
      this.cameraBtnText = '사진 추가';
      this.body = params.body;
      this.diary.title = this.body.content;
      this.diary.cost = this.body.sum;
    } else if (params.type == 'modify') {
      this.pageName = '다이어리 수정';
      this.btnText = '수정하기';
      this.cameraBtnText = '사진 변경';
      this.diary = params.diary;
    }
  },
  mounted() {
    this.AccessToken = this.$store.state.AccessToken;
    this.Iscd = this.$store.state.Iscd;
  },
  methods: {
    complete() {
      if (this.type == 'write') {
        this.finish();
      } else if (this.type == 'modify') {
        this.modify();
      }
    },
    modify() {
      console.log('click modify');
      axios
        .put(`http://localhost/diary`, this.diary)
        .then((res) => {
          console.log(res);
          this.$router.push({ name: 'Detail', params: this.diary });
        })
        .catch((err) => {
          console.error(err);
        });
    },
    close() {
      this.$router.push({ name: 'Detail', params: this.diary });
    },
    back() {},
    finish() {
      var date = new Date();
      var today =
        String(date.getFullYear()) +
        String(date.getMonth() + 1) +
        String(date.getDate());
      var tuno = Math.floor(Math.random() * 99999999999999999999);
      var tuno2 = Math.floor(Math.random() * 99999999999999999999);
      WithdrawalAccount(
        {
          Header: {
            ApiNm: 'DrawingTransfer',
            Tsymd: today,
            Trtm: '112428',
            Iscd: this.Iscd,
            FintechApsno: '001',
            ApiSvcCd: 'DrawingTransferA',
            IsTuno: tuno,
            AccessToken: this.AccessToken,
          },
          FinAcno: this.$route.params.pinAccount,
          Tram: this.body.sum,
          MractOtlt: '새록새록',
        },
        (response) => {
          console.log(response);
          DepositAccount(
            {
              Header: {
                ApiNm: 'ReceivedTransferAccountNumber',
                Tsymd: today,
                Trtm: '112428',
                Iscd: this.Iscd,
                FintechApsno: '001',
                ApiSvcCd: 'DrawingTransferA',
                IsTuno: tuno2,
                AccessToken: this.AccessToken,
              },
              Bncd: '011',
              Acno: this.body.babyAccount,
              Tram: this.body.sum,
              DractOtlt: '테스트',
              MractOtlt: this.body.content,
            },
            (response) => {
              console.log(response);
              this.$emit('finishTransfer', this.diary.content);
            },
            (error) => {
              console.log(error);
            }
          );
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style scoped>
.header-fixed {
  position: fixed;
  top: 0;
  height: 56px;
  width: 100%;
  z-index: 999;
  background-color: white;
}
</style>
