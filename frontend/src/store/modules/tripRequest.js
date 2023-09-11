import { sendJoinRequest, getUserRequest } from '@/api/tripRequest'

const tripRequest = {
    namespaced: true,
    state: () => ({
        tripRequests : [],
    }),
    getters: {
    },
    mutations: {
        LOAD_JOIN_REQUESTS(state, tripRequests) {
            state.tripRequests = tripRequests;
        },
        ADD_JOIN_REQUEST(state, tripRequest) {
            state.tripRequests.push(tripRequest);
        },
        LOAD_TRIP_REQUESTS(state, tripRequests) {
            state.tripRequests = tripRequests;
        }
    },
    actions: {
        async joinRequest({ commit }, { tripId, fail }) {
            console.log("fail: " + fail);

            await sendJoinRequest(tripId, "동행 신청합니다.", (tripRequest) => {
                commit("ADD_JOIN_REQUEST", tripRequest);
            }, fail);
            
        },
        loadRequests({ commit }, memberId) {
            getUserRequest(memberId, (tripRequests) => {
                commit("LOAD_TRIP_REQUESTS", tripRequests);
            },
                (error) => {
                    console.log(error);
            })
        }
    },
}

export { tripRequest };