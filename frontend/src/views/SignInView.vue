<template>
  <div>
    <h3 class="d-flex justify-content-center mt-5 pb-5">로그인</h3>

    <b-alert class="d-flex justify-content-center" show variant="danger" v-if="isLoginError">
      아이디 또는 비밀번호를 확인하세요.
    </b-alert>

    <b-container class="d-flex justify-content-center mt-3 pb-5">
      <b-col sm="10">
        <b-form-input
          id="email"
          size="md"
          placeholder="이메일을 입력하세요."
          :trim="true"
          :autofocus="true"
          v-model="form.email"
          type="email"
          v-bind:class="{
            'form-control': true,
            'is-invalid': !validEmail(form.email) && emailBlured,
          }"
          v-on:blur="emailBlured = true"
        >
          ></b-form-input
        >
      </b-col>
    </b-container>

    <b-container class="d-flex justify-content-center mt-3 pb-5">
      <b-col sm="10">
        <b-form-input
          id="password"
          size="md"
          placeholder="비밀번호를 입력하세요."
          v-model="form.password"
          type="password"
          required
        ></b-form-input>
      </b-col>
    </b-container>

    <div class="d-flex justify-content-center">
      <b-button @click.prevent="signIn" variant="info" size="lg">로그인</b-button>
    </div>

    <div class="d-flex justify-content-center mt-5">
      비밀번호 재설정 | <router-link :to="{ name: 'signup' }">회원가입</router-link>
    </div>

    <h3 class="d-flex justify-content-center mt-5 pb-5">SNS 계정으로 간편 로그인</h3>

    <div class="d-flex justify-content-center mt-5">
      <!-- 버튼 가이드에 맞게 버튼 이미지 수정 필수 -->
      <b-link :href="oAuthLink.kakao">
        <b-img
          height="80rem"
          width="300rem"
          :src="require('@/assets/kakao_login_large_narrow.png')"
        >
        </b-img>
      </b-link>
    </div>
    <div class="d-flex justify-content-center mt-5">
      <b-link :href="oAuthLink.naver">
        <b-img height="80rem" width="300rem" :src="require('@/assets/btnG_완성형.png')"></b-img>
      </b-link>
    </div>
    <div class="d-flex justify-content-center mt-5">
      <b-link :href="oAuthLink.google">
        <b-img
          height="80rem"
          width="300rem"
          :src="require('@/assets/btn_google_signin_light_normal_web@2x.png')"
        ></b-img>
      </b-link>
    </div>

    <small class="d-flex justify-content-center mt-5">로그인에 문제가 있으신가요?</small>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
export default {
  name: "SignInView",
  data() {
    return {
      form: {
        email: "",
        password: "",
      },
      // TODO: email 기억하기 구현
      rememberEmail: false,
      emailBlured: false,
      oAuthLink: {
        kakao: "",
        naver: "",
        google: "",
      },
    };
  },
  created() {
    // console.log(process.env.VUE_APP_OAUTH2_KAKAO);
    this.oAuthLink.kakao = process.env.VUE_APP_OAUTH2_KAKAO;
    this.oAuthLink.naver = process.env.VUE_APP_OAUTH2_NAVER;
    this.oAuthLink.google = process.env.VUE_APP_OAUTH2_GOOGLE;
  },
  methods: {
    ...mapActions("memberStore", ["userConfirm", "getUserInfo"]),
    async signIn() {
      if (!this.allValid) {
        alert("이메일을 확인해주세요.");
        return;
      }

      await this.userConfirm(this.form);
      await this.getUserInfo(sessionStorage.getItem("access-token"));

      if (!this.isLoginError) {
        this.$router.push({ name: "main" });
      }
    },
    validEmail: function (email) {
      var re = /(.+)@(.+){2,}\.(.+){2,}/;
      return re.test(email.toLowerCase());
    },
  },
  computed: {
    ...mapState("memberStore", ["isLoginError"]),
    allValid() {
      return this.validEmail(this.form.email);
    },
  },
};
</script>
