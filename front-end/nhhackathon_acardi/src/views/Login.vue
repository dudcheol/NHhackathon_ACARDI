<template>
  <div>
    <b-container id="container" fluid="sm" class="px-5">
      <b-row align-v="center" class="vh-100">
        <b-col>
          <b-row align-h="center" class="pb-3">
            <h1>
              <b-icon icon="emoji-laughing"></b-icon>
              icon area
            </h1>
          </b-row>
          <b-row class="mt-2">
            <b-input v-model="memberInfo.id" placeholder="ID"></b-input>
          </b-row>
          <b-row class="mt-2">
            <b-input
              type="password"
              v-model="memberInfo.password"
              placeholder="비밀번호"
            ></b-input>
          </b-row>
          <b-row class="mt-4">
            <b-button block pill @click="login">로그인</b-button>
          </b-row>
          <b-row class="mt-2">
            <b-button block pill @click="join">회원가입</b-button>
          </b-row>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      memberInfo: {
        id: '',
        password: '',
      },
    };
  },
  methods: {
    login() {
      axios
        .post('http://localhost/login', this.memberInfo)
        .then((response) => {
          console.log(response.data.message);
          if (response.data.message == '로그인 성공') {
            this.$session.start();
            this.$session.set('userID', this.memberInfo.id);
            alert(this.$session.get('userID') + '님, 환영합니다!');
            this.$router.push({ name: 'Main' });
          } else {
            alert('ID와 비밀번호를 다시 입력해주세요.');
            (this.memberInfo.id = ''), (this.memberInfo.password = '');
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    join() {
      this.$router.push({ name: 'Join' });
    },
  },
};
</script>

<style scoped>
#container {
  min-height: 100vh;
}
</style>
