<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import * as echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'
import { GetPieChart } from '@/api/resource/home-data'
// 商业价值占比
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
      default: '100%'
    }
  },
  data() {
    return {
      chart: null,
      seriesData: [],
    }
  },
  created() {
    this.initData();
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    async initData() {
      const res = await GetPieChart();
      if(res.code === 200) {
        console.log(res.data)
        res.data.map((item, index) => {
          this.seriesData[index] = {
            value: item.typeNum,
            name: item.typeName
          }
        })
        this.seriesData.sort((a, b) => a.value - b.value)
      }
      this.initChart();
    },
    initChart() {
      this.chart = echarts.init(this.$el, 'macarons')

      this.chart.setOption({
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        // legend: {
        //   left: 'center',
        //   bottom: '10',
        //   data: this.seriesData.map(item => item.name)
        // },
        series: [
          {
            name: '商业价值占比',
            type: 'pie',
            roseType: 'radius',
            radius: [15, 95],
            center: ['50%', '38%'],
            data: this.seriesData,
            animationEasing: 'cubicInOut',
            animationDuration: 2600
          }
        ]
      })
    }
  }
}
</script>
