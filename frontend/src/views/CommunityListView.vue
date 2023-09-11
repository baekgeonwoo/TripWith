<template>
    <div class="container">
      <div class="board-title">커뮤니티</div>
      <div class="community-list">
        <community-list-item v-for="communityItem in communityItems" :key="communityItem.id" :communityItem="communityItem"></community-list-item>
      </div>
      <pagination-bar></pagination-bar>
    </div>
  </template>
  
  <script>
  import CommunityListItem from "@/components/community/CommunityListItem.vue";
  import PaginationBar from "@/components/PaginationBar.vue";
  
  export default {
    name: 'CommunityListView',
    components: { CommunityListItem, PaginationBar },
    data() {
      return {
        message: '',
      };
    },
    created() {},
    methods: {},
    computed: {
      communityItems() {
        return [
          {
            title: "제목1",
            content: "내용1",
            id: 1
          }
        ];
      }
    }
  };
  </script>
  
  <style scoped>
  .board-title {
    font-size: 30px;
    font-weight: bold;
    margin-bottom: 5px;
    margin-top: 20px;
  }
  
  .container {
    display: flex;
    flex-direction: column;
    justify-content: flex-start; /* 위쪽으로 정렬합니다. */
    align-items: center;
    min-height: 100vh;
  }
  
  .community-list {
    margin-top: 20px; /* community-list의 상단 마진을 추가합니다. */
  }
  </style>
  