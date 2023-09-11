<template>
    <div>
    <div class="mt-3">
      
      <b-pagination v-model="currentPage" :total-rows="rows" align="center"></b-pagination>
    </div>

    </div>
</template>

<script>
export default {
    name: 'PaginationBar',
    components: {},
    data() {
        return {
            rows : 100,
            currentPage : 1
        };
    },
    created() {},
    methods: {},
    watch: {
        // 부모에게 현재 페이지 전달
        currentPage() {
            this.$emit('currentPage', this.currentPage);
        }
    }
};
</script>

<style scoped></style>