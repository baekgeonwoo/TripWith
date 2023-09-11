import { apiInstance } from "@/api/index.js";

const api = apiInstance();
const suffix = "/api/trips";
const Bearer = "Bearer ";

function getTrips(pageRequest, success) {
    api.get(suffix, {params: pageRequest})
    .then(({data}) => {
        success(data.content);
    })
    .catch(error => {
        console.log(error);
    })
}

function getTripsByMemberId(memberId, success, fail) {
    api.get(`${suffix}/members/${memberId}`)
        .then(({ data }) => {
            success(data.content);
        })
        .catch(error => {
            fail(error);
    })
}

async function getTrip(id, success, fail) {
    await api.get(`${suffix}/${id}`)
        .then(({ data }) => {
            success(data);
        })
        .catch(error => {
            fail(error);
    })
}

function writeTrip(tripWriteRequestDto, success, fail) {
    api.post(`${suffix}/auth`, JSON.stringify(tripWriteRequestDto), {
        headers: {
            Authorization: Bearer + sessionStorage.getItem("access-token"),
        }
    })
        .then(() => {
            success();
        })
        .catch(error => {
            fail(error);
        })
}

function updateTrip(tripId, tripUpdateRequestDto, success, fail) {
    api.put(`${suffix}/auth/${tripId}`, JSON.stringify(tripUpdateRequestDto), {
        headers: {
            Authorization: Bearer + sessionStorage.getItem("access-token"),
        }
    })
        .then(() => {
            success();
        })
        .catch(error => {
            fail(error);
    })
}

function deleteTrip(tripId, success, fail) {
    api.delete(`${suffix}/auth/${tripId}`, {
        headers: {
            Authorization: Bearer + sessionStorage.getItem("access-token"),
        }
    })
        .then(() => {
            success();
        })
        .catch(error => {
            fail(error);
    })
}

export { getTrips, getTrip , writeTrip, updateTrip, deleteTrip, getTripsByMemberId};
