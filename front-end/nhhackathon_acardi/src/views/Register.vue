<template>
  <div>
    <div class="temp">
      <router-link to="/register/profile">profile</router-link> |
      <router-link to="/register/account">account</router-link>
    </div>
    <register-profile v-if="take == 1" v-on:babyProfile="getBabyInfo"></register-profile>  
    <hr>
    <register-account v-if="take == 2" :babyInfo="babyInfo" v-on:goProfile="goProfile"></register-account>
    </div>  
</template>

<script>
import RegisterAccount from "@/components/register/RegisterAccount.vue";
import RegisterProfile from '../components/register/RegisterProfile.vue';
export default {
  components: {
    RegisterAccount,
    RegisterProfile
  },
  data() {
    return {
      take:1,
      babyInfo: {},
    };
  },
  methods: {
    goProfile(){
      this.take--;
    },
    getBabyInfo(bi){
      this.take++;
      this.babyInfo = bi;
    },
    
  },
};
</script>

<style scoped>
.temp {
  padding-top: 20px;
  position: fixed;
}
</style>