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
                정보 수정
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
                <md-icon>abc</md-icon>
                <label>아이디</label>
                <md-input
                  v-model="user.id"
                  readonly
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
              <md-field
                slot="inputs"
                class="md-form-group"
              >
                <md-icon>check</md-icon>
                <label>비밀번호 확인</label>
                <md-input
                  v-model="passwordChk"
                  required
                  type="password"
                />
              </md-field>
              <md-field
                slot="inputs"
                class="md-form-group"
              >
                <md-icon>email</md-icon>
                <label>이메일</label>
                <md-input
                  v-model="user.email"
                  required
                  type="email"
                />
              </md-field>
              <md-field
                slot="inputs"
                class="md-form-group"
              >
                <md-icon>phone</md-icon>
                <label>연락처</label>
                <md-input
                  v-model="user.tel"
                  type="tel"
                />
              </md-field>
              <md-button
                slot="footer"
                class="md-simple md-success md-lg"
                @click="modifyUser"
              >
                정보 수정하기
              </md-button>
            </login-card>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { LoginCard } from '@/components';
import {  modifyUser } from '@/server/user';
import { mapGetters } from 'vuex';
const userStore = 'userStore';
export default {
	components: {
		LoginCard,
	},
	bodyClass: 'join-page',
	data() {
		return {
			passwordChk: '',
			user: {
				id: '',
				password: '',
				tel: '',
				email: '',
				name: '',
			},
		};
	},
	props: {
		header: {
			type: String,
			default: require('@/assets/img/profile_city.jpg'),
		},
	},
	computed: {
		...mapGetters(userStore, ['checkUserInfo']),
		headerStyle() {
			return {
				backgroundImage: `url(${this.header})`,
			};
		},
	},
	created() {
		this.user = this.checkUserInfo;
	},
	methods: {
		check() {
			if (this.user.name.trim() === '') {
				alert('이름을 입력하세요');
				return false;
			}
			if (this.user.id.trim() === '') {
				alert('아이디를 입력하세요');
				return false;
			}
			if (this.user.password.trim() === '') {
				alert('비밀번호를 입력하세요');
				return false;
			}
			if (this.user.email.trim() === '') {
				alert('이메일을 입력하세요');
				return false;
			}
			if (this.user.password !== this.passwordChk) {
				alert('입력한 비밀번호가 다릅니다.');
				return false;
			}
			return true;
		},
		async modifyUser() {
			if (!this.check()) return;
			await modifyUser(this.user.id, this.user);
			this.$router.push({ name: 'home' });
		},
	},
};
</script>

<style lang="css"></style>
