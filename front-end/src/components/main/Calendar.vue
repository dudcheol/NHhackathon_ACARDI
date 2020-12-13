<template>
  <div class="text-center section">
    <v-calendar
      ref="vcalendar"
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
              @click="onClickDate(attr.customData)"
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
import { mapGetters, mapActions } from 'vuex';
export default {
  props: {
    attributes: Array,
  },
  data() {
    return {
      cnt: 0,
      date: new Date(),
      masks: {
        weekdays: 'WWW',
      },
      // attributes: [{ title: 'asdf' }],
    };
  },
  computed: {
    ...mapGetters(['getCurDate']),
  },
  mounted() {
    console.log(this.$refs.vcalendar);
    console.log('현재 가리키고 있는 위치 : ' + JSON.stringify(this.getCurDate));
    console.log(
      '현재 가리키고 있는 위치 : ' + JSON.stringify(this.$store.state.curDate)
    );
    // this.$refs.vcalendar.move({
    //   month: this.getCurDate.month,
    //   year: this.getCurDate.year,
    // });
    this.$refs.vcalendar.move(this.$store.state.curDate);
    // console.log(calendar);
    // this.basic();
  },
  created() {},
  methods: {
    ...mapActions(['CHANGE_CUR_DATE']),
    onClickDate(attr) {
      console.log(attr);
      this.$router.push({
        name: 'Detail',
        params: attr,
      });
    },
    updatePage(date) {
      console.log('updated - ' + date.year + '.' + date.month);
      console.log(this.cnt);
      if (this.cnt++ != 0) this.CHANGE_CUR_DATE(date);
    },
    // async basic() {
    //   await calendar.move(this.getCurDate);
    // },
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
