<template>
  <div id="container" class="footer-spacing">
    <h1>
      <b-icon icon="arrow-left" class="p-1 m-2" @click="goBack"></b-icon>
    </h1>

    <b-container fluid="sm">
      <!-- 아이와의 관계 입력 -->
      <h3>
        {{ userId }}님, <br />
        {{ babyInfo.nickname }}와의 관계를 알려주세요.
      </h3>
      <b-input
        v-model="relation"
        type="text"
        placeholder="예) 엄마/아빠/가족/보호자"
      ></b-input>
      <br />
      <br />

      <h3>아이를 위한<br />계좌를 알려주세요.</h3>
      <b-row class="mb-2">
        <b-col>
          <b-input-group>
            <b-input
              v-model="babyInfo.account"
              type="text"
              placeholder="계좌번호"
            ></b-input>
            <b-button
              ><b-icon icon="check" @click="accountCheck"></b-icon
            ></b-button>
          </b-input-group>
        </b-col>
      </b-row>
    </b-container>
    <div class="footer-fixed">
      <b-button
        v-if="!relation || !accountChecked"
        disabled
        block
        squared
        style="height:58px"
        >등록</b-button
      >
      <b-button
        v-else
        @click="registerBaby"
        block
        squared
        style="height: 58px"
        variant="warning"
        >등록</b-button
      >
    </div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  props: {
    babyInfo: Object,
  },
  data() {
    return {
      userId: this.$session.get('userID'),
      accountChecked: false,
      relation: this.babyInfo.relation,
    };
  },
  methods: {
    goBack() {
      this.babyInfo.relation = this.relation;
      this.$emit('goProfile');
    },
    accountCheck() {
      //계좌 확인 - nh rest api 활용하여 예금주 확인 후 (fin) account 저장
      //Join.vue/makeFin() 내용 수정
      var date = new Date();
      var today =
        String(date.getFullYear()) +
        String(date.getMonth() + 1) +
        String(date.getDate());
      console.log(today);
      var tuno = Math.floor(Math.random() * 99999999999999999999);
      console.log(tuno);
      console.log(this.babyInfo.account);
      axios
        .post(
          'https://developers.nonghyup.com/InquireDepositorAccountNumber.nh',
          {
            Header: {
              ApiNm: 'InquireDepositorAccountNumber',
              Tsymd: today,
              Trtm: '112428',
              Iscd: this.$store.state.Iscd,
              FintechApsno: '001',
              ApiSvcCd: 'DrawingTransferA',
              IsTuno: tuno,
              AccessToken: this.$store.state.AccessToken,
            },
            Bncd: '011',
            Acno: this.babyInfo.account,
          }
        )
        .then((response) => {
          console.log(response);
          this.accountChecked = confirm(response.data.Dpnm + '님 맞으신가요?');
          console.log(this.accountChecked);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    registerBaby() {
      if (!this.relation) {
        alert('아이와의 관계를 입력해주세요.');
      } else if (!this.accountChecked) {
        alert('계좌번호를 확인해주세요.');
      } else {
        console.log(this.babyInfo);
        axios
          .post('http://localhost/baby', this.babyInfo)
          .then((response) => {
            console.log('baby regist reponse:' + response.data);
            if (response.data > 0) {
              alert('아이 추가를 완료했습니다.');
              //family 등록
              var family = {
                member_id: this.userId,
                baby_no: response.data,
                relation: this.relation,
              };
              console.log(family);
              axios
                .post('http://localhost/family', family)
                .then((response) => {
                  console.log(response);
                })
                .catch((error) => {
                  console.log(error);
                });
              this.$router.push({
                path: '/main',
              });
              window.location.reload('/main');
            } else {
              alert('아이 추가가 되지 않았습니다.');
            }
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
  },
};
</script>

<style scoped>
#container {
  min-height: 100vh;
}
</style>
