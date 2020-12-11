import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue';
import VueSession from 'vue-session';

var sessionOptions = {
  persist: true
}

Vue.use(BootstrapVue);
Vue.use(IconsPlugin);
Vue.use(VueSession, sessionOptions);

import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount('#app');
