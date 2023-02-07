import server from '@/server';
const URL = 'news/';
export async function getNews() {
	return await server.get(URL);
}
export async function updateNews() {
	return await server.put(URL);
}
