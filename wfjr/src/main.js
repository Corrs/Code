// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import 'babel-polyfill'
import Vue from 'vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-default/index.css'
import $ from './assets/js/jquery'
import Global from './assets/js/global'
import './assets/css/common/layout.css'
import './assets/css/animate/animate.min.css'
import axios from 'axios'
import App from './App'

Vue.config.productionTip = false;
Vue.prototype.$axios = axios;
Vue.use(ElementUI);
axios.defaults.withCredentials=true;
// 路由全局钩子
router.beforeEach((to, from, next) => {
  // 请求后台，获得是否登录信息 未登录跳转到登录页面
  axios.post(jr.server.url + "/login/isLogin").then(function(response) {
    if(response.data.isLogin || to.fullPath == "/login") {
      next();
    } else {
      alert("用户未登录，将跳转到登录页面进行用户登录！");
      next({
        path: '/login'
      });
    }
  }).catch(function(error) {
    if(to.fullPath != "/login") {
      alert("服务器访问失败！");
      next({
        path: '/login'
      });
    } else {
      next();
    }
  });
});

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
});

