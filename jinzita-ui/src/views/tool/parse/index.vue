<template>
  <div class="parse-douyin-user">
    <el-card class="parse-layout-card">
      <div slot="header">
        解析用户
      </div>
      <el-row :gutter="10">
        <el-col :span="12">
          <el-card class="parse-home-link-card">

            <el-input placeholder="请输入抖音的主页链接" v-model:value="homeLinkUrl" />
            {{homeLinkUrl}}

          </el-card>
        </el-col>
        <el-col :span="12">
          <el-card>
            <el-button @click="parseHomeLink">点击解析主页链接</el-button>
          </el-card>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'index',
  data() {
    return {
      homeLinkUrl: ''
    }
  },
  methods: {

    async parseHomeLink() {

      const userToken = this.homeLinkUrl.split('/').splice(-1)
      console.log(userToken)

    }
  }
}
</script>

<style scoped lang="scss">
.parse-douyin-user {
  padding: 10px;
  height: calc(100vh - 84px);
  .parse-layout-card {
    height: 100%;

    .parse-home-link-card {


      .home-link-iframe {
        width: 100%;
      }
    }

  }
}
</style>
