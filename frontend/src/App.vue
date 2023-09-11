<template>
  <div>
    <nav-header class="mb-5"></nav-header>
    <router-view />
  </div>
</template>

<script>
import NavHeader from "./components/NavHeader.vue";
export default {
  name: "App",
  components: {
    NavHeader,
  },
  data() {
    return {
      message: "",
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped>
body{
  font-family: "Pretendard Variable", Pretendard, -apple-system, BlinkMacSystemFont, system-ui, Roboto, "Helvetica Neue", "Segoe UI", "Apple SD Gothic Neo", "Noto Sans KR", "Malgun Gothic", "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", sans-serif;
}
</style>
