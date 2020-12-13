import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';

Vue.use(Vuex);

const getDefaultState = () => {
  const date = new Date();
  const year = date.getFullYear();
  const month = date.getMonth() + 1;
  console.log(year + ' ' + month);
  return {
    AccessToken:
      '7420ed5362e94397e8c59d66a5e4b17548c1ace502f5ca404ba2ed446d63aa38',
    Iscd: '000541',
    // AccessToken:
    //   'f97844c4b63e36f17d0d67c8bd9761768f50a38dc14c3fef4c7b812e427f0d59',
    // Iscd: '000536',
    babyno: null,
    babyidx: null,
    babyinfos: null,
    mainState: 'Calendar',
    curDate: { year, month },
  };
};

// initial state
const state = getDefaultState();

export const store = new Vuex.Store({
  state,
  getters: {
    getBabyNo(state) {
      return state.babyno;
    },
    getBabyInfos(state) {
      return state.babyinfos;
    },
    getBabyIdx(state) {
      return state.babyidx;
    },
    getMainState(state) {
      return state.mainState;
    },
    getCurDate(state) {
      return state.curDate;
    },
  },
  mutations: {
    resetState(state) {
      // Merge rather than replace so we don't lose observers
      // https://github.com/vuejs/vuex/issues/1118
      Object.assign(state, getDefaultState());
    },
    setBabyNo(state, babyno) {
      state.babyno = babyno;
    },
    setBabyInfos(state, babyinfos) {
      state.babyinfos = babyinfos;
    },
    setBabyIdx(state, babyidx) {
      state.babyidx = babyidx;
    },
    setMainState(state, type) {
      state.mainState = type;
    },
    setCurDate(state, curDate) {
      state.curDate = curDate;
    },
  },
  actions: {
    RESET_STATE({ commit }) {
      commit('resetState');
    },
    GET_BABYNO(store, payload) {
      console.log('actions - get babyno');
      axios
        .get(`http://localhost/baby/list/${payload}`)
        .then((res) => {
          console.log(res.data);
          store.commit('setBabyInfos', res.data);
          store.commit('setBabyNo', res.data[0].no);
          store.commit('setBabyIdx', 0);
        })
        .catch((err) => {
          console.error(err);
        });
    },
    CHANGE_MAIN_STATE(store, payload) {
      store.commit('setMainState', payload);
    },
    CHANGE_CUR_DATE(store, payload) {
      store.commit('setCurDate', payload);
    },
  },
  modules: {},
});
export default store;
