<template>
  <div>
    <b-container>
      <b-list-group>
        <b-list-group-item v-for="(item, index) in items" :key="index">
          {{ item.img }} | {{ item.date }} | {{ item.title }} | {{ item.money }}
        </b-list-group-item>
      </b-list-group>
    </b-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      items: [],
    };
  },
  created() {
    for (let i = 0; i < 30; i++) {
      this.items.push({
        img: 'img' + i,
        date: '2020.12.11',
        title: 'test' + i,
        money: '20,000',
      });
    }
  },
};
</script>

<style scoped></style>
