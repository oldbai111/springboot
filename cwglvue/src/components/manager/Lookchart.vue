<template>
    <div id="lookchart">

 <div id="chart_example">
 
    </div>


    </div>
</template>

<script>

 import echarts from 'echarts'

export default {

    name: 'Lookchart',
    data() {
      return {
      query: {
            pageNo: 1,
            pageSize: 10,
            total: 4,
            userId: this.getCookie('user').userId,
        },
        data1:[],
        data2:[],
        data3:[]
      }
    },
     created() {
 this.init()
    },
 methods: {
      init(){
        this.$axios.post("/account/pointing3", JSON.stringify(this.query), {
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

         var data3 = new Array()
          res.data.result.forEach(item=>{
            data3.push(item.value2)
          })
        this.data3=data3

          this.query=res.data.query
           this.mounted()
        })
 

      },

    mounted() {
      let this_ = this;
      let myChart = echarts.init(document.getElementById('chart_example'));
      let option =  {
    title: {
        text: '人员收支情况表',
        subtext: '本周'
    },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data: ['支出', '收入']
    },
    toolbox: {
        show: true,
        feature: {
            dataView: {show: true, readOnly: false},
            magicType: {show: true, type: ['line', 'bar']},
            restore: {show: true},
            saveAsImage: {show: true}
        }
    },
    calculable: true,
    xAxis: [
        {
            type: 'category',
            data: this.data1
        }
    ],
    yAxis: [
        {
            type: 'value'
        }
    ],
    series: [
        {
            name: '支出',
            type: 'bar',
            data: this.data2,
            // markPoint: {
            //     data: [
            //         {type: 'max', name: '最大值'},
            //         {type: 'min', name: '最小值'}
            //     ]
            // }
            markLine: {
                data: [
                    {type: 'average', name: '平均值'}
                ]
            }
        },
        {
            name: '收入',
            type: 'bar',
            data: this.data3,
            // markPoint: {
            //     data: [
            //         {name: '年最高', value: 182.2, xAxis: 7, yAxis: 183},
            //         {name: '年最低', value: 2.3, xAxis: 11, yAxis: 3}
            //     ]
            // },
            markLine: {
                data: [
                    {type: 'average', name: '平均值'}
                ]
            }
        }
    ]
};
      myChart.setOption(option);
 
      //建议加上以下这一行代码，不加的效果图如下（当浏览器窗口缩小的时候）。超过了div的界限（红色边框）
      window.addEventListener('resize',function() {myChart.resize()});
    },
     
    watch: {},
 }
  
   
}
</script>

<style scoped>
 #chart_example{
     padding-top:30px;
    width: 100%;
    height: 500px;
    border: 1px solid gray;
    margin: 0 auto;
  }
  canvas{
      width:100%
  }
</style>