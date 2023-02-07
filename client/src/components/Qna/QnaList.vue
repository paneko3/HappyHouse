<template>
  <div class="container">
    <md-table
      v-model="qnaList"
      md-card
      md-model-id="qna"
      @md-selected="onSelect"
    >
      <md-table-toolbar>
        <h1 class="md-headline">
          Q & A
        </h1>
        <md-button
          class="md-info md-simple"
          @click="movePage"
        >
          글 작성하기
        </md-button>
      </md-table-toolbar>

      <md-table-row
        slot="md-table-row"
        slot-scope="{ item }"
        md-selectable="single"
      >
        <md-table-cell
          md-label="NO"
          md-sort-by="no"
          md-numeric
        >
          {{ item.qnano }}
        </md-table-cell>
        <md-table-cell
          md-label="제목"
          md-sort-by="name"
        >
          {{ item.subject.slice(0, 50) }}
        </md-table-cell>
        <md-table-cell
          md-label="내용"
          md-sort-by="email"
        >
          {{ item.content.slice(0, 50) }}
        </md-table-cell>
        <md-table-cell
          md-label="작성자"
          md-sort-by="gender"
        >
          {{ item.id }}
        </md-table-cell>
        <md-table-cell
          md-label="작성시간"
          md-sort-by="title"
        >
          {{ item.regtime }}
        </md-table-cell>
      </md-table-row>
    </md-table>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
const qnaStore='qnaStore';
export default {
	computed: {
    ...mapState(qnaStore,['qnaList']),
	},
	created() {
		this.getQnas();
	},
	methods: {
    ...mapActions(qnaStore,['getQnas']),
		onSelect(item) {
			this.viewQna(item);
		},
		movePage() {
			this.$router.push({ name: 'qna-create' });
		},
		viewQna(qna) {
			this.$router.push({ name: 'qna-view', query: { qnano: qna.qnano } });
		},
	},
};
</script>

<style>
.md-table-row:hover {
	cursor: pointer;
}
.md-toolbar .md-button {
	align-self: flex-end;
	margin-left: auto;
}
.md-headline {
	color: rgb(0, 0, 0) !important;
	font-size: 30px;
}
.md-numeric {
	text-align: center !important;
}
.md-numeric .md-table-head-label {
	padding-left: 0 !important;
	padding-right: 0 !important;
	text-align: center !important;
}
.md-table .md-table-cell .md-table-cell-container {
	max-width: 20vw;
	text-overflow: ellipsis;
	overflow: hidden;
}
</style>
