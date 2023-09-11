<template>
  <el-header class="header">
    <el-menu mode="horizontal" router class="nav">
      <el-menu-item
        ><router-link :to="{ name: 'main' }"
          ><b-img :src="require('@/assets/logo-removebg.png')" height="60%"></b-img></router-link
      ></el-menu-item>
      <el-menu-item class="navmenu"
        ><router-link :to="{ name: 'attractions' }">관광지</router-link></el-menu-item
      >
      <el-menu-item class="navmenu"
        ><router-link :to="{ name: 'trips' }">동행</router-link></el-menu-item
      >
      <el-menu-item class="navmenu"
        ><router-link :to="{ name: 'community' }">커뮤니티</router-link></el-menu-item
      >

      <template v-if="userInfo">
        <el-submenu style="margin-left: auto" index="1">
          <template slot="title">내 정보</template>
          <el-menu-item
            ><router-link :to="{ name: 'mytriparticle' }">내가 만든 동행</router-link></el-menu-item
          >
        </el-submenu>

        <el-menu-item @click="logout">로그아웃</el-menu-item>
      </template>

      <template v-else>
        <el-menu-item class="login" style="margin-left: auto"
          ><router-link :to="{ name: 'signin' }">로그인</router-link></el-menu-item
        >
        <el-menu-item><router-link :to="{ name: 'signup' }">회원가입</router-link></el-menu-item>
      </template>
    </el-menu>
  </el-header>
</template>

<script>
import { mapActions, mapState } from "vuex";
export default {
  name: "NavHeader",
  props: {
    msg: String,
  },
  methods: {
    ...mapActions("memberStore", ["userLogout"]),
    logout() {
      this.userLogout();
    },
  },
  computed: {
    ...mapState("memberStore", ["userInfo"]),
  },
};
</script>

<style scoped>
.header {
  padding: 0;
  height: 60px;
}
.nav {
  height: 60px;
  border: none;
}
.navmenu {
  margin-top: 5px;
}
</style>
