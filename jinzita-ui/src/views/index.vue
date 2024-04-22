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
      <el-row>
        <el-col :span="12">
          <el-card> 1 </el-card>
        </el-col>
        <el-col :span="12">
          <el-card> 2 </el-card>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
import logoSvg from "../layout/components/Sidebar/logoSvg.vue";
export default {
  components: { logoSvg },
  name: "Index",
  data() {
    return {
      title: process.env.VUE_APP_TITLE,
    };
  },
  methods: {},
};
</script>

<style scoped lang="scss">
$width: 60px;
.home-card {
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
}
</style>

