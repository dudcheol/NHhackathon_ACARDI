<template>
  <div>
    <h2 style="position:fixed">
      <b-icon icon="arrow-left" class="p-1 m-2" @click="back"></b-icon>
    </h2>
    <div
      style="height:100vh; padding-bottom:58px"
      class="d-flex align-items-center justify-content-center"
    >
      <b-container>
        <!-- <b-row>
        <b-col class="text-right">
          <h6>
            현재 잔액 <b-badge>{{ money }}</b-badge> 원
          </h6>
        </b-col>
      </b-row> -->
        <b-row class="pb-3">
          <b-col>
            <h1>얼마를 <strong>입금</strong>할까요?</h1>
          </b-col>
        </b-row>
        <b-row>
          <b-col cols="7">
            <b-button-group size="sm">
              <b-button @click="addOne" variant="warning">+1만</b-button>
              <b-button @click="addFive" variant="warning">+5만</b-button>
              <b-button @click="addTen" variant="warning">+10만</b-button>
              <b-button @click="addHundred" variant="warning">+100만</b-button>
              <b-button @click="allMoney" variant="success">전액</b-button>
            </b-button-group>
          </b-col>
          <b-col class="text-right" align-self="end"
            ><h6>
              잔액
              <b-badge variant="light">{{ money | moneycomma }}</b-badge> 원
            </h6></b-col
          >
        </b-row>
        <b-row class="pb-3">
          <b-col>
            <!-- <b-input
            type="number"
            v-model.number="body.sum"
            size="lg"
            placeholder="잔액을 입력하세요"
          >
          </b-input> -->
            <b-input-group prepend="₩">
              <b-form-input
                size="lg"
                v-model.number="body.sum"
                type="number"
                placeholder="잔액을 입력하세요"
                class="text-right"
              ></b-form-input>
              <b-input-group-append>
                <b-button variant="outline-info" @click="clickCancel"
                  >취소</b-button
                >
              </b-input-group-append>
            </b-input-group>
          </b-col>
        </b-row>
        <b-input
          type="search"
          v-model="body.content"
          size="lg"
          maxlength="6"
          placeholder="통장 표시 내용 또는 제목을 입력하세요"
        ></b-input>
      </b-container>

      <div class="footer-fixed">
        <b-button
          v-if="
            body.sum <= 0 ||
              body.sum > money ||
              !body.content ||
              body.content.length > 6
          "
          disabled
          block
          squared
          style="height:58px"
          >다음</b-button
        >
        <b-button
          v-else
          block
          squared
          style="height:58px"
          variant="warning"
          @click="signin"
          >다음</b-button
        >
      </div>
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
      this.body.sum += 10000;
    },
    addFive() {
      this.body.sum += 50000;
    },
    addTen() {
      this.body.sum += 100000;
    },
    addHundred() {
      this.body.sum += 1000000;
    },
    allMoney() {
      this.body.sum = parseInt(this.money);
    },
    signin() {
      if (this.body.sum > this.money || this.body.sum == 0) {
        alert('금액을 확인해 주세요!');
      } else if (
        this.body.content.length > 6 ||
        this.body.content.length == 0
      ) {
        alert('글자 수를 확인해주세요!');
      } else {
        console.log(this.body);
        this.$emit('update', this.body);
      }
    },
    clickCancel() {
      this.body.sum = 0;
    },
    back() {
      this.$router.push({ name: 'Main' });
    },
  },
};
</script>

<style scoped></style>
