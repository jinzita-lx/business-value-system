<template>
  <div class="parse-douyin-user">
    <el-card class="parse-layout-card">
      <div slot="header">解析用户</div>
      <el-row :gutter="10">
        <el-col :span="12">
          <el-card class="parse-home-link-card">
            <el-input
              placeholder="请输入抖音的主页链接"
              v-model="homeLinkUrl"
            />
            <div v-html="homeLinkUrl" style="width: 400px; height: 400px"></div>
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
import request from "@/utils/request";
export default {
  name: "index",
  data() {
    return {
      homeLinkUrl: "",
      douyin_oauth: "https://open.douyin.com/platform/oauth/connect/",
      homeDoc: "",
    };
  },
  methods: {
    async oauthApi() {
      console.log("主页信息", this.homeLinkUrl);
      this.homeDoc = await request({
        url: this.homeLinkUrl,
        method: "GET",
        headers: {
          "User-Agent":
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36",
        },
      }).catch((err) => {
        console.log(err, "链接错误");
      });
      console.log(this.homeDoc, "用户主页");
    },

    async parseHomeLink() {
      const userToken = this.homeLinkUrl.split("/").splice(-1);
      console.log(userToken);
      await this.oauthApi();
    },
  },
};
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
