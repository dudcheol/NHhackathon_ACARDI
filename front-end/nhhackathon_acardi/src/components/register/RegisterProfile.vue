<template>
  <b-container id="container">
    <!-- 아이 이름 입력 -->
    <h3>
      {{ username }}님,<br />
      아이에 대해 알려주세요.
    </h3>

    <!-- 아이 프로필 사진 -->
    <b-jumbotron>
      <b-icon icon="camera-fill" @click="selectPhoto"></b-icon>
    </b-jumbotron>

    <!-- 아이 이름 -->
    <h4>이름/별명</h4>
    <b-input v-model="babyInfo.nickname" type="text" placeholder=""></b-input>
    <br />

    <!-- 성별 선택-->
    <h4>성별</h4>
    <b-row>
      <b-col>
        <h1 @click="gender('male')">
          <b-icon :icon="male" variant="primary"></b-icon>남자
        </h1>
      </b-col>
      <b-col>
        <h1 @click="gender('female')">
          <b-icon :icon="female" variant="danger"></b-icon>여자
        </h1>
      </b-col>
    </b-row>
    <br />

    <!-- 아이 생일/생시 입력 -->
    <h4>생일</h4>
    <b-datepicker v-model="babyInfo.birthday"></b-datepicker>
    <br />
    <b-timepicker v-model="babyInfo.birth_time"></b-timepicker>
    <br />

    <div class="footer-fixed">
      <b-button
        @click="next"
        block
        squared
        style="height:58px"
        variant="warning"
        >다음</b-button
      >
    </div>
  </b-container>
</template>

<script>
export default {
  data() {
    return {
      username: this.$session.get('userID'),
      male: 'emoji-laughing',
      female: 'emoji-laughing',
      babyInfo: {
        nickname: '',
        birthday: '',
        gender: 0,
        birth_time: '',
        account: '',
        profile: '',
      },
    };
  },
  methods: {
    next() {
      if (!this.babyInfo.nickname) {
        alert('이름을 입력해주세요.');
      } else if (!this.babyInfo.birthday || !this.babyInfo.birth_time) {
        alert('생일을 선택해주세요.'); //생일 default값 지정 필요시 수정할 부분
        // } else if(!this.babyInfo.profile){
        //   alert('프로필 사진을 선택해주세요.');
      } else {
        this.$emit('babyProfile', this.babyInfo);
      }
    },
    selectPhoto() {
      console.log('프로필 사진 선택');
      this.babyInfo.profile = 'temp'; //선택한 사진의 경로로 저장. default값 지정 필요
    },
    gender(g) {
      if (g == 'male') {
        this.male = 'emoji-laughing-fill';
        this.female = 'emoji-laughing';
        this.babyInfo.gender = 1;
      } else {
        this.male = 'emoji-laughing';
        this.female = 'emoji-laughing-fill';
        this.babyInfo.gender = 2;
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
