import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue';
import VueSession from 'vue-session';
import VCalendar from 'v-calendar';

var sessionOptions = {
  persist: true,
};

Vue.use(BootstrapVue);
Vue.use(IconsPlugin);
Vue.use(VueSession, sessionOptions);
Vue.use(VCalendar);

import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';

Vue.config.productionTip = false;
Vue.filter('moneycomma', (value) => {
  var num = new Number(value);
  return num.toFixed(0).replace(/(\d)(?=(\d{3})+(?:\.\d+)?$)/g, '$1,');
});

window.Kakao.init('7b9314f4f254e9d9634e8c638bcc81ea');

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount('#app');
