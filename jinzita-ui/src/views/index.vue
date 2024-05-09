<template>
  <div class="app-container home">
    <el-card class="home-card">
      <template slot="header">
        <div class="header">
          <logo-svg class="header-logo" />
          <span class="header-title">
            {{ title }}
          </span>
        </div>
      </template>

      <div class="dashboard-editor-container">
        <panel-group @handleSetLineChartData="handleSetLineChartData" />
        <el-row
          style="background: #fff; padding: 16px 16px 0; margin-bottom: 32px"
        >
          <line-chart :chart-data="lineChartData" />
        </el-row>

        <el-row :gutter="32">
          <el-col :xs="24" :sm="24" :lg="8">
            <div class="chart-wrapper">
              <raddar-chart />
            </div>
          </el-col>
          <el-col :xs="24" :sm="24" :lg="8">
            <div class="chart-wrapper">
              <pie-chart />
            </div>
          </el-col>
          <el-col :xs="24" :sm="24" :lg="8">
            <div class="chart-wrapper">
              <bar-chart />
            </div>
          </el-col>
        </el-row>
      </div>
    </el-card>
  </div>
</template>

<script>
import PanelGroup from "./dashboard/PanelGroup";
import LineChart from "./dashboard/LineChart";
import RaddarChart from "./dashboard/RaddarChart";
import PieChart from "./dashboard/PieChart";
import BarChart from "./dashboard/BarChart";
import logoSvg from "../layout/components/Sidebar/logoSvg.vue";

const lineChartData = {
  newVisitis: {
    expectedData: [100, 120, 161, 134, 105, 160, 165],
    actualData: [120, 82, 91, 154, 162, 140, 145],
  },
  messages: {
    expectedData: [200, 192, 120, 144, 160, 130, 140],
    actualData: [180, 160, 151, 106, 145, 150, 130],
  },
  purchases: {
    expectedData: [80, 100, 121, 104, 105, 90, 100],
    actualData: [120, 90, 100, 138, 142, 130, 130],
  },
  shoppings: {
    expectedData: [130, 140, 141, 142, 145, 150, 160],
    actualData: [120, 82, 91, 154, 162, 140, 130],
  },
};

export default {
  name: "Index",
  components: {
    logoSvg,
    PanelGroup,
    LineChart,
    RaddarChart,
    PieChart,
    BarChart,
  },
  data() {
    return {
      title: process.env.VUE_APP_TITLE,
      lineChartData: lineChartData.newVisitis,
    };
  },
  methods: {
    handleSetLineChartData(type) {
      this.lineChartData = lineChartData[type];
    },
  },
};
</script>

<style scoped lang="scss">
$width: 40px;
.home-card {
  height: calc(100vh - 124px);
  .header {
    display: flex;
    justify-content: center;
    align-items: center;
    height: $width * 4 / 3;
    .header-title {
      font-size: $width;
      font-weight: 700;
    }
    .header-logo {
      height: 100%;
    }
  }
  .dashboard-editor-container {
    padding: 32px;
    background-color: rgb(240, 242, 245);
    position: relative;

    .chart-wrapper {
      background: #fff;
      padding: 16px 16px 0;
    }
  }

  @media (max-width: 1024px) {
    .chart-wrapper {
      padding: 8px;
    }
  }
}
</style>

