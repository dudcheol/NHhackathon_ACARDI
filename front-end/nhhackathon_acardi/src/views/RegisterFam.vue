<template>
  <div>
    <b-container id="container" fluid="sm" class="px-5">
      <b-row align-v="center" class="vh-100" style="padding-bottom:58px">
        <b-col class="text-right p-0">
          <h1><b-icon icon="emoji-heart-eyes"></b-icon></h1>
        </b-col>
        <b-col class="text-center p-0">
          <h1><b-icon icon="link"></b-icon></h1>
        </b-col>
        <b-col class="text-left p-0">
          <h1><b-icon icon="emoji-heart-eyes"></b-icon></h1>
        </b-col>
      </b-row>
      <b-row>
        초대하려면 전송버튼을 클릭하세요!
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
