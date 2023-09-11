<template>
  <div>
    <b-list-group>
      <b-list-group-item class="d-flex align-items-center" style="width: 100%">
        <b-avatar variant="info" src="https://placekitten.com/300/300" class="mr-3"></b-avatar>
        <span class="mr-auto">J. Circlehead</span>
      </b-list-group-item>

      <b-list-group-item class="d-flex align-items-center">
        <b-button v-if="!requested" variant="success" @click="tripRequest">동행 신청하기</b-button>
        <b-button v-else variant="secondary" @click="tripRequest">이미 신청한 동행입니다</b-button>
      </b-list-group-item>
    </b-list-group>

    <div>
      <br />
      <br />
      <br />

      <b-list-group style="max-width: 300px">
        <b-list-group-item class="d-flex align-items-center">
          <h4>
            <b-badge variant="primary">출발지</b-badge>
          </h4>
        </b-list-group-item>
        <template v-for="attraction in attractions">
          <b-list-group-item class="d-flex align-items-center" :key="attraction.id">
            <h3>
              <b-badge variant="light" :key="attraction.id">{{ attraction.name }}</b-badge>
            </h3>
          </b-list-group-item>
        </template>
        <b-list-group-item class="d-flex align-items-center">
          <h4>
            <b-badge variant="dark">도착지</b-badge>
          </h4>
        </b-list-group-item>
      </b-list-group>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters, mapState } from "vuex";
export default {
  name: "TripHostBar",
  components: {},
  props: {
    trip: Object,
    attractions: [],
  },
  data() {
    return {
      requested: false,
    };
  },
  created() {
    console.log("checkUserInfo: " + this.checkUserInfo);
    if (this.checkUserInfo) {
      this.loadRequests(this.checkUserInfo.id);
      this.checkRequested();
    }
  },
  methods: {
    async tripRequest() {
      // TODO: 동행하기 신청에 요청내용 적기 추가
      if (!this.checkUserInfo) {
        alert("로그인이 필요한 서비스입니다.");
        this.$router.push({ name: "signin" });

        return;
      }

      this.joinRequest({
        tripId: this.trip.id,
        fail: () => {
          alert("이미 신청한 동행입니다.");
        },
      });
      this.checkRequested();

      this.reload();
    },
    reload() {
      // this.$router.go(0);
    },
    checkRequested() {
      for (let i = 0; i < this.tripRequests.length; i++) {
        console.log(
          "this.tripRequests[i].tripId: " +
            this.tripRequests[i].tripId +
            " this.trip.id: " +
            "this.trip.id"
        );
        if (this.tripRequests[i].tripId === this.trip.id) {
          this.requested = true;
          return;
        }
      }

      this.requested = false;
      return;
    },
    ...mapActions("tripRequest", ["joinRequest", "loadRequests"]),
  },
  computed: {
    ...mapGetters("memberStore", ["checkUserInfo"]),
    ...mapState("tripRequest", ["tripRequests"]),
  },
};
</script>

<style scoped></style>
