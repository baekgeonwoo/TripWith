import { apiInstance } from "@/api/index.js";

const api = apiInstance();
const suffix = "/api/trip-requests";
const Bearer = "Bearer ";

function sendJoinRequest(tripId, content, success, fail) {
    api.post(`${suffix}/auth/request/${tripId}`, JSON.stringify(content), {
        headers: {
            Authorization: Bearer + sessionStorage.getItem("access-token"),
        }
    })
        .then(({ data }) => {
            success(data);
        })
        .catch(() => {
            fail();
    })
}

function getUserRequest(memberId, success, fail) {
    api.get(`${suffix}/auth/member/${memberId}`, {
        headers: {
            Authorization: Bearer + sessionStorage.getItem("access-token"),
        }
    })
        .then(({ data }) => {
            console.log(data);
            success(data);
        })
        .catch(error => {
            fail(error);
    })
}

function getRequestByTripId(tripId, success, fail) {
    api.get(`${suffix}/auth/trip/${tripId}`, {
        headers: {
            Authorization: Bearer + sessionStorage.getItem("access-token"),
        }
        })
        .then(({ data }) => {
            success(data);
        })
        .catch(error => {
            fail(error);
        });
        
}

function acceptRequest(tripRequestId, success, fail) {
    api.get(`${suffix}/auth/${tripRequestId}/accept`, {
        headers: {
            Authorization: Bearer + sessionStorage.getItem("access-token"),
        }
        })
        .then(() => {
            success();
        })
        .catch(error => {
            fail(error);
        });
}

function rejectRequest(tripRequestId, success, fail) {
    api.get(`${suffix}/auth/${tripRequestId}/reject`, {
        headers: {
            Authorization: Bearer + sessionStorage.getItem("access-token"),
        }
        })
        .then(() => {
            success();
        })
        .catch(error => {
            fail(error);
        });
}

export { sendJoinRequest, getUserRequest, getRequestByTripId, acceptRequest, rejectRequest }