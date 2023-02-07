import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate';

import userStore from '@/store/modules/userStore';
import aptStore from '@/store/modules/aptStore';
import qnaStore from '@/store/modules/qnaStore'

Vue.use(Vuex);
const store = new Vuex.Store({
	modules: {
		userStore,
		aptStore,
		qnaStore,
	},
	plugins: [
		createPersistedState({
			paths: ['userStore'],
			storage: sessionStorage,
		}),
	],
});
export default store;
