<template>
    <div id="person">



   <div style="width:30%;padding-top:110px;margin-left:40%">
      <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign">
          
        <el-upload
  class="avatar-uploader"
  :action="'http://localhost:8083/user/uploadUserTx?id='+this.getCookie('user').userId"
  :show-file-list="false"
  :on-success="handleAvatarSuccess"
  :before-upload="beforeAvatarUpload">
  <img :src="'http://localhost:8083/img/'+formLabelAlign.userTouxiang" class="avatar">
  <i v-if="!formLabelAlign.userTouxiang" class="el-icon-plus avatar-uploader-icon"></i>
</el-upload>
  <el-form-item label="姓名">
    <el-input v-model="formLabelAlign.name" ></el-input>
  </el-form-item>

  <el-form-item label="密码">
    <el-input v-model="formLabelAlign.password"></el-input>
  </el-form-item>
  
  <el-form-item label="性别">
  <el-radio v-model="formLabelAlign.radio" label="1">男</el-radio>
  <el-radio v-model="formLabelAlign.radio" label="2">女</el-radio>
    <!-- <el-input v-model="formLabelAlign.type"></el-input> -->
  </el-form-item>
  <el-button type="primary" style="margin-left:20%">取消修改</el-button>
  <el-button type="success"  @click="tiaozhuan()">确认修改</el-button>
</el-form>
</div>


    </div>
</template>

<script>
export default {
  inject: ['reload'],
    name: 'Person',
    data(){
        return{
            id: this.$route.query.id,

              formLabelAlign: {
          name: this.getCookie('user').userName,
          password: this.getCookie('user').userPassword,
           radio: this.getCookie('user').userSex,
           userTouxiang: this.getCookie('user').userTouxiang,
        },
     
       
        imageUrl: '',
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
              this.formLabelAlign.userTouxiang=res.data
            })
        },
        tiaozhuan(){
              this.$router.push({
                    name:'Lookingzd'
              })
        },
      handleAvatarSuccess(res, file) {
        // this.imageUrl = URL.createObjectURL(file.raw);
        this.queryUserTouxiang()
        this.reload()
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      }
    }
    
}
</script>

<style scoped>
.el-form >>> .avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  left: 40%;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.el-form >>> .avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.el-form >>> .avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 100px;
  height: 100px;
  line-height: 100px;
  text-align: center;
}
.el-form >>> .avatar {
  width: 100px;
  height: 100px;
  display: block;
}
</style>