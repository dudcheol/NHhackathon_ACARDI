<template>
  <div>
    <Image
      src="https://cdn.pixabay.com/photo/2014/10/28/20/29/baby-507132_960_720.png"
    />

    <div class="footer-fixed">
      <b-button
        class="kakao-link"
        block
        squared
        style="height:58px"
        variant="warning"
        @click="onClick"
        >전송</b-button
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
