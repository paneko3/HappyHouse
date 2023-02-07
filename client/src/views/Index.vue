<template>
  <div class="wrapper">
    <parallax
      class="page-header header-filter"
      :style="headerStyle"
    >
      <div class="md-layout">
        <div class="md-layout-item">
          <div class="image-wrapper">
            <div class="brand">
              <h1>Happy House</h1>
              <h3>주택 거래정보 사이트</h3>
            </div>
          </div>
        </div>
      </div>
      <div class="index-footer">
        <div class="copyright">
          &copy; 2022, made by 정재철 & 최진합 ( SSAFY 7기 광주 4반 )
        </div>
      </div>
    </parallax>
  </div>
</template>

<script>
export default {
	name: 'Index',
	bodyClass: 'index-page',
	props: {
		signup: {
			type: String,
			default: require('@/assets/img/city.jpg'),
		},
	},
	data() {
		return {
			firstname: null,
			email: null,
			password: null,
			leafShow: false,
			timer: null,
			imgIdx: 0,
			image: require('@/assets/img/main/1.jpg'),
		};
	},
	computed: {
		headerStyle() {
			return {
				backgroundImage: `url(${this.image})`,
			};
		},
		signupImage() {
			return {
				backgroundImage: `url(${this.signup})`,
			};
		},
	},
	mounted() {
		this.leafActive();
		this.timer = setInterval(this.changeImage, 1500);
		window.addEventListener('resize', this.leafActive);
	},
	beforeDestroy() {
		clearInterval(this.timer);
		window.removeEventListener('resize', this.leafActive);
	},
	methods: {
		leafActive() {
			if (window.innerWidth < 768) {
				this.leafShow = false;
			} else {
				this.leafShow = true;
			}
		},
		changeImage() {
			var imageArray = [
				require(`@/assets/img/main/1.jpg`),
				require(`@/assets/img/main/2.jpg`),
				require(`@/assets/img/main/3.jpg`),
				require(`@/assets/img/main/4.jpg`),
			];
			this.imgIdx++;
			if (this.imgIdx >= imageArray.length) {
				this.imgIdx = 0;
			}
			this.image = imageArray[this.imgIdx];
		},
	},
};
</script>
<style lang="scss">
.page-header {
	height: 100vh !important;
	transition: background-image 0.4s ease-in-out;
}
.section-download {
	.md-button + .md-button {
		margin-left: 5px;
	}
}

.index-footer {
	position: fixed;
	bottom: 0;
	right: 0;
	color: white;
}

@media all and (min-width: 991px) {
	.btn-container {
		display: flex;
	}
}
</style>
