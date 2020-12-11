<template>
  <div>
    <b-container fluid="sm" class="h-100 footer-spacing">
      <h1 class="pl-2 py-2">회원가입</h1>
      <b-row align-h="center m-2">
        <b-col>
          <b-row class="mb-2">
            <b-input placeholder="아이디"></b-input>
          </b-row>
          <b-row class="mb-2">
            <b-input type="password" placeholder="비밀번호"></b-input>
          </b-row>
          <b-row class="mb-2">
            <b-input type="password" placeholder="비밀번호 확인"></b-input>
          </b-row>
          <b-row class="mb-2">
            <b-input type="email" placeholder="이메일"></b-input>
          </b-row>
          <b-row class="mb-2">
            <b-input type="tel" placeholder="휴대폰 번호"></b-input>
          </b-row>
          <b-row class="mb-2">
            <b-input type="email" placeholder="이메일"></b-input>
          </b-row>
          <b-row class="mb-2">
            <b-input-group>
              <b-form-input
                v-model="value"
                type="text"
                placeholder="생년월일 YYYY-MM-DD"
                autocomplete="off"
              ></b-form-input>
              <b-input-group-append>
                <b-form-datepicker
                  v-model="value"
                  button-only
                  right
                  locale="en-US"
                  aria-controls="example-input"
                  @context="onContext"
                ></b-form-datepicker>
              </b-input-group-append>
            </b-input-group>
          </b-row>
          <b-row class="mb-2">
            <b-input-group>
              <b-input type="text" placeholder="계좌번호"></b-input>
              <b-button><b-icon icon="check"></b-icon></b-button>
            </b-input-group>
          </b-row>
        </b-col>
      </b-row>
    </b-container>

    <div class="footer-fixed">
      <b-button block squared style="height:58px" variant="warning"
        >다음</b-button
      >
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      value: '',
      formatted: '',
      selected: '',
    };
  },
  methods: {
    onContext(ctx) {
      // The date formatted in the locale, or the `label-no-date-selected` string
      this.formatted = ctx.selectedFormatted;
      // The following will be an empty string until a valid date is entered
      this.selected = ctx.selectedYMD;
    },
  },
};
</script>

<style scoped></style>
