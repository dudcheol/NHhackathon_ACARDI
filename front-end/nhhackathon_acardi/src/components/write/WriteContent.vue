<template>
  <div>
    <div class="header-fixed px-3">
      <b-row class="pt-2">
        <b-col align-self="center" class="p-0">
          <h2 v-if="type == 'write'">
            <b-icon icon="arrow-left" class="p-1 m-2" @click="back"></b-icon>
          </h2>
        </b-col>
        <b-col align-self="center" class="text-center">
          <h5>{{ pageName }}</h5>
        </b-col>
        <b-col class="p-0">
          <h2 v-if="type == 'modify'" class="text-right">
            <b-icon icon="x" class="m-2" @click="close"></b-icon>
          </h2>
        </b-col>
      </b-row>
    </div>
    <b-container style="padding-top: 56px; padding-bottom: 56px" class="">
      <!-- <b-container>
      <b-button><b-icon icon="camera-fill"></b-icon></b-button>
      <b-textarea :readonly="true" :placeholder="body.content"> </b-textarea>
      <b-textarea
        v-model="todayDiary"
        placeholder="내용을 입력하세요"
      ></b-textarea>
    </b-container> -->
      <div class="image-box">
        <b-row v-if="!diary.imgsrc" class="pt-3 p-0 m-0">
          <b-col class="text-center">
            <b-button variant="info"
              ><label for="file">
                <input
                  type="file"
                  id="file"
                  ref="files"
                  @change="imageUpload"
                  multiple
                /><b-icon icon="camera-fill"></b-icon>
              </label>
              {{ cameraBtnText }}</b-button
            >
          </b-col>
        </b-row>
      </div>
      <b-row align-v="center" class="p-0 m-0">
        <b-col>
          <b-card
            :title="diary.title"
            header-tag="header"
            :img-src="diary.imgsrc"
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 30rem"
            class="mb-2 mx-auto"
            border-variant="info"
          >
            <template #header>
              <b-row>
                <b-col class="text-right"
                  ><b-icon icon="cash-stack"></b-icon>
                  <strong> {{ diary.cost | moneycomma }}</strong
                  >원</b-col
                >
              </b-row>
              <!-- <div class="mb-0 text-right">
              <strong>{{ diary.cost }}</strong
              >원
            </div> -->
            </template>
            <b-textarea
              v-model="diary.content"
              placeholder="내용을 입력하세요"
              rows="3"
              max-rows="20"
            ></b-textarea>

            <!-- <b-button href="#" variant="warning">Go somewhere</b-button> -->
          </b-card>
        </b-col>
      </b-row>
      <center>
        <div class="file-preview-container">
          <div
            v-for="(file, index) in files"
            :key="index"
            class="file-preview-wrapper"
          >
            <div
              class="file-close-button"
              @click="fileDeleteButton"
              :name="file.number"
            ></div>
            <img :src="file.preview" />
          </div>
        </div>
      </center>
    </b-container>
    <div class="footer-fixed">
      <b-row class="p-0 m-0">
        <b-col class="p-0 m-0">
          <b-button
            block
            squared
            style="height: 58px"
            variant="warning"
            @click="complete"
            >{{ btnText }}</b-button
          >
        </b-col>
      </b-row>
    </div>
  </div>
</template>

<script>
import { WithdrawalAccount, DepositAccount } from '../../api/account.js';
import axios from 'axios';

