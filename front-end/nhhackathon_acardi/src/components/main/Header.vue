<template>
  <div
    class="d-flex justify-content-between align-items-center px-3 main-header"
  >
    <h3 @click="clickList"><b-icon icon="list"></b-icon></h3>
    <h3><b-icon icon="emoji-laughing"></b-icon>logoarea</h3>
    <h3><b-icon icon="bell"></b-icon></h3>
  </div>
</template>

<script>
export default {
  methods: {
    clickList() {
      this.$emit('open-sidebar');
      console.log('click');
    },
  },
};
</script>

<style scoped>
.main-header {
  background-color: white;
}
</style>
