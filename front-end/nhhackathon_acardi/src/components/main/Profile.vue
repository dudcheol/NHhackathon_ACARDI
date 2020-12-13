<template>
  <div class="px-2 pb-2">
    <b-card class="px-2">
      <b-row align-v="center" class="justify-content-between">
        <b-col cols="2" class="p-0">
          <!-- <b-avatar :v-show="src == ''"></b-avatar> -->
          <img
            :src="require(`@/assets/img/${baby.no}/profile/profile.jpg`)"
            style="width:60px; height:60px; border-radius: 15px;"
          />
          {{ `@/assets/img/${baby.no}/profile/profile.jpg` }}
        </b-col>
        <b-col cols="8" class="p-0">
          <h5>
            <strong>{{ baby.nickname }}</strong> {{ this.message }}
          </h5>
          <h6>
            우리가 만난지 <strong>{{ this.dday }}</strong
            >일
          </h6>
        </b-col>
        <b-col cols="2" class="ml-auto text-right p-0">
          <b-button pill
            ><b-icon icon="pencil" @click="insertDiary"></b-icon
          ></b-button>
        </b-col>
      </b-row>
    </b-card>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  props: ['baby'],
  data() {
    return {
      dday: '',
      message: '',
      imgsrc: '',
    };
  },
  watch: {
    baby: function(val) {
      this.dday = this.calDay(val.birthday);
    },
  },
  created() {
    //this.no = this.$store.state.babyno;
    var num = Math.floor(Math.random() * 4);
    switch (num) {
      case 0:
        this.message = '최고야❤️';
        break;
      case 1:
        this.message = '멋있어❤️';
        break;
      case 2:
        this.message = '사랑해❤️';
        break;
      case 3:
        this.message = '좋아해❤️';
        break;
    }
  },
  methods: {
    setValue(val) {
      this.babyno = val;
      axios
        .get('http://localhost/baby/' + this.babyno)
        .then((response) => {
          console.log(response);
          this.imgsrc = response.data.profile;
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
      console.log(this.baby.no);
      this.$router.push({
        name: 'Write',
      });
    },
  },
};
</script>

<style scoped></style>
