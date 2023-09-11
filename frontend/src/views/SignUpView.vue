<template>
  <div>
    <b-row>
      <b-col></b-col>
      <b-col cols="4">
        <b-form class="signup-form">
          <h3 class="form-title">이메일</h3>
          <b-form-input
            type="email"
            placeholder="example@ssafy.com"
            v-model="form.email"
            :trim="true"
            :autofocus="true"
            v-bind:class="{
              'form-control': true,
              'is-invalid': !validEmail(form.email) && emailBlured,
            }"
            v-on:blur="emailBlured = true"
            required
          ></b-form-input>
          <!-- TODO: 이메일 인증 추가 -->

          <h3 class="form-title">비밀번호</h3>
          <b-form-input
            type="password"
            placeholder="비밀번호"
            v-model="form.password"
            required
          ></b-form-input>
          <h3 class="form-title">비밀번호 확인</h3>
          <b-form-input
            type="password"
            placeholder="비밀번호 확인"
            v-model="confirmPassword"
            required
          ></b-form-input>
          <small v-if="checkPassword && form.password" class="password-match-text">비밀번호가 일치합니다.</small>
          <small v-else-if="form.password" class="password-mismatch-text">비밀번호가 일치하지 않습니다.</small>

          <h3 class="form-title">이름</h3>
          <b-form-input
            type="text"
            placeholder="홍길동"
            :trim="true"
            v-model="form.name"
            required
          ></b-form-input>
          <h3 class="form-title">나이</h3>
          <b-form-input type="number" placeholder="나이" v-model="form.age" required></b-form-input>
          <h3 class="form-title">성별</h3>
          <b-form-select v-model="form.gender" :options="genders" class="gender-select"></b-form-select>
          <h3 class="form-title">지역</h3>
          <b-form-input
            type="text"
            placeholder="지역"
            v-model="form.region"
            required
          ></b-form-input>
          <b-button @click="regist" variant="success" style="width: 100%" class="mt-5 signup-button">회원 가입하기</b-button>
        </b-form>
      </b-col>
      <b-col></b-col>
    </b-row>
  </div>
</template>

<script>
import { regist } from "@/api/member.js";

export default {
  name: "SignUpView",
  components: {},
  data() {
    return {
      confirmPassword: "",
      form: {
        email: "",
        password: "",
        name: "",
        age: 0,
        gender: "",
        region: 0,
        role: 1, // User
      },
      genders: ["M", "F"],
      emailBlured: false,
    };
  },
  created() {},
  methods: {
    regist() {
      if (!this.allValid) {
        alert("입력을 확인해 주세요.");
        return;
      }

      // 요청
      regist(
        this.form,
        () => {
          this.$router.push({ name: "main" });
        },
        () => {
          alert("이미 가입된 이메일입니다.");
        }
      );
    },
    validEmail: function (email) {
      var re = /(.+)@(.+){2,}\.(.+){2,}/;
      return re.test(email.toLowerCase());
    },
  },
  computed: {
    checkPassword() {
      return this.form.password === this.confirmPassword;
    },
    allValid() {
      return this.validEmail(this.form.email) && this.checkPassword && this.form.gender;
    },
  },
};
</script>

<style scoped>
.signup-form {
  background-color: #f8f9fa;
  padding: 20px;
  border-radius: 8px;
}

.form-title {
  font-size: 18px;
  margin-bottom: 10px; /* 위 아래 간격을 10px로 조정 */
}

.form-control {
  border-color: #ced4da;
  margin-bottom: 10px; /* 위 아래 간격을 10px로 조정 */
}

.is-invalid {
  border-color: #dc3545;
}

.password-match-text {
  color: green;
  margin-top: 10px; /* 위 간격을 10px로 조정 */
}

.password-mismatch-text {
  color: red;
  margin-top: 10px; /* 위 간격을 10px로 조정 */
}

.gender-select {
  width: 100%;
  margin-bottom: 10px; /* 위 아래 간격을 10px로 조정 */
}

.signup-button {
  margin-top: 20px; /* 위 간격을 20px로 조정 */
}
</style>