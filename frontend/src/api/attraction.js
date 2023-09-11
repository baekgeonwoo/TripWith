import { apiInstance } from "@/api/index.js";

const api = apiInstance();
const suffix = "/api/attractions"

function getAttraction(id, success) {
    api.get(`${suffix}/${id}`)
    .then(({data}) => {
        success(data);
    })
    .catch(error => {
        console.log(error);
    })
}

function getAttractions(pageRequest, success) {
    api.get(suffix, {params: pageRequest})
    .then(({data}) => {
        success(data.content);
    })
    .catch(error => {
        console.log(error);
    })
}

function searchAttractionsByGugunCode(sidoCode, gugunCode, success) {
    api.get(`${suffix}/search/gugun`, { params: { sidoCode, gugunCode } })
        .then(({ data }) => {
            success(data.content);
        })
        .catch(error => {
            console.log(error);
    })

}

export { getAttraction, getAttractions, searchAttractionsByGugunCode }