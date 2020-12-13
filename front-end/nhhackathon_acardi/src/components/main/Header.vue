<template>
  <div
    class="d-flex justify-content-between align-items-center px-3 main-header"
  >
    <h3 @click="clickList"><b-icon icon="list"></b-icon></h3>
    <!-- <h3><b-icon icon="emoji-laughing"></b-icon>logoarea</h3> -->
    <img
      :src="require(`@/assets/logo.png`)"
      style="height:40px; border-radius: 15px;"
    />
    <h3><b-icon icon="bell" @click="clickIcon"></b-icon></h3>
  </div>
</template>

<script>
export default {
  methods: {
    clickList() {
      this.$emit('open-sidebar');
      console.log('click');
    },
    clickIcon() {
      this.$emit('open-eventList');
      console.log('clickEvent');
    },
  },
};
</script>

<style scoped>
.main-header {
  background-color: white;
}
</style>
