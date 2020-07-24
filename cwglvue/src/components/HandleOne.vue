<template>
<div id='handleone'>
添加账单 

<el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
  <el-form-item label="项目名称" prop="name">
    <el-input v-model="ruleForm.name"></el-input>
  </el-form-item>
  <el-form-item label="收支类型" prop="c">
    <el-select v-model="ruleForm.region" placeholder="请选择收支类型">
      <el-option label="支出" value="2"></el-option>
      <el-option label="收入" value="1"></el-option>
    </el-select>
  </el-form-item>
  <el-form-item
    label="金额"
    prop="money"
    :rules="[
      { required: true, message: '金额不能为空'},
      { type: 'number', message: '金额必须为数字值'}
    ]"
  >
    <el-input type="money" v-model.number="ruleForm.money" autocomplete="off"></el-input>
  </el-form-item>

  <el-form-item label="日期" required>
    <el-col :span="11">
      <el-form-item prop="date1">
        <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.date1" style="width: 100%;"></el-date-picker>
      </el-form-item>
    </el-col>
  </el-form-item>

 
  <el-form-item label="详情备注" prop="desc">
    <el-input type="textarea" v-model="ruleForm.desc"></el-input>
  </el-form-item>

  <el-form-item>
    <el-button type="primary" @click="addaccount">立即添加</el-button>
    <el-button @click="resetForm('ruleForm')">取消</el-button>
  </el-form-item>
</el-form>

</div>
</template>

<script>
export default {

    name: 'HandleOne',
   data() {
      return {
         
        ruleForm: {
          name: '', 
          region: '',
          date1: '',
          userid: this.getCookie('user').userId,
          delivery: false,
          type: [],
          resource: '',
          desc: '',
           money: ''
        },
        rules: {
          name: [
            { required: true, message: '请输入收支项目名称', trigger: 'blur' },
            { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
          ],
          region: [
            { required: true, message: '请选择收支类型', trigger: 'change' }
          ],
          date1: [
            { type: 'date', required: true, message: '请选择日期', trigger: 'change' }
          ],
          desc: [
            { required: true, message: '请填写收支详情', trigger: 'blur' }
          ]
        }
      };
    },
    methods: {
      // submitForm(formName) {
      //   this.$refs[formName].validate((valid) => {
      //     if (valid) {
      //       alert('submit!');
      //     } else {
      //       console.log('error submit!!');
      //       return false;
      //     }
      //   });
      // },
      addaccount(){
           //Post的方式
          this.$axios.post("/account/addAccount", this.ruleForm).then(response => {
            // 拿到返回值后的处理
            // alert(response.data.status)
            if(response.data.status=="成功" ){
              this.$router.push({
                    name:'Lookingzd'
              })
            }else{
              alert("添加失败！")
            }
            // alert(res.data.users[0].userId)
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