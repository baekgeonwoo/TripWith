<template>
  <div>
    <trip-article></trip-article>
    <reply-input></reply-input>
    <reply-item></reply-item>
  </div>
</template>

<script>
import TripArticle from "@/components/trip/TripArticle.vue";
import ReplyInput from "@/components/reply/ReplyInput.vue";
import ReplyItem from "@/components/reply/ReplyItem.vue";
export default {
  name: "CommunityDetailView",
  components: {TripArticle, ReplyInput, ReplyItem},
  data() {
    return {
      message: "",
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped></style>
