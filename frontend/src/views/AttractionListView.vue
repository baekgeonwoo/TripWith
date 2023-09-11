<template>
  <div class="container mt-5">
    <div class="d-flex flex-column align-items-center">
      <!-- 가로로 배치하고 중앙 정렬 -->
      <h1 class="mb-5">관광지 검색</h1>

      <div class="d-flex mb-3">
        <div class="mr-3" style="width: 150px">
          <b-form-select v-model="areaCode" :options="areaCodes"></b-form-select>
        </div>
        <div class="mr-3" style="width: 150px">
          <b-form-select v-model="sigunguCode" :options="sigunguCodes"></b-form-select>
        </div>
        <b-button @click="search" variant="success">검색하기</b-button>
      </div>

      <!-- 카카오맵 넣고 관광지들 마커찍고, 무한스크롤, 모달 -->
      <!-- <the-kakao-map :attractions="attractions" :showRoute="false"></the-kakao-map> -->
      <div class="calendar-container">
        <attraction-card
          v-for="attraction in attractions"
          :key="attraction.id"
          :attraction="attraction"
        ></attraction-card>
      </div>
    </div>
    <pagination-bar @currentPage="pageChange"></pagination-bar>
  </div>
</template>

<script>
import AttractionCard from "@/components/attraction/AttractionCard.vue";
import PaginationBar from "@/components/PaginationBar.vue";
import { getAreaCodes, getSigunguCodesByAreaCode } from "@/api/code";
// import TheKakaoMap from "@/components/trip/TheKakaoMap.vue";
import { getAttractions, searchAttractionsByGugunCode } from "@/api/attraction";

export default {
  name: "AttractionList",
  components: {
    AttractionCard,
    PaginationBar,
    // TheKakaoMap,
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
    // 지역, 시군구 코드 비동기 통신
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

    let param = {
      page: 0,
      size: 8, // 한 페이지에 8개 Item
      sort: "id",
    };

    getAttractions(param, (attractions) => {
      this.attractions = attractions;
    });
  },
  methods: {
    search() {
      // TODO: 시군구 코드 or 제목 키워드로 검색 비동기 통신,
      searchAttractionsByGugunCode(this.areaCode, this.sigunguCode, (attractions) => {
        this.attractions = attractions;
      });
    },
    pageChange(currentPage) {
      // 페이지 번호가 바뀌었을 때 호출
      // currentPage 바꾸리 때마다 실행할 코드
      // 통신 코드 작성
      const pageRequest = {
        page: currentPage,
        size: 8,
        sort: "id",
      };

      getAttractions(pageRequest, (attractions) => {
        this.attractions = attractions;
      });
    },
  },
  computed: {},
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
.attraction-card {
}

.calendar-container {
  display: grid;
  grid-template-columns: repeat(4, 1fr); /* 4개의 열을 생성합니다. */
  grid-gap: 1%; /* 각 항목 사이의 간격을 조정합니다. */
  justify-items: center; /* 내부 요소를 가운데로 정렬합니다. */
  flex: 1;
}

.container {
  display: flex;
  flex-direction: column;
  justify-content: flex-start; /* 가로 방향으로 가운데 정렬합니다. */
  align-items: center; /* 세로 방향으로 가운데 정렬합니다. */
  height: 100vh; /* 컨테이너를 화면의 100% 높이로 설정합니다. */
}
.pagination-container {
  margin-top: 10px; /* pagination-bar와의 간격을 조정합니다. */
}
</style>
