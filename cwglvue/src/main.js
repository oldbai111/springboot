// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import '../node_modules/element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import moment from 'moment'

Vue.config.productionTip = false
Vue.prototype.$axios = axios;
Vue.use(ElementUI)
Vue.prototype.$moment = moment
axios.defaults.baseURL = 'http://localhost:8083/'

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
