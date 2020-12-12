import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export const store = new Vuex.Store({
  state: {
    AccessToken: "f97844c4b63e36f17d0d67c8bd9761768f50a38dc14c3fef4c7b812e427f0d59",
    Iscd : "000536"
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