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
                로그인
              </h4>
              <md-field
                slot="inputs"
                class="md-form-group"
              >
                <md-icon>abc</md-icon>
                <label>아이디</label>
                <md-input
                  v-model="user.id"
                  required
                  type="text"
                />
              </md-field>
              <md-field
                slot="inputs"
                class="md-form-group"
              >
                <md-icon>lock_outline</md-icon>
                <label>비밀번호</label>
                <md-input
                  v-model="user.password"
                  required
                  type="password"
                />
              </md-field>
              <md-button
                slot="footer"
                class="md-simple md-success md-lg"
                @click="login"
              >
                로그인하기
              </md-button>
              <div
                slot="links"
                class="links"
              >
                <router-link :to="{ name: 'findId' }">
                  아이디 찾기
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
import { mapActions, mapState } from 'vuex';
const userStore = 'userStore';
export default {
	components: {
		LoginCard,
	},
	// bodyClass: 'login-page',
	props: {
		header: {
			type: String,
			default: require('@/assets/img/profile_city.jpg'),
		},
	},
	data() {
		return {
			user: {
				id: '',
				password: '',
			},
		};
	},

	computed: {
		...mapState(userStore, ['isLogin', 'isLoginError']),
		headerStyle() {
			return {
				backgroundImage: `url(${this.header})`,
			};
		},
	},
	methods: {
		...mapActions(userStore, ['userConfirm', 'getUserInfo']),
		check() {
			if (this.user.id.trim() === '') {
				alert('아이디를 입력하세요');
				return false;
			}
			if (this.user.password.trim() === '') {
				alert('비밀번호를 입력하세요');
				return false;
			}
			return true;
		},
		async login() {
			if (!this.check()) return;
			await this.userConfirm(this.user);
			const token = sessionStorage.getItem('access-token');
			if (this.isLogin) {
				await this.getUserInfo(token);
				this.$router.push({ name: 'home' });
			} else {
				alert('맞는 회원정보가 없습니다.');
			}
		},
	},
};
</script>

<style lang="css"></style>
