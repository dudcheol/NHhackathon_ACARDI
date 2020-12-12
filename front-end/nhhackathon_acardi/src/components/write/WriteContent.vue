<template>
  <div>
    <b-container>
      <b-button><b-icon icon="camera-fill"></b-icon></b-button>
      <b-textarea :readonly="true" :placeholder="body.content"> </b-textarea>
      <!-- 제목 표시가 안됨! -->
      <b-textarea
        v-model="todayDiary"
        placeholder="내용을 입력하세요"
      ></b-textarea>
    </b-container>

    <div class="footer-fixed">
      <b-button
        block
        squared
        style="height:58px"
        variant="warning"
        @click="finish"
        >완료</b-button
      >
    </div>
  </div>
</template>

<script>
import { WithdrawalAccount, DepositAccount } from '../../api/account.js';

export default {
  name: 'writecontent',

  data() {
    return {
      body: {},
      todayDiary: '',
    };
  },
  created() {
    this.body = this.$route.params;
    console.log(this.body);
  },
  methods: {
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
            Iscd: '000536',
            FintechApsno: '001',
            ApiSvcCd: 'DrawingTransferA',
            IsTuno: tuno,
            AccessToken:
              'f97844c4b63e36f17d0d67c8bd9761768f50a38dc14c3fef4c7b812e427f0d59',
          },
          FinAcno: this.$route.params.pinAccount,
          Tram: this.body.sum,
          DractOtlt: '테스트',
          MractOtlt: '테스트',
        },
        (response) => {
          console.log(response);
          DepositAccount(
            {
              Header: {
                ApiNm: 'ReceivedTransferAccountNumber',
                Tsymd: today,
                Trtm: '112428',
                Iscd: '000536',
                FintechApsno: '001',
                ApiSvcCd: 'DrawingTransferA',
                IsTuno: tuno2,
                AccessToken:
                  'f97844c4b63e36f17d0d67c8bd9761768f50a38dc14c3fef4c7b812e427f0d59',
              },
              Bncd: '011',
              Acno: this.body.babyAccount,
              Tram: this.body.sum,
              DractOtlt: '테스트',
              MractOtlt: this.body.content,
            },
            (response) => {
              console.log(response);
              this.$emit('finishTransfer', this.todayDiary);
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

<style scoped></style>
