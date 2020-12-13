<template>
  <div>
    <b-container id="container" fluid="sm" style="padding-bottom:58px">
      <br />
      <br />
      <b-row>
        <b-col>
          <h2><strong>육아통장</strong>,</h2>
          <h3><strong>가족</strong>과 함께 꾸며보세요.</h3>
        </b-col>
      </b-row>
      <br />
      <b-row class="pt-5">
        <b-col class="text-center">
          <img
            src="https://www.flaticon.com/svg/static/icons/svg/3597/3597740.svg"
            style="height:200px"
          />
        </b-col>
      </b-row>
      <br />
      <!-- <b-row align-v="center">
        <b-col class="text-right p-0">
          <h1><b-icon icon="emoji-heart-eyes"></b-icon></h1>
        </b-col>
        <b-col cols="2" class="text-center p-0">
          <h1><b-icon icon="link"></b-icon></h1>
        </b-col>
        <b-col class="text-left p-0">
          <h1><b-icon icon="emoji-heart-eyes"></b-icon></h1>
        </b-col>
      </b-row> -->
      <br />
      <b-row>
        <b-col class="text-center">
          <h5>아래 <b-badge variant="warning">공유하기</b-badge>를 클릭해서</h5>
          <h5>새록새록 육아통장을 함께 사용해보세요!</h5>
        </b-col>
      </b-row>
    </b-container>
    <div class="footer-fixed">
      <b-button
        class="kakao-link"
        block
        squared
        style="height:58px"
        variant="warning"
        @click="onClick"
        >공유하기</b-button
      >
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'registerFam',

  data() {
    return {
      babyno: '',
      babyname: '',
    };
  },
  created() {
    this.babyno = this.$store.state.babyno;
    axios
      .get(`http://localhost/baby/${this.babyno}`)
      .then((res) => {
        console.log(res);
        this.babyname = res.data.nickname;
      })
      .catch((err) => {
        console.error(err);
      });
    console.log(this.babyno);
  },
  methods: {
    onClick() {
      var url = 'http://localhost:8080/join?babyno=' + this.babyno;
      var descr = this.babyname + '의 일기에 가입하세요.';
      console.log(url);
      window.Kakao.Link.createDefaultButton({
        container: '.kakao-link',
        objectType: 'feed',
        content: {
          title: '새록새록!',
          description: descr,
          imageUrl:
            'https://cdn.crowdpic.net/list-thumb/thumb_l_238B4B58BB065F4F5E114CDFAD1E70AC.jpg',
          link: {
            webUrl: url,
            mobileWebUrl: url,
          },
        },
        social: {
          likeCount: 100,
          commentCount: 200,
          sharedCount: 300,
        },
        buttons: [
          {
            title: '웹으로 이동',
            link: {
              mobileWebUrl: url,
              webUrl: url,
            },
          },
          {
            title: '앱으로 이동',
            link: {
              mobileWebUrl: url,
              webUrl: url,
            },
          },
        ],
      });
    },
  },
};
</script>

<style scoped>
.temp {
  padding-top: 20px;
  position: fixed;
}
</style>
