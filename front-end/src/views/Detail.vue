<template>
  <div>
    <div class="header-fixed px-3">
      <b-row class="pt-2">
        <b-col align-self="center" class="p-0">
          <h2>
            <b-icon
              icon="arrow-left"
              class="p-1 m-2"
              @click="goToMain"
            ></b-icon>
          </h2>
        </b-col>
        <b-col align-self="center" class="text-center p-0">
          <h5 v-if="diary.registered_at">
            {{ diary.registered_at.split(' ')[0] }}
          </h5>
        </b-col>
        <b-col align-self="center" class="text-right p-0 m-0">
          <b-dropdown
            size="lg"
            variant="none"
            toggle-class="text-decoration-none"
            no-caret
          >
            <template #button-content>
              <h6><b-icon icon="three-dots-vertical"></b-icon></h6>
            </template>
            <b-dropdown-item @click="modify">수정</b-dropdown-item>
            <b-dropdown-item variant="danger" @click="remove"
              >삭제</b-dropdown-item
            >
          </b-dropdown>
        </b-col>
      </b-row>
    </div>
    <b-container class="" style="padding-top:56px;padding-bottom:56px;">
      <b-row align-v="center" class="pt-5 p-0 m-0">
        <b-col>
          <b-card
            :title="diary.title"
            header-tag="header"
            tag="article"
            style="max-width: 30rem;"
            class="mb-2 mx-auto"
          >
            <div>
              <img
                v-for="p in photo"
                :key="p"
                :src="
                  require(`@/assets/img/${babyno}/diary/${diary.registered_at}/${p.save_name}`)
                "
                style="width:120px; height:120px; border-radius: 5px; margin:10px;"
              />
            </div>
            <template #header>
              <b-row>
                <b-col class="text-right"
                  ><b-icon icon="cash-stack"></b-icon>
                  <strong> {{ diary.cost }}</strong
                  >원</b-col
                >
              </b-row>
              <!-- <div class="mb-0 text-right">
              <strong>{{ diary.cost }}</strong
              >원
            </div> -->
            </template>
            <b-card-text style="min-height:100px;">
              {{ diary.content }}
            </b-card-text>

            <!-- <b-button href="#" variant="warning">Go somewhere</b-button> -->
          </b-card>
        </b-col>
      </b-row>
      <!-- <div class="footer-fixed">
      <b-row class="p-0 m-0">
        <b-col class="p-0 m-0">
          <b-button block squared style="height:58px" variant="warning"
            >수정</b-button
          >
        </b-col>
        <b-col class="p-0 m-0">
          <b-button block squared style="height:58px" variant="danger"
            >삭제</b-button
          >
        </b-col>
      </b-row>
    </div> -->
    </b-container>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      babyno: '',
      diary: {},
      photo: [],
    };
  },
  created() {
    console.log(this.$route.params);
    this.getDiary(this.$route.params);
    // this.diary = this.$route.params.no;
  },
  methods: {
    getDiary(diary) {
      console.log(this.$store.state.babyno);
      this.babyno = this.$store.state.babyno;
      axios
        .get(`http://localhost/diary/${this.$store.state.babyno}/${diary.no}`)
        .then((res) => {
          console.log(res.data);
          this.diary = res.data;
          var year = this.diary.registered_at.substring(2, 4);
          var month = this.diary.registered_at.substring(5, 7);
          var day = this.diary.registered_at.substring(8, 10);
          this.diary.registered_at = year + month + day;
          console.log(this.diary.registered_at);
          axios
            .get(`http://localhost/diary/photo/${diary.no}`)
            .then((res) => {
              console.log(res.data);
              this.photo = res.data;
            })
            .catch((err) => {
              console.error(err);
            });
        })
        .catch((err) => {
          console.error(err);
        });
    },
    remove() {
      console.log('remove');
      axios
        .delete(`http://localhost/diary/${this.diary.no}`)
        .then((res) => {
          if (res.data == 1) {
            this.$router.push({
              name: 'Main',
            });
          }
        })
        .catch((err) => {
          console.error(err);
        });
    },
    modify() {
      this.$router.push({
        name: 'WriteContent',
        params: { type: 'modify', diary: this.diary },
      });
    },
    goToMain() {
      this.$router.push({ name: 'Main' });
    },
  },
};
</script>

<style scoped>
.header-fixed {
  position: fixed;
  top: 0;
  height: 56px;
  width: 100%;
  z-index: 999;
  background-color: white;
}
</style>
