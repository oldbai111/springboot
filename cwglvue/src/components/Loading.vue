<template>
    <div id="loading">
     <!-- 测试id:{{id}} -->
     <el-menu
  :default-active="activeIndex2"
  class="el-menu-demo"
  mode="horizontal"
  @select="handleSelect"
  background-color="#545c64"
  text-color="#fff"
  active-text-color="#ffd04b">

   <el-submenu index="2">
    <template slot="title"><router-link to="/lookingzd">查看账单</router-link></template>
    <!-- <el-menu-item index="2-1"><router-link to="/timelook">根据时间查询</router-link></el-menu-item>
    <el-menu-item index="2-2"><router-link to="/moneylook">根据金额查询</router-link></el-menu-item> -->
    <el-menu-item index="2-3"><router-link to="/otherlook">查看他人账单（近一周）</router-link></el-menu-item>
    
  </el-submenu>
  <el-menu-item index="1"><router-link to="/handleOne">添加账单</router-link></el-menu-item>
  
 <el-menu-item index="6"><router-link to="/zhichushenqing">支出申请</router-link></el-menu-item>

    <el-menu-item index="5"> <router-link to="/handleTwo">图表展示</router-link></el-menu-item>
    
        
  <!-- 用户头像及下拉选项 -->
        <el-dropdown style="float:right;display:inline;padding:0.5vh 2vh">
          <span class="el-dropdown-link">
            <el-badge class="item">
              <el-avatar
                style="width:10vh;height:10vh"
                :src="'http://localhost:8083/img/'+userTouxiang"
              ></el-avatar>
              <!-- <span v-if="!user.photo" style="font-size:10vh;color:white" class="iconfont">&#xe607;</span> -->
              <!-- <el-avatar
                v-if="user.photo"
                style="width:10vh;height:10vh"
                :src="user.photo ? user.photo:tx"
              ></el-avatar>
              <span v-if="!user.photo" style="font-size:6vh" class="iconfont">&#xe7b4;</span> -->
            </el-badge>
          </span>
          <el-dropdown-menu slot="dropdown">


              <label>
                <router-link to="/person">
                <el-dropdown-item>
                  个人中心
                </el-dropdown-item>
                </router-link>
              </label>
              <label>
                <router-link to="/auditing">
                <el-dropdown-item>
                  审核详情
                </el-dropdown-item>
                </router-link>
              </label>
               <label>
                <router-link to="/">
                <el-dropdown-item>
                  退出登录
                </el-dropdown-item>
                </router-link>
              </label>
          </el-dropdown-menu>
        </el-dropdown>
 <!-- <el-menu-item index="4"><router-link to="/handleThree">删除账单</router-link></el-menu-item> -->
  <!-- <el-menu-item index="3" disabled>消息中心</el-menu-item> -->
  
</el-menu>
<div style="position:relative;top:60px">
<router-view />
</div>
    </div>
</template>



<script>
export default {
    name: 'Loading',
    data(){
   return{
 id: this.$route.query.id,
 userTouxiang: ''
   }

    },
    created(){
      this.queryUserTouxiang()
    },
    methods:{
      queryUserTouxiang(){
            this.$axios.get("/user/queryUserTouxiang", {
                params:{
                    id: this.getCookie("user").userId
                }
            }).then(res => {
              this.userTouxiang=res.data
            })
      }
    }
}
</script>

<style scoped>
.el-menu{
  position: fixed;
  top:0;
  height: 60px;
  z-index: 1;
  width: 100vw;
}
.el-avatar /deep/ img{
  width: 100%;
  height: 100%;
}
</style>