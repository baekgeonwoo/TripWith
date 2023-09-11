<template>
  <div>
    <div>
      <h1 class="d-flex justify-content-center mb-5 mt-5">관광지 검색</h1>
      <div class="d-flex flex-column align-items-center">
        <div class="d-flex mb-3">
          <div class="mr-3" style="width: 150px">
            <b-form-select v-model="areaCode" :options="areaCodes"></b-form-select>
          </div>
          <div class="mr-3" style="width: 150px">
            <b-form-select v-model="sigunguCode" :options="sigunguCodes"></b-form-select>
          </div>
          <b-button @click="search" variant="success">검색하기</b-button>
        </div>
      </div>
      <div class="d-flex justify-content-center">
      <the-kakao-map :attractions="attractions" class="map"></the-kakao-map>
    </div>
    </div>
  </div>
</template>

<script>
import { searchAttractionsByGugunCode } from "@/api/attraction";
import { getAreaCodes, getSigunguCodesByAreaCode } from "@/api/code";
import TheKakaoMap from "../TheKakaoMap.vue";

export default {
  name: "TripInfoBox",
  components: {
    TheKakaoMap,
  },
  data() {
    return {
      areaCodes: [],
      sigunguCodes: [],
      areaCode: 0,
      sigunguCode: 0,

      attractions: [],
    };
  },
  created() {
    getAreaCodes(
      (areaCodes) => {
        this.areaCodes = areaCodes.map(({ sidoName, sidoCode }) => {
          return {
            value: sidoCode,
            text: sidoName,
          };
        });
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    search() {
      // TODO: 시군구 코드 or 제목 키워드로 검색 비동기 통신,
      searchAttractionsByGugunCode(this.areaCode, this.sigunguCode, (attractions) => {
        this.attractions = attractions;
        console.log(this.attractions);
      });
    },
  },
  watch: {
    areaCode() {
      getSigunguCodesByAreaCode(
        this.areaCode,
        (sigunguCodes) => {
          this.sigunguCodes = sigunguCodes.map(({ gugunName, gugunCode }) => {
            return {
              value: gugunCode,
              text: gugunName,
            };
          });
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style scoped>
.map{
  width: 90%;

}
</style>
