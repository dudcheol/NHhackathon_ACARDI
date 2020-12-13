<template>
  <div>
    <div class="temp">
      <router-link to="/register/profile">profile</router-link> |
      <router-link to="/register/account">account</router-link>
    </div>
    <register-profile
      v-if="take == 1"
      :babyInfo="babyInfo"
      v-on:goAccount="goAccount"
      v-on:postPhotos="onPostPhotos"
    ></register-profile>
    <hr />
    <register-account
      v-if="take == 2"
      :babyInfo="babyInfo"
      v-on:goProfile="goProfile"
    ></register-account>
  </div>
</template>

<script>
import axios from 'axios';
import RegisterAccount from '@/components/register/RegisterAccount.vue';
import RegisterProfile from '../components/register/RegisterProfile.vue';
export default {
  components: {
    RegisterAccount,
    RegisterProfile,
  },
  data() {
    return {
      take: 1,
      babyInfo: {
        nickname: '',
        birthday: '',
        gender: 0,
        birth_time: '',
        account: '',
        profile: '',
        relation: '',
      },
    };
  },
  methods: {
    goProfile() {
      this.take--;
      console.log(this.babyInfo);
    },
    goAccount() {
      this.take++;
    },
    onPostPhotos(photos) {
      console.log(photos);
      var frm = new FormData();
      for (var i = 0; i < photos.file.length; i++) {
        frm.append('multipartFiles', photos.file[i].file);
      }
      console.log('------------');
      console.log(frm);
      axios
        .post(`http://localhost/upload/profile/${photos.type}`, frm, {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        })
        .then((response) => {
          console.log(response);
          console.log('사진 저장 완료');
          //alert('저장되었습니다.');
          // this.$router.push({
          //   path: '/main',
          // });
        })
        .catch((error) => {
          console.log(error);
        });
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
