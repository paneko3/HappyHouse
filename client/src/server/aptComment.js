import server from '@/server';
const URL = 'aptcomment/';

export async function getComments(aptCode) {
	return await server.get(URL + aptCode);
}
export async function postComment(comment) {
	return await server.post(URL, comment);
}
export async function modifyComment(commentNo) {
	return await server.put(URL + commentNo);
}
export async function deleteComment(commentNo) {
	return await server.delete(URL + commentNo);
}
