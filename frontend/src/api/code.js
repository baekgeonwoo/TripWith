import { apiInstance } from "@/api/index.js"

const api = apiInstance();
const suffix = '/api/sidos';

function getAreaCodes(success, fail) {
    api.get(suffix)
        .then(({ data }) => {
            success(data);
        })
        .catch(error => {
            fail(error);
    })
}

function getSigunguCodesByAreaCode(areaCode, success, fail) {
    // TODO: url 패턴 재상의
    api.get(`${suffix}/${areaCode}`)
        .then(({ data }) => {
            success(data);
        })
        .catch(error => {
            fail(error);
    })
}

export { getAreaCodes, getSigunguCodesByAreaCode }