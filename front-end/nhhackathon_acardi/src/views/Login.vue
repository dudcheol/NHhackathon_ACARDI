<template>
  <div>
    <b-container id="container" fluid="sm" class="px-5">
      <b-row align-v="center" class="vh-100">
        <b-col>
          <b-row align-h="center" class="pb-3">
            <h1>
              <b-icon icon="emoji-laughing"></b-icon>
              icon area
            </h1>
          </b-row>
          <b-row class="mt-2">
            <b-input></b-input>
          </b-row>
          <b-row class="mt-2">
            <b-input></b-input>
          </b-row>
          <b-row class="mt-4">
            <b-button block pill>로그인</b-button>
          </b-row>
          <b-row class="mt-2">
            <b-button block pill>회원가입</b-button>
          </b-row>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script></script>

<style scoped>
#container {
  min-height: 100vh;
}
</style>
