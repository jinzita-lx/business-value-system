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
      default: '100%'
    }
  },
  data() {
    return {
      chart: null,
      businessValueList: ['compositeMarketValue', 'businessAdaptationExponent', 'spreadExponent', 'activityExponent', 'growthExponent', 'healthExponent'],
      businessValueIndicatorList: [],
      listTypeList: [],
      indicatorData: [],
      seriesData: [],
    }
  },
  created() {
    this.initData()
  },
  mounted() {
    this.$nextTick(() => {
      this.initChart()
    })
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
      const res = await GetRaddarChart();
      if(res.code === 200) {
        this.businessValueIndicatorList = res.data.businessValueIndicatorList;
        this.listTypeList = res.data.listTypeList;
        this.businessValueIndicatorList.map((item, index) => {
          this.indicatorData[index] = {
            name: item.indicatorName,
            max: item.indicatorMax
          }
        })
        this.listTypeList.map((item, index) => {
          this.seriesData[index] = {
            value: this.businessValueList.map(valueKey => item[valueKey]),
            name: item.typeName
          }
        })
        this.seriesData.sort((a, b) => b.value.reduce(this.reducer) - a.value.reduce(this.reducer))
        this.initChart()
      }
    },
    reducer(x, y) {
      return x + y;
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
          indicator: this.indicatorData
        },
        legend: {
          left: 'center',
          type: 'scroll',
          bottom: '10',
          data: this.seriesData.map(item => item.name).sort(() => -1)
        },
        series: [{
          type: 'radar',
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
          data: this.seriesData,
          animationDuration: animationDuration
        }]
      })
    }
  }
}
</script>
