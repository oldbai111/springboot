<template>
  <div id="app">
    <!-- <img src="./assets/logo.png"> -->
    <router-view v-if="isRouterAlive"></router-view>
  </div>
</template>

<script>
export default {
   name: 'App',
  // inject: ['reload'],
  provide () {    //父组件中通过provide来提供变量，在子组件中通过inject来注入变量。                                             
      return {
          reload: this.reload                                              
      }
  },
  data() {
    return{
    // header_show:false,
    isRouterAlive: true
    }
  },
  components: {
        // 'Header':Header,
  },
  methods:{
      //是否显示头部
      // header:function (bool) {
      //   this.header_show = bool;
      // },
      reload () {
          this.isRouterAlive = false;            //先关闭，
          this.$nextTick(function () {
              this.isRouterAlive = true;         //再打开
          }) 
      }
  }
}
</script>

<style>
/* #app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
} */
</style>
