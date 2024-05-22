<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import * as echarts from 'echarts'
import 'echarts/theme/macarons'// echarts theme
import resize from './mixins/resize'
import { GetBarChart } from '@/api/resource/home-data'

// 横坐标榜单类型
// 纵坐标整体价值
// 每一条是一种价值指标

const animationDuration = 4000

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
      businessValueList: ['compositeMarketValue', 'businessAdaptationExponent', 'spreadExponent', 'activityExponent', 'growthExponent', 'healthExponent'],
      indicatorList: [],
      listTypeList: [],
      xAxisData: [],
      seriesList: []
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
      const res = await GetBarChart();
      if(res.code === 200) {
        this.indicatorList = res.data.businessValueIndicatorList;
        this.listTypeList = res.data.listTypeList;

        this.indicatorList.map((item, index) => {
          this.seriesList[index] = {
            name: item.indicatorName,
            type: 'bar',
            stack: 'vistors',
            barWidth: '60%',
            data: [],
            animationDuration
          }
        })
        this.listTypeList = this.listTypeList.sort(this.compareFn)
        this.listTypeList.map((item, index) => {
          this.xAxisData[index] = item.typeName
          this.businessValueList.map((legend, idx) => {
            this.seriesList[idx].data[index] = item[legend]
          })
        })
        this.initChart()
      }

    },
    compareFn(a, b) {
      const aSum = this.businessValueList.reduce((sum, value) => sum + a[value], 0)
      const bSum = this.businessValueList.reduce((sum, value) => sum + b[value], 0)
      return bSum - aSum
    },
    initChart() {
      this.chart = echarts.init(this.$el, 'macarons')

      this.chart.setOption({
        tooltip: {
          trigger: 'axis',
          axisPointer: { // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        grid: {
          top: 10,
          left: '2%',
          right: '2%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [{
          type: 'category',
          data: this.xAxisData,
          axisTick: {
            alignWithLabel: true
          }
        }],
        yAxis: [{
          type: 'value',
          axisTick: {
            show: false
          }
        }],
        series: this.seriesList
      })
    }
  }
}
</script>
