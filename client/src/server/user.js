import server from '@/server';
const URL = 'user';
export async function join(userInfo) {
	return await server.post(URL, userInfo);
}

export async function modifyUser(id, userInfo) {
	return await server.put(`${URL}/${id}`, userInfo);
}

export async function deleteUser(id) {
	return await server.delete(`${URL}/${id}`);
}

export async function login(userInfo) {
	console.log(userInfo.id);
	return await server.post(`${URL}/login`, userInfo);
}

export async function findId(userInfo) {
	return await server.post(`${URL}/findId`, userInfo);
}

export async function findPassword(userInfo) {
	return await server.post(`${URL}/findpass`, userInfo);
}

export async function findById(userId) {
	server.defaults.headers['access-token'] = sessionStorage.getItem(
		'access-token',
	);
	return await server.get(`${URL}/info/${userId}`);
}
