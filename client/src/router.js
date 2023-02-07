import Vue from 'vue';
import Router from 'vue-router';
import Index from './views/Index.vue';
import Login from './views/Login.vue';
import Join from './views/Join.vue';
import Mypage from './views/Mypage.vue';
import FindId from './views/FindId.vue';
import FindPassword from './views/FindPassword.vue';
import Apt from './views/Apt.vue';
import Qna from './views/Qna.vue';
import News from './views/News.vue';
import MainNavbar from './layout/MainNavbar.vue';
import MainFooter from './layout/MainFooter.vue';

import store from '@/store/index.js';

Vue.use(Router);

const onlyAuthUser = async (to, from, next) => {
	// console.log(store);
	const checkUserInfo = store.getters['userStore/checkUserInfo'];
	const getUserInfo = store._actions['userStore/getUserInfo'];
	let token = sessionStorage.getItem('access-token');
	if (checkUserInfo == null && token) {
		await getUserInfo(token);
	}
	if (checkUserInfo === null) {
		alert('로그인이 필요한 페이지입니다..');
		next({ name: 'login' });
	} else {
		next();
	}
};

export default new Router({
	mode: 'history',
	routes: [
		{
			path: '/',
			name: 'home',
			components: { default: Index, header: MainNavbar },
			props: {
				header: { colorOnScroll: 100 },
			},
		},
		{
			path: '/apt',
			components: { default: Apt, header: MainNavbar },
			props: {
				header: { colorOnScroll: 100 },
			},
			children: [
				{
					path: '',
					name: 'apt-list',
					component: () => import('@/components/Apt/Aptlist.vue'),
				},
				{
					path: ':aptcode',
					name: 'apt-detail',
					component: () => import('@/components/Apt/AptDetail.vue'),
				},
			],
		},
		{
			path: '/qna',
			components: { default: Qna, header: MainNavbar, footer: MainFooter },
			beforeEnter: onlyAuthUser,
			props: {
				header: { colorOnScroll: 100 },
				footer: { backgroundColor: 'black' },
			},
			children: [
				{
					path: '',
					name: 'qna-list',
					component: () => import('@/components/Qna/QnaList.vue'),
				},
				{
					path: 'create',
					name: 'qna-create',
					component: () => import('@/components/Qna/QnaCreate.vue'),
				},
				{
					path: 'view',
					name: 'qna-view',
					component: () => import('@/components/Qna/QnaView.vue'),
				},
				{
					path: 'modify',
					name: 'qna-modify',
					component: () => import('@/components/Qna/QnaModify.vue'),
				},
			],
		},
		{
			path: '/news',
			name: 'news',
			components: { default: News, header: MainNavbar, footer: MainFooter },
			props: {
				header: { colorOnScroll: 100 },
			},
		},
		{
			path: '/login',
			name: 'login',
			components: { default: Login, header: MainNavbar, footer: MainFooter },
			props: {
				header: { colorOnScroll: 100 },
			},
		},
		{
			path: '/join',
			name: 'join',
			components: { default: Join, header: MainNavbar, footer: MainFooter },
			props: {
				header: { colorOnScroll: 100 },
			},
		},
		{
			path: '/mypage',
			name: 'mypage',
			components: { default: Mypage, header: MainNavbar, footer: MainFooter },
			props: {
				header: { colorOnScroll: 100 },
			},
		},
		{
			path: '/findid',
			name: 'findId',
			components: { default: FindId, header: MainNavbar, footer: MainFooter },
			props: {
				header: { colorOnScroll: 100 },
			},
		},
		{
			path: '/findpassword',
			name: 'findPassword',
			components: {
				default: FindPassword,
				header: MainNavbar,
				footer: MainFooter,
			},
			props: {
				header: { colorOnScroll: 100 },
			},
		},
	],
	scrollBehavior: to => {
		if (to.hash) {
			return { selector: to.hash };
		} else {
			return { x: 0, y: 0 };
		}
	},
});
