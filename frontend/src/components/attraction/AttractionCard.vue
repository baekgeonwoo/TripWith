<template>
  <el-card :body-style="{ padding: '0px' }" class="card">
    <div :id="attraction.id" class="card-header"><div class="card-header-is_closed" /></div>

    <!--  카드 바디 -->
    <div @click="remove" class="card-body">
      <div class="card-body-header">
        <h1>{{ attraction.name }}</h1>
      </div>
      <p class="card-body-description">{{ attraction.content }}</p>
      <!--  카드 바디 본문 -->

      <!--  카드 바디 푸터 -->
      <div class="card-body-footer">
        <hr style="margin-bottom: 8px; opacity: 0.5; border-color: #ef5a31" />
        <i class="address">{{ attraction.address }}</i
        ><br />
        <i class="reg_date">{{ attraction.modifiedTime }}</i>
      </div>
    </div>
  </el-card>
</template>

<script>
export default {
  name: "AttractionCard",
  components: {},
  props: {
    attraction: Object,
  },
  data() {
    return {
      message: "",
    };
  },
  created() {},
  methods: {
    moveDetail() {
      // AttractionView로 link
      // this.$router.push({name: 'attractions', params{}})
    },
    remove() {
      console.log("remove");
      this.$emit("removeAttraction", this.attraction.id);
    },
  },
  mounted() {
    // console.log("attraction.img1: ", this.attraction.img1);
    if (this.attraction.img1) {
      var url = `${this.attraction.img1}`;
      // console.log(url);
      var cardHeader = document.getElementById(this.attraction.id);
      cardHeader.style.backgroundImage = `url(${url})`;
    }
  },
};
</script>

<style scoped lang="scss">
html,
body,
div,
span,
applet,
object,
iframes,
h1,
h2,
h3,
h4,
h5,
h6,
p,
blockquote,
pre,
a,
abbr,
acronym,
address,
big,
quotes,
code,
del,
dfn,
em,
img,
ins,
kbd,
q,
s,
samp,
small,
strike,
sub,
sup,
tt,
var,
u,
i,
center,
dl,
dt,
dd,
ol,
ul,
li,
fieldset,
form,
label,
legend,
table,
caption,
tbody,
tfoot,
thead,
tr,
th,
td,
article,
aside,
canvas,
details,
embed,
figure,
figcaption,
footer,
header,
hgroup,
menu,
nav,
output,
ruby,
section,
summary,
time,
mark,
audio,
video {
  margin: 0;
  padding: 0;
  border: 0;
  font-size: 100%;
  do: inherit;
  vertical-align: baseline;
}

article,
aside,
details,
figcaption,
figure,
footer,
header,
hgroup,
menu,
nav,
section {
  display: block;
}

blockquote,
q {
  quotes: none;
}

table {
  border-collapse: collapse;
  border-spacing: 0;
}

/* css 초기화*/
.card-header {
  -webkit-transition: 0.5s; /*사파리 & 크롬*/
  -moz-transition: 0.5s; /*파이어폭스*/
  -ms-transition: 0.5s; /*인터넷 익스플로러*/
  -o-transition: 0.5s; /*오페라*/
  transition: 0.5s;
  width: 100%;
  height: 270px;
  border-radius: 15px 15px 0 0;
  background-image: url("http://t1.daumcdn.net/friends/prod/editor/dc8b3d02-a15a-4afa-a88b-989cf2a50476.jpg");
  background-size: 100% 280px;
  background-repeat: no-repeat;
}

.card {
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

.card:hover .card-header {
  opacity: 0.8;
  height: 100px;
}

h1 {
  font-size: 22px;
  font-weight: bold;
}

.card-body {
}

.card-body-header {
  line-height: 25px;
  margin: 13px 20px 0px 20px;
}

.card-body-description {
  opacity: 0;
  color: #757b82;
  line-height: 25px;
  -webkit-transition: 0.2s ease-in-out; /*사파리&크롬*/
  -moz-transition: 0.2s ease-in-out; /*파이어폭스*/
  -ms-transition: 0.2s ease-in-out; /*익스플로러*/
  -o-transition: 0.2s ease-in-out; /*오페라*/
  transition: 0.2s ease-in-out;

  margin: 5px 20px;
}
.card:hover .card-body-description {
  opacity: 1;
  -webkit-transition: 0.5s ease-in-out;
  -moz-transition: 0.5s ease-in-out;
  -ms-transition: 0.5s ease-in-out;
  -o-transition: 0.5s ease-in-out;
  transition: 0.5s ease-in-out;
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
  color: #9fa5a8;
  padding: 0 15px;
}

.address {
  float: right;
}
.reg_date {
  float: right;
}
</style>
