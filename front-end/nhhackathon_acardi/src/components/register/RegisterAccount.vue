<template>
  <div id="container" class="footer-spacing">
    <h1><b-icon icon="arrow-left" class="p-1 m-2"></b-icon></h1>

    <b-container fluid="sm">
      <h3>
        username님,<br />
        babyname은 언제태어났나요?
      </h3>

      <b-datepicker></b-datepicker>

      <hr />

      <h3>babyname을 위한<br />계좌를 알려주세요.</h3>
      <b-row class="mb-2">
        <b-col>
          <b-input-group>
            <b-input type="text" placeholder="계좌번호"></b-input>
            <b-button><b-icon icon="check"></b-icon></b-button>
          </b-input-group>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
#container {
  min-height: 100vh;
}
</style>
