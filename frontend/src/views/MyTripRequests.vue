<template>
  <div>
    <h2>내 동행에 대한 요청</h2>
    <trip-item-not-exist v-if="!tripRequests.length"></trip-item-not-exist>
    <div class="calendar-container">
      <my-trip-request-card
        v-for="tripRequest in tripRequests"
        :key="tripRequest.id"
        :tripRequest="tripRequest"
        @reloadRequests="load"
      ></my-trip-request-card>
    </div>
  </div>
</template>

<script>
import MyTripRequestCard from "@/components/trip/MyTripRequestCard.vue";
import TripItemNotExist from "@/components/trip/TripItemNotExist.vue";
import { getRequestByTripId } from "@/api/tripRequest";
import { mapGetters } from "vuex";
export default {
  name: "MyTripArticles",
  components: { MyTripRequestCard, TripItemNotExist },
  data() {
    return {
      tripRequests: [],
      tripId: 0,
    };
  },
  created() {
    this.load();
  },
  methods: {
    load() {
      this.tripId = this.$route.params.id;
      getRequestByTripId(
        this.tripId,
        (tripRequests) => {
          this.tripRequests = tripRequests;
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
  computed: {
    ...mapGetters("memberStore", ["checkUserInfo"]),
  },
};
</script>

<style scoped></style>
