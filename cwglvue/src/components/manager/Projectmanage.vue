<template>
    <div id="projectmanage"> 
       审批项目申请 
<el-table
    :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
    style="width: 100%" >
<el-table-column
      prop="itemMingcheng"
      label="项目名称"
      width="180">
    </el-table-column>

    <!-- <el-table-column
      prop="type"
      label="收支类型"
      width="180">
    </el-table-column> -->

<el-table-column
      prop="itemMoney"
      label="申请金额"
      width="180" >
    </el-table-column>

    <el-table-column
      prop="name"
      label="申请人"
      width="180">
    </el-table-column>

<el-table-column
      prop="itemRenson"
      label="申请原因"
      width="180">
    </el-table-column>
    
 
<el-table-column
      prop="itemStatus"
      label="申请状态"
      width="180" >
      <template slot-scope="scope">
        <span v-if="scope.row.itemStatus=='1'">待审核</span>
        </template>
    </el-table-column>


    <el-table-column
      align="right">
      <template slot-scope="scope">
           <el-button
          size="mini"
          type="success"
          @click="handlepass(scope.$index, scope.row)">通过</el-button>

        <el-button
          size="mini"
          type="danger"
          @click="handlerefuse(scope.$index, scope.row)">拒绝</el-button>
      </template>
    </el-table-column>
  </el-table>
  <div style="width:100%;text-align:center">
  <div class="block" >
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[1, 20, 25, 100]"
      :page-size="query.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="query.total">
    </el-pagination>
  </div>
  </div>
</div>
</template>


<script>
export default {
    inject: ['reload'],
    name: 'Projectmanage',
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
          total: 4
        }
  
      }
    },
     created(){
      this.init()
    },
    methods: {
 init(){
        this.$axios.post("/item/selectItemInfo", JSON.stringify(this.query), {
            headers: {
                "Content-Type": "application/json;charset=utf-8"  //头部信息
            }
        }).then(res => {
          
          this.tableData=res.data.result
          this.query=res.data.query
        })
      },
      handlepass(index, row){
          this.form=row;
       alert("确定通过？")
         //Post的方式
          this.$axios.post("/item/itemPass", this.form).then(response => {
            // 拿到返回值后的处理
            // alert(response.data.status)
            if(response.data.status=="成功" ){
              this.reload()
            }else{
              alert("审核失败！")
              this.reload()
            }
            // alert(res.data.users[0].userId)
          }).catch({
            //异常处理
          })
        },
      handlerefuse(index, row) {
               this.form=row;
       alert("确定拒绝该申请？")
         //Post的方式
          this.$axios.post("/item/itemrefuse", this.form).then(response => {
            // 拿到返回值后的处理
            // alert(response.data.status)
            if(response.data.status=="成功" ){
              this.reload()
            }else{
              alert("审核失败！")
              this.reload()
            }
           
          }).catch({
            //异常处理
          })
      }
    },
      handleSizeChange(val){
        this.query.pageSize=val;
        // this.init()
      },
      handleCurrentChange(val){
        this.query.pageNo=val;
        // this.init()
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