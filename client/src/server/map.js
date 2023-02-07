import server from '@/server';
const URL = 'map/';

export async function getSidos() {
	return await server.get(URL + 'sido');
}
export async function getGuguns(sido) {
	return await server.get(URL + 'gugun?sido=' + sido);
}
export async function getDongs(gugun) {
	return await server.get(URL + 'dong?gugun=' + gugun);
}
export async function getDeals(aptcode) {
	return await server.get(URL + 'deal?aptCode=' + aptcode);
}
export async function getApts(dong) {
	return await server.get(URL + 'apt?dong=' + dong);
}

export async function getAptsByLatLng(lat, lng, zoomLevel) {
	return await server.get(
		URL + `currentlocation?lat=${lat}&lng=${lng}&zoomlevel=${zoomLevel}`,
	);
}
export async function getFaicilitiesByLatLng(lat, lng, zoomLevel) {
	return await server.get(
		URL + `currentfacility?lat=${lat}&lng=${lng}&zoomlevel=${zoomLevel}`,
	);
}

export async function getFacilities(aptcode) {
	return await server.get(URL + `nearfacilitydis?aptCode=` + aptcode);
}
export async function getStations(aptcode) {
	return await server.get(URL + `deal/station?aptCode=` + aptcode);
}
