<template>
  <div>
    <div class = "tripTitleContainer">
      <span class="tripTitle">동행을 찾고 있어요</span>
      <router-link :to="{ name: 'trips' }" class="routerLink">더 많은 동행 보러가기</router-link>
    </div>
    <b-card-group deck class="deck">
      <b-row>
        <template v-for="trip in getTripsLimit4">
          <b-col :key="trip.id">
            <trip-card :trip="trip" :key="trip.id"></trip-card>
          </b-col>
        </template>
      </b-row>
    </b-card-group>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import TripCard from "./TripCard.vue";

export default {
  name: "TripsMain",
  components: {
    TripCard,
  },
  data() {
    return {};
  },
  created() {
    let param = {
      page: 0,
      size: 10,
      sort: "id",
    };

    this.loadTrips(param);
  },
  methods: {
    ...mapActions("trip", ["loadTrips"]),
  },
  computed: {
    ...mapGetters("trip", ["getTripsLimit4"]),
    // trips() {
    //   return [
    //     {
    //       id: 1,
    //       address: "역삼",
    //       title: "여행가요",
    //       content: "역삼으로",
    //     },
    //   ];
    // },
  },
};
</script>

<style scoped>

.tripTitleContainer {
  display: flex;
  justify-content: space-between;
  margin: 1% 7% 0px 7%; /* 원하는 좌우 공백 넓이를 설정합니다. */
}
.tripTitle{
  color:#333333;
  font-family: Arial,sans-serif;
  font-weight: bold;
  font-size: 33px;
}

.routerLink{
  float:right;
}

.deck{
  justify-content: center;
  margin: 0 -10px; /* deck 요소의 좌우 공백을 없앱니다. */
}
</style>
