<template>
<div id='edituser'>
修改用户 

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
      <el-option label="普通用户" value="simple"></el-option>
      <el-option label="管理员" value="vip"></el-option>
    </el-select>
  </el-form-item>

<el-form-item label="密码" prop="password">
    <el-input v-model="ruleForm.password"></el-input>
  </el-form-item>


  <el-form-item>
    <el-button type="primary" @click="submitForm('ruleForm')">确认修改</el-button>
    <el-button @click="resetForm('ruleForm')">取消</el-button>
  </el-form-item>
</el-form>

</div>
</template>

<script>
export default {

    name: 'Edituser',
   data() {
      return {
         
        ruleForm: {
          name: '九月',
            sex:'1', 
          region: '普通用户',
          password:'123',
        
          
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
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!');
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
}


</script>

<style scoped>

</style>
