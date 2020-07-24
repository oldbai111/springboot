<template>
<div id='handletwo'>
图表展示 
<div id="main" style="width: 100%;height: 700px;">


        </div>


</div>
</template>

<script>
// export default {
//     name: 'HandleTwo',
//      data() {
//       return {
       
//       }
//     }
    
// }
import echarts from 'echarts'
    export default {
        name: '',
        data () {
            return {
                charts: '',
                // opinion:['外卖','网上购物','买水果','买食材','工资'],
                opinion:[],
                // opinionData:[
                //   {accountMoney:335, accountNmae:'外卖'},
                //   {accountMoney:310, accountNmae:'网上购物'},
                //   {value:234, name:'买水果'},
                //   {value:135, name:'买食材'},
                //   {value:1548, name:'工资'}
                // ],
                opinionData:[],
                // opinionData:[{name:"买手机",value:4000},{name:"学费",value:1000},{name:"测试2",value:520},{name:"订外卖",value:60}],
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
        methods:{
            init(){
        this.$axios.post("/account/pointing", JSON.stringify(this.query), {
            headers: {
                "Content-Type": "application/json;charset=utf-8"  //头部信息
            }
        }).then(res => {
          this.opinionData=res.data.result
          var opinion = new Array()
          res.data.result.forEach(item=>{
            opinion.push(item.name)
          })
          this.opinion=opinion
          this.query=res.data.query
          //重新渲染
          this.drawPie('main')
        })
      },
            drawPie(id){
               this.charts = echarts.init(document.getElementById(id))
               this.charts.setOption({
                 tooltip: {
                   trigger: 'item',
                   formatter: '{a}<br/>{b}:{c} ({d}%)'
                 },
                 legend: {
                   orient: 'vertical',
                   x: 'left',
                   data:this.opinion
                 },
                 series: [
                   {
                     name:'访问来源',
                     type:'pie',
                     radius:['50%','70%'],
                     avoidLabelOverlap: false,
                     label: {
                       normal: {
                         show: false,
                         position: 'center'
                       },
                       emphasis: {
                         show: true,
                         textStyle: {
                           fontSize: '30',
                           fontWeight: 'blod'
                         }
                       }
                     },
                     labelLine: {
                       normal: {
                         show: false
                       }
                     },
                     data:this.opinionData
                   }
                 ]
               })
            }
        },
      //调用
        mounted(){
            this.$nextTick(function() {
                this.drawPie('main')
            })
        },
        // method:{
          // queryData(){
          //   this.$axios.get("/echart/painting1").then(res => {
          //     this.opinionData = res.data.opinionData;
          //   })
          // }
        // }
    }
</script>

<style scoped>

</style>