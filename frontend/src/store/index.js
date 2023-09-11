import Vue from 'vue'
import Vuex from 'vuex'
import { trip } from './modules/trip.js'
import { attraction } from './modules/attraction.js'
import { memberStore } from './modules/memberStore.js'
import { tripRequest } from "./modules/tripRequest.js"
// 데이터 유지를 위해 import
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    trip,
    attraction,
    memberStore,
    tripRequest,
  },
  plugins: [  
    createPersistedState({
      paths: ["memberStore"]
    })
  ],
})
