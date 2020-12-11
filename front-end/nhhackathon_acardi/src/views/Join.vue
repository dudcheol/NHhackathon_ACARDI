<template>
  <div>
    <b-container fluid="sm" class="h-100 footer-spacing">
      <h1 class="pl-2 py-2">회원가입</h1>
      <b-row align-h="center m-2">
        <b-col>
          <b-row class="mb-2">
            <b-input v-model="memberInfo.id" placeholder="아이디"></b-input>
          </b-row>
          <b-row class="mb-2">
            <b-input
              type="password"
              v-model="memberInfo.password"
              placeholder="비밀번호"
            ></b-input>
          </b-row>
          <b-row class="mb-2">
            <b-input
              type="password"
              @keyup="confirmPass"
              v-model="pass"
              placeholder="비밀번호 확인"
            ></b-input>
          </b-row>
          <p>{{ this.confirmText }}</p>
          <b-row class="mb-2">
            <b-input
              type="email"
              v-model="memberInfo.email"
              placeholder="이메일"
            ></b-input>
          </b-row>
          <b-row class="mb-2">
            <b-input
              type="tel"
              v-model="memberInfo.phone_number"
              placeholder="휴대폰 번호"
            ></b-input>
          </b-row>
          <b-row class="mb-2">
            <b-input-group>
              <b-form-input
                v-model="birthday"
                type="text"
                placeholder="생년월일 YYYY-MM-DD"
                autocomplete="off"
              ></b-form-input>
              <b-input-group-append>
                <b-form-datepicker
                  v-model="birthday"
                  button-only
                  right
                  locale="en-US"
                  aria-controls="example-input"
                  @context="onContext"
                ></b-form-datepicker>
              </b-input-group-append>
            </b-input-group>
          </b-row>
          <b-row class="mb-2">
            <b-input-group>
              <b-input
                type="text"
                v-model="account"
                placeholder="계좌번호"
              ></b-input>
              <b-button @click="makeFin"
                ><b-icon icon="check"></b-icon
              ></b-button>
            </b-input-group>
          </b-row>
        </b-col>
      </b-row>
    </b-container>

    <div class="footer-fixed">
      <b-button
        block
        squared
        style="height:58px"
        variant="warning"
        @click="signin"
        >회원가입</b-button
      >
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      date: '',
      birthday: '',
      formatted: '',
      selected: '',
      account: '',
      pass: '',
      confirmText: '',
      memberInfo: {
        id: '',
        password: '',
        name: '',
        phone_number: '',
        email: '',
        fin_account: '',
        status: 1,
      },
      AccessToken: '',
      Iscd: '',
    };
  },
  mounted() {
    this.AccessToken = this.$store.state.AccessToken;
    this.Iscd = this.$store.state.Iscd;
  },
  methods: {
    onContext(ctx) {
      this.formatted = ctx.selectedFormatted;
      this.selected = ctx.selectedYMD;
    },
    confirmPass() {
      if (this.pass == this.memberInfo.password) {
        this.confirmText = '';
      } else {
        this.confirmText = '비밀번호가 일치하지 않습니다.';
      }
    },
    makeFin() {
      var date = new Date();
      var today =
        String(date.getFullYear()) +
        String(date.getMonth() + 1) +
        String(date.getDate());
      console.log(today);
      var tuno = Math.floor(Math.random() * 99999999999999999999);
      console.log(tuno);
      console.log(this.account);
      console.log(this.Iscd, this.AccessToken);
      axios
        .post(
          'https://developers.nonghyup.com/InquireDepositorAccountNumber.nh',
          {
            Header: {
              ApiNm: 'InquireDepositorAccountNumber',
              Tsymd: today,
              Trtm: '112428',
              Iscd: this.Iscd,
              FintechApsno: '001',
              ApiSvcCd: 'DrawingTransferA',
              IsTuno: tuno,
              AccessToken: this.AccessToken,
            },
            Bncd: '011',
            Acno: this.account,
          }
        )
        .then((response) => {
          console.log(response);
          var res = confirm(response.data.Dpnm + '님 맞으신가요?');
          this.memberInfo.name = response.data.Dpnm;
          console.log(res);
          if (res) {
            var tuno = Math.floor(Math.random() * 99999999999999999999);
            console.log(tuno);
            var birth = this.birthday.replaceAll('-', '');
            console.log(birth);
            axios
              .post('https://developers.nonghyup.com/OpenFinAccountDirect.nh', {
                Header: {
                  ApiNm: 'OpenFinAccountDirect',
                  Tsymd: today,
                  Trtm: '112428',
                  Iscd: this.Iscd,
                  FintechApsno: '001',
                  ApiSvcCd: 'DrawingTransferA',
                  IsTuno: tuno,
                  AccessToken: this.AccessToken,
                },
                DrtrRgyn: 'Y',
                BrdtBrno: birth,
                Bncd: '011',
                Acno: this.account,
              })
              .then((response) => {
                console.log(response);
                var tuno = Math.floor(Math.random() * 99999999999999999999);
                axios
                  .post(
                    'https://developers.nonghyup.com/CheckOpenFinAccountDirect.nh',
                    {
                      Header: {
                        ApiNm: 'CheckOpenFinAccountDirect',
                        Tsymd: today,
                        Trtm: '112428',
                        Iscd: this.Iscd,
                        FintechApsno: '001',
                        ApiSvcCd: 'DrawingTransferA',
                        IsTuno: tuno,
                        AccessToken: this.AccessToken,
                      },
                      Rgno: response.data.Rgno,
                      BrdtBrno: birth,
                    }
                  )
                  .then((response) => {
                    console.log(response);
                    this.memberInfo.fin_account = response.data.FinAcno;
                  })
                  .catch((error) => {
                    console.log(error);
                  });
              })
              .catch((error) => {
                console.log(error);
              });
          } else {
            this.account = '';
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    signin() {
      console.log(this.memberInfo);
      axios
        .post('http://localhost/member', this.memberInfo)
        .then((response) => {
          console.log(response);
          if (response.data.result == '추가성공') {
            alert('새록새록 육아통장에 오신 것을 환영합니다!');
            this.$session.start();
            this.$session.set('userID', this.memberInfo.id);
            this.$router.push({
              path: '/main',
            });
            window.location.reload('/main');
          } else {
            alert('회원가입에 실패했습니다. 다시 진행해주세요.');
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style scoped></style>
