<template>
  <div>
    <!-- <b-form-file
      v-model="file1"
      :state="Boolean(file1)"
      placeholder="사진을 선택하세요"
      drop-placeholder="Drop file here..."
    ></b-form-file>
    <div class="mt-3">선택된 파일 : {{ file1 ? file1.name : "" }}</div> -->

    <b-container class="justify-content-center">
      <div class="d-flex flex-row">
        <div class="form-group mr-3 text-center">
          <b-form-datepicker
            id="datepicker-invalid"
            v-model="startDate"
            :state="false"
            class="mb-2"
            style="width: 300px"
            placeholder="여행 시작 날짜"
          ></b-form-datepicker>
        </div>

        <div class="form-group text-center">
          <b-form-datepicker
            id="datepicker-valid"
            v-model="endDate"
            :state="true"
            style="width: 300px"
            placeholder="여행 마지막 날짜"
          ></b-form-datepicker>
        </div>

        <div class="d-flex align-items-start ml-auto">
          <p class="mr-2">몇 명과 같이 갈까요?</p>
          <b-form-input
            type="number"
            v-model="limitGuest"
            style="width: 200px"
            min="0"
          ></b-form-input>
        </div>
      </div>

      <b-form-input
        id="title-input"
        type="text"
        placeholder="ex) 12월 3박 4일 제주 바다 보러갈 동행 3명 구해요"
        v-model="title"
        class="input-title mb-2"
      ></b-form-input>

      <b-form-textarea
        id="content-textarea"
        placeholder="1. 현재 동행이 있나요? ex) 혼자에요 / 동행 1명 있어요."
        rows="8"
        v-model="content"
        class="textarea-content"
      ></b-form-textarea>
      <b-button @click="post" variant="success" class="float-right mt-2">등록하기</b-button>
    </b-container>
  </div>
</template>

<script>
import { writeTrip } from "@/api/trip";
import { mapState } from "vuex";
export default {
  name: "TripContent",
  components: {},
  data() {
    return {
      title: "",
      content: "",
      startDate: "",
      endDate: "",
      limitGuest: 0,
    };
  },
  created() {},
  methods: {
    post() {
      let tripWriteRequest = {
        title: this.title,
        attractionIds: this.attractionIdsInTrip,
        // LocalDateTime은 시간이 반드시 필요
        startDate: this.startDate + " 00:00:00",
        endDate: this.endDate + " 00:00:00",
        content: this.content,
        limitGuest: this.limitGuest,
      };

      writeTrip(tripWriteRequest, this.moveTripList, (error) => {
        console.log(error);
      });
    },
    moveTripList() {
      this.$router.push({ name: "trips" });
    },
  },
  computed: {
    ...mapState("attraction", ["attractionIdsInTrip"]),
  },
};
</script>

<style scoped>
.input-title {
  width: 100%;
  resize: none;
}

.textarea-content {
  width: 100%;
  resize: none;
}
</style>
