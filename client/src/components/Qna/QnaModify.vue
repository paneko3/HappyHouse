<template>
  <md-content
    class="container md-elevation-8"
    md-card
  >
    <h1 class="md-headline">
      Q & A 수정
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
          글 수정
        </md-button>
        <md-button
          class="md-info md-simple"
          @click="movePage"
        >
          글 목록
        </md-button>
        <md-button
          class="md-info md-simple"
          @click="goBack"
        >
          돌아가기
        </md-button>
      </div>
    </div>
  </md-content>
</template>

<script>
import { updateQna } from '@/server/qna.js';
import { mapActions, mapState } from 'vuex';
const qnaStore='qnaStore';
export default {
	data() {
		return {
			qnano: this.selectedQna.qnano,
			id: this.selectedQna.id,
			subject: this.selectedQna.subject,
			content: this.selectedQna.content,
			regtime: this.selectedQna.regtime,
		};
	},
	computed: {
    	...mapState(qnaStore,['selectedQna']),
	},
	created() {
		this.getQna(this.$route.query.qnano)
	},
	methods: {
		...mapActions(qnaStore,['getQna']),
		goBack() {
			this.$router.push(`./view?qnano=${this.qnano}`);
		},
		checkValue() {
			//사용자 입력값 체크
			let err = true;
			let msg = '';
			!this.subject &&
				((msg = '제목 입력해주세요'),
				(err = false),
				this.$refs.subject.focus());
			!this.content &&
				((msg = '내용 입력해주세요'),
				(err = false),
				this.$refs.content.focus());
			//라이브 자료 참고. 더 상세하게 입력값 체크해도 무관
			if (!err) alert(msg);
			else this.modifyBook();
		},
		async modifyBook() {
			const qna = {
				qnano: this.qnano,
				subject: this.subject,
				content: this.content,
				id: this.id,
			};
			const res = await updateQna(this.$route.query.qnano, qna);
			console.log(res);
			alert('수정되었습니다.');
			this.movePage();
		},
		movePage() {
			this.$router.push({ name: 'qna-list' });
		},
	},
};
</script>

<style></style>
