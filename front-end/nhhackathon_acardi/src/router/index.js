import Vue from 'vue';
import VueRouter from 'vue-router';
import Main from '../views/Main.vue';
import Join from '../views/Join.vue';
import Login from '../views/Login.vue';
import Register from '../views/Register.vue';
import Write from '../views/Write.vue';
import Detail from '../views/Detail.vue';
import Profile from '../components/main/Profile.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'Main',
    component: Main,
  },
  {
    path: '/join',
    name: 'Join',
    component: Join,
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/profile',
    name: 'Profile',
    component: Profile,
  },
  {
    path: '/detail',
    name: 'Detail',
    component: Detail,
  },
  {
    path: '/write',
    name: 'Write',
    component: Write,
    children: [
      {
        path: 'account',
        name: 'WriteAccount',
        component: () => import('@/components/write/WriteAccount.vue'),
      },
      {
        path: 'content',
        name: 'WriteContent',
        component: () => import('@/components/write/WriteContent.vue'),
      },
    ],
  },
  {
    path: '/register',
    name: 'Register',
    component: Register,
    children: [
      {
        path: 'profile',
        name: 'RegisterProfile',
        component: () => import('@/components/register/RegisterProfile.vue'),
      },
      {
        path: 'account',
        name: 'RegisterAccount',
        component: () => import('@/components/register/RegisterAccount.vue'),
      },
    ],
  },
  {
    path: '/main',
    name: 'Main',
    component: Main,
    props: true,
    children: [
      {
        path: 'calendar',
        name: 'Calendar',
        component: () => import('@/components/main/Calendar.vue'),
      },
      {
        path: 'list',
        name: 'List',
        component: () => import('@/components/main/List.vue'),
        props: true,
      },
    ],
  },
  // {
  //   path: '/about',
  //   name: 'About',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () =>
  //     import(/* webpackChunkName: "about" */ '../views/About.vue'),
  // },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
