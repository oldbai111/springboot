import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/Index'

Vue.use(Router)


Vue.prototype.setCookie = function (name, value, minutes) {
  if (minutes !== 0) { //当设置的时间等于0时，不设置expires属性，cookie在浏览器关闭后删除
      var now = new Date();
      var nowTime = now.getTime();
      var leftTime = new Date(nowTime + 60 * 1000 * minutes);
      document.cookie = name + "=" + escape(JSON.stringify(value)) + ";expires=" + leftTime.toGMTString();
  } else {
      document.cookie = name + "=" + escape(JSON.stringify(value));
  }
}

Vue.prototype.getCookie = function (name) {
  var arr;
  var reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
  if (arr = document.cookie.match(reg))
      return JSON.parse(unescape(arr[2]));
  else
      return null;
}

const Regist = ()=>import("@/components/Regist")
const Loading =()=>import("@/components/Loading")
const Lookingzd =()=>import("@/components/Lookingzd")
const HandleOne =()=>import("@/components/HandleOne")
const HandleTwo =()=>import("@/components/HandleTwo")
const Zhichushenqing =()=>import("@/components/Zhichushenqing")
const HandleThree =()=>import("@/components/HandleThree")
const Timelook =()=>import("@/components/Timelook")
const Moneylook =()=>import("@/components/Moneylook")
const Otherlook =()=>import("@/components/Otherlook")
const Person =()=>import("@/components/Person")
const Auditing =()=>import("@/components/Auditing")

const Viploading =()=>import("@/components/manager/Viploading")
const Lookchart =()=>import("@/components/manager/Lookchart")
const Usermanage =()=>import("@/components/manager/Usermanage")
const Adduser =()=>import("@/components/manager/Adduser")
const Edituser =()=>import("@/components/manager/Edituser")
const Zdmanage =()=>import("@/components/manager/Zdmanage")
const Projectmanage =()=>import("@/components/manager/Projectmanage")
const Addproject =()=>import("@/components/manager/Addproject")
export default new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index
    },
    {
      path: '/regist',
      name: 'Regist',
      component: Regist
    },
    {
      path: '/',
      // name: 'Viploading',
      component: Viploading,
      children: [
        {
        path: '/lookchart',
        name: 'Lookchart',
        component: Lookchart,
      },  {
        path: '/usermanage',
        name: 'Usermanage',
        component: Usermanage,
      },
      {
        path: '/adduser',
        name: 'Adduser',
        component: Adduser,
      },
      {
        path: '/edituser',
        name: 'Edituser',
        component: Edituser,
      },
      {
        path: '/zdmanage',
        name: 'Zdmanage',
        component: Zdmanage,
      },
      {
        path: '/projectmanage',
        name: 'Projectmanage',
        component: Projectmanage,
      },
      {
        path: '/addproject',
        name: 'Addproject',
        component: Addproject,
      }

    ]
    },

    {
     path: '/',
    //  name: 'Loading',
     component: Loading ,
     children: [
      {
        path: '/lookingzd',
        name: 'Lookingzd',
        component: Lookingzd 
       },{
      path: '/handleOne',
      name: 'HandleOne',
      component: HandleOne ,
     },
     {
      path: '/zhichushenqing',
      name: 'Zhichushenqing',
      component: Zhichushenqing ,
     },
     {
      path: '/handleTwo',
      name: 'HandleTwo',
      component: HandleTwo ,
     },{
      path: '/handleThree',
      name: 'HandleThree',
      component: HandleThree ,
     },
     {
      path: '/timelook',
      name: 'Timelook',
      component: Timelook ,
     },
     {
      path: '/moneylook',
      name: 'Moneylook',
      component: Moneylook ,
     },
     {
      path: '/otherlook',
      name: 'Otherlook',
      component: Otherlook ,
     },
     {
      path: '/person',
      name: 'Person',
      component: Person ,
     },
     {
      path: '/auditing',
      name: 'Auditing',
      component: Auditing ,
     }

    ]
    }
  ]
})
