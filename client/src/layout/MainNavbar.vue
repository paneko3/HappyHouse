<template>
  <md-toolbar
    id="toolbar"
    md-elevation="0"
    class="md-transparent md-absolute"
    :class="extraNavClasses"
    :color-on-scroll="colorOnScroll"
  >
    <div class="md-toolbar-row md-collapse-lateral">
      <div class="md-toolbar-section-start">
        <router-link to="/">
          <h3 class="md-title">
            <i class="material-icons">home</i>HappyHouse
          </h3>
        </router-link>
      </div>
      <div class="md-toolbar-section-end">
        <md-button
          class="md-just-icon md-simple md-toolbar-toggle"
          :class="{ toggled: toggledClass }"
          @click="toggleNavbarMobile()"
        >
          <span class="icon-bar" />
          <span class="icon-bar" />
          <span class="icon-bar" />
        </md-button>

        <div class="md-collapse">
          <div class="md-collapse-wrapper">
            <mobile-menu nav-mobile-section-start="false">
              <!-- Here you can add your items from the section-start of your toolbar -->
            </mobile-menu>
            <md-list>
              <md-list-item to="/apt">
                <i class="fa fa-building" />
                <p>아파트</p>
                <md-tooltip md-direction="bottom">
                  아파트 거래 정보 페이지
                </md-tooltip>
              </md-list-item>
              <md-list-item to="/qna">
                <i class="fa fa-question" />
                <p>Q&A</p>
                <md-tooltip md-direction="bottom">
                  Q&A 페이지
                </md-tooltip>
              </md-list-item>
              <md-list-item to="/news">
                <i class="fa fa-newspaper" />
                <p>NEWS</p>
                <md-tooltip md-direction="bottom">
                  부동산 관련 뉴스 제공 페이지
                </md-tooltip>
              </md-list-item>
              <li class="md-list-item">
                <a
                  href="javascript:void(0)"
                  class="md-list-item-router md-list-item-container md-button-clean dropdown"
                >
                  <div class="md-list-item-content">
                    <drop-down direction="down">
                      <md-button
                        slot="title"
                        class="md-button md-button-link md-white md-simple dropdown-toggle"
                        data-toggle="dropdown"
                      >
                        <i class="material-icons">account_circle</i>
                        <p>User</p>
                      </md-button>
                      <ul
                        v-if="!checkUserInfo"
                        class="dropdown-menu dropdown-with-icons"
                      >
                        <li>
                          <router-link to="/login">
                            <i class="material-icons">login</i>
                            <p>로그인</p>
                          </router-link>
                        </li>
                        <li>
                          <router-link to="/join">
                            <i class="material-icons">person_add</i>
                            <p>회원가입</p>
                          </router-link>
                        </li>
                      </ul>
                      <ul
                        v-else
                        class="dropdown-menu dropdown-with-icons"
                      >
                        <li>
                          <a @click="doLogout">
                            <i class="material-icons">logout</i>
                            <p>로그아웃</p>
                          </a>
                        </li>
                        <li>
                          <router-link to="/mypage">
                            <i class="material-icons">manage_accounts</i>
                            <p>정보수정</p>
                          </router-link>
                        </li>
                      </ul>
                    </drop-down>
                  </div>
                </a>
              </li>
            </md-list>
          </div>
        </div>
      </div>
    </div>
  </md-toolbar>
</template>

<script>
let resizeTimeout;
function resizeThrottler(actualResizeHandler) {
	// ignore resize events as long as an actualResizeHandler execution is in the queue
	if (!resizeTimeout) {
		resizeTimeout = setTimeout(() => {
			resizeTimeout = null;
			actualResizeHandler();

			// The actualResizeHandler will execute at a rate of 15fps
		}, 66);
	}
}

import MobileMenu from '@/layout/MobileMenu';
import { mapGetters, mapMutations } from 'vuex';

const userStore = 'userStore';
export default {
	components: {
		MobileMenu,
	},
	props: {
		type: {
			type: String,
			default: 'white',
			validator(value) {
				return [
					'white',
					'default',
					'primary',
					'danger',
					'success',
					'warning',
					'info',
				].includes(value);
			},
		},
		colorOnScroll: {
			type: Number,
			default: 0,
		},
	},
	data() {
		return {
			extraNavClasses: '',
			toggledClass: false,
		};
	},
	computed: {
		...mapGetters(userStore, ['checkUserInfo']),
	},
	mounted() {
		document.addEventListener('scroll', this.scrollListener);
	},

	beforeDestroy() {
		document.removeEventListener('scroll', this.scrollListener);
	},
	methods: {
		...mapMutations(userStore, ['SET_IS_LOGIN', 'SET_USER_INFO']),
		doLogout() {
			this.SET_IS_LOGIN(false);
			this.SET_USER_INFO(null);
			sessionStorage.removeItem('access-token');
			if (this.$route.path != '/') this.$router.push({ name: 'home' });
		},
		bodyClick() {
			let bodyClick = document.getElementById('bodyClick');

			if (bodyClick === null) {
				let body = document.querySelector('body');
				let elem = document.createElement('div');
				elem.setAttribute('id', 'bodyClick');
				body.appendChild(elem);

				bodyClick = document.getElementById('bodyClick');
				bodyClick.addEventListener('click', this.toggleNavbarMobile);
			} else {
				bodyClick.remove();
			}
		},
		toggleNavbarMobile() {
			this.NavbarStore.showNavbar = !this.NavbarStore.showNavbar;
			this.toggledClass = !this.toggledClass;
			this.bodyClick();
		},
		handleScroll() {
			let scrollValue =
				document.body.scrollTop || document.documentElement.scrollTop;
			let navbarColor = document.getElementById('toolbar');
			this.currentScrollValue = scrollValue;
			if (this.colorOnScroll > 0 && scrollValue > this.colorOnScroll) {
				this.extraNavClasses = `md-${this.type}`;
				navbarColor.classList.remove('md-transparent');
			} else {
				if (this.extraNavClasses) {
					this.extraNavClasses = '';
					navbarColor.classList.add('md-transparent');
				}
			}
		},
		scrollListener() {
			resizeThrottler(this.handleScroll);
		},
		scrollToElement() {
			let element_id = document.getElementById('downloadSection');
			if (element_id) {
				element_id.scrollIntoView({ block: 'end', behavior: 'smooth' });
			}
		},
	},
	
};
</script>
