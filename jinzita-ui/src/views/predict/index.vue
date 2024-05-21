<script>
import store from "@/store";
import PredictLogin from "@/views/predict/login";
import { everyDayLoginSystem } from "@/api/predict/login";
import AwemeCard from "@/views/predict/components/aweme-card/index.vue";
import { getUserData, getUserVideo } from "@/api/predict/userInfo";
export default {
  name: "index",
  components: { AwemeCard, PredictLogin },
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
      aweme_list: [],
      max_cursor: "0",
      count: 18,
      home_link: "",
      accountId: "",
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
        const resArr = await this.parseDouYinUser().catch((err) => {
          this.$message.error(err);
          console.log(err);
          return [];
        });
        // const resArr = [user_data, user_post_v4];
        if (resArr.length === 2) {
          this.user_data = resArr[0].data;
          this.aweme_list = resArr[1].data.aweme_list;
          this.showParseInfo = true;
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
  },
  created() {},
  mounted() {},
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
                      <el-button @click="handleParse">点击解析</el-button>
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
                          <span>年龄: </span>
                          <span class="douyin-user-age">
                            <span>{{ user_data.user.user_age }}岁</span>
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
                <el-card header="预测模块" class="predict-module" :body-style="{  height: 'calc(100% - 44px)' }">
                  <el-button>点击预测</el-button>
                </el-card>
              </el-col>
            </el-row>
          </el-card>
        </el-col>
        <el-col :span="6" style="height: 100%">
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
          <el-card header="预测结果"></el-card>
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
              margin-right: 20px;
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
      }
    }
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
  }
}
</style>
