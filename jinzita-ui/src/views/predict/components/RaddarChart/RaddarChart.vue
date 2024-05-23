<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import * as echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'
import { GetRaddarChart } from '@/api/resource/home-data'

const animationDuration = 3000

// 商业价值均值
export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '600px'
    }
  },
  data() {
    return {
      chart: null,
      chartData: null,
    }
  },
  mounted() {
    if(this.chartData) {
      this.renderChart()
    }
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    reducer(x, y) {
      return x + y;
    },
    renderChart() {
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        tooltip: {
          trigger: 'axis',
          axisPointer: { // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        radar: {
          radius: '66%',
          center: ['50%', '42%'],
          splitNumber: 8,
          splitArea: {
            areaStyle: {
              color: 'rgba(127,95,132,.3)',
              opacity: 1,
              shadowBlur: 45,
              shadowColor: 'rgba(0,0,0,.5)',
              shadowOffsetX: 0,
              shadowOffsetY: 15
            }
          },
          indicator: [
            { name: '综合营销价值', max: 100 },
            { name:  '商业适应指数', max: 100 },
            { name: '传播指数', max: 100 },
            { name:  '活跃度指数', max: 100 },
            { name: '成长指数', max: 100 },
            { name: '健康指数', max: 100 },
          ]
        },
        series: [{
          type: 'radar',
          tooltip: {
            trigger: 'item'
          },
          symbolSize: 0,
          areaStyle: {
            normal: {
              shadowBlur: 13,
              shadowColor: 'rgba(0,0,0,.2)',
              shadowOffsetX: 0,
              shadowOffsetY: 10,
              opacity: 1
            }
          },
          data: [
            {
              value: this.chartData,
              name: '预测结果'
            }
          ],
          animationDuration: animationDuration
        }]
      })
    },
    initChart(chartData) {
      this.chartData = chartData;
      this.renderChart()
    }
  }
}
</script>
