import {
	getApts,
	getAptsByLatLng,
	getDongs,
	getFaicilitiesByLatLng,
	getGuguns,
	getSidos,
} from '@/server/map';

const aptStore = {
	namespaced: true,
	state: {
		sido: '11',
		gugun: '11170',
		dong: '1117010200',
		sidoRegcodes: [],
		gugunRegcodes: [],
		dongRegcodes: [],
		map: null,
		zoomLevel: 5,
		lat: 37.541,
		lng: 126.986,
		apts: [],
		facilities: {},
		facilityOptions: [],
		facilityMarkers: {
			cinema: [],
			mac: [],
			starbucks: [],
			daiso: [],
			store: [],
		},
		areaRange: [],
		priceRange: [],
		filteredApts: [],
		markers: [],
	},
	getters: {
		aptsCount: state => state.filteredApts.length,
		aptsMinPrice: state =>
			state.apts.length > 0
				? state.apts.reduce((prev, cur) => {
						return prev < cur.recentPrice.replace(/,/g, '') * 1
							? prev
							: cur.recentPrice.replace(/,/g, '') * 1;
				  }, state.apts[0].recentPrice.replace(/,/g, '') * 1)
				: 0,
		aptsMaxPrice: state =>
			state.apts.length > 0
				? state.apts.reduce((prev, cur) => {
						return prev > cur.recentPrice.replace(/,/g, '') * 1
							? prev
							: cur.recentPrice.replace(/,/g, '') * 1;
				  }, state.apts[0].recentPrice.replace(/,/g, '') * 1)
				: 0,
		aptsMinArea: state =>
			state.apts.length > 0
				? state.apts.reduce((prev, cur) => {
						return prev < cur.area * 1 ? prev : Math.floor(cur.area * 1);
				  }, Math.floor(state.apts[0].area * 1))
				: 0,
		aptsMaxArea: state =>
			state.apts.length > 0
				? state.apts.reduce((prev, cur) => {
						return prev > cur.area * 1 ? prev : Math.ceil(cur.area * 1);
				  }, Math.ceil(state.apts[0].area * 1))
				: 0,
	},
	mutations: {
		SET_SIDO: (state, sido) => (state.sido = sido),
		SET_GUGUN: (state, gugun) => (state.gugun = gugun),
		SET_DONG: (state, dong) => (state.dong = dong),
		SET_SIDO_REGCODES: (state, sidoRegcodes) =>
			(state.sidoRegcodes = sidoRegcodes),
		SET_GUGUN_REGCODES: (state, gugunRegcodes) =>
			(state.gugunRegcodes = gugunRegcodes),
		SET_DONG_REGCODES: (state, dongRegcodes) =>
			(state.dongRegcodes = dongRegcodes),
		SET_LAT: (state, lat) => (state.lat = lat),
		SET_LNG: (state, lng) => (state.lng = lng),
		SET_APTS: (state, apts) => (state.apts = apts),
		SET_FACILITIES: (state, facilities) => (state.facilities = facilities),
		SET_FACILITIY_OPTIONS: (state, facilityOptions) =>
			(state.facilityOptions = facilityOptions),
		SET_FILTERED_APTS: (state, filteredApts) =>
			(state.filteredApts = filteredApts),
		SET_AREA_RANGE: (state, areaRange) => (state.areaRange = areaRange),
		SET_PRICE_RANGE: (state, priceRange) => (state.priceRange = priceRange),
		SET_MAP: (state, map) => (state.map = map),
		SET_ZOOM_LEVEL: (state, zoomLevel) => (state.zoomLevel = zoomLevel),
		SET_MARKERS: (state, markers) => (state.markers = markers),
		SET_FACILITY_MARKERS: (state, facilityMarkers) =>
			(state.facilityMarkers = facilityMarkers),
	},
	actions: {
		async getSidoRegcodes({ commit }) {
			const res = await getSidos();
			commit('SET_SIDO_REGCODES', res.data);
		},
		async getGugunRegcodes({ commit }, sido) {
			commit('SET_DONG_REGCODES', []);
			const res = await getGuguns(sido);
			commit('SET_GUGUN_REGCODES', res.data);
		},
		async getDongRegcodes({ commit }, gugun) {
			const res = await getDongs(gugun);
			commit('SET_DONG_REGCODES', res.data);
		},
		async getApts({ commit }, dong) {
			const res = await getApts(dong);
			const apts = res.data;
			commit('SET_APTS', apts);
			commit('SET_FILTERED_APTS', apts);
			const maxPrice =
				apts.length > 0
					? apts.reduce((prev, cur) => {
							return prev > cur.recentPrice.replace(/,/g, '') * 1
								? prev
								: cur.recentPrice.replace(/,/g, '') * 1;
					  }, apts[0].recentPrice.replace(/,/g, '') * 1)
					: 0;
			const minPrice =
				apts.length > 0
					? apts.reduce((prev, cur) => {
							return prev < cur.recentPrice.replace(/,/g, '') * 1
								? prev
								: cur.recentPrice.replace(/,/g, '') * 1;
					  }, apts[0].recentPrice.replace(/,/g, '') * 1)
					: 0;
			const minArea =
				apts.length > 0
					? apts.reduce((prev, cur) => {
							return prev < cur.area * 1 ? prev : Math.floor(cur.area * 1);
					  }, Math.floor(apts[0].area * 1))
					: 0;
			const maxArea =
				apts.length > 0
					? apts.reduce((prev, cur) => {
							return prev > cur.area * 1 ? prev : Math.floor(cur.area * 1);
					  }, Math.floor(apts[0].area * 1))
					: 0;
			commit('SET_PRICE_RANGE', [minPrice, maxPrice]);
			commit('SET_AREA_RANGE', [minArea, maxArea]);
		},
		async getAptsByLatLng({ state, commit }) {
			const res = await getAptsByLatLng(state.lat, state.lng, state.zoomLevel);
			if (res.data) {
				const apts = res.data;
				commit('SET_APTS', apts);
				commit('SET_FILTERED_APTS', apts);
				const maxPrice =
					apts.length > 0
						? apts.reduce((prev, cur) => {
								return prev > cur.recentPrice.replace(/,/g, '') * 1
									? prev
									: cur.recentPrice.replace(/,/g, '') * 1;
						  }, apts[0].recentPrice.replace(/,/g, '') * 1)
						: 0;
				const minPrice =
					apts.length > 0
						? apts.reduce((prev, cur) => {
								return prev < cur.recentPrice.replace(/,/g, '') * 1
									? prev
									: cur.recentPrice.replace(/,/g, '') * 1;
						  }, apts[0].recentPrice.replace(/,/g, '') * 1)
						: 0;
				const minArea =
					apts.length > 0
						? apts.reduce((prev, cur) => {
								return prev < cur.area * 1 ? prev : Math.floor(cur.area * 1);
						  }, Math.floor(apts[0].area * 1))
						: 0;
				const maxArea =
					apts.length > 0
						? apts.reduce((prev, cur) => {
								return prev > cur.area * 1 ? prev : Math.ceil(cur.area * 1);
						  }, Math.ceil(apts[0].area * 1))
						: 0;
				commit('SET_PRICE_RANGE', [minPrice, maxPrice]);
				commit('SET_AREA_RANGE', [minArea, maxArea]);
			}
		},
		async getFaicilitiesByLatLng({ state, commit }) {
			const res = await getFaicilitiesByLatLng(
				state.lat,
				state.lng,
				state.zoomLevel,
			);
			commit('SET_FACILITIES', res.data);
		},
	},
};
export default aptStore;
