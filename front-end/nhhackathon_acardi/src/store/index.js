import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export const store = new Vuex.Store({
  state: {
    AccessToken: "인증키",
    Iscd : "기관코드"
  },
  getters: {
    getAccessToken: state => {
      return state.AccessToken;
    },
    getIscd: state => {
      return state.Iscd;
    }
  },
  mutations: {},
  actions: {},
  modules: {}
});
export default store;