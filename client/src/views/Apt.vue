<template>
  <div class="wrapper">
    <div
      class="container section"
      :style="headerStyle"
    />
    <div class="main">
      <div class="map-container">
        <apt-option-box />
        <kakao-map />
        <router-view />
      </div>
    </div>
  </div>
</template>

<script>
import KakaoMap from '@/components/Apt/KakaoMap.vue';
import AptOptionBox from '@/components/Apt/AptOptionBox.vue';
import 'vue-range-component/dist/vue-range-slider.css';

export default {
	components: { KakaoMap, AptOptionBox },
	props: {
		header: {
			type: String,
			default: require('@/assets/img/main/banner.png'),
		},
	},
	computed: {
		headerStyle() {
			return {
				backgroundImage: `url(${this.header})`,
				backgroundSize: '100% 200%',
			};
		},
	},
};
</script>

<style lang="scss">
.map-container {
	height: calc(100vh - 140px);
	display: flex;
	position: relative;
}

</style>
