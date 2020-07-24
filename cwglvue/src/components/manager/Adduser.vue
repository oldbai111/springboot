<template>
<div id='adduser'>
添加用户 

<el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
  
  <el-form-item label="姓名" prop="name">
    <el-input v-model="ruleForm.name"></el-input>
  </el-form-item>
 
 <el-form-item label="性别" prop="sex"> 
 <el-radio v-model="ruleForm.sex" label="1">男</el-radio>
  <el-radio v-model="ruleForm.sex" label="2">女</el-radio>
   </el-form-item>

  <el-form-item label="权限" prop="region">
    <el-select v-model="ruleForm.region" placeholder="请选择权限">
      <el-option label="普通用户" value="2"></el-option>
      <el-option label="管理员" value="1"></el-option>
    </el-select>
  </el-form-item>

<el-form-item label="密码" prop="password">
    <el-input v-model="ruleForm.password"></el-input>
  </el-form-item>


  <el-form-item>
    <el-button type="primary" @click="adduser">立即添加</el-button>
    <el-button @click="resetForm('ruleForm')">取消</el-button>
  </el-form-item>
</el-form>

</div>
</template>

<script>
export default {
    inject: ['reload'],
    name: 'Adduser',
   data() {
      return {
         
        ruleForm: {
          name: '',
            sex:'1', 
          region: '',
          password:'',
        
          
          delivery: false,
          type: [],
          resource: '',
           money: ''
        },
        rules: {
          name: [
            { required: true, message: '请输入姓名', trigger: 'blur' },
            { min: 2, max: 10, message: '长度在 2 到 4 个字符', trigger: 'blur' }
          ],
          sex: [
            { required: true, message: '请选择性别', trigger: 'change' }
          ],
          region: [
            { required: true, message: '请选择权限', trigger: 'change' }
          ]
        
        }
      };
    },
    methods: {
      adduser() {
            alert("确认添加？")
        //Post的方式
          this.$axios.post("/user/adduser", this.ruleForm).then(response => {
            // 拿到返回值后的处理
            if(response.data.status=="成功" ){

            // this.reload()
            this.$router.push({
              path: "usermanage"
            })
            }else{
              alert("添加失败！")
            }
          
          }).catch({
            //异常处理
          })
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
}


</script>

<style scoped>

</style>