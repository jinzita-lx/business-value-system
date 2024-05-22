<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import * as echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'
import { GetLineChart } from '@/api/resource/home-data'
// 每种榜单类型的商业价值均值
// 横坐标 榜单类型
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
    },
    autoResize: {
      type: Boolean,
      default: true
    },
  },
  data() {
    return {
      animationEasingList: ['quadraticIn', 'quadraticOut', 'quadraticInOut', 'cubicIn', 'cubicOut', 'cubicInOut', 'quarticIn', 'quarticOut'],
      businessValueList: ['compositeMarketValue', 'businessAdaptationExponent', 'spreadExponent', 'activityExponent', 'growthExponent', 'healthExponent'],
      chart: null,
      indicatorList: null,
      xAxisData: [],
      seriesList: [],
      legendData: [],
      listTypeList: null,
    }
  },
  watch: {
    chartData: {
      deep: true,
      handler(val) {
        this.setOptions(val)
      }
    }
  },
  created() {
    this.initData();
  },
  mounted() {
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
      const res = await GetLineChart()
      if(res.code === 200) {
        this.indicatorList = res.data.businessValueIndicatorList;
        this.listTypeList = res.data.listTypeList;

        this.indicatorList.map((item, index) => {
          this.legendData[index] = item.indicatorName
          this.seriesList[index] = {
            name: item.indicatorName,
            smooth: true,
            type: 'line',
            data: [],
            animationDuration: 2800,
            animationEasing: this.animationEasingList[index]
          }
        })
        this.listTypeList.sort(this.compareFn)
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
      return aSum - bSum
    },
    initChart() {
      this.chart = echarts.init(this.$el, 'macarons')
      this.setOptions()
    },
    setOptions() {
      this.chart.setOption({
        xAxis: {
          data: this.xAxisData,
          boundaryGap: false,
          axisTick: {
            show: false
          }
        },
        grid: {
          left: 10,
          right: 10,
          bottom: 20,
          top: 30,
          containLabel: true
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross'
          },
          padding: [5, 10]
        },
        yAxis: {
          axisTick: {
            show: false
          }
        },
        legend: {
          data: this.legendData
        },
        series: this.seriesList
      })
    }
  }
}
</script>
