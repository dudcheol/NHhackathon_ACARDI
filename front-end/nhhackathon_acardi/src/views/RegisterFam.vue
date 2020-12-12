<template>
  <div>
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
export default {
  name: 'registerFam',

  data() {
    return {
      babyno: '',
    };
  },
  created() {
    this.babyno = this.$route.params.babyno;
    console.log(this.babyno);
  },
  methods: {
    onClick() {
      var url = 'http://localhost:8080/join?babyno=' + this.babyno;
      console.log(url);
      window.Kakao.Link.createDefaultButton({
        container: '.kakao-link',
        objectType: 'feed',
        content: {
          title: '새록새록!',
          description: '지금 가입하세요.',
          imageUrl:
            'http://mud-kage.kakao.co.kr/dn/NTmhS/btqfEUdFAUf/FjKzkZsnoeE4o19klTOVI1/openlink_640x640s.jpg',
          link: {
            webUrl: url,
            mobileWebUrl: url,
          },
        },
        social: {
          likeCount: 10,
          commentCount: 20,
          sharedCount: 30,
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
