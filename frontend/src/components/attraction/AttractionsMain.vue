<template>
  <div>
    <div class="attractionTitleContainer">
      <span class="attractionTitle">관광지</span>
      <router-link :to="{ name: 'attractions' }" class="routerLink">더 많은 관광지 보러가기</router-link>
    </div>
    <b-card-group deck class="deck">
      <b-row>
        <template v-for="attraction in getAttractionsLimit4">
          <b-col :key="attraction.id">
            <attraction-card :attraction="attraction" :key="attraction.id"></attraction-card>
          </b-col>
        </template>
      </b-row>
    </b-card-group>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
import AttractionCard from "./AttractionCard.vue";
export default {
  name: "AttractionsMain",
  components: {
    AttractionCard,
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

    this.loadAttractions(param);
  },
  methods: {
    ...mapActions('attraction', ['loadAttractions'])
  },
  computed: {
    ...mapGetters('attraction', ['getAttractionsLimit4']),
  },
};
</script>

<style scoped>
.attractionTitleContainer {
  display: flex;
  justify-content: space-between;
  margin: 1% 7% 0px 7%; /* 원하는 좌우 공백 넓이를 설정합니다. */
}

.attractionTitle{
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
