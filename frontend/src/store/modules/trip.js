import { getTrips } from '@/api/trip.js'

const trip = {
    namespaced: true,
    state: () => ({
        trips : [],
    }),
    getters: {
        getTripsLimit4(state) {
            return state.trips.slice(0, 4);
        }
    },
    mutations: {
        LOAD_TRIPS(state, trips) {
            state.trips = trips;
        }
    },
    actions: {
        loadTrips({commit}, param) {
            getTrips(param, trips => {
                commit("LOAD_TRIPS", trips);
            })
        }
    },
}

export { trip };