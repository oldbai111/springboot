<template>
<div id='addproject'>
添加用户 

<el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
  
  <el-form-item label="项目名称" prop="name">
    <el-input v-model="ruleForm.name"></el-input>
  </el-form-item>
 
 <!-- <el-form-item label="性别" prop="sex"> 
 <el-radio v-model="ruleForm.sex" label="1">男</el-radio>
  <el-radio v-model="ruleForm.sex" label="2">女</el-radio>
   </el-form-item> -->

  <el-form-item label="权限" prop="region">
    <el-select v-model="ruleForm.region" placeholder="请选择收支类型">
      <el-option label="支出" value="play"></el-option>
      <el-option label="收入" value="income"></el-option>
    </el-select>
  </el-form-item>

<!-- <el-form-item label="密码" prop="password">
    <el-input v-model="ruleForm.password"></el-input>
  </el-form-item> -->


  <el-form-item>
    <el-button type="primary" @click="submitForm('ruleForm')">立即添加</el-button>
    <el-button @click="resetForm('ruleForm')">取消</el-button>
  </el-form-item>
</el-form>

</div>
</template>

<script>
export default {

    name: 'Addproject',
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
            { required: true, message: '请输入项目名称', trigger: 'blur' },
            { min: 2, max: 10, message: '长度在 2 到 8 个字符', trigger: 'blur' }
          ],
          
          region: [
            { required: true, message: '请选择收支类型', trigger: 'change' }
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