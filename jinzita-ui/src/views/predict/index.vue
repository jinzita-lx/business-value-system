<script>
import store from "@/store";
import PredictLogin from "@/views/predict/login";
import { everyDayLoginSystem } from "@/api/predict/login";
import AwemeCard from "@/views/predict/components/aweme-card/index.vue";
import { getUserData, getUserVideo, predictUserData } from '@/api/predict/userInfo'
import { user_data, user_post_v4 } from '@/views/predict/mock'
import RaddarChart from '@/views/dashboard/RaddarChart.vue'
export default {
  name: "index",
  components: { RaddarChart, AwemeCard, PredictLogin },
  data() {
    return {
      predictState: store.state.predict,
      userAvatar: store.getters.avatar,
      username: store.getters.name,
      everyClickLoading: false,
      showParseInfo: false,
      loadingParseInfo: false,
      user_data: {},
      activeMenuMy: true,
      predictLoading: false,
      predictButtonDisable: true,
      predictStartTime: null,
      predictTime: null,
      predictResult: {},
      parseHomeLinkEnd: '',
      predictEndTime: null,
      aweme_list: [],
      predictAwemeList: [],
      max_cursor: "0",
      count: 500,
      home_link: "",
      accountId: "",
      predictLogInfo: [],
    };
  },
  methods: {
    async everydayClick() {
      this.everyClickLoading = true;
      const res = await everyDayLoginSystem(
        this.predictState.predictCookie
      ).catch((err) => {
        this.$message.error(err);
      });
      try {
        if (res.code === 200) {
          await this.$store.dispatch("SetEveryStatus", true).catch((err) => {
            this.$message.error(err);
          });
          const times = Math.round(Math.random() * 30 + 20);
          this.$message.success(`恭喜您🎉！！领取了${times}次查询用户机会！！`);
          this.everyClickLoading = false;
        }
      } catch (e) {
        this.$message.error(e);
      }
    },
    async parseDouYinUser() {
      return Promise.all([
        getUserData(this.predictState.predictCookie, {
          sec_user_id: this.accountId,
          share_text: this.home_link,
        }),
        getUserVideo(this.predictState.predictCookie, {
          sec_user_id: this.accountId,
          share_text: this.home_link,
          max_cursor: this.max_cursor,
          count: this.count,
        }),
      ]);
    },
    parseHomeLink() {
      if (this.home_link.startsWith("https://www.douyin.com/user")) {
        const token = this.home_link.split("/").splice(-1)[0];
        this.predictButtonDisable = false;
        return token.split("?")[0];
      } else {
        return undefined;
      }
    },
    async handleParse() {
      this.accountId = this.parseHomeLink();
      if (!this.accountId) {
        this.$message.error("主页链接有误，请检查！");
        return;
      }
      this.loadingParseInfo = true;
      try {
        // const resArr = await this.parseDouYinUser().catch((err) => {
        //   this.$message.error(err);
        //   return [];
        // });
        const resArr = [user_data, user_post_v4];
        if (resArr.length === 2) {
          this.user_data = resArr[0].data;
          this.aweme_list = resArr[1].data.aweme_list;
          this.showParseInfo = true;
          this.parseHomeLinkEnd = this.home_link
        }
      } catch (e) {
        this.$message.error(e);
        console.log(e);
      } finally {
        this.loadingParseInfo = false;
      }
    },
    selectWorks() {
      this.activeMenuMy = true;
    },
    selectLikes() {
      this.$message.info("暂未开放喜欢接口～");
    },
    logoutDouYinToken() {
      this.$store.dispatch("SetCookie", {
        cookie: "",
        phone: "",
      });
    },
    async predictHandle() {
      this.predictStartTime = new Date();
      this.predictLoading = true
      let timer = null
      await new Promise((resolve) => {
          timer = setInterval(() => {
          const nowTime = new Date();
          this.predictTime = nowTime - this.predictStartTime;
            if(this.predictAwemeList.length >= this.aweme_list.length) {
              resolve()
            } else {
              if(this.predictTime % 100 < 100) {
                this.predictAwemeList.push({
                  ...this.aweme_list[this.predictAwemeList.length],
                  predictTime: new Date(),
                })
              }
            }
        }, 200)

      })
      clearInterval(timer);
      const res = await predictUserData(this.predictAwemeList)
      this.predictResult = res.data;
      this.predictButtonDisable = true;
      this.predictLoading = false;
      this.predictEndTime = new Date();
    }
  },
  computed: {
    predictLoadingText() {
      let str = ''
      for (let i = 0; i < this.predictTime / 300; i++) {
        str += '. '
        if(str.length > 7) {
          str = ''
        }
      }
      return str
    }
  },
  created() {},
};
</script>

<template>
  <div class="app-container predict">
    <el-card
      v-show="this.predictState.predictCookie"
      class="predict-system"
      :body-style="{ height: '100%' }"
    >
      <div class="predict-home-title">预测系统</div>
      <el-row :gutter="20" style="height: calc(100% - 56px)">
        <el-col :span="18" style="height: 100%">
          <el-card
            header="预测系统"
            style="height: 100%; display: flex; flex-direction: column"
            :body-style="{ height: 'calc(100% - 40px)' }"
          >
            <el-row :gutter="20" class="predict-card">
              <el-col :span="12" style="height: 100%">
                <el-card
                  header="解析模块"
                  style="height: 100%"
                  :body-style="{ height: 'calc(100% - 44px)' }"
                  v-loading="loadingParseInfo"
                >
                  <el-row :gutter="20">
                    <el-col :span="19">
                      <el-input
                        v-model="home_link"
                        placeholder="请输入抖音主页链接"
                      />
                    </el-col>
                    <el-col :span="5">
                      <el-button @click="handleParse" :disabled="home_link === parseHomeLinkEnd">点击解析</el-button>
                    </el-col>
                  </el-row>
                  <div v-if="showParseInfo" class="parse-info">
                    <div class="parse-header">
                      <div class="douyin-avatar">
                        <img
                          :src="
                            user_data.user.avatar_300x300.url_list[0] ||
                            userAvatar
                          "
                        />
                      </div>
                      <div class="douyin-info">
                        <div class="douyin-nickname">
                          {{ user_data.user.nickname }}
                        </div>
                        <div class="douyin-follow-fans">
                          <div class="douyin-like-block douyin-follow">
                            <div class="douyin-like-label">关注</div>
                            <div class="douyin-like-value">
                              {{ user_data.user.following_count }}
                            </div>
                          </div>
                          <div class="douyin-like-block douyin-follow">
                            <div class="douyin-like-label">粉丝</div>
                            <div class="douyin-like-value">
                              {{ user_data.user.follower_count }}
                            </div>
                          </div>
                          <div class="douyin-like-block douyin-like">
                            <div class="douyin-like-label">获赞</div>
                            <div class="douyin-like-value">
                              {{ user_data.user.total_favorited }}
                            </div>
                          </div>
                        </div>
                        <div class="douyin-account-info">
                          <span class="douyin-account-id">
                            <span>抖音号: {{ user_data.user.unique_id }}</span>
                          </span>
                          <span class="douyin-user-age">
<!--                            <span v-if="user_data.user.gender" class="douyin-user-age-gender"><svg width="12" height="12" fill="none" xmlns="http://www.w3.org/2000/svg" class="" viewBox="0 0 12 12" style="margin-right: 4px;"><path fill-rule="evenodd" clip-rule="evenodd" d="M8 1.25a.75.75 0 000 1.5h1.09L7.54 4.298a.757.757 0 00-.058.066 4 4 0 10.968 1.112.752.752 0 00.15-.117L10.25 3.71V5a.75.75 0 001.5 0V2a.75.75 0 00-.75-.75H8zM5 10a2.5 2.5 0 100-5 2.5 2.5 0 000 5z" fill="#168EF9"></path></svg></span>-->
<!--                            <span  v-if="!user_data.user.gender" class="douyin-user-age-gender"><svg width="12" height="12" fill="none" xmlns="http://www.w3.org/2000/svg" class="" viewBox="0 0 12 12" style="margin-right: 4px;"><mask id="woman_svg__a" maskUnits="userSpaceOnUse" x="-2" y="-2" width="16" height="16" style="mask-type: alpha;"><path fill="#C4C4C4" d="M-2-2h16v16H-2z"></path></mask><g mask="url(#woman_svg__a)" stroke="#F5588E" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"><circle cx="7.2" cy="4.896" r="3.25"></circle><path d="M1.617 10.511l3.115-3.115M1.904 7.396l2.828 2.829"></path></g></svg></span>-->
                            <span v-if="user_data.user.user_age >= 0">{{ user_data.user.user_age }}岁</span>
                          </span>
                        </div>
                        <div class="douyin-description">
                          <div class="douyin-description-value">
                            {{ user_data.user.signature }}
                          </div>
                          <el-tooltip effect="dark" placement="bottom-start">
                            <template #content>
                              <div
                                style="white-space: pre-wrap; max-width: 400px"
                              >
                                {{ user_data.user.signature }}
                              </div>
                            </template>
                            <div
                              class="douyin-description-more"
                              v-if="user_data.user.signature.length > 54"
                            >
                              更多
                            </div>
                          </el-tooltip>
                        </div>
                      </div>
                    </div>
                    <div class="parse-body">
                      <div class="card-title">
                        <div class="card-title-value">
                          <div class="douyin-select-value">
                            <div
                              :class="`douyin-select-menu ${
                                activeMenuMy ? 'douyin-active-menu' : ''
                              }`"
                              @click="selectWorks"
                            >
                              作品 {{ user_data.user.aweme_count }}
                            </div>
                            <div
                              :class="`douyin-select-menu ${
                                activeMenuMy ? '' : 'douyin-active-menu'
                              }`"
                              @click="selectLikes"
                            >
                              喜欢 {{ user_data.user.favoriting_count }}
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="parse-content">
                        <div class="douyin-aweme-list">
                          <aweme-card
                            v-for="(aweme, index) in aweme_list"
                            :aweme_info="aweme"
                            :key="index"
                          />
                        </div>
                      </div>
                    </div>
                  </div>
                </el-card>
              </el-col>
              <el-col
                :span="12"
                style="height: 100%;"
              >
                <el-card header="预测模块" class="predict-module" :body-style="{ flex: 1, display: 'flex',flexDirection: 'column', overflow: 'hidden' }">
                  <div class="predict-module-button">
                    <el-button @click="predictHandle" :disabled="predictButtonDisable" :loading="predictLoading">点击预测</el-button>
                  </div>
                  <div class="predict-module-log">
                    <div v-for="logItem in predictAwemeList">
                      {{ logItem.predictTime.toLocaleString() }} [INFO] 解析视频《{{ logItem.desc }}》
                    </div>
                    <div class="log-loading" v-if="predictLoading">
                      预测中{{predictLoadingText}}
                    </div>
                    <div class="log-loading" v-if="!predictLoading && predictAwemeList.length">
                      预测结束，共耗时：{{ predictTime / 1000 }} 秒
                    </div>
                  </div>
                </el-card>
              </el-col>
            </el-row>
          </el-card>
        </el-col>
        <el-col :span="6" class="predict-right" style="height: 100%">
          <el-card style="margin-bottom: 20px">
            <template #header>
              <span>每日签到</span>
              <el-button
                style="float: right; padding: 3px 0"
                type="text"
                @click="logoutDouYinToken"
                >退出登录</el-button
              >
            </template>
            <div class="every-day-card">
              <img class="predict-user-avatar" :src="userAvatar" />
              <div class="predict-user-info">
                <div>{{ username }}</div>
                <div>{{ predictState.userPhone }}</div>
                <div>
                  <el-button
                    @click="everydayClick"
                    :loading="everyClickLoading"
                    :disabled="predictState.everyStatus"
                  >
                    {{ predictState.everyStatus ? "签过了" : "签到" }}
                  </el-button>
                </div>
              </div>
            </div>
          </el-card>
          <el-card header="预测结果" class="predict-result" :body-style="{
            flex: 1, display: 'flex', flexDirection: 'column'
          }">
            <div class="predict-result-body" v-loading="predictLoading">
              <RaddarChart />
            </div>
          </el-card>
        </el-col>
      </el-row>
    </el-card>
    <predict-login v-show="!this.predictState.predictCookie" />
  </div>
</template>

<style scoped lang="scss">
.predict {
  height: calc(100vh - 84px);
  .predict-system {
    height: 100%;
    .predict-home-title {
      font-size: 40px;
      font-weight: 700;
      text-align: center;
    }
    .predict-card {
      height: 100%;
      .parse-info {
        display: flex;
        flex-direction: column;
        border-radius: 12px;
        box-shadow: 0 0 24px rgba(0, 0, 0, 0.4);
        color: #ffffffe6;
        font-family: PingFang SC, DFPKingGothicGB-Medium, sans-serif;
        position: relative;
        height: calc(100% - 40px);
        margin: 10px 0;
        padding: 12px;
        background: #161720;
        .parse-header {
          display: flex;
          height: 120px;
          margin-top: 16px;
          margin-bottom: 32px;
          .douyin-avatar {
            width: 120px;
            border-radius: 50%;
            img {
              border-radius: 50%;
              width: 100%;
              height: 100%;
            }
          }
        }
        .parse-body {
          display: flex;
          flex-direction: column;
          position: relative;
          margin-top: -16px;
          padding-top: 16px;
          height: 100%;
          overflow: hidden;
          box-sizing: border-box;
          .card-title {
            border-top: 1px solid rgba(255, 255, 255, 0.04);
            .card-title-value {
              width: 100%;
              height: 36px;
              justify-content: space-between;
              align-items: center;
              margin: 11px 0;
              display: flex;
              position: relative;
              .douyin-select-value {
                height: 26px;
                display: flex;
                .douyin-select-menu {
                  cursor: pointer;
                  color: rgba(255, 255, 255, 0.34);
                  align-items: center;
                  margin-right: 40px;
                  display: flex;
                }
                .douyin-active-menu {
                  color: rgba(255, 255, 255, 1);
                }
              }
            }
          }

          .parse-content {
            flex: 1;
            display: flex;
            overflow: hidden;
            ::-webkit-scrollbar-track {
              background-color: transparent;
            }
            ::-webkit-scrollbar-thumb {
              background-color: #c0c0c066;
              border-radius: 3px;
            }
            .douyin-aweme-list {
              flex: 1;
              display: grid;
              grid-template-columns: repeat(3, 33%);
              height: 100%;
              overflow-y: scroll;
            }
          }
        }
        .douyin-info {
          min-height: 120px;
          flex-wrap: wrap;
          flex: 1;
          align-content: center;
          align-items: center;
          margin-left: 32px;
          display: flex;
          .douyin-nickname {
            width: 100%;
            font-size: 20px;
            font-weight: 500;
            line-height: 28px;
          }
          .douyin-follow-fans {
            width: 100%;
            display: flex;
            margin-top: 4px;
            .douyin-like-block {
              display: flex;
              align-items: center;
              .douyin-like-label {
                margin-right: 6px;
                line-height: 22px;
                font-size: 14px;
              }
              .douyin-like-value {
                line-height: 24px;
                font-size: 16px;
              }
            }
            .douyin-follow::after {
              content: "";
              width: 0;
              height: 16px;
              border-left: 1px solid rgba(242, 242, 244, 0.08);
              margin: 0 16px;
            }
          }
          .douyin-account-info {
            width: 100%;
            display: flex;
            height: 20px;
            margin-top: 12px;
            align-items: center;
            font-size: 12px;
            line-height: 20px;
            .douyin-account-id {
              margin-right: 10px;
            }
            .douyin-user-age {
              background: rgba(242, 242, 244, 0.08);
              border-radius: 4px;
              align-items: center;
              margin: 0 4px;
              padding: 0 8px;
              font-size: 12px;
              line-height: 20px;
              display: flex;
              .douyin-user-age-gender {
                display: flex;
                align-items: center;
              }
            }
          }
          .douyin-description {
            width: 100%;
            display: flex;
            margin-top: 4px;
            position: relative;
            height: 20px;
            font-size: 12px;
            line-height: 20px;
            .douyin-description-value {
              text-overflow: ellipsis;
              overflow: hidden;
              white-space: nowrap;
              max-width: 320px;
            }
            .douyin-description-more {
              color: rgba(255, 255, 255, 0.34);
              margin-left: 8px;
              cursor: default;
              position: relative;
            }
          }
        }
      }
      .predict-module {
        height: 100%;
        display: flex;
        flex-direction: column;
        .predict-module-button {
          text-align: center;
        }

        .predict-module-log {
          flex: 1;
          margin: 10px;
          padding: 10px;
          border-radius: 10px;
          background-color: #dddddd;
          overflow-y: auto;
          .log-loading {
            font-size: 20px;
            color: #1e1e1e;
          }
          &::-webkit-scrollbar-track {
            border-bottom-right-radius:  20px;
            border-top-right-radius: 20px;
            background-color: #dddddd;
          }
        }
      }
    }

    .predict-right {
      display: flex;
      flex-direction: column;
      .every-day-card {
        display: flex;
        align-items: stretch;
        justify-content: space-between;
        .predict-user-avatar {
          width: 70%;
          border-radius: 50%;
        }
        .predict-user-info {
          width: 30%;
          display: flex;
          justify-content: space-around;
          align-items: center;
          flex-direction: column;
        }
      }
      .predict-result {
        display: flex;
        flex-direction: column;
        flex: 1;
        .predict-result-body {
          flex: 1;
          display: grid;
        }
      }
    }
  }
}
</style>
