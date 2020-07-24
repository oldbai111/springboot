<template>
  <div id="otherlook">
    <h2>近期支出账单</h2>
    
    <div id="chart_example">
 
    </div>
  </div>
</template>

<script>
  import echarts from 'echarts'
  export default {
    data() {
      return {

            query: {
            pageNo: 1,
            pageSize: 10,
            total: 4,
            userId: this.getCookie('user').userId,
        },
        data1:[],
        data2:[]


      }
    },
      created() {
     this.init()
    },
      methods: {

   init(){
        this.$axios.post("/account/pointing2", JSON.stringify(this.query), {
            headers: {
                "Content-Type": "application/json;charset=utf-8"  //头部信息
            }
        }).then(res => {
         
          var data1 = new Array()
          res.data.result.forEach(item=>{
            data1.push(item.name)
          })
          this.data1=data1

          var data2 = new Array()
          res.data.result.forEach(item=>{
            data2.push(item.value)
          })
        this.data2=data2

          this.query=res.data.query
           this.mounted()
        })
 

      },

    mounted() {
      let this_ = this;
      let myChart = echarts.init(document.getElementById('chart_example'));
      let option = {
        color: ['#f44'],
        tooltip : {
          trigger: 'axis',
          axisPointer : {
            type : 'shadow'
          }
        },
        xAxis : [
          {
            type : 'category',
            data :this.data1,
            axisTick: {
              alignWithLabel: true
            }
          }
        ],
        yAxis : [
          {
            type : 'value'
          }
        ],
        series : [
          {
            name:'近期花费',
            type:'bar',
            barWidth: '60%',
            data:this.data2
          }
        ]
      };
      myChart.setOption(option);
 
      //建议加上以下这一行代码，不加的效果图如下（当浏览器窗口缩小的时候）。超过了div的界限（红色边框）
      window.addEventListener('resize',function() {myChart.resize()});
    },
  
    watch: {}
  }

  }
</script>

<style scoped>
  h2{
    text-align: center;
    padding: 30px;
    font-size: 18px;
  }
  #chart_example{
    width: 50%;
    height: 500px;
    border: 1px solid red;
    margin: 0 auto;
  }
</style>

 


