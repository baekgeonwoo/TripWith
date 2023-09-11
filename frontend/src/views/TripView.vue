<template>
  <div>
    <div class="d-flex justify-content-center">
      <img src="../components/trip/images/findTrip.jpg" class="image" />
    </div>
    <div class="d-flex justify-content-center">
      <div class="trip flex-grow-1 mb-5 ml-5 mt-3">
        <trip-article :trip="trip"></trip-article>
        <the-kakao-map :attractions="attractions" :showRoute="true"></the-kakao-map>
      </div>

      <div class="host mt-3">
        <trip-host-bar :trip="trip" :attractions="attractions"></trip-host-bar>
      </div>
    </div>
  </div>
</template>

<script>
import TripArticle from "@/components/trip/TripArticle.vue";
import TheKakaoMap from "@/components/trip/TheKakaoMap.vue";
import TripHostBar from "@/components/trip/TripHostBar.vue";

import { getTrip } from "@/api/trip";
import { getAttraction } from "@/api/attraction";

export default {
  name: "TripView",
  components: { TripArticle, TheKakaoMap, TripHostBar },
  data() {
    return {
      trip: {},
      attractions: [],
    };
  },
  created() {
    getTrip(
      this.$route.params.id,
      (data) => {
        this.trip = data;
        this.trip.attractionIds.forEach((id) => {
          getAttraction(id, (attraction) => {
            this.attractions.push(attraction);
          });
        });
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {},
  mounted() {
    // this.trip = this.$route.params.data;
  },
};
</script>

<style scoped>
.image {
  width: 100%;
  display: block;
}
</style>
