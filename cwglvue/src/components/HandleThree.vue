<template>
    <div id='handlethree'> 
        修改账单页面

<el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
  <el-form-item label="项目名称" prop="name">
    <el-input v-model="ruleForm.name"></el-input>
  </el-form-item>
  <el-form-item label="收支类型" prop="region">
    <el-select v-model="ruleForm.region" placeholder="请选择收支类型">
      <el-option label="支出" value="zhichu"></el-option>
      <el-option label="收入" value="shouru"></el-option>
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
    <el-button type="primary" @click="submitForm('ruleForm')">立即修改</el-button>
    <el-button @click="resetForm('ruleForm')">取消</el-button>
  </el-form-item>
</el-form>

    </div>
</template>


<script>
export default {
    name: 'HandleThree',
 data() {
      return {
         
        ruleForm: {
          name: '学费', 
          region: '支出',
          date1: '2019-05-02',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: '电子支付',
           money: '1000'
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
     created(){
      this.init()
    },
    methods: {
       init(){
        this.$axios.post("/account/selecAccountInfoByuserId", JSON.stringify(this.query), {
            headers: {
                "Content-Type": "application/json;charset=utf-8"  //头部信息
            }
        }).then(res => {
          
          this.tableData=res.data.result
          this.query=res.data.query
        })
      },
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