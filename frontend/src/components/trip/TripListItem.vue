<template>
  <div>
    <el-card :body-style="{ padding: '0px' }" class="card">
      <div class="card-header" >
        <div class = "card-header-is_closed" > 
          <div class = "card-header-text" > 모집중 </div > 
          <div class = "card-header-number" >{{ tripListItem.accepted }} / {{ tripListItem.limitGuest }}</div > 
        </div >
      </div>
      <!--  카드 바디 -->
      <div class="card-body">
        <div class="card-body-header">
          <h1 @click="moveTripView">{{ tripListItem.title }}</h1>
        </div>
        <p class="card-body-description" @click="moveTripView">{{ dynamicContent }}</p>
        <!--  카드 바디 본문 -->

        <!--  카드 바디 푸터 -->
        <div class="card-body-footer">
          <hr style="margin-bottom: 8px; opacity: 0.5; border-color: #EF5A31">
          <b-icon icon="eye" class="icon icon-view_count"></b-icon>조회 {{tripListItem.viewCount}}회
          <b-icon icon="calendar2-date" class="reg_date">{{ tripListItem.createdAt }}</b-icon>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "TripListItem",
  components: {},
  props: {
    tripListItem: Object,
  },
  data() {
    return {
      showFullContent: false,
      dynamicContent: "s",
    };
  },
  created() {
    this.dynamicContent = this.tripListItem.content.slice(0, 100) + "......";
  },
  methods: {
    toggle() {
      this.showFullContent = true;
    },
    moveTripView() {
      // TODO: route 에 포함된 id를 이용해서 TripView가 Vuex의 getters를 호출하여 data를 가져오게 할까? vs 라우터에 data를 담아서 TripView에 전달하게 할까?(url 직접 입력하여 이동시 data 안넘어감)
      this.$router.push({ name: "trip", params: { id: this.tripListItem.id, data: this.tripListItem } });
    },
  },
  watch: {
    showFullContent(val) {
      if (val) {
        this.dynamicContent = this.tripListItem.content;
      }
    },
  },
};
</script>

<style scoped lang="scss">
  html, body, div, span, applet, object, iframes, h1, h2, h3, h4, h5, h6,
  p, blockquote, pre, a, abbr, acronym, address, big, quotes, code, del,
  dfn, em, img, ins, kbd, q, s, samp, small, strike, sub, sup, tt, var, u,
  i, center, dl, dt, dd, ol, ul, li, fieldset, form, label, legend, table,
  caption, tbody, tfoot, thead, tr, th, td, article, aside, canvas,
  details, embed, figure, figcaption, footer, header, hgroup, menu, nav,
  output, ruby, section, summary, time, mark, audio, video {
  margin: 0;
  padding: 0;
  border: 0;
  font-size: 100%;
  do: inherit;
  vertical-align: baseline;
  }


  article, aside, details, figcaption, figure, footer, header, hgroup,menu, nav, section {
    display: block;
  }

  blockquote, q {
    quotes: none;
  }

  table {
    border-collapse: collapse;
    border-spacing: 0;
  }

/* css 초기화*/ 

.card-header {
  -webkit-transition: 0.5s; /*사파리 & 크롬*/
    -moz-transition: 0.5s;  /*파이어폭스*/
    -ms-transition: 0.5s;	/*인터넷 익스플로러*/
    -o-transition: 0.5s;  /*오페라*/
    transition: 0.5s;
  width: 100%;
  height: 270px;
  border-radius: 15px 15px 0 0;
  background-image: url("https://picsum.photos/600/300/?image=41");
  background-size: 100% 280px;
  background-repeat: no-repeat;	
}




.card-body-header{
  line-height: 25px;
  margin: 13px 20px 0px 20px;
}

.card{
  height: 400px;
  width: 350px;
  border-radius: 15px;
  display: inline-block;
  margin-top: 30px;
  margin-left: 30px;
  margin-bottom: 30px;
  position: relative;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  overflow: hidden;
}

.card:hover .card-header{
  opacity: 0.8;
  height: 100px;
}

.card-header-is_closed{
  background-color: #EF5A31 ;
  color: #FFF ;
  font-weight: bold ;
  text-align: center ;
  float: right;
  margin: 15px 15px 0 0;
  border-radius: 50%;
  font-weight: bold;
  padding: 10px 10px;
  line-height: 20px;
}

h1 {
  font-size: 22px;
  font-weight: bold;
}


.card-body {
}

.card-body-header{
	line-height: 25px;
	margin: 10px 20px 0px 20px;
}

.card-body-description  {
  opacity: 0;
  color: #757B82;
  line-height: 25px;
  -webkit-transition: .2s ease-in-out; /*사파리&크롬*/
  -moz-transition: .2s ease-in-out; /*파이어폭스*/
  -ms-transition: .2s ease-in-out; /*익스플로러*/
  -o-transition: .2s ease-in-out; /*오페라*/
  transition : .2s ease-in-out;

	margin: 5px 20px;
}
.card:hover .card-body-description {
  opacity: 1;
  -webkit-transition: .5s ease-in-out;
  -moz-transition: .5s ease-in-out;
  -ms-transition: .5s ease-in-out;
  -o-transition: .5s ease-in-out;
  transition : .5s ease-in-out;
  overflow: hidden;

  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 7;
}

.card-body-footer {
  position: absolute; 
  margin-top: 15px;
  margin-bottom: 6px;
  bottom: 0; 
  width: 100%;
  font-size: 14px;
  color: #9FA5A8;
  padding: 0 15px;
}

.address{
  float: right;
}
.reg_date {
  float: right;
}

.icon {

display: inline-block;

vertical-align: middle;

margin-right: 2px;

}
.icon-view_count {
  width: 25px;
  height: 17px;
  background: url("images/eye.jpg") no-repeat;
}
</style>
