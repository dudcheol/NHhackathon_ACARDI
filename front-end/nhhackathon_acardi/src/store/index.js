import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export const store = new Vuex.Store({
  state: {
    AccessToken: "인증키",
    Iscd : "기관코드"
  },
  mutations: {},
  actions: {},
  modules: {}
});
