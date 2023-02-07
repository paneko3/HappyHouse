<template>
  <div class="wrapper">
    <div
      class="container section"
      :style="headerStyle"
    />
    <div class="main">
      <div class="section ">
        <router-view />
      </div>
    </div>
  </div>
</template>

<script>
export default {
	props: {
		header: {
			type: String,
			default: require('@/assets/img/main/banner.png'),
		},
	},
	computed: {
		headerStyle() {
			return {
				backgroundImage: `url(${this.header})`,
				backgroundSize: '100% 200%',
			};
		},
	},
};
</script>

<style lang="scss"></style>
