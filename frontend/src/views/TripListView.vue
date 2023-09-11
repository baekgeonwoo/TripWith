<template>
  <div class="container">
    <div class="board-title">동행을 찾고 있어요</div>
    <div>
      <br />
      <b-button @click="moveWrite">동행 찾기를 시작하세요</b-button>
    </div>
    <div class="calendar-container">
      <trip-list-item
        v-for="tripListItem in trips"
        :key="tripListItem.id"
        :tripListItem="tripListItem"
      ></trip-list-item>
    </div>
    <div class="pagination-container">
      <pagination-bar @currentPage="pageChange"></pagination-bar>
    </div>
  </div>
</template>

<script>
import TripListItem from "@/components/trip/TripListItem.vue";
import { mapActions, mapState } from "vuex";
import PaginationBar from "@/components/PaginationBar.vue";
export default {
  name: "TripListView",
  components: { TripListItem, PaginationBar },
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
    pageChange(currentPage) {
      const pageRequest = {
        page: currentPage,
        size: 8,
        sort: "id",
      };

      this.loadTrips(pageRequest);
    },
    moveWrite() {
      this.$router.push({ name: "tripwrite" });
    },
  },
  computed: {
    ...mapState("trip", ["trips"]),
    // [
    //   백에서 넘어오는 데이터 형식 TripResponse
    //     {
    //       id: "1",
    //       title: "제목1",
    //       startAttractionId: 1,
    //       endAttractionId: 4,
    //       startDate: "YYYY:MM:DD-HH:mm:ss",
    //       endDate: "YYYY:MM:DD-HH:mm:ss",
    //       content:
    //         "내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용",
    //       viewCount: 0,
    //       createdAt: "YYYY:MM:DD-HH:mm:ss",
    //     },
    // ]
  },
};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  justify-content: flex-start; /* 가로 방향으로 가운데 정렬합니다. */
  align-items: center; /* 세로 방향으로 가운데 정렬합니다. */
  height: 100vh; /* 컨테이너를 화면의 100% 높이로 설정합니다. */
}

.calendar-container {
  display: grid;
  grid-template-columns: repeat(4, 1fr); /* 4개의 열을 생성합니다. */
  grid-gap: 1%; /* 각 항목 사이의 간격을 조정합니다. */
  justify-items: center; /* 내부 요소를 가운데로 정렬합니다. */
  flex: 1;
}

.trip-list-item {
  /* trip-list-item에 대한 추가적인 스타일을 지정할 수 있습니다. */
}

.pagination-container {
  margin-top: 10px; /* pagination-bar와의 간격을 조정합니다. */
}

.board-title {
  font-size: 30px;
  font-weight: bold;
  margin-bottom: 5px;
  margin-top: 20px;
}
</style>