export default {
  name: 'writecontent',
  data() {
    return {
      type: '',
      diary: {
        date: '',
        title: '',
        content: '',
      },
      images: {
        baby_no: '',
        type: 'diary',
        file: [],
      },
      pageName: '다이어리 작성',
      btnText: '작성하기',
      cameraBtnText: '사진 추가',
      body: {},
      todayDiary: '',
      AccessToken: '',
      Iscd: '',
      ////
      files: [], //업로드용 파일
      filesPreview: [],
      uploadImageIndex: 0, // 이미지 업로드를 위한 변수
    };
  },
  created() {
    var params = this.$route.params;
    console.log('writecontent params - ' + JSON.stringify(params));
    this.type = params.type;
    if (params.type == 'write') {
      this.pageName = '다이어리 작성';
      this.btnText = '작성하기';
      this.cameraBtnText = '사진 추가';
      this.body = params.body;
      this.diary.title = this.body.content;
      this.diary.cost = this.body.sum;
    } else if (params.type == 'modify') {
      this.pageName = '다이어리 수정';
      this.btnText = '수정하기';
      this.cameraBtnText = '사진 변경';
      this.diary = params.diary;
    }
  },
  mounted() {
    this.AccessToken = this.$store.state.AccessToken;
    this.Iscd = this.$store.state.Iscd;
  },
  methods: {
    imageUpload() {
      console.log(this.$refs.files.files);

      // this.files = [...this.files, this.$refs.files.files];
      //하나의 배열로 넣기
      let num = -1;
      for (let i = 0; i < this.$refs.files.files.length; i++) {
        this.files = [
          ...this.files,
          //이미지 업로드
          {
            //실제 파일
            file: this.$refs.files.files[i],
            //이미지 프리뷰
            preview: URL.createObjectURL(this.$refs.files.files[i]),
            //삭제및 관리를 위한 number
            number: i,
          },
        ];
        num = i;
        //이미지 업로드용 프리뷰
        // this.filesPreview = [
        //   ...this.filesPreview,
        //   { file: URL.createObjectURL(this.$refs.files.files[i]), number: i }
        // ];
      }
      this.uploadImageIndex = num + 1; //이미지 index의 마지막 값 + 1 저장
      console.log(this.files);
      // console.log(this.filesPreview);
    },
    imageAddUpload() {
      console.log(this.$refs.files.files);

      // this.files = [...this.files, this.$refs.files.files];
      //하나의 배열로 넣기c
      let num = -1;
      for (let i = 0; i < this.$refs.files.files.length; i++) {
        console.log(this.uploadImageIndex);
        this.files = [
          ...this.files,
          //이미지 업로드
          {
            //실제 파일
            file: this.$refs.files.files[i],
            //이미지 프리뷰
            preview: URL.createObjectURL(this.$refs.files.files[i]),
            //삭제및 관리를 위한 number
            number: i + this.uploadImageIndex,
          },
        ];
        num = i;
      }
      this.uploadImageIndex = this.uploadImageIndex + num + 1;

      console.log(this.files);
      // console.log(this.filesPreview);
    },
    fileDeleteButton(e) {
      const name = e.target.getAttribute('name');
      this.files = this.files.filter((data) => data.number !== Number(name));
      // console.log(this.files);
    },
    imageServer() {
      // this.$emit('finishTransfer', this.todayDiary);
    },
    //////////////////////

    complete() {
      if (this.type == 'write') {
        this.finish();
      } else if (this.type == 'modify') {
        this.modify();
      }
    },
    modify() {
      console.log('click modify');
      axios
        .put(`http://localhost/diary`, this.diary)
        .then((res) => {
          console.log(res);
          this.$router.push({ name: 'Detail', params: this.diary });
        })
        .catch((err) => {
          console.error(err);
        });
    },
    close() {
      this.$router.push({ name: 'Detail', params: this.diary });
    },
    back() {},
    finish() {
      var date = new Date();
      var today =
        String(date.getFullYear()) +
        String(date.getMonth() + 1) +
        String(date.getDate());
      var tuno = Math.floor(Math.random() * 99999999999999999999);
      var tuno2 = Math.floor(Math.random() * 99999999999999999999);
      WithdrawalAccount(
        {
          Header: {
            ApiNm: 'DrawingTransfer',
            Tsymd: today,
            Trtm: '112428',
            Iscd: this.Iscd,
            FintechApsno: '001',
            ApiSvcCd: 'DrawingTransferA',
            IsTuno: tuno,
            AccessToken: this.AccessToken,
          },
          FinAcno: this.$route.params.pinAccount,
          Tram: this.body.sum,
          MractOtlt: '새록새록',
        },
        (response) => {
          console.log(response);
          DepositAccount(
            {
              Header: {
                ApiNm: 'ReceivedTransferAccountNumber',
                Tsymd: today,
                Trtm: '112428',
                Iscd: this.Iscd,
                FintechApsno: '001',
                ApiSvcCd: 'DrawingTransferA',
                IsTuno: tuno2,
                AccessToken: this.AccessToken,
              },
              Bncd: '011',
              Acno: this.body.babyAccount,
              Tram: this.body.sum,
              DractOtlt: '테스트',
              MractOtlt: this.body.content,
            },
            (response) => {
              console.log(response);
              this.images.file = this.files;
              console.log(this.images);
              console.log(this.diary);
              this.$emit('finishTransfer', this.diary.content);
              this.$emit('postPhotos', this.images);
            },
            (error) => {
              console.log(error);
            }
          );
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style scoped>
.image-box {
  margin-top: 30px;
  padding-bottom: 20px;
}

.image-box input[type='file'] {
  position: absolute;
  width: 0;
  height: 0;
  padding: 0;
  overflow: hidden;
  border: 0;
}

.image-box label {
  display: inline-block;
  padding: 5px 5px;
  color: #fff;
  vertical-align: middle;
  font-size: 15px;
  cursor: pointer;
  border-radius: 5px;
}

.file-preview-wrapper {
  padding: 10px;
  position: center;
}

.file-preview-wrapper > img {
  position: center;
  width: 400px;
  height: 400px;
  z-index: 10;
}

/* .file-close-button {
  position: absolute;
  line-height: 18px;
  z-index: 99;
  font-size: 18px;
  right: 5px;
  top: 10px;
  color: #fff;
  font-weight: bold;
  background-color: #666666;
  width: 20px;
  height: 20px;
  text-align: center;
  cursor: pointer;
} */

.header-fixed {
  position: fixed;
  top: 0;
  height: 56px;
  width: 100%;
  z-index: 999;
  background-color: white;
}
</style>
