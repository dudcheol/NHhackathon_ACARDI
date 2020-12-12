<template>
  <b-container class="py-4">
    <b-row>
      <b-col>
        <b-card
          title="이곳에 제목"
          header-tag="header"
          img-src="https://img.insight.co.kr/static/2019/03/23/700/hc9i8ft6vj5133yz537s.jpg"
          img-alt="Image"
          img-top
          tag="article"
          style="max-width: 30rem;"
          class="mb-2 mx-auto"
        >
          <template #header>
            <h6 class="mb-0">이곳에 입금액</h6>
          </template>
          <b-card-text>
            이곳에 내용
          </b-card-text>

          <b-button href="#" variant="warning">Go somewhere</b-button>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {};
</script>

<style scoped></style>
