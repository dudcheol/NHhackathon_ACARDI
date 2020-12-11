<template>
  <div>
    <h1>register</h1>
    <b-button @click="test" variant="dark">계좌 얻어오기 Test</b-button>
    <p>{{ this.name }}</p>

    <br />
    <br />

    <b-button @click="create" variant="dark">Pin Account 생성 Test</b-button>

    <br />
    <br />
    <b-button @click="check" variant="dark">Pin Account check</b-button>
    <br />
    <br />
    <b-button @click="search" variant="dark">잔액조회</b-button>
    <p>{{ this.Ldbl }}</p>
    <!-- 잔액 -->
    <br />
    <br />
    <b-button @click="deposit" variant="dark">입금</b-button>
  </div>
</template>

<script>
import {
  NumberAccount,
  CreatePinAccount,
  CheckPinAccount,
  SearchAccount,
  WithdrawalAccount,
  DepositAccount,
} from '@/api/account.js';

export default {
  name: 'register',
  data() {
    return {
      form: {
        // 1. Header : Header
        // 2.
        Header: {
          // store에서 필요한 값들은 얻어와야 할 듯. (Iscd, AccessToken)
          // 날짜도 자동으로 바뀌게 해줘야 함.
          ApiNm: 'InquireDepositorAccountNumber',
          Tsymd: '20201211',
          Trtm: '112428',
          Iscd: '000536',
          FintechApsno: '001',
          ApiSvcCd: 'DrawingTransferA',
          IsTuno: '123456789', //시간 + 고유값.
          AccessToken:
            'f97844c4b63e36f17d0d67c8bd9761768f50a38dc14c3fef4c7b812e427f0d59',
        },
        // Bncd, Acno은 버튼을 누를 때 얻어와야 함.
        Bncd: '011',
        Acno: '3020000002233',
      },
      pin: {
        //발급하고 확인하는 절차가 필요함. 발급은 정상적으로 잘 됨.
        Header: {
          ApiNm: 'OpenFinAccountDirect',
          Tsymd: '20201211',
          Trtm: '112428',
          Iscd: '000536',
          FintechApsno: '001',
          ApiSvcCd: 'DrawingTransferA',
          IsTuno: '202012110000000012',
          AccessToken:
            'f97844c4b63e36f17d0d67c8bd9761768f50a38dc14c3fef4c7b812e427f0d59',
        },
        DrtrRgyn: 'Y',
        BrdtBrno: '19940316',
        Bncd: '011',
        Acno: '3020000002233',
      },
      pinCheck: {
        Header: {
          ApiNm: 'CheckOpenFinAccountDirect',
          Tsymd: '20201211',
          Trtm: '112428',
          Iscd: '000536',
          FintechApsno: '001',
          ApiSvcCd: 'DrawingTransferA',
          IsTuno: '202012110000000011',
          AccessToken:
            'f97844c4b63e36f17d0d67c8bd9761768f50a38dc14c3fef4c7b812e427f0d59',
        },
        BrdtBrno: '19940316',
        Rgno: '',
      },
      money: {
        Header: {
          ApiNm: 'InquireBalance',
          Tsymd: '20201211',
          Trtm: '112428',
          Iscd: '000536',
          FintechApsno: '001',
          ApiSvcCd: 'DrawingTransferA',
          IsTuno: '202012110000000060',
          AccessToken:
            'f97844c4b63e36f17d0d67c8bd9761768f50a38dc14c3fef4c7b812e427f0d59',
        },
        FinAcno: '00820100005360000000000004191',
      },
      getMoney: {
        Header: {
          ApiNm: 'DrawingTransfer',
          Tsymd: '20201211',
          Trtm: '112428',
          Iscd: '000536',
          FintechApsno: '001',
          ApiSvcCd: 'DrawingTransferA',
          IsTuno: '202012110000000100',
          AccessToken:
            'f97844c4b63e36f17d0d67c8bd9761768f50a38dc14c3fef4c7b812e427f0d59',
        },
        FinAcno: '00820100005360000000000004191',
        Tram: '1000000',
        DractOtlt: '테스트',
        MractOtlt: '테스트',
      },
      sendMoney: {
        Header: {
          ApiNm: 'ReceivedTransferAccountNumber',
          Tsymd: '20201211',
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
        Tram: '200000',
        DractOtlt: '테스트',
        MractOtlt: '테스트',
      },
      name: '',
      Ldbl: '',
    };
  },
  methods: {
    test() {
      NumberAccount(
        this.form,
        (response) => {
          console.log(response);
          this.name = response.data.Dpnm;
        },
        (error) => {
          console.log(error);
        }
      );
    },
    create() {
      CreatePinAccount(
        this.pin,
        (response) => {
          console.log(response);
          //여기서 받아오는 Rgno를 저장해두어야 함.
          this.pinCheck.Rgno = response.data.Rgno;
          CheckPinAccount(
            this.pinCheck,
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
    check() {
      CheckPinAccount(
        this.pinCheck,
        (response) => {
          console.log(response);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    search() {
      SearchAccount(
        this.money,
        (response) => {
          console.log(response);
          this.Ldbl = response.data.Ldbl;
        },
        (error) => {
          console.log(error);
        }
      );
    },
    deposit() {
      WithdrawalAccount(
        this.getMoney,
        (response) => {
          console.log(response);
        },
        (error) => {
          console.log(error);
        }
      );
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
  },
};
</script>

<style scoped></style>
