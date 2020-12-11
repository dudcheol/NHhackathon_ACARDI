<template>
  <div>
    <b-container>
      <h6>
        현재 잔액 <b-badge>{{ money }}</b-badge
        >원
      </h6>
      <h3>얼마를 입금할까요?</h3>
      <b-button-group>
        <b-button @click="addOne">+1만</b-button>
        <b-button @click="addFive">+5만</b-button>
        <b-button @click="addTen">+10만</b-button>
        <b-button @click="addHundred">+100만</b-button>
        <b-button>잔액</b-button>
      </b-button-group>
      <b-input
        type="text"
        v-model.number="sum"
        size="lg"
        placeholder="잔액을 입력하세요"
      >
      </b-input>
      <b-input
        type="text"
        v-model="content"
        size="lg"
        placeholder="내용을 입력하세요"
      ></b-input>
    </b-container>

    <div class="footer-fixed">
      <b-button
        block
        squared
        style="height:58px"
        variant="warning"
        @click="signin"
        >다음</b-button
      >
    </div>
  </div>
</template>

<script>
export default {
  props: ['money'],
  data() {
    return {
      body: {
        sum: 0,
        content: '',
      },
    };
  },
  methods: {
    addOne() {
      this.sum += 10000;
    },
    addFive() {
      this.sum += 50000;
    },
    addTen() {
      this.sum += 100000;
    },
    addHundred() {
      this.sum += 1000000;
    },
    signin() {
      if (this.sum > this.money) {
        alert('금액을 확인해 주세요!');
      } else {
        this.$emit('update', this.body);
      }
    },
  },
};
</script>

<style scoped></style>
