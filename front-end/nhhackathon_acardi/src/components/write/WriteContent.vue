<template>
  <div>
    <b-container>
      <b-button><b-icon icon="camera-fill"></b-icon></b-button>
      <p>{{ this.body.title }}</p>
      <b-textarea> {{ this.body.title }} </b-textarea>
      <!-- 제목 표시가 안됨! -->
      <b-textarea placeholder="내용을 입력하세요"></b-textarea>
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
import { WithdrawalAccount, DepositAccount } from '@/api/account.js';

export default {
  data() {
    return {
      // 아이의 계좌 얻어오는 방법을 고민해봐야 함.
      body: this.$route.params.body,
      getMoney: {
        Header: {
          ApiNm: 'DrawingTransfer',
          Tsymd: '20201212',
          Trtm: '112428',
          Iscd: '000536',
          FintechApsno: '001',
          ApiSvcCd: 'DrawingTransferA',
          IsTuno: '202012110000000100',
          AccessToken:
            'f97844c4b63e36f17d0d67c8bd9761768f50a38dc14c3fef4c7b812e427f0d59',
        },
        FinAcno: this.body.pinAccount,
        Tram: '1000000',
        DractOtlt: '테스트',
        MractOtlt: '테스트',
      },
      sendMoney: {
        Header: {
          ApiNm: 'ReceivedTransferAccountNumber',
          Tsymd: '20201212',
          Trtm: '112428',
          Iscd: '000536',
          FintechApsno: '001',
          ApiSvcCd: 'DrawingTransferA',
          IsTuno: '202012110000000101',
          AccessToken:
            'f97844c4b63e36f17d0d67c8bd9761768f50a38dc14c3fef4c7b812e427f0d59',
        },
        Bncd: '011',
        Acno: '3020000002233',
        Tram: this.body.sum,
        DractOtlt: '테스트',
        MractOtlt: this.body.content,
      },
    };
  },
  methods: {
    finish() {
      WithdrawalAccount(
        this.getMoney,
        (response) => {
          console.log(response);
          DepositAccount(
            this.sendMoney,
            (response) => {
              console.log(response);
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
