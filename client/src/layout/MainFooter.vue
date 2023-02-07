<template>
  <footer
    class="footer"
    :class="{ [`footer-${type}`]: type }"
    :data-background-color="backgroundColor"
  >
    <div class="container">
      <div class="copyright">
        &copy; 2022, made by 정재철 & 최진합 ( SSAFY 7기 광주 4반 )
      </div>
    </div>
  </footer>
</template>
<script>
export default {
	props: {
		backgroundColor: String,
		type: String,
	},
	created() {
		console.log(this.color);
	},
};
</script>
<style></style>
