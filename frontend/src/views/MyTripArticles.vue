<template>
  <div>
    <h2>내 동행 게시글</h2>
    <trip-item-not-exist v-if="!trips.length"></trip-item-not-exist>
    <div class="calendar-container">
      <my-trip-card v-for="trip in trips" :key="trip.id" :trip="trip"></my-trip-card>
    </div>
  </div>
</template>

<script>
import MyTripCard from "@/components/trip/MyTripCard.vue";
import TripItemNotExist from "@/components/trip/TripItemNotExist.vue";
import { getTripsByMemberId } from "@/api/trip";
import { mapGetters } from "vuex";
export default {
  name: "MyTripArticles",
  components: { MyTripCard, TripItemNotExist },
  data() {
    return {
      trips: [],
    };
  },
  created() {
    getTripsByMemberId(
      this.checkUserInfo.id,
      (trips) => {
        this.trips = trips;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {},
  computed: {
    ...mapGetters("memberStore", ["checkUserInfo"]),
  },
};
</script>

<style scoped></style>
