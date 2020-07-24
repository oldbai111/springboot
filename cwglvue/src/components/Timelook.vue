<template>
    <div id="timelook">

   <span >时间查询账单</span>

<el-divider></el-divider>


  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
  
  <el-form-item label="查询日期" required>
    <el-col :span="11">
      <el-form-item prop="date1">
        <el-date-picker type="date" placeholder="开始日期" v-model="ruleForm.date1" style="width: 100%;"></el-date-picker>
      </el-form-item>
    </el-col>
    <el-col class="line" :span="1">————————</el-col>
    <el-col :span="11">
      <el-form-item prop="date2">
        <el-date-picker type="date" placeholder="结束日期" v-model="ruleForm.date2" style="width: 100%;"></el-date-picker>
      </el-form-item>
    </el-col>
  </el-form-item>
   <el-form-item>
    <el-button style="width:500px" type="primary" @click="submitForm('ruleForm')">立即查询</el-button>
  </el-form-item>
</el-form>

    </div>
</template>

<script>
export default {
    name: 'Timelook',
        data() {
      return {
   ruleForm: {
          name: '',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        }
      };
    }

}
</script>

<style scoped>
#timelook{
    text-align: center;
}
</style>