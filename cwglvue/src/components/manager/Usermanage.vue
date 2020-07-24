<template>
    <div id="usermanage"> 
      
        <router-link to="/adduser" >
 <el-button type="primary" style="">添加新用户</el-button>
        </router-link>
<el-table
    :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
    style="width: 100%">
   <!-- <el-table-column
      prop="date"
      label="日期"
      width="180">
    </el-table-column>

<el-table-column
      prop="desption"
      label="项目描述"
      width="180">
    </el-table-column> -->

    <!-- <el-table-column
      prop="type"
      label="收支类型"
      width="180">
    </el-table-column> -->

    <el-table-column
      prop="userName"
      label="姓名"
      width="180">
    </el-table-column>

    <el-table-column
      prop="userSex"
      label="性别"
      width="180" >
      <template slot-scope="scope">
        <span v-if="scope.row.userSex=='1'">男</span>
         <span v-if="scope.row.userSex=='2'">女</span>
        </template>
    </el-table-column>
 
<el-table-column
      prop="userPassword"
      label="密码"
      width="180" >
    </el-table-column>

<el-table-column
      prop="loadPower"
      label="权限"
      width="180" >
      <template slot-scope="scope">
        <span v-if="scope.row.loadPower=='1'">管理员</span>
         <span v-if="scope.row.loadPower=='2'">普通用户</span>
        </template>
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
      :page-sizes="[1, 20, 25, 100]"
      :page-size="query.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="query.total">
    </el-pagination>
  </div>
  </div>

 <el-dialog title="修改用户信息" :visible.sync="dialogFormVisible" @close='init'>
  <el-form :model="form">

      <el-form-item label="用户id" prop="id">
    <el-input v-model="form.userId" autocomplete="off" readonly="true"></el-input>
  </el-form-item>

    <el-form-item label="姓名" prop="name">
    <el-input v-model="form.userName" autocomplete="off"></el-input>
  </el-form-item>

  <el-form-item label="性别" prop="region">
    <el-select v-model="form.userSex" :placeholder="form.userSex==1 ? '男' : '女'">
      <el-option label="男" value="1"></el-option>
      <el-option label="女" value="2"></el-option>
    </el-select>
  </el-form-item>

     <el-form-item label="密码" prop="password">
    <el-input v-model="form.userPassword" autocomplete="off"></el-input>
  </el-form-item>

 
  <el-form-item label="权限" prop="power">
     <el-select v-model="form.loadPower" :placeholder="form.loadPower==1 ? '管理员' : '普通用户'" >
        <el-option label="管理员" value="1"></el-option>
      <el-option label="普通用户" value="2"></el-option>
     </el-select>
  </el-form-item>

  </el-form>

  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisible = false,init()">取 消</el-button>
    <el-button type="primary" @click="updateuser()">确 定</el-button>
  </div>

</el-dialog>


    </div>
</template>


<script>
export default {
  inject: ['reload'],
    name: 'Usermanage',
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
        this.$axios.post("/user/selectUserInfo", JSON.stringify(this.query), {
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
        this.dialogFormVisible=true;
        },
       updateuser(){
           //Post的方式
          this.$axios.post("/user/updateuser", this.form).then(response => {
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
        alert("确定删除？")
         //Post的方式
          this.$axios.post("/user/deleteuser", this.form).then(response => {
            // 拿到返回值后的处理
            if(response.data.status=="成功" ){
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