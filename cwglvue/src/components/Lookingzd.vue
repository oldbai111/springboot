<template>
    <div id="lookingzd"> 
        自己最近的账单展示 

  
<el-table
    :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
    style="width: 100%">
   <el-table-column
      prop="accountDate"
      label="日期"
      width="180">
      <!-- <template slot-scope="scope">
      {{scope.row.accountDate | datefmt('YYYY-MM-DD')}}
      </template> -->
    </el-table-column>

<el-table-column
      prop="accountName"
      label="项目描述"
      width="180">
    </el-table-column>

    <el-table-column
      prop="accountType"
      label="收支类型"
      width="180">
      <template slot-scope="scope">
        <span v-if="scope.row.accountType=='1'">收入</span>
        <span v-if="scope.row.accountType=='2'">支出</span>
        </template>
    </el-table-column>
<!-- 
    <el-table-column
      prop="name"
      label="姓名"
      width="180">
    </el-table-column> -->

    <el-table-column
      prop="accountMoney"
      label="金额"
      width="180" >
    </el-table-column>
 
<el-table-column
      prop="accountBeizhu"
      label="备注"
      width="180" >
    </el-table-column>


    <el-table-column
      align="right">

      <template slot="header" slot-scope="{}">
        <el-input
          v-model="search"
          size="mini"
          placeholder="输入关键字搜索"/>
      </template>

      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="handleEdit(scope.$index, scope.row)">编辑</el-button>

          
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
  <div style="width:100%;text-align:center">
  <div class="block" >
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[1, 10, 25, 50, 100]"
      :page-size="query.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="query.total">
    </el-pagination>
  </div>
  </div>

  <el-dialog title="修改账单信息" :visible.sync="dialogFormVisible" @close='init'>
  <el-form :model="form">

      <el-form-item label="账单id" prop="id">
    <el-input v-model="form.accountId" autocomplete="off" readonly="true"></el-input>
  </el-form-item>

    <el-form-item label="项目描述" prop="name">
    <el-input v-model="form.accountName" autocomplete="off"></el-input>
  </el-form-item>
  <el-form-item label="收支类型" prop="region">
    <el-select v-model="form.accountType" :placeholder="form.accountType==1 ? '收入' : '支出'">
      <el-option label="收入" value="1"></el-option>
      <el-option label="支出" value="2"></el-option>
    </el-select>
  </el-form-item>
  <el-form-item
    label="金额"
    prop="money"
  >
    <el-input type="money" v-model.number="form.accountMoney" autocomplete="off"></el-input>
  </el-form-item>

  <el-form-item label="日期" required>
    <el-col :span="11">
      <el-form-item prop="date1">
        <el-date-picker type="date" placeholder="选择日期" v-model="form.accountDate" style="width: 100%;"></el-date-picker>
      </el-form-item>
    </el-col>
  </el-form-item>

 
  <el-form-item label="详情备注" prop="desc">
    <el-input type="textarea" v-model="form.accountBeizhu"></el-input>
  </el-form-item>
  </el-form>

  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisible = false,init()">取 消</el-button>
    <el-button type="primary" @click="updateAccount()">确 定</el-button>
  </div>
  
</el-dialog>

    </div>
</template>


<script>
export default {
  inject: ['reload'],
    name: 'Lookingzd',
 data() {
      return {
        tableData: [],
        dialogFormVisible: false,
        form: '',
        search: '',
        currentPage: 1,
        query: {
          pageNo: 1,
          pageSize: 10,
          total: 4,
          userId: this.getCookie('user').userId,
        }
      }
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
      handleEdit(index, row){
        this.form=row;
        // alert( this.form.accountId)
        this.dialogFormVisible=true;
          // this.$router.push({
          //       name:'HandleThree',
          //        query:{
          //      accountDate: val.accountDate,
          //      accountName: val.accountName,
          //      accountType: val.accountType,
          //      accountMoney: val.accountMoney,
          //      accountBeizhu: val.accountBeizhu
          //     }
          // })
        },
        updateAccount(){
           //Post的方式
          this.$axios.post("/account/updateAccount", this.form).then(response => {
            // 拿到返回值后的处理
            // alert(response.data.status)
            if(response.data.status=="成功" ){
              // this.$router.push({
              //       name:'Lookingzd'
              // })
              this.reload()
            }else{
              alert("修改失败！")
              this.reload()
            }
            // alert(res.data.users[0].userId)
          }).catch({
            //异常处理
          })
        },
      handleDelete(index, row) {
          this.form=row;
        // alert( this.form.accountId)
        // this.dialogFormVisible=true;
         //Post的方式
          this.$axios.post("/account/deleteAccount", this.form).then(response => {
            // 拿到返回值后的处理
            // alert(response.data.status)
            if(response.data.status=="成功" ){
              // this.$router.push({
              //       name:'Lookingzd'
              // })
              alert("确定删除？")
              this.reload()
            }else{
              alert("修改失败！")
              this.reload()
            }
            // alert(res.data.users[0].userId)
          }).catch({
            //异常处理
          })

      }
    },
      handleSizeChange(val){
        // this.query.pageSize=val;
        this.reload()
        // this.init()
      },
      handleCurrentChange(val){
        this.query.pageNo=val;
        // this.init()
      },
      timestampToTime (cjsj) {
        var date = new Date(cjsj) //时间戳为10位需*1000，时间戳为13位的话不需乘1000
        var Y = date.getFullYear() + '-'
        var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-'
        var D = date.getDate() + ' '
        var h = date.getHours() + ':'
        var m = date.getMinutes() + ':'
        var s = date.getSeconds()
        return Y+M+D+h+m+s
        // console.log(timestampToTime (1533293827000))
    }
}


</script>

<style scoped>
#mian * {
      margin: 0;
        padding: 0;
        list-style: none; 
}
</style>