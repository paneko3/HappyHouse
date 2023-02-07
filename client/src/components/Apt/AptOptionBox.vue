<template>
  <md-content class="select-box md-elevation-10">
    <div class="checkbox md-layout md-gutter">
      <div class="md-layout-item md-size-20">
        <Checkbox
          v-model="facilityValues"
          value="starbucks"
        >
          스타벅스
        </Checkbox>
      </div>
      <div class="md-layout-item md-size-20">
        <Checkbox
          v-model="facilityValues"
          value="mac"
        >
          맥도날드
        </Checkbox>
      </div>
      <div class="md-layout-item md-size-20">
        <Checkbox
          v-model="facilityValues"
          value="cinema"
        >
          영화관
        </Checkbox>
      </div>
      <div class="md-layout-item md-size-20">
        <Checkbox
          v-model="facilityValues"
          value="daiso"
        >
          다이소
        </Checkbox>
      </div>
      <div class="md-layout-item md-size-20">
        <Checkbox
          v-model="facilityValues"
          value="store"
        >
          편의점
        </Checkbox>
      </div>
    </div>
    <div class="dong-select md-layout md-gutter">
      <div class="md-layout-item md-size-30">
        <md-field>
          <md-select
            id="sido"
            v-model="sido"
            name="sido"
            placeholder="시/도"
          >
            <md-option
              v-for="(regcode, index) in sidoRegcodes"
              :key="index"
              :value="regcode.sidoCode"
              class="asd"
            >
              {{ regcode.sidoName }}
            </md-option>
          </md-select>
        </md-field>
      </div>
      <div class="md-layout-item md-size-40">
        <md-field>
          <md-select
            id="gugun"
            v-model="gugun"
            name="gugun"
            placeholder="구/군"
          >
            <md-option
              v-for="(regcode, index) in gugunRegcodes"
              :key="index"
              :value="regcode.gugunCode"
            >
              {{ regcode.gugunName }}
            </md-option>
          </md-select>
        </md-field>
      </div>
      <div class="md-layout-item md-size-30">
        <md-field>
          <md-select
            id="dong"
            v-model="dong"
            name="dong"
            placeholder="동"
          >
            <md-option
              v-for="(regcode, index) in dongRegcodes"
              :key="index"
              :value="regcode.dongCode"
            >
              {{ regcode.dongName }}
            </md-option>
          </md-select>
        </md-field>
      </div>
    </div>
    <div class="md-layout">
      <div class="md-layout-item bottom">
        <div
          class="option-detail-choose"
          @click="toggleDetailOption"
        >
          검색조건을 선택해주세요
          <i class="material-icons">filter_list</i>
        </div>
        <md-button
          class="md-sm"
          @click="search"
        >
          검색
        </md-button>
      </div>
    </div>
    <div
      v-if="isOptionDeatilActive"
      :class="`option-detail`"
    >
      <md-divider />
      <md-list class="md-double-line">
        <i
          class="material-icons"
          style="cursor:pointer;"
          @click="toggleDetailOption"
        >close</i>
        <md-button @click="resetOptionDetail">
          초기화
        </md-button>
      </md-list>
      <md-list class="md-double-line">
        <md-subheader>
          거래금액 {{ priceToMoney(priceRange[0]) }} ~
          {{ priceToMoney(priceRange[1]) }} 원
        </md-subheader>
        <vue-range-slider
          v-model="priceRange"
          tooltip="hover"
          :min="aptsMinPrice"
          :max="aptsMaxPrice"
        />
      </md-list>
      <md-divider />
      <md-list class="md-double-line">
        <md-subheader>
          면적 {{ areaRange[0] }} ~ {{ areaRange[1] }} m²
        </md-subheader>
        <vue-range-slider
          v-model="areaRange"
          tooltip="hover"
          :min="aptsMinArea"
          :max="aptsMaxArea"
        />
      </md-list>
      <md-divider />
    </div>
  </md-content>
</template>

<script>
import { mapActions, mapGetters, mapMutations, mapState } from 'vuex';
import VueRangeSlider from 'vue-range-component';
import Checkbox from 'vue-material-checkbox';

import _ from 'lodash';

