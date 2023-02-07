import server from '@/server';
const URL = 'qna/';
export async function getQnas() {
	return await server.get(URL);
}

export async function getQna(qnaNo) {
	return await server.get(URL + qnaNo);
}

export async function postQna(qna) {
	return await server.post(URL, qna);
}

export async function deleteQna(qnaNo) {
	return await server.delete(URL + qnaNo);
}

export async function updateQna(qnaNo, qna) {
	return await server.put(URL + qnaNo, qna);
}
