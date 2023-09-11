<template>
  <div class="card-container">
    <el-card class="card">
      <div class="card-header">
        <h3>{{ communityItem.title }}</h3>
      </div>
      <div class="card-body">
        <p>{{ communityItem.content }}</p>
        <p>A second paragraph of text in the card.</p>
      </div>
      <div class="card-footer">
        <a href="#" class="card-link">Card link</a>
        <a href="#" class="card-link">Another link</a>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "CommunityListItem",
  props: {
    communityItem: Object,
  },
};
</script>

<style scoped>
.card-container {
  display: flex;
  justify-content: center;
}

.card {
  width: 600px;
  margin-bottom: 20px;
  border-radius: 15px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.card-header {
  background-color: #74b5f5;
  color: #fff;
  padding: 10px;
  font-size: 18px;
  font-weight: bold;
  border-top-left-radius: 15px;
  border-top-right-radius: 15px;
}

.card-body {
  padding: 20px;
}

.card-footer {
  background-color: #f0f0f0;
  padding: 10px;
  border-bottom-left-radius: 15px;
  border-bottom-right-radius: 15px;
}

.card-link {
  margin-right: 10px;
  color: #194d88;
  text-decoration: none;
}

.card-link:hover {
  text-decoration: underline;
}
</style>
