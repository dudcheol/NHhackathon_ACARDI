<template>
  <div>
    <b-container>
      <b-list-group>
        <b-list-group-item
          v-for="(item, index) in items"
          :key="index"
          @click="onClickDate(item)"
        >
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
  props: {
    babyno: String,
  },
  data() {
    return {
      items: [],
    };
  },
  created() {
    console.log(this.$route.params.babyno);
    console.log(this.babyno);
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
  methods: {
    onClickDate(attr) {
      attr.date = attr.registered_at;
      attr.imgsrc = attr.profile;
      this.$router.push({
        name: 'Detail',
        params: attr,
      });
    },
  },
};
</script>

<style scoped></style>
