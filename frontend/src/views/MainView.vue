<template>
  <div>
    <div class="imgSlide">
      <carousel-main></carousel-main>
    </div>
    <div class="attracrionlist">
      <attractions-main></attractions-main>
    </div>
    <div class="triplist">
      <trips-main></trips-main>
    </div>
  </div>
</template>

<script>
import AttractionsMain from "@/components/attraction/AttractionsMain.vue";
import TripsMain from "@/components/trip/TripsMain.vue";
import Carousel from "@/components/CarouselMain.vue";
import CarouselMain from "@/components/CarouselMain.vue";
export default {
  name: "MainView",
  components: { AttractionsMain, TripsMain, CarouselMain },
  data() {
    Carousel;
    return {
      message: "",
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped>
  .container {
  width:100%;
  display: flex;
  justify-content: center;
}

.imgSlide,
.attracrionlist,
.triplist {
  flex: 1;
  margin-top: 3%; /* 각 div 사이의 간격을 조정합니다. */
}
</style>