<template>
    <div id="viploading">
        <!-- 下面是上部 -->
      <div class="mheader">
        <router-link to="/publishGood">
        <label
          style="margin-left:1vw;line-height:7vh;font-size:6vh;font-family:Fantasy;color:white;cursor:pointer"
        >{{user.name ? user.name:'财务管理'}}</label></router-link>
        
  <!-- 用户头像及下拉选项 -->
        <el-dropdown style="float:right;display:inline;padding:0.5vh 2vh">
          <span class="el-dropdown-link">
            <el-badge class="item">
              <el-avatar
                style="width:10vh;height:10vh"
                src="../../static/img/1.jpg"
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


              <label @click="exit()">
                <el-dropdown-item>
                  {{user ? "退出":"登录"}}
                </el-dropdown-item>
              </label>
          </el-dropdown-menu>
        </el-dropdown>
        </div>

















      <div class="menu">
          <!-- 下面是左侧菜单，图片啥在下面改 -->
<el-menu
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
      background-color="rgb(173, 195, 192)"
      text-color="#fff"
      active-text-color="#ffd04b">
      <!-- <el-submenu index="1">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>发布商品</span>
        </template> -->
        <!-- <el-menu-item-group>
          <template slot="title">分组一</template>
          <el-menu-item index="1-1">编辑商品</el-menu-item>
          <el-menu-item index="1-2">预览商品</el-menu-item>
        </el-menu-item-group> -->
        <!-- <el-menu-item-group title="分组2"> -->
          <!-- <el-menu-item index="1-3">选项3</el-menu-item> -->
        <!-- </el-menu-item-group> -->
        <!-- <el-submenu index="1-4">
          <template slot="title">选项4</template>
          <el-menu-item index="1-4-1">选项1</el-menu-item>
        </el-submenu> -->
      <!-- </el-submenu> -->
      
      <router-link to="/zdmanage">
        <el-menu-item index="1">
          <i class="el-icon-menu"></i>
          <span slot="title">账单管理</span>
        </el-menu-item>
      </router-link>


      <router-link to="/projectmanage">
        <el-menu-item index="2">
          <i class="el-icon-setting"></i>
          <span slot="title">项目管理</span>
        </el-menu-item>
      </router-link>
      <router-link to="/usermanage">
        <el-menu-item index="3">
          <i class="el-icon-menu"></i>
          <span slot="title">用户管理</span>
        </el-menu-item>
      </router-link>
      <router-link to="/lookchart">
        <el-menu-item index="4">
          <i class="el-icon-menu"></i>
          <span slot="title">图表显示</span>
        </el-menu-item>
      </router-link>

      <!-- <router-link to="/mGood">
        <el-menu-item index="4">
          <i class="el-icon-document"></i>
          <span slot="title">商品管理</span>
        </el-menu-item>
      </router-link>

      <router-link to="/mOrder">
        <el-menu-item index="5">
          <i class="el-icon-setting"></i>
          <span slot="title">订单管理</span>
        </el-menu-item>
      </router-link> -->




    </el-menu>
    </div>

<div class="managerMain" style="position:relative;left:15vw;width:85vw">
    <router-view/>



    <div class="mFooter">&copy; 2020-2021 财务管理 版权所有</div>
</div>
</div>
</template>

<script>
export default {
    name: 'Viploading',
    data() {
      return {
        activeIndex: '1',
      tx: "img/tx.png",
      user:  this.getCookie('user')?this.getCookie('user'):'',
      };
    },
    methods: {
        handleSelect(key, keyPath) {
          console.log(key, keyPath);
        },
        exit() {
            if(this.user){
                this.$confirm('', '退出？', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          center: true
        }).then(() => {
          this.setCookie('user','',-1)
          var storage = window.localStorage
          storage.clear()
          this.$router.push("/")
          this.$message({
            type: 'success',
            message: '已退出'
          });
        });
            }else{
          this.setCookie('user','',-1)
          var storage = window.localStorage
          storage.clear()
          this.$router.push("/")
            }
      },
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }
    }
    
}
</script>

<style scoped>
.mheader{
  position: fixed;
  top:0;
  height: 7vh;
  z-index: 1;
  width: 100vw;
  /* background-color: rgb(84, 92, 100); */
  background-color:rgb(173, 195, 192);
}
.mFooter{
    text-align: center;
    position: fixed;
    bottom: 0;
    width:85%;
    color:gray;
    /* color:rgba(128, 128, 128, 0.5); */
    padding:5px;
    pointer-events:none;
}
/* .el-menu{
  position: fixed;
  width: 100vw;
  height: 60px;
  z-index: 1;
  overflow:visible;
} */
.menu{
  position: fixed;
  left: 0;
  width: 15vw;
  top:7vh;
  height: 93vh;
  z-index: 1;
  overflow-y: scroll;
}
.menu::-webkit-scrollbar {
  width: 0 !important;
}
.el-menu{
    padding-top:10vh;
  min-height: 83vh;
  /* text-align: center; */
}
.el-menu .el-menu-item{
  position: relative;
  left:2vw
}
.el-menu i,span{
  font-size: 1.3em;
}
.managerMain{
  position: absolute;
  top:7vh;
  margin-bottom: 30px;
}
    
/* 头像框内img长宽适应父级容器 */
.el-avatar /deep/ img{
  width: 100%;
  height: 100%;
}
</style>