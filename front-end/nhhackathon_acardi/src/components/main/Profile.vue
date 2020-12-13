<template>
  <div class="px-2 pb-2">
    <b-card class="px-2">
      <b-row align-v="center" class="justify-content-between">
        <b-col cols="2" class="p-0">
          <img style="width:50px; height:50px;" />
        </b-col>
        <b-col cols="8" class="p-0">
          <h5>{{ this.baby.nickname }} 최고야❤️</h5>
          <h6>우리가 만난지 {{ this.dday }}일</h6>
        </b-col>
        <b-col cols="2" class="ml-auto text-right p-0">
          <b-button pill @click="insertDiary"
            ><b-icon icon="pencil"></b-icon
          ></b-button>
        </b-col>
      </b-row>
    </b-card>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  props: ['babyno'],
  data() {
    return {
      baby: {
        no: '',
        nickname: '',
        birthday: '',
        gender: '',
        birth_time: '',
        account: '',
        profile: '',
      },
      dday: '',
    };
  },
  created() {
    this.babyno = this.$route.params.no;
    axios
      .get('http://localhost/baby/' + this.babyno)
      .then((response) => {
        console.log(response);
        this.baby = response.data;
        this.dday = this.calDay(this.baby.birthday);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    setValue(val) {
      //console.log('profile: ' + this.babyno + val);
      this.babyno = val;
      console.log(this.babyno);
      axios
        .get('http://localhost/baby/' + this.babyno)
        .then((response) => {
          console.log(response);
          this.baby = response.data;
          this.dday = this.calDay(this.baby.birthday);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    calDay(date) {
      var day = new Date(date);
      var now = new Date();
      var gap = now.getTime() - day.getTime();
      return Math.floor(gap / (1000 * 60 * 60 * 24));
    },
    insertDiary() {
      this.$router.push({
        name: 'Write',
        params: { babyno: this.baby.no },
      });
    },
  },
};
</script>

<style scoped></style>
