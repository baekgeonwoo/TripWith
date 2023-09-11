<template>
  <div>
    <div id="map" style="width: 100%; height: 30rem"></div>
  </div>
</template>

<script>
import { mapActions } from "vuex";
export default {
  name: "TheKakaoMap",
  components: {},
  data() {
    return {
      map: null,
    };
  },
  props: {
    attractions: [],
    showRoute: Boolean,
  },
  created() {},
  computed: {
    wayPoints() {
      return this.attractions.map((attraction) => {
        return {
          name: attraction.name,
          x: attraction.longitude,
          y: attraction.latitude,
        };
      });
    },
  },
  methods: {
    loadMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.5000776, 127.0385419), // 중심좌표(역삼역), 필수
        level: 3, // 지도 확대, 축소 정도
      };

      this.map = new window.kakao.maps.Map(container, options);

      // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
      var mapTypeControl = new kakao.maps.MapTypeControl();

      // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
      // kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
      this.map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

      // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
      var zoomControl = new kakao.maps.ZoomControl();
      this.map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

      // 지도에 교통정보를 표시하도록 지도타입을 추가합니다
      this.map.addOverlayMapTypeId(kakao.maps.MapTypeId.TRAFFIC);
    },
    loadScript() {
      // TODO: api로 분리하기

      const script = document.createElement("script");
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=" +
        process.env.VUE_APP_KAKAO_MAP_API_KEY +
        "&autoload=false";

      /* global kakao */
      script.onload = () => window.kakao.maps.load(this.loadMap);

      const scriptLib = document.createElement("script");
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=" +
        process.env.VUE_APP_KAKAO_MAP_API_KEY +
        "&libraries=services,clusterer,drawing&autoload=false";

      document.head.appendChild(script);
      document.head.appendChild(scriptLib);
    },
    async setMarkers() {
      console.log("setMarkers() called");
      if (!this.attractions || !this.attractions.length) {
        return;
      }

      // if (this.showRoute) {
      //   makeRequestObject(this.attractions);
      //   await multiWaypoints();
      // }

      let points = [];

      for (let i = 0; i < this.attractions.length; i++) {
        let lat = this.attractions[i].latitude;
        let lng = this.attractions[i].longitude;

        points.push(new kakao.maps.LatLng(lat, lng));
      }

      let bounds = new kakao.maps.LatLngBounds();

      for (let i = 0; i < points.length; i++) {
        var marker = new kakao.maps.Marker({
          position: points[i],
          clickable: true,
        });
        marker.setMap(this.map);

        var iwContent = `<div style="padding: 5px">
  <img src="${this.attractions[i].img1}" height="120px" width="160px" />

  <br />
  <a
    href="https://map.kakao.com/link/map/Hello World!,33.450701,126.570667"
    style="color: blue"
    target="_blank"
    >큰지도보기
  </a>
  <a
    href="https://map.kakao.com/link/to/Hello World!,33.450701,126.570667"
    style="color: blue"
    target="_blank"
    >길찾기
  </a>
</div>

`;
        let iwPosition = new kakao.maps.LatLng(
          this.attractions[i].latitude,
          this.attractions[i].longitude
        ); //인포윈도우 표시 위치입니다

        // 인포윈도우를 생성합니다
        var infowindow = new kakao.maps.InfoWindow({
          position: iwPosition,
          content: iwContent,
          removable: true,
        });

        var map = this.map;
        // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
        // 이벤트 리스너로는 클로저를 만들어 등록합니다
        // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
        kakao.maps.event.addListener(
          marker,
          "mouseover",
          this.makeOverListener(map, marker, infowindow)
        );
        kakao.maps.event.addListener(marker, "mouseout", this.makeOutListener(infowindow));
        kakao.maps.event.addListener(
          marker,
          "click",
          this.makeClickListener(this.addAttraction, this.attractions[i].id)
        );

        bounds.extend(points[i]);
      }

      // LatLngBounds 객체에 추가된 좌표들을 기준으로 지도의 범위를 재설정합니다
      // 이때 지도의 중심좌표와 레벨이 변경될 수 있습니다
      this.map.setBounds(bounds);
    },
    makeOverListener(map, marker, infowindow) {
      return function () {
        infowindow.open(map, marker);
      };
    },
    makeClickListener(func, attractionId) {
      return function () {
        func(attractionId);
        console.log("makeClickListener 진짜 끝");
      };
    },
    // 인포윈도우를 닫는 클로저를 만드는 함수입니다
    makeOutListener(infowindow) {
      return function () {
        infowindow.close();
      };
    },
    addAttraction(attractionId) {
      this.addToAttractionIdsInTrip(attractionId);
      this.$forceUpdate();
    },
    ...mapActions("attraction", ["addToAttractionIdsInTrip"]),
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.loadMap();
    } else {
      this.loadScript();
    }

    this.setMarkers();
  },
  watch: {
    attractions() {
      this.setMarkers();
    },
  },
};
</script>

<style scoped>
#map {
  width: 100%;
}
</style>
