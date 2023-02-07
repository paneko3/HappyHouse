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
                  required
                  type="text"
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
              <div
                slot="footer"
                class="footer"
              >
                <md-button
                  class="md-simple md-success md-lg"
                  @click="doFindPassword"
                >
                  비밀번호 찾기
                </md-button>
                <p>기존에 가입하신 이메일로 임시 비밀번호가 발송됩니다.</p>
              </div>
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
import { findPassword } from '@/server/user';
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
				id: '',
				name: '',
				email: '',
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
			if (this.user.id.trim() === '') {
				alert('아이디를 입력하세요');
				return false;
			}
			if (this.user.email.trim() === '') {
				alert('이메일을 입력하세요');
				return false;
			}
			return true;
		},
		async doFindPassword() {
			if (!this.check()) return;
			const res = await findPassword(this.user);
			if (res.data === 'success') {
				//비밀번호 찾기 성공 다이얼록 구현 필요
				alert('메일로 임시 비밀번호를 전송했습니다.');
			} else {
				//실패 다이얼로그
				alert('정보를 다시 확인해주세요');
			}
		},
	},
};
</script>

<style lang="scss">
.footer {
	display: flex !important;
	flex-direction: column;
	p {
		font-size: 50%;
	}
}
.links {
	width: 70%;
	display: flex !important;
	justify-content: space-between;
	font-size: 50%;
}
</style>
