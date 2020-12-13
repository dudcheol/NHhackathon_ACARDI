<template>
  <div>
    <b-container id="container">
      <!-- 아이 이름 입력 -->
      <h3>
        {{ username }}님,<br />
        아이에 대해 알려주세요.
      </h3>

      <!-- 아이 프로필 사진 -->

      <b-jumbotron>
        <center>
          <div class="image-box">
            <label for="file">
              <input
                type="file"
                id="file"
                ref="files"
                @change="imageUpload"
                multiple
              />

              <b-icon icon="camera-fill" @click="selectPhoto"></b-icon>

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
            </label>
          </div>
        </center>
      </b-jumbotron>

      <!-- 아이 이름 -->
      <h4>이름/별명</h4>
      <b-input v-model="babyInfo.nickname" type="text" placeholder=""></b-input>
      <br />

      <!-- 성별 선택-->
      <h4>성별</h4>
      <b-row>
        <b-col>
          <h1 @click="gender('male')">
            <b-icon :icon="male" variant="primary"></b-icon>남자
          </h1>
        </b-col>
        <b-col>
          <h1 @click="gender('female')">
            <b-icon :icon="female" variant="danger"></b-icon>여자
          </h1>
        </b-col>
      </b-row>
      <br />

      <!-- 아이 생일/생시 입력 -->
      <h4>생일</h4>
      <b-datepicker v-model="babyInfo.birthday"></b-datepicker>
      <br />
      <b-timepicker v-model="babyInfo.birth_time"></b-timepicker>
      <br />
    </b-container>
    <div class="footer-fixed">
      <b-button
        v-if="!babyInfo.nickname || !babyInfo.birthday || !babyInfo.birth_time"
        disabled
        block
        squared
        style="height:58px"
        >다음</b-button
      >
      <b-button
        v-else
        @click="next"
        block
        squared
        style="height: 58px"
        variant="warning"
        >다음</b-button
      >
    </div>
  </div>
</template>

<script>
export default {
  props: {
    babyInfo: Object,
  },
  data() {
    return {
      username: this.$session.get('userID'),
      male: 'emoji-laughing',
      female: 'emoji-laughing',
      images: {
        type: 'profile',
        file: [],
      },
      ////
      files: [], //업로드용 파일
      filesPreview: [],
      uploadImageIndex: 0, // 이미지 업로드를 위한 변수
    };
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

    //////////////////
    next() {
      this.$emit('goAccount');
      this.images.file = this.files;
      this.$emit('postPhotos', this.images);
    },
    selectPhoto() {
      console.log('프로필 사진 선택');
      this.babyInfo.profile = 'temp'; //선택한 사진의 경로로 저장. default값 지정 필요
    },
    gender(g) {
      if (g == 'male') {
        this.male = 'emoji-laughing-fill';
        this.female = 'emoji-laughing';
        this.babyInfo.gender = 1;
      } else {
        this.male = 'emoji-laughing';
        this.female = 'emoji-laughing-fill';
        this.babyInfo.gender = 2;
      }
    },
  },
};
</script>

<style scoped>
.image-box {
  margin-top: 0px;
  padding-bottom: 0px;
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
  padding: 0px 0px;
  /* color: #fff; */
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
#container {
  min-height: 100vh;
}
</style>
