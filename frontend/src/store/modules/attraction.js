import { getAttractions, getAttraction } from '@/api/attraction.js'

const attraction = {
    namespaced: true,
    state: () => ({
        attractions: [],
        attractionIdsInTrip: [],
    }),
    getters: {
        getAttractionsLimit4(state) {
            return state.attractions.slice(0, 4);
        },
        attractionsInTrip(state) {
            let attractionsInTrip = [];
            for (let i = 0; i < state.attractionIdsInTrip.length; i++) {
                getAttraction(state.attractionIdsInTrip[i], (attraction) => {
                    attractionsInTrip.push(attraction);
                })
            }
            
            return attractionsInTrip;
        }
    },
    mutations: {
        LOAD_ATTRACTIONS(state, attractions) {
            state.attractions = attractions;
        },
        ADD_TO_ATTRACTION_IDS_IN_TRIP(state, attractionId) {
            console.log("ADD_TO_ATTRACTION_IDS_IN_TRIP: ", attractionId);
            if (state.attractionIdsInTrip.includes(attractionId)) {
                return;
            }
            state.attractionIdsInTrip.push(attractionId);
            console.log("ADD_TO_ATTRACTION_IDS_IN_TRIP: ", attractionId, "끝");
        },
        REMOVE_FROM_ATTRACTION_IDS_IN_TRIP(state, attractionId) {
            state.attractionIdsInTrip = state.attractionIdsInTrip.filter((item) => {
                return item != attractionId;
            })
        }
    },
    actions: {
        loadAttractions({commit}, param) {
            getAttractions(param, (attractions) => {
                commit("LOAD_ATTRACTIONS", attractions);
            })
        },
        addToAttractionIdsInTrip({ commit }, attractionId) {
            console.log("addToAttractionIdsInTrip: ", attractionId);
            commit("ADD_TO_ATTRACTION_IDS_IN_TRIP", attractionId);
        },
        removeFromAttractionIdsInTrip({ commit }, attractionId) {
            commit("REMOVE_FROM_ATTRACTION_IDS_IN_TRIP", attractionId);
        }
    },
}

export { attraction };