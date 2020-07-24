<template>
    <div id="index">
        <!-- <h2 >家庭财务管理</h2> -->
      <div style="width:30%;padding-top:100px;margin-left:40%">
        家庭财务管理
      <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign">
  <el-form-item label="姓名">
    <el-input v-model="formLabelAlign.name" ></el-input>
  </el-form-item>
  <el-form-item label="密码" >
    <el-input type="password" v-model="formLabelAlign.password" @keyup.enter.native="loading"></el-input>
  </el-form-item>
  <el-form-item label="登录类型">
  <el-select v-model="formLabelAlign.type" placeholder="请选择">
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>
    <!-- <el-input v-model="formLabelAlign.type"></el-input> -->
  </el-form-item>
  <!-- <el-button type="primary" style="margin-left:5%" @click="viploading">管理员登录</el-button> -->
  <el-button type="primary" style="margin-left:20%" @click="loading">登录</el-button>
  <el-button type="success" @click="regist">注册</el-button>
</el-form>
</div>
<!-- <el-button @click="userTest">请求后台user/test接口</el-button>
后台user/test接口返回：{{test}}<br />




<el-button @click="userTestMysql">请求后台user/testMysql接口</el-button>
后台user/testMysql接口返回：{{testMysql}} -->
    </div>
</template>

<script>
export default {
    name: 'index',
    data() {
      return {
          test: '',
          testMysql: '初始',
        labelPosition: 'right',
        formLabelAlign: {
          name: '',
          password: '',
          type: ''
        },
          options: [{
          value: '2',
          label: '用户'
        }, {
          value: '1',
          label: '管理员'
        }],
        // value: this.formLabelAlign.type
      }
    },
    methods: {
        userTest(){
            this.$axios.get("/user/test", {
                params:{
                    id: 'h1hj3-hj34-3ij13'
                }
            }).then(res => {
                if(res.data.status == "成功"){
                    this.test = res.data.id
                }else{
                    this.test = '失败'
                }
            }).catch(err => {
                this.test = '异常'
            })
        },
        userTestMysql(){
            this.$axios.get("/user/testMysql").then(res => {
                if(res.data.status == "成功"){
                    this.testMysql = res.data.users
                }else{
                    this.testMysql = '失败'
                }
            }).catch(err => {
                this.testMysql = '异常'
            })
        },
        regist(){
            this.$router.push({
              name:'Regist',
              query:{
                id: 'adfafa'
              }
            })
        },
        loading(){
          // this.$router.push({
          //       name:'Lookingzd',
          //        query:{
          //       id: 'fdogk'
          //     }

          // })

          //Post的方式
          this.$axios.post("/user/testMysql", this.formLabelAlign).then(response => {
            // 拿到返回值后的处理
            // alert(response.data.status)
            if(response.data.status=="成功"){
              //账密正确设置用户信息Cookie,单位分钟
              let { user } = response.data;
              this.setCookie("user", user, 1440);
              // alert(this.getCookie('user').userId);
              if(this.getCookie('user').loadPower=="2"){
                  this.$router.push({
                          name:'Lookingzd'
                    })
              }else if(this.getCookie('user').loadPower=="1"){
                  this.$router.push({
                      name:'Lookchart'
                      })
              }
            }

            // alert(res.data.users[0].userId)
          }).catch({
            //异常处理
          })
        },
  //       viploading(){
  // this.$router.push({
  //               name:'Lookchart',
  //                query:{
  //               user: 'fdogk'
  //             }

  //         })

  //       }

    }
    
}
</script>

<style scoped>

#index{
  width: 100vw;
  height:100vh;
  background-image: url('../../static/img/1.jpg');
}
.el-form{
  backdrop-filter: blur(50px) brightness(130%);
}
.el-input{
  width:300px
}
</style>