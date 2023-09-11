<template>
  <div>
    <trip-write-header-img></trip-write-header-img>
    <trip-info-box></trip-info-box>
    <b-card-group>
      <b-row class="d-flex justify-content-center" cols="12">
        <template v-for="attraction in attractionsInTripLocal">
          <attraction-card
            :attraction="attraction"
            :key="attraction.id"
            @removeAttraction="popAttraction"
          ></attraction-card>
        </template>
      </b-row>
    </b-card-group>
    <div class="d-flex justify-content-center">
      <h2 class="text-center">
        ----------------------------------------------------------------------------------------------------
      </h2>
    </div>
    <trip-content></trip-content>
  </div>
</template>

<script>
import TripWriteHeaderImg from "@/components/trip/write/TripWriteHeaderImg.vue";
import TripInfoBox from "@/components/trip/write/TripInfoBox.vue";
import TripContent from "@/components/trip/write/TripContent.vue";
import { mapActions, mapGetters, mapState } from "vuex";
import AttractionCard from "@/components/attraction/AttractionCard.vue";
export default {
  name: "TripArticleWriteView",
  components: { TripWriteHeaderImg, TripInfoBox, TripContent, AttractionCard },
  data() {
    return {
      attractionsInTripLocal: [],
    };
  },
  created() {
    if (!this.isLogin) {
      alert("로그인이 필요한 서비스입니다.");
      this.$router.push({ name: "signin" });
      return;
    }
  },
  methods: {
    popAttraction(attractionId) {
      console.log(attractionId);
      this.removeFromAttractionIdsInTrip(attractionId);
    },
    ...mapActions("attraction", ["removeFromAttractionIdsInTrip"]),
  },
  computed: {
    ...mapGetters("attraction", ["attractionsInTrip"]),
    ...mapState("memberStore", ["isLogin"]),
  },
  watch: {
    attractionsInTrip() {
      this.attractionsInTripLocal = this.attractionsInTrip;
    },
  },
};
</script>

<style scoped></style>
