<template>
    <div id="regist">
<!-- 测试id:{{id}} -->

   <div style="width:30%;padding-top:110px;margin-left:40%">
      <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign">
  <el-form-item label="姓名">
    <el-input v-model="formLabelAlign.name" ></el-input>
  </el-form-item>
  <el-form-item label="密码">
    <el-input v-model="formLabelAlign.password"></el-input>
  </el-form-item>
  <el-form-item label="确认密码">
    <el-input v-model="formLabelAlign.repassword"></el-input>
  </el-form-item >
  <el-form-item label="性别" >
  <el-radio v-model="formLabelAlign.radio" label="1" >男</el-radio>
  <el-radio v-model="formLabelAlign.radio" label="2">女</el-radio>
    <!-- <el-input v-model="formLabelAlign.type"></el-input> -->
  </el-form-item>
  <el-button type="primary" style="margin-left:20%" ><router-link to="/">取消注册</router-link></el-button>
  <el-button type="success"  @click="zhuce">确认注册</el-button>
      </el-form>
  </div>


    </div>
</template>

<script>
export default {
    name: 'Regist',
    data(){
        return{
            id: this.$route.query.id,

              formLabelAlign: {
          name: '',
          password: '',
          repassword: '',
            radio: ''
        },
     
        }
    },
    methods: {

   zhuce(){

       if(this.formLabelAlign.password == this.formLabelAlign.repassword){
    //Post的方式
          this.$axios.post("/user/zhuceMysql", this.formLabelAlign).then(response => {
            // 拿到返回值后的处理
            // alert(response.data.status)
            if(response.data.status=="成功"){
              this.$router.push({
                    name:'Index'
              })
            }else {
             alert("注册失败！")
            //  this.$router.push({
            //                       name:'Lookchart'
            //                 })
            }

            // alert(res.data.users[0].userId)
          }).catch({
            //异常处理
          })
      }else{
        alert("密码前后输入不一致，请重新输入!")
      }
      
        }

    }

}
</script>

<style scoped>




</style>