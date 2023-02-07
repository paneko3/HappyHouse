<template>
  <div id="map" />
</template>

<script>
import { mapActions, mapMutations, mapState } from 'vuex';
import { MARKER_TYPES, getMarker } from '@/utils/MapMarker';
const aptStore = 'aptStore';
export default {
	computed: {
		...mapState(aptStore, [
			'map',
			'lat',
			'lng',
			'apts',
			'markers',
			'dong',
			'sido',
			'filteredApts',
			'map',
			'facilities',
			'facilityOptions',
			'facilityMarkers',
		]),
	},
	watch: {
		filteredApts() {
			if (this.map) {
				this.markers.forEach(marker => marker.setMap(null));
				const markers = this.filteredApts.map(apt => {
					const marker = getMarker(apt.lat, apt.lng);
					let markerContent = `<div>${apt.aptName}</div>`;
					let infoWindow = new kakao.maps.InfoWindow({
						content: markerContent,
					});
					kakao.maps.event.addListener(marker, 'mouseover', () => {
						infoWindow.open(this.map, marker);
						setTimeout(() => {
							infoWindow.close();
						}, 1000);
					});
					kakao.maps.event.addListener(marker, 'mouseout', () =>
						infoWindow.close(),
					);
					return marker;
				});
				this.SET_MARKERS(markers);
				markers.forEach(marker => marker.setMap(this.map));
			}
		},
		facilities() {
			if (this.map) {
				for (let facilityOptions in this.facilityMarkers) {
					this.facilityMarkers[facilityOptions].forEach(marker =>
						marker.setMap(null),
					);
				}
				const newFacilityMarkers = {};
				for (let kind in this.facilities) {
					const markers = this.facilities[kind].map(facility =>
						getMarker(facility.lat, facility.lng, MARKER_TYPES[kind]),
					);
					newFacilityMarkers[kind] = markers;
				}
				this.SET_FACILITY_MARKERS(newFacilityMarkers);
				this.facilityOptions.forEach(key => {
					this.facilityMarkers[key].forEach(marker => marker.setMap(this.map));
				});
			}
		},
		facilityOptions() {
			if (this.map) {
				//옵션 바뀌면 마커 전부 지우기
				for (let options in this.facilityMarkers) {
					this.facilityMarkers[options].forEach(marker => marker.setMap(null));
				}
				//체크된 옵션은 마커 다시그리기
				this.facilityOptions.forEach(key => {
					this.facilityMarkers[key].forEach(marker => marker.setMap(this.map));
				});
			}
		},
	},
	mounted() {
		const script = document.createElement('script');

		/* global kakao */
		script.onload = () => kakao.maps.load(this.initMap);

		script.src =
			'//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=d84386396c758144e0a61348cd543891&libraries=services,clusterer,drawing';
		document.head.appendChild(script);
	},
	methods: {
		...mapMutations(aptStore, [
			'SET_MAP',
			'SET_LAT',
			'SET_LNG',
			'SET_MARKERS',
			'SET_FACILITY_MARKERS',
			'SET_SIDO',
			'SET_GUGUN',
			'SET_DONG',
			'SET_ZOOM_LEVEL',
		]),
		...mapActions(aptStore, ['getAptsByLatLng', 'getFaicilitiesByLatLng']),
		initMap() {
			const container = document.querySelector('#map');
			const options = {
				center: new kakao.maps.LatLng(this.lat, this.lng, 16),
				level: 5,
			};
			this.SET_MAP(new kakao.maps.Map(container, options));
			this.getAptsByLatLng();
			this.getFaicilitiesByLatLng();
			kakao.maps.event.addListener(this.map, 'dragend', () => {
				const latlng = this.map.getCenter();
				this.SET_LAT(latlng.getLat());
				this.SET_LNG(latlng.getLng());
				this.getAptsByLatLng();

				this.getFaicilitiesByLatLng();
				new kakao.maps.services.Geocoder().coord2RegionCode(
					this.lng,
					this.lat,
					(a, b) => {
						if (b === kakao.maps.services.Status.OK) {
							this.SET_SIDO(a[0].code.slice(0, 2));
							this.SET_GUGUN(a[0].code.slice(0, 5));
							this.SET_DONG(a[0].code);
						}
					},
				);
			});
			kakao.maps.event.addListener(this.map, 'zoom_changed', () => {
				const level = this.map.getLevel();
				this.SET_ZOOM_LEVEL(level);
				this.getAptsByLatLng();
				this.getFaicilitiesByLatLng();
			});
		},
	},
};
</script>

<style>
#map {
	width: calc(100% - 200px);
	height: 100%;
}
</style>
