<template>
    <div id="moneylook">

   <span >金额查询账单</span>

<el-divider></el-divider>


  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
  
  <el-form-item label="查询金额" required>
    <el-col :span="11">
      <el-form-item prop="money1">
          <el-input  type="number" v-model="formInline.money1" placeholder="金额"></el-input>
        
      </el-form-item>
    </el-col>
    <el-col class="line" :span="1">————————</el-col>
    <el-col :span="11">
      <el-form-item prop="money2">
         <el-input type="number" v-model="formInline.money2" placeholder="金额"></el-input>
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
    name: 'Moneylook',
        data() {
      return {
   formInline: {
         money1:'',
        money2: ''
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