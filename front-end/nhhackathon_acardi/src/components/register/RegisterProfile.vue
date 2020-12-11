<template>
  <b-container id="container">
    <h3>
      username님,<br />
      아이에 대해 알려주세요.
    </h3>
    <b-jumbotron>
      <b-icon icon="camera-fill"></b-icon>
    </b-jumbotron>
    <!-- <b-avatar></b-avatar> -->
    <h4>이름/별명</h4>
    <b-input type="text" placeholder=""></b-input>
    <br />
    <h4>성별</h4>
    <b-row>
      <b-col align-h="">
        <h1><b-icon icon="emoji-laughing" variant="primary"></b-icon></h1>
      </b-col>
      <b-col>
        <h1><b-icon icon="emoji-laughing" variant="danger"></b-icon></h1>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {};
</script>

<style scoped>
#container {
  min-height: 100vh;
}
</style>
