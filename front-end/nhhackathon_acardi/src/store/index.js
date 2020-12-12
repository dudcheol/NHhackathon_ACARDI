import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export const store = new Vuex.Store({
  state: {
    AccessToken: "7420ed5362e94397e8c59d66a5e4b17548c1ace502f5ca404ba2ed446d63aa38",
    Iscd : "000541"
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