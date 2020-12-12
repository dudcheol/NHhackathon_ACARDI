<template>
  <div class="text-center section">
    <v-calendar
      ref="calendar"
      class="custom-calendar max-w-full"
      :masks="masks"
      :attributes="attributes"
      is-expanded
      trim-weeks
      @update:from-page="updatePage"
    >
      <template v-slot:day-content="{ day, attributes }">
        <div class="flex flex-col h-full z-10 overflow-hidden">
          <span class="day-label text-sm text-gray-900">{{ day.day }}</span>
          <div class="flex-grow overflow-y-auto overflow-x-auto day-style">
            <b-card
              no-body
              v-for="attr in attributes"
              :key="attr.key"
              class="text-xs leading-tight rounded-sm font-size p-1 m-0"
              @click="onClickDate(attr)"
            >
              <b-row>
                <b-col class="p-0 m-0"
                  ><b-badge variant="warning">{{
                    attr.customData.title
                  }}</b-badge></b-col
                >
              </b-row>
              <b-row>
                <b-col>
                  <b-img
                    rounded
                    fluid
                    :src="attr.customData.imgsrc"
                    style="max-height:50px"
                  ></b-img>
                  <!-- <div
                    class="center-cropped"
                    style="background-image: url('https://contents.sixshop.com/thumbnails/uploadedFiles/27688/default/image_1579233003051_1000.png');"
                  ></div> -->
                  <!-- <div class="img-container">
                    <img
                      src="https://contents.sixshop.com/thumbnails/uploadedFiles/27688/default/image_1579233003051_1000.png"
                      class="img-responsive"
                    />
                  </div> -->
                </b-col>
              </b-row>
            </b-card>
          </div>
        </div>
      </template>
    </v-calendar>
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
      date: new Date(),
      masks: {
        weekdays: 'WWW',
      },
      attributes: [{ title: 'asdf' }],
    };
  },
  created() {},
  methods: {
    onClickDate(attr) {
      console.log(attr);
    },
    updatePage(date) {
      console.log('updated - ' + date.year + '.' + date.month);
      console.log('babyno - ' + this.babyno);
      // 서버에 년,달에 해당하는 다이어리 요청 response받으면 attributes clear 후 push
      axios
        .get(`http://localhost/diary/${this.babyno}/${date.year}/${date.month}`)
        // .get(`http://localhost/diary/1/${date.year}/${date.month}`)
        .then((response) => {
          console.log(response.data);
          this.updateAttributes(response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    updateAttributes(diaries) {
      console.log(diaries);
      // diary 순회해서 push
      var tmp = [];
      for (let index = 0; index < diaries.length; index++) {
        const d = diaries[index];
        tmp.push({
          key: index,
          customData: {
            title: d.title,
            content: d.content,
            cost: d.cost,
            member_id: d.member_id,
            no: d.no,
            imgsrc: d.imgsrc,
          },
          dates: new Date(d.registered_at),
        }); //baby_no, content, cost, member_id, no, registered_at, title
      }
      this.attributes = tmp;
      console.log(this.attributes);
    },
  },
  filters: {},
};
</script>

<style lang="postcss" scoped>
.day-style {
  min-height: 50px;
  min-width: 50px;
}
.font-size {
  font-size: 12px;
}
.center-cropped {
  position: absolute;
  width: auto;
  height: 100%;
  left: 50%;
  top: 50%;
  -webkit-transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
}
.img-container {
  overflow: hidden;
  position: relative;
  height: 50px;
  width: 50px;
}
.img-responsive {
  position: absolute;
  width: 100%;
  height: auto;
  left: 50%;
  top: 50%;
  -webkit-transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
}

::-webkit-scrollbar {
  width: 0px;
}
::-webkit-scrollbar-track {
  display: none;
}
/deep/ .custom-calendar.vc-container {
  --day-border: 1px solid #b8c2cc;
  --day-border-highlight: 1px solid #b8c2cc;
  --day-width: 90px;
  --day-height: 90px;
  --weekday-bg: #f8fafc;
  --weekday-border: 1px solid #eaeaea;
  border-radius: 0;
  width: 100%;
  & .vc-header {
    background-color: #f1f5f8;
    padding: 10px 0;
  }
  & .vc-weeks {
    padding: 0;
  }
  & .vc-weekday {
    background-color: var(--weekday-bg);
    border-bottom: var(--weekday-border);
    border-top: var(--weekday-border);
    padding: 5px 0;
  }
  & .vc-day {
    padding: 0 5px 3px 5px;
    text-align: left;
    height: var(--day-height);
    min-width: var(--day-width);
    background-color: white;
    &.weekday-1,
    &.weekday-7 {
      background-color: #eff8ff;
    }
    &:not(.on-bottom) {
      border-bottom: var(--day-border);
      &.weekday-1 {
        border-bottom: var(--day-border-highlight);
      }
    }
    &:not(.on-right) {
      border-right: var(--day-border);
    }
  }
  & .vc-day-dots {
    margin-bottom: 5px;
  }
}
</style>
