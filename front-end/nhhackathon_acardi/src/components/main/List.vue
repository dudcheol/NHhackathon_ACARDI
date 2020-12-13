<template>
  <div>
    <b-list-group>
      <b-list-group-item class="pb-0">
        <a
          href="https://www.nhlife.co.kr/ho/ig/HOIG0001M00.nhl?prodCd=N0000709"
          target="_blank"
          v-b-tooltip
          title="어린이보험"
          ><img
            src="@/assets/img/보험배너.png"
            style="width:100%; height:150px;"
        /></a>
      </b-list-group-item>
      <b-list-group-item
        v-for="(item, index) in attributes"
        :key="index"
        @click="onClickDate(item.customData)"
        class="pb-0"
      >
        <!-- {{ item.customData.imgsrc }}
        <h3>{{ item.customData.title }} | {{ item.customData.cost }}원</h3>
        <p>
          작성일자 : {{ item.customData.date }} | 작성자 :
          {{ item.customData.member_id }}
        </p> -->
        <b-media>
          <template #aside v-if="item.customData.imgsrc">
            <b-img
              rounded
              blank
              blank-color="#ccc"
              width="70"
              alt="placeholder"
              :src="item.customData.imgsrc"
            ></b-img>
          </template>
          <template #aside v-else>
            <b-img
              rounded
              blank
              blank-color="#ccc"
              width="70"
              alt="placeholder"
            ></b-img>
          </template>

          <b-row>
            <b-col>
              <h5 class="mt-0">{{ item.customData.title }}</h5>
            </b-col>
            <b-col class="p-0 m-0">
              <p class="font-sm text-right pr-3">
                {{ item.customData.date.split(' ')[0] }}
                <b-badge variant="light"
                  ><strong>{{
                    item.customData.cost | moneycomma
                  }}</strong></b-badge
                >원
              </p>
            </b-col>
          </b-row>
          <b-row>
            <b-col style="color:grey">
              {{ item.customData.content }}
            </b-col>
          </b-row>
          <b-row class="p-0 m-0">
            <b-col class="text-right font-sm p-0 m-0">
              <p>by {{ item.customData.member_id }}</p>
            </b-col>
          </b-row>
        </b-media>
      </b-list-group-item>
    </b-list-group>
  </div>
</template>

<script>
export default {
  props: {
    attributes: Array,
  },
  data() {
    return {};
  },
  created() {},
  methods: {
    onClickDate(attr) {
      attr.date = attr.registered_at;
      attr.imgsrc = attr.profile;
      this.$router.push({
        name: 'Detail',
        params: attr,
      });
    },
  },
};
</script>

<style scoped>
.font-sm {
  font-size: 12px;
}
</style>
