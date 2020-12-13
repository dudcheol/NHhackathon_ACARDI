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
    registerBaby() {
      if (!this.relation) {
        alert('아이와의 관계를 입력해주세요.');
      } else if (!this.accountChecked) {
        alert('계좌번호를 확인해주세요.');
      } else {
        console.log(this.babyInfo);
        axios
          .post('http://localhost/baby', this.babyInfo)
          .then((response) => {
            console.log('baby regist reponse:' + response.data);
            if (response.data > 0) {
              alert('아이 추가를 완료했습니다.');
              //family 등록
              var family = {
                member_id: this.userId,
                baby_no: response.data,
                relation: this.relation,
              };
              console.log(family);
              axios
                .post('http://localhost/family', family)
                .then((response) => {
                  console.log(response);
                })
                .catch((error) => {
                  console.log(error);
                });

              this.$router.push({
                path: '/main',
              });
              // window.location.reload('/main');
            } else {
              alert('아이 추가에 실패했습니다. 다시 시도해주세요.');
            }
          })
          .catch((error) => {
            console.log(error);
          });
      }
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
          alert('저장되었습니다.');
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
