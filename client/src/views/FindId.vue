<template>
  <div class="wrapper">
    <div
      class="section page-header header-filter"
      :style="headerStyle"
    >
      <div class="container">
        <div class="md-layout">
          <div
            class="md-layout-item md-size-33 md-small-size-66 md-xsmall-size-100 md-medium-size-40 mx-auto"
          >
            <login-card header-color="green">
              <h4
                slot="title"
                class="card-title"
              >
                아이디 찾기
              </h4>
              <md-field
                slot="inputs"
                class="md-form-group"
              >
                <md-icon>face</md-icon>
                <label>이름</label>
                <md-input
                  v-model="user.name"
                  required
                  type="text"
                />
              </md-field>
              <md-field
                slot="inputs"
                class="md-form-group"
              >
                <md-icon>phone</md-icon>
                <label>전화번호</label>
                <md-input
                  v-model="user.tel"
                  required
                  type="tel"
                />
              </md-field>
              <md-button
                slot="footer"
                class="md-simple md-success md-lg"
                @click="doFindId"
              >
                아이디 찾기
              </md-button>
              <div
                slot="links"
                class="links"
              >
                <router-link :to="{ name: 'login' }">
                  로그인 하기
                </router-link>
                <router-link :to="{ name: 'findPassword' }">
                  비밀번호 찾기
                </router-link>
              </div>
            </login-card>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { LoginCard } from '@/components';
import { findId } from '@/server/user';
export default {
	components: {
		LoginCard,
	},
	props: {
		header: {
			type: String,
			default: require('@/assets/img/profile_city.jpg'),
		},
	},
	data() {
		return {
			user: {
				name: '',
				tel: '',
			},
		};
	},

	computed: {
		headerStyle() {
			return {
				backgroundImage: `url(${this.header})`,
			};
		},
	},
	methods: {
		check() {
			if (this.user.name.trim() === '') {
				alert('이름을 입력하세요');
				return false;
			}
			if (this.user.tel.trim() === '') {
				alert('전화번호 입력하세요');
				return false;
			}
			return true;
		},
		async doFindId() {
			if (!this.check()) return;
			const res = await findId(this.user);

			//아이디 찾기 결과 부분 추가 구현 필요 다이얼로그 사용
			alert(res.data);
		},
	},
};
</script>

<style lang="scss">
.links {
	width: 70%;
	display: flex !important;
	justify-content: space-between;
	font-size: 50%;
}
</style>
