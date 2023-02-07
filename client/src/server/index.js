import axios from 'axios';
const SERVER_URL = 'http://localhost:9999/happyhouse/';
const ACCESS_TOKEN = sessionStorage.getItem('access-token');
export default axios.create({
	baseURL: SERVER_URL,
	headers: {
		'Content-type': 'application/json',
		'access-token': ACCESS_TOKEN,
	},
});
