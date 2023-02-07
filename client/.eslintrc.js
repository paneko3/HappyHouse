module.exports = {
	root: true,
	env: {
		node: true,
	},
	extends: ['plugin:vue/recommended', 'eslint:recommended'],
	rules: {
		'no-console': process.env.NODE_ENV === 'production' ? 'error' : 'off',
		'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off',
		'vue/no-use-v-if-with-v-for': 'off',
		'no-mixed-spaces-and-tabs': 0,
		'no-undef': 'off',
	},
	parserOptions: {
		parser: 'babel-eslint',
	},
};
