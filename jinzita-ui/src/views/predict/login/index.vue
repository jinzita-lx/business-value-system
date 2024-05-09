<script>
import logoSvg from '@/layout/components/Sidebar/logoSvg.vue'
import { loginPredictSystem } from '@/api/predict/login'

export default {
  name: 'PredictLogin',
  components: { logoSvg },
  data() {
    return {
      form: {
        mobile: '',
        password: ''
      },
      loading: false
    }
  },
  methods: {
    async handleLogin() {
      this.loading = true;
      const res = await loginPredictSystem(this.form).catch(err => {
        console.log(err);
        this.$message.error(err);
      })
      try {
        if(res.code === 200) {
          await this.$store.dispatch('SetCookie', {cookie: res.data.authorization_token.token, phone: this.form.mobile});
          this.$message.success('登录成功');
        }
      } catch (e) {
        this.$message.error(e);
      } finally {
        this.loading = false;
      }
    }
  }
}
</script>

<template>
  <div class="predict-login-card">
    <el-card class="predict-login">
      <div class="title">
        <div><logo-svg class="title-logo" /></div>
        <div>预测系统登录</div>
      </div>
      <el-form ref="predict-form">
        <el-form-item prop="mobile">
          <el-input v-model="form.mobile" type="text" placeholder="手机号"
                    auto-complete="off">
            <svg-icon slot="prefix" icon-class="user" class="el-input__icon input-icon" />
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="form.password" type="password"
                    auto-complete="off"
                    placeholder="密码">
            <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon" />
          </el-input>
        </el-form-item>
        <el-form-item style="width:100%;">
          <el-button
            :loading="loading"
            size="medium"
            type="primary"
            style="width:100%;"
            @click.native.prevent="handleLogin"
          >
            <span v-if="!loading">登 录</span>
            <span v-else>登 录 中...</span>
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<style scoped lang="scss">
.predict-login-card {
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  .predict-login {
    height: 370px;
    width: 400px;
    .title {
      flex-direction: column;
      margin: 20px auto;
      display: flex;
      justify-content: center;
      align-items: center;
      font-size: 24px;
      text-align: center;
      .title-logo {
        height: 80px;
      }
    }
  }

}
</style>
