<template>
  <div class="container">
    <h1 class="md-headline">
      Q & A 작성
    </h1>
    <div class="regist_form">
      <md-field>
        <label>제목</label>
        <md-input v-model="subject" />
      </md-field>
      <md-field>
        <label>내용</label>
        <md-textarea v-model="content" />
      </md-field>
      <div class="bottom">
        <md-button
          class="md-info md-simple"
          @click="checkValue"
        >
          글 등록
        </md-button>
        <md-button
          class="md-info md-simple"
          @click="moveList"
        >
          글 목록
        </md-button>
      </div>
    </div>
  </div>
</template>

<script>
import { postQna } from '@/server/qna.js';
import { mapState } from 'vuex';
const userStore = 'userStore';
export default {
	data() {
		return {
			subject: '',
			content: '',
		};
	},
	computed: {
		...mapState(userStore, ['userInfo']),
	},
	methods: {
		moveList() {
			this.$router.push('.');
		},
		checkValue() {
			//사용자 입력값 체크
			if (this.subject == '' || this.content == '')
				alert('내용을 모두 입력하세요.');
			else this.registQna();
		},
		registQna() {
			const qna = {
				subject: this.subject,
				content: this.content,
				id: this.userInfo.id,
			};
			postQna(qna);
			this.moveList();
		},
	},
};
</script>

<style>
.bottom {
	display: flex;
	justify-content: center;
}
</style>
