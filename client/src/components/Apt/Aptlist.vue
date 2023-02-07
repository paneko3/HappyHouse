<template>
  <div id="info">
    <div class="header">
      <h4 class="title">
        아파트 목록 : {{ aptsCount }}
      </h4>
    </div>
    <div class="apt-list md-scrollbar">
      <div
        v-for="(apt, index) in filteredApts"
        :key="index"
        class="apt-card"
        :data-key="index"
        @mouseenter="aptCardMouseEnter"
        @mouseleave="aptCardMouseLeave"
        @click="showDetail"
      >
        <div class="image">
          <img
            :src="getAptImgSrc(apt.aptCode)"
            alt="apt"
          >
        </div>
        <div class="desc">
          <div class="name">
            {{ apt.aptName }}
          </div>
          <div class="">
            {{ apt.recentPrice }}
          </div>
          <div class="">
            {{ apt.buildYear }}
          </div>
          <div class="">
            {{ apt.dongName }}
          </div>
          <md-divider />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getMarkerImage, MARKER_SIZES, MARKER_TYPES } from '@/utils/MapMarker';
import { mapGetters, mapState } from 'vuex';
const aptStore = 'aptStore';
export default {
	computed: {
		...mapGetters(aptStore, ['aptsCount']),
		...mapState(aptStore, ['filteredApts', 'markers']),
	},
	methods: {
		showDetail(e) {
			this.$router.push({
				name: 'apt-detail',
				params: {
					aptcode: `${
						this.filteredApts[e.currentTarget.getAttribute('data-key')].aptCode
					}`,
				},
			});
		},
		aptCardMouseEnter(e) {
			const key = e.target.getAttribute('data-key');
			this.markers[key].setImage(
				getMarkerImage(MARKER_TYPES.basicSelected, MARKER_SIZES.expand),
			);
		},
		aptCardMouseLeave(e) {
			const key = e.target.getAttribute('data-key');
			this.markers[key].setImage(
				getMarkerImage(MARKER_TYPES.basic, MARKER_SIZES.basic),
			);
		},
		getAptImgSrc(aptcode) {
			return require(`@/assets/img/apts/${aptcode[aptcode.length - 1]}.jpg`);
		},
	},
};
</script>

<style lang="scss">
#info {
	background-color: #f0f0f0f0;
	width: 300px;
	height: calc(100vh - 140px);
	overflow: hidden;
	.header {
		text-align: center;
	}
	.apt-list {
		overflow-y: scroll;
		height: calc(100vh - 227px);
	}

	.apt-card {
		opacity: 0.9;
		/* background-color: red; */
		padding: 5px;
		display: flex;
		height: 100px;
		&:hover {
			opacity: 1;
			cursor: pointer;
			background-color: #f5f5f5;
		}
		.image {
			display: inline-block;
			width: 50%;
			height: 100%;
			img {
				width: 100%;
				height: 100%;
			}
		}
		.desc {
			display: inline-block;
			width: 50%;
			font-size: 10px;
			padding: 3px;
			.name {
				overflow: hidden;
				white-space: nowrap;
				text-align: center;
				text-overflow: ellipsis;
			}
		}
	}
}
</style>
