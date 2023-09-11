<template>
    <div>
    <h2>{{trip.title}}</h2>
    <div style="height:auto; min-height:500px"> 
      {{trip.content}} 
    </div>
    {{trip.createdAt}}
    <b-icon icon="suit-heart-fill"></b-icon>
    {{trip.like}}
    <b-icon icon="eye"></b-icon>
    {{trip.viewCount}}

  </div>

</template>

<script>
export default {
  data() {
    return {
    };
  },
  props: {
    trip: Object,
  }
};
</script>
