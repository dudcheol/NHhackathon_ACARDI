<template>
  <div style="padding-top:20px">
    <router-link to="/write/account">write-add account</router-link>
    |
    <router-link to="/write/content">write-content</router-link>

    <div style="padding-bottom:58px">
      <router-view @update="onUpdateContent" :money="localMoney"></router-view>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

import {
  SearchAccount,
  //   WithdrawalAccount,
  //   DepositAccount,
} from '@/api/account.js';

export default {
  name: 'write',
  tuno: Math.floor(Math.random() * 99999999999999999999),
  //return 아래와 위에 선언하는 field 차이 물어보기.
  data() {
    return {
      body: {
        id: '', //사용할 아이디.
        pinAccount: '', //사용할 핀어카운트.
      },
      localMoney: '',
      money: {
        Header: {
          ApiNm: 'InquireBalance',
          Tsymd: '20201212',
          Trtm: '112428',
          Iscd: '000536',
          FintechApsno: '001',
          ApiSvcCd: 'DrawingTransferA',
          IsTuno: this.tuno,
          AccessToken:
            'f97844c4b63e36f17d0d67c8bd9761768f50a38dc14c3fef4c7b812e427f0d59',
        },
        FinAcno: '00820100005360000000000004191',
      },
    };
  },
  created() {
    this.body.id = this.$session.get('userID');
    console.log(this.id);
    SearchAccount(
      this.money,
      (response) => {
        console.log(response);
        this.localMoney = response.data.Ldbl;
      },
      (error) => {
        console.log(error);
      }
    );
    // 여기서 핀-어카운트 얻어와야 함.
    axios
      .get(`http://localhost/member/${this.id}`)
      .then((response) => {
        console.log(response);
        this.body.pinAccount = response.data.pinAccount;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    onUpdateContent(body) {
      //이름 받아옴.
      this.body.sum = body.sum;
      this.body.content = body.content;
      this.$router.push({ name: 'WriteContent', params: { body } });
    },
  },
};
</script>

<style scoped></style>
