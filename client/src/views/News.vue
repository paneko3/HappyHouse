<template>
  <div class="wrapper">
    <div
      class="container section"
      :style="headerStyle"
    />
    <div class="main">
      <div class="section">
        <div class="container">
          <md-table md-card>
            <md-table-toolbar>
              <h1 class="md-headline">
                NEWS
              </h1>
            </md-table-toolbar>
            <div
              v-for="(item, idx) in newsList"
              :key="idx"
              class="news"
            >
              <div class="img-wrapper">
                <img
                  :src="item.imgsrc"
                  :alt="item.company"
                >
              </div>
              <div class="content">
                <a :href="item.url">{{ item.title }}</a>

                <div class="">
                  {{ item.content }}
                </div>
                <div class="news-footer">
                  {{ item.company }}
                  {{ item.regtime }}
                </div>
              </div>
            </div>
          </md-table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getNews, updateNews } from '@/server/news';
export default {
	props: {
		header: {
			type: String,
			default: require('@/assets/img/main/banner.png'),
		},
	},
	data() {
		return {
			newsList: [],
		};
	},
	computed: {
		headerStyle() {
			return {
				backgroundImage: `url(${this.header})`,
				backgroundSize: '100% 200%',
			};
		},
	},
	created() {
		const fetchNews = async () => {
			const res = await getNews();
			this.newsList = res.data;
			console.log(res.data);
		};
		const doUpdateNews = async () => {
			await updateNews();
		};
		doUpdateNews();
		fetchNews();
	},
};
</script>

<style lang="scss">
.md-headline {
	color: black;
}
.news {
	padding: 0 10px;
	display: flex;
	flex-wrap: wrap;
	height: 150px;
	a {
		color: blue !important;
	}
	.img-wrapper {
		width: 120px;
		height: 120px;
		display: inline;
		margin-right: 10px;
		img {
			width: 120px;
			height: 120px;
		}
	}
	.content {
		width: calc(100% - 130px);
		display: inline-block;
	}
	.news-footer {
		display: flex;
		justify-content: flex-end;
		font-size: 14px;
	}
}
</style>
