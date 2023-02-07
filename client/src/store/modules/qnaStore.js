import { getQnas, getQna } from '@/server/qna';

const qnaStore = {
    namespaced: true,
    state: {
        qnaList: [],
        selectedQna: {
            qnano:'',
            id:'',
            subject:'',
            content:'',
            regtime:'',
        },
    },
    getters: {
		getQnas(state) {
			return state.qnaList;
		},
		getQna(state) {
			return state.selectedQna;
		},
    },
    mutations: {
		setQnas(state, payload) {
			state.qnaList = payload;
		},
		setQna(state, payload) {
			state.selectedQna = payload;
		},
    },
    actions: {
		async getQnas(context) {
			const { data } = await getQnas();
			context.commit('setQnas', data);
		},
		async getQna(context, qnano) {
			const { data } = await getQna(qnano);
			context.commit('setQna', data);
		},
	},
}
export default qnaStore;