const aptStore = 'aptStore';
export default {
	components: { VueRangeSlider, Checkbox },
	data() {
		return {
			isOptionDeatilActive: false,
			facilityValues: [],
		};
	},
	computed: {
		...mapGetters(aptStore, [
			'aptsMinPrice',
			'aptsMaxPrice',
			'aptsMinArea',
			'aptsMaxArea',
		]),
		...mapState(aptStore, [
			'sidoRegcodes',
			'gugunRegcodes',
			'dongRegcodes',
			'map',
			'lat',
			'lng',
			'apts',
			'filteredApts',
			'priceRange',
			'areaRange',
			'facilityOptions',
		]),
		areaRange: {
			get() {
				return this.$store.state.aptStore.areaRange;
			},
			set(value) {
				this.SET_AREA_RANGE([value[0], value[1]]);
			},
		},
		priceRange: {
			get() {
				return this.$store.state.aptStore.priceRange;
			},
			set(value) {
				this.SET_PRICE_RANGE([value[0], value[1]]);
			},
		},
		sido: {
			get() {
				return this.$store.state.aptStore.sido;
			},
			set(value) {
				this.SET_SIDO(value);
				this.SET_GUGUN('');
				this.SET_DONG('');
				this.getGugunRegcodes(value);
			},
		},
		gugun: {
			get() {
				return this.$store.state.aptStore.gugun;
			},
			set(value) {
				if (!value) return;
				this.SET_GUGUN(value);
				this.SET_DONG('');
				this.getDongRegcodes(value);
			},
		},
		dong: {
			get() {
				return this.$store.state.aptStore.dong;
			},
			set(value) {
				if (!value) return;
				this.SET_DONG(value);
				const { lat, lng } = this.dongRegcodes.find(
					regcode => regcode.dongCode === value,
				);
				this.SET_LAT(lat);
				this.SET_LNG(lng);
				this.getApts(value);
			},
		},
	},
	watch: {
		priceRange: _.debounce(function(v) {
			const filteredApts = this.apts.filter(apt => {
				const recentPrice = apt.recentPrice.replace(/,/g, '');
				const area = apt.area.replace(/,/g, '');
				if (
					recentPrice >= v[0] &&
					recentPrice <= v[1] &&
					area >= this.areaRange[0] &&
					area <= this.areaRange[1]
				)
					return true;
			});
			this.SET_FILTERED_APTS(filteredApts);
		}, 100),
		areaRange: _.debounce(function(v) {
			const filteredApts = this.apts.filter(apt => {
				const recentPrice = apt.recentPrice.replace(/,/g, '');
				const area = apt.area.replace(/,/g, '');
				if (
					recentPrice >= this.priceRange[0] &&
					recentPrice <= this.priceRange[1] &&
					area >= v[0] &&
					area <= v[1]
				)
					return true;
			});
			this.SET_FILTERED_APTS(filteredApts);
		}, 100),
		facilityValues(v) {
			this.SET_FACILITIY_OPTIONS(v);
		},
	},
	created() {
		this.getSidoRegcodes();
		this.getGugunRegcodes(this.sido);
		this.getDongRegcodes(this.gugun);
		this.getAptsByLatLng();
		this.getFaicilitiesByLatLng();
		this.facilityValues = this.facilityOptions;
	},
	methods: {
		...mapActions(aptStore, [
			'getSidoRegcodes',
			'getGugunRegcodes',
			'getDongRegcodes',
			'getApts',
			'drawMarkers',
			'getAptsByLatLng',
			'getFaicilitiesByLatLng',
		]),
		...mapMutations(aptStore, [
			'SET_SIDO',
			'SET_GUGUN',
			'SET_DONG',
			'SET_LAT',
			'SET_LNG',
			'SET_FILTERED_APTS',
			'SET_AREA_RANGE',
			'SET_PRICE_RANGE',
			'SET_FACILITIY_OPTIONS',
		]),
		moveMap(lat, lng) {
			// eslint-disable-next-line no-undef
			const latlng = new kakao.maps.LatLng(lat, lng);
			this.map.panTo(latlng);
		},
		search() {
			const { lat, lng } = this.dongRegcodes.find(
				regcode => regcode.dongCode === this.dong,
			);
			this.moveMap(lat, lng);
			this.getApts(this.dong);
		},
		toggleDetailOption() {
			this.isOptionDeatilActive = !this.isOptionDeatilActive;
		},
		resetOptionDetail() {
			this.SET_PRICE_RANGE([this.aptsMinPrice, this.aptsMaxPrice]);
			this.SET_AREA_RANGE([this.aptsMinArea, this.aptsMaxArea]);
		},
		priceToMoney(price) {
			if (price < 1000) return `${price} 만`;
			return `${Math.floor(price / 10000)} 억 ${
				price % 10000 > 0 ? Math.floor(price % 10000) + '만' : ''
			}`;
		},
	},
};
</script>

<style lang="scss">
.select-box {
	position: absolute;
	left: 20px;
	top: 20px;
	width: 400px;
	padding: 0 5px 5px 5px;
	background-color: white;
	z-index: 2;
	.dong-select {
		i {
			display: none;
		}
	}
	.checkbox {
		height: 30px;
	}
	.md-layout-item {
		padding-right: 0 !important;
		.m-chckbox--label {
			padding-left: 2px !important;
			font-size: 12px;
		}
		.m-chckbox--group,
		.m-chckbox--ripple {
			width: 15px;
			height: 15px;

			svg {
				display: none;
			}
		}
	}
    .bottom{
        display: flex;
        margin-right: 50px;
		.option-detail-choose {
            width: 300px;
            text-align: center;
            margin: auto;
            display: flex;
            justify-content: center;
            i{
                text-align: center;
            }
			&:hover {
				cursor: pointer;
			}
		}

    }
}
</style>
