<template>
  <div class="px-2 pb-2">
    <b-card class="px-2">
      <b-row align-v="center" class="justify-content-between">
        <b-col cols="2" class="p-0">
          <b-avatar></b-avatar>
        </b-col>
        <b-col cols="8" class="p-0">
          <h5>{{ this.baby.nickname }} 최고야❤️</h5>
          <h6>d-day area {{ this.baby.birthday }}</h6>
        </b-col>
        <b-col cols="2" class="ml-auto text-right p-0">
          <b-button pill
            ><b-icon icon="pencil" @click="onClick"></b-icon
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
    };
  },
  created() {
    //console.log(this.$route.query.baby_no);
    console.log(this.babyno);
    axios
      .get('http://localhost/baby/' + this.babyno)
      .then((response) => {
        console.log(response);
        this.baby = response.data;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  updated() {
    //console.log(this.babyno);
    //console.log(this.$route.query.baby_no);
  },
  methods: {
    onClick() {
      console.log(this.babyno);
      this.$router.push({ name: 'Write', params: { babyno: this.baby.no } });
    },
  },
};
</script>

<style scoped></style>
