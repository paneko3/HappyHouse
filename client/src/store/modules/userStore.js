import jwt_decode from 'jwt-decode';
import { login, findById } from '@/server/user';

const userStore = {
	namespaced: true,
	state: {
		isLogin: false,
		isLoginError: false,
		userInfo: null,
	},
	getters: {
		checkUserInfo: function(state) {
			return state.userInfo;
		},
	},
	mutations: {
		SET_IS_LOGIN: (state, isLogin) => (state.isLogin = isLogin),
		SET_IS_LOGIN_ERROR: (state, isLoginError) =>
			(state.isLoginError = isLoginError),
		SET_USER_INFO: (state, userInfo) => {
			state.isLogin = true;
			state.userInfo = userInfo;
		},
	},
	actions: {
		async userConfirm({ commit }, user) {
			const res = await login(user);
			if (res.data.message === 'success') {
				const token = res.data['access-token'];
				commit('SET_IS_LOGIN', true);
				commit('SET_IS_LOGIN_ERROR', false);
				sessionStorage.setItem('access-token', token);
			} else {
				commit('SET_IS_LOGIN', false);
				commit('SET_IS_LOGIN_ERROR', true);
			}
		},
		async getUserInfo({ commit }, token) {
			let decode_token = jwt_decode(token);
			const res = await findById(decode_token.id);
			if (res.data.message === 'success') {
				commit('SET_USER_INFO', res.data.userInfo);
			} else {
				console.log('유저 정보 없음!!');
			}
		},
	},
};

export default userStore;
