export const MARKER_TYPES = {
	basic: 'basic.png',
	basicSelected: 'basic-selected.png',
	starbucks: 'coffee.svg',
	mac: 'mac.svg',
	cinema: 'cinema.svg',
	daiso: 'daiso.svg',
	store: 'store.svg',
};
export const MARKER_SIZES = {
	basic: [32, 33],
	expand: [51, 54],
};
export const getMarkerImage = (
	type = MARKER_TYPES.basic,
	size = MARKER_SIZES.basic,
) => {
	const imageSrc = require(`@/assets/img/markers/${type}`);
	// eslint-disable-next-line no-undef
	const imageSize = new kakao.maps.Size(size[0], size[1]);
	// eslint-disable-next-line no-undef
	return new kakao.maps.MarkerImage(imageSrc, imageSize);
};
export const getMarker = (
	lat,
	lng,
	type = MARKER_TYPES.basic,
	size = MARKER_SIZES.basic,
) => {
	// eslint-disable-next-line no-undef
	const markerPosition = new kakao.maps.LatLng(lat, lng);
	const markerImage = getMarkerImage(type, size);
	// eslint-disable-next-line no-undef
	return new kakao.maps.Marker({
		position: markerPosition,
		image: markerImage,
	});
};
