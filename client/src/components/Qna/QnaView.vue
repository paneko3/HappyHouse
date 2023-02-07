<template>
  <md-content
    class="container md-elevation-8"
    md-card
  >
    <div
      class="md-layout-item md-size-66 md-xsmall-size-100 mx-auto text-center"
    >
      <h1 class="md-title">
        {{ qna.subject }}
      </h1>
      <div class="md-body-1">
        {{ qna.id }}
      </div>
      <div class="md-body-1">
        {{ qna.regtime }}
      </div>
      <div class="description text-center">
        <p>{{ qna.content }}</p>
      </div>
      <div class="bottom">
        <md-button
          v-if="userInfo.id === qna.id"
          class="md-info md-simple"
          @click="goModify"
        >
          글 수정
        </md-button>
        <md-button
          v-if="userInfo.id === qna.id"
          class="md-info md-simple"
          @click="removeQna"
        >
          글 삭제
        </md-button>
        <md-button
          class="md-info md-simple"
          @click="moveList"
        >
          글 목록
        </md-button>
      </div>
    </div>
  </md-content>
</template>

<script>
import { deleteQna } from '@/server/qna.js';
import { mapActions, mapState } from 'vuex';
const userStore = 'userStore';
const qnaStore='qnaStore';
export default {
	computed: {
		...mapState(userStore, ['userInfo']),
    ...mapState(qnaStore,['selectedQna']),
		qna() {
			return this.selectedQna;
		},
	},
	created() {
		this.getQna(this.$route.query.qnano)
	},
	methods: {
    ...mapActions(qnaStore,['getQna']),
		goModify() {
			this.$router.push(`./modify?qnano=${this.qna.qnano}`);
		},
		moveList() {
			this.$router.push('.');
		},
		async removeQna() {
			const res = await deleteQna(this.$route.query.qnano);
			if (res.data === 'success') {
				this.$router.push({ name: 'qna-list' });
				alert('삭제되었습니다');
			}
		},
	},
};
</script>

<style>
p {
	word-break: break-all;
}
</style>
