<template>
	<div class="apt-detail md-scrollbar">
		<div class="header">
			<router-link :to="{ name: 'apt-list' }">
				<i class="material-icons" @click="setMarkerBack"
>arrow_back</i>
			</router-link>
			<div>
				{{ aptName }}
			</div>
		</div>
		<div class="wrapper">
			<div class="image-wrapper">
				<img :src="getAptImgSrc(this.$route.params.aptcode)" alt="" />
			</div>
			<div v-if="avgRate" class="avg-rate"
>
				<h2 class="">
					{{ avgRate.toFixed(2) }}
				</h2>
				<div class="stars">
					<div v-for="item in Math.floor(avgRate)" :key="item"
class="star"
/>
					<div v-if="avgRate - Math.floor(avgRate) >= 0.5" class="star half"
/>
				</div>
			</div>
			<md-divider />
			<div class="desc">
				<div class="">
					상권정보
				</div>
				<div class="md-layout">
					<div class="md-layout-item md-size-33">
						<i
							class="material-icons"
							:style="facilities.station ? 'color:green' : ''"
						>
							train
						</i>
						<div v-if="facilities.station">
							{{ disToTime(facilities.station) }}
						</div>
					</div>
					<div class="md-layout-item md-size-33">
						<i
							class="material-icons"
							:style="facilities.starbucks ? 'color:green' : ''"
						>
							coffee
						</i>
						<div v-if="facilities.starbucks">
							{{ disToTime(facilities.starbucks) }}
						</div>
					</div>
					<div class="md-layout-item md-size-33">
						<i
							class="material-icons"
							:style="facilities.mac ? 'color:green' : ''"
						>
							lunch_dining
						</i>
						<div v-if="facilities.mac">
							{{ disToTime(facilities.mac) }}
						</div>
					</div>
					<div class="md-layout-item md-size-33">
						<i
							class="material-icons"
							:style="facilities.daiso ? 'color:green' : ''"
						>
							shopping_bag
						</i>
						<div v-if="facilities.daiso">
							{{ disToTime(facilities.daiso) }}
						</div>
					</div>
					<div class="md-layout-item md-size-33">
						<i
							class="material-icons"
							:style="facilities.cinema ? 'color:green' : ''"
						>
							movie
						</i>
						<div v-if="facilities.cinema">
							{{ disToTime(facilities.cinema) }}
						</div>
					</div>
					<div class="md-layout-item md-size-33">
						<i
							class="material-icons"
							:style="facilities.store ? 'color:green' : ''"
						>
							local_convenience_store
						</i>
						<div v-if="facilities.store">
							{{ disToTime(facilities.store) }}
						</div>
					</div>
				</div>
			</div>
			<md-divider />
			<div class="">
				거래내역
			</div>
			<div class="deal-list md-scrollbar">
				<div v-for="(deal, index) in deals" :key="index"
class="deal-card"
>
					<div class="">
						거래일 {{ deal.dealYear }} / {{ deal.dealMonth }} /
						{{ deal.dealDay }}
					</div>
					<div class="">가격 {{ deal.dealAmount }}</div>
					<div class="">면적 {{ deal.area }}m²</div>
					<div class="">{{ deal.floor }} 층</div>
				</div>
			</div>
			<md-divider />
			<div class="">
				한줄평
			</div>
			<div class="post-list md-scrollbar">
				<div
					v-for="(com, index) in commentsList"
					:key="index"
					class="post-card"
				>
					<div class="post-card-header">
						<div class="writer">
							{{ com.id }}
						</div>
						<div class="regdate">
							{{ com.regtime }}
						</div>
					</div>
					<div class="post-card-content">
						{{ com.content }}
					</div>
					<div class="post-card-bottom">
						<div class="post-card-btn">
							<div v-for="idx in parseInt(com.rank)" :key="idx"
class="star"
/>
						</div>
						<div
							v-if="userInfo && userInfo.id === com.id"
							class="post-card-btn"
							:data-key="comment.commentno"
							@click="deleteComment"
						>
							삭제
						</div>
					</div>
				</div>
			</div>
			<md-field class="comment">
				<label>한줄평 남기기</label>
				<md-textarea v-model="comment" md-counter="40"
/>
			</md-field>
			<div class="rating" @click="rateClick"
>
				<!-- 해당 별점을 클릭하면 해당 별과 그 왼쪽의 모든 별의 체크박스에 checked 적용 -->
				<input
					id="rating1"
					type="checkbox"
					name="rating"
					value="1"
					class="rate_radio"
					title="1점"
				/>
				<label for="rating1" />
				<input
					id="rating2"
					type="checkbox"
					name="rating"
					value="2"
					class="rate_radio"
					title="2점"
				/>
				<label for="rating2" />
				<input
					id="rating3"
					type="checkbox"
					name="rating"
					value="3"
					class="rate_radio"
					title="3점"
				/>
				<label for="rating3" />
				<input
					id="rating4"
					type="checkbox"
					name="rating"
					value="4"
					class="rate_radio"
					title="4점"
				/>
				<label for="rating4" />
				<input
					id="rating5"
					type="checkbox"
					name="rating"
					value="5"
					class="rate_radio"
					title="5점"
				/>
				<label for="rating5" />
			</div>
			<md-button class="md-raised" type="button"
@click="submitComment"
>
				등록
			</md-button>
		</div>
	</div>
</template>

<script>
import { getDeals, getFacilities } from '@/server/map';
import { deleteComment, getComments, postComment } from '@/server/aptComment';
import { mapState } from 'vuex';
import { getMarkerImage, MARKER_TYPES } from '@/utils/MapMarker';
const aptStore = 'aptStore';
const userStore = 'userStore';
export default {
	data() {
		return {
			aptName: '',
			aptNo: 0,
			deals: [],
			facilities: {
				station: 0.21,
				mac: 0.126,
				cinema: 0.126,
				store: 0.126,
				daiso: null,
				starbucks: null,
			},
			commentsList: [],
			comment: '',
			rate: 0,
		};
	},
	computed: {
		...mapState(aptStore, ['markers']),
		...mapState(userStore, ['userInfo']),
		avgRate() {
			if (this.commentsList.length > 0) {
				return (
					this.commentsList.reduce((sum, cur) => sum + cur.rank * 1, 0) /
					this.commentsList.length
				);
			}
			return null;
		},
	},
	created() {
		const fetchAptDeals = async () => {
			const res = await getDeals(this.$route.params.aptcode);
			this.deals = res.data;
			this.aptName = res.data[0].aptName;
		};
		const fetchFacilities = async () => {
			const res = await getFacilities(this.$route.params.aptcode);
			this.facilities = res.data;
		};
		fetchAptDeals();
		fetchFacilities();
		this.fetchComments();
	},
	methods: {
		rateClick(e) {
			const el = e.target;
			if (el.classList.contains('rate_radio')) {
				this.setRate(parseInt(el.value));
			}
		},
		setRate(newrate) {
			this.rate = newrate;
			let items = document.querySelectorAll('.rate_radio');
			items.forEach(function(item, idx) {
				if (idx < newrate) {
					item.checked = true;
				} else {
					item.checked = false;
				}
			});
		},
		async deleteComment(e) {
			const commentNo = e.target.getAttribute('data-key');
			const res = await deleteComment(commentNo);
			if (res.data === 'success') {
				this.fetchComments();
			}
		},
		async fetchComments() {
			const res = await getComments(this.$route.params.aptcode);
			this.commentsList = res.data;
		},
		checkComment() {
			if (this.rate === 0) {
				alert('별점을 등록해주세요.');
				return false;
			}
			if (this.comment.trim() === '') {
				alert('한줄평을 작성해주세요.');
				return false;
			}
			return true;
		},
		async submitComment() {
			if (this.userInfo) {
				if (!this.checkComment()) return;
				const comment = {
					content: this.comment,
					aptCode: this.$route.params.aptcode,
					id: this.userInfo.id,
					rank: this.rate,
				};
				const res = await postComment(comment);
				if (res.data === 'success') {
					this.fetchComments();
					this.comment = '';
					this.setRate(0);
				}
			} else {
				alert('로그인후 글을 써주세요.');
				this.$router.push({ name: 'login' });
			}
		},
		setMarkerBack() {
			this.markers.forEach(marker => {
				marker.setImage(getMarkerImage(MARKER_TYPES.basic));
			});
		},
		disToTime(dis) {
			return Math.round(dis * 16) + '분';
		},
		getAptImgSrc(aptcode) {
			return require(`@/assets/img/apts/${aptcode[aptcode.length - 1]}.jpg`);
		},
	},
};
</script>

<style lang="scss">
.star {
	cursor: default;
	display: inline-block;
	margin-left: -1px;
	background-image: url('../../assets/img/starrate.png');
	background-repeat: no-repeat;
	background-color: #ffa500;
	background-size: 30px 30px;
	width: 30px;
	height: 30px;
}
.star.half {
	width: 15px;
}
.apt-detail {
	width: 300px;
	height: calc(100vh - 140px);
	overflow-y: scroll;
	padding: 5px;
	padding-top: 35px;
	position: relative;
	.wrapper > * {
		margin-bottom: 10px;
	}
	.image-wrapper {
		height: 140px;
		img {
			width: 100%;
			height: 100%;
		}
	}
	.header {
		position: fixed;
		height: 30px;
		display: flex;
		top: 140px;
		background: white;
		width: 270px;
		a {
			width: 20px;
		}
		div {
			width: calc(100% - 20px);
			text-align: center;
		}
	}
	.avg-rate {
		h2 {
			margin: 0;
		}
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}
	.desc {
		.md-layout-item {
			text-align: center;
		}
	}
	.deal-list {
		max-height: 200px;
		overflow-y: scroll;
		.deal-card {
			border: 1px solid black;
		}
	}
	.post-list {
		max-height: 200px;
		overflow-y: scroll;
		.post-card {
			.post-card-header {
				display: flex;
				justify-content: space-between;
				.writer {
					font-size: 10px;
				}
				.regdate {
					font-size: 6px;
				}
			}
			.post-card-content {
				font-size: 14px;
			}
			.post-card-bottom {
				display: flex;
				justify-content: flex-end;
				font-size: 11px;
				color: blue;
				.post-card-btn {
					padding: 0 3px;
					margin-right: 7px;
					cursor: pointer;
				}
			}
			.star {
				width: 14px;
				height: 14px;
				background-size: 15px 15px;
			}

			border-bottom: 1px solid rgba($color: #000000, $alpha: 0.3);
		}
	}
	.rating .rate_radio {
		position: relative;
		display: inline-block;
		z-index: 20;
		opacity: 0.001;
		width: 60px;
		height: 60px;
		background-color: #fff;
		cursor: pointer;
		vertical-align: top;
		display: none;
	}
	.rating .rate_radio + label {
		position: relative;
		display: inline-block;
		margin-left: -1px;
		z-index: 10;
		width: 20px;
		height: 20px;
		background-image: url('../../assets/img/starrate.png');
		background-repeat: no-repeat;
		background-size: 20px 20px;
		cursor: pointer;
		background-color: #f0f0f0;
	}
	.rating .rate_radio:checked + label {
		background-color: #ffa500;
	}
}
</style>
