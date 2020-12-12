<template>
  <div>
    <b-container>
      <b-list-group>
        <b-list-group-item v-for="(item, index) in items" :key="index">
          {{ item.img }}
          <h3>{{ item.title }} | {{ item.cost }}원</h3>
          <p>
            작성일자 : {{ item.registered_at }} | 작성자 : {{ item.member_id }}
          </p>
        </b-list-group-item>
      </b-list-group>
    </b-container>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      items: [],
    };
  },
  created() {
    console.log(this.$route.params.babyno);
    axios
      .get('http://localhost/diary/' + this.$route.params.babyno)
      .then((response) => {
        console.log(response);
        this.items = response.data;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  updated() {
    console.log(this.$route.params.babyno);
  },
};
</script>

<style scoped></style>
