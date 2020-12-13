<template>
  <div class="px-2 pb-2">
    <b-card class="p-0">
      <b-row align-v="center" class="justify-content-between">
        <b-col cols="2" class="p-0 text-center">
          <b-avatar></b-avatar>
        </b-col>
        <b-col cols="8" class="p-0">
          <h5 class="m-0">
            <strong>{{ baby.nickname }}</strong> {{ this.message }}
          </h5>
          <h6 class="m-0">
            우리가 만난지 <strong>{{ calDay(baby.birthday) }}</strong
            >일
          </h6>
        </b-col>
        <b-col cols="2" class="ml-auto text-center p-0">
          <b-button variant="outline-success" size="lg" pill
            ><b-icon icon="pencil-fill" @click="insertDiary"></b-icon
          ></b-button>
        </b-col>
      </b-row>
    </b-card>
  </div>
</template>

<script>
export default {
  props: ['baby'],
  data() {
    return {
      message: '',
    };
  },
  created() {
    console.log(this.baby);
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
    calDay(date) {
      var day = new Date(date);
      var now = new Date();
      var gap = now.getTime() - day.getTime();
      return Math.floor(gap / (1000 * 60 * 60 * 24));
    },
    insertDiary() {
      console.log(this.baby.no);
      this.$router.push({
        name: 'WriteAccount',
      });
    },
  },
};
</script>

<style scoped></style>
