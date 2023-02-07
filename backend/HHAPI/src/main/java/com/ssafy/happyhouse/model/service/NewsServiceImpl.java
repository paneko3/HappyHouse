package com.ssafy.happyhouse.model.service;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.dto.AnswerDto;
import com.ssafy.happyhouse.model.dto.NewsDto;
import com.ssafy.happyhouse.model.dto.QnADto;
import com.ssafy.happyhouse.model.mapper.AnswerMapper;
import com.ssafy.happyhouse.model.mapper.NewsMapper;


@Service
public class NewsServiceImpl implements NewsService {
	
    @Autowired
	private NewsMapper newsMapper;

    @Override
	public List<NewsDto> retrieveNews() {
		return newsMapper.selectNews();
	}

	@Override
	@Transactional
	public boolean updateNews() {
		String URL = "https://search.naver.com/search.naver?where=news&sm=tab_pge&query=%EB%B6%80%EB%8F%99%EC%82%B0&sort=0&photo=0&field=0&pd=0&ds=&de=&cluster_rank=180&mynews=0&office_type=0&office_section_code=0&news_office_checked=&nso=so:r,p:all,a:all&start=1";
		Document doc;
		try {
			doc = Jsoup.connect(URL).get();
			Elements titles = doc.select(".news_tit");
			Elements contents = doc.select("a.api_txt_lines");
			Elements regtimes = doc.select("span.info");
			Elements companies = doc.select("a.info");
			Elements urls = doc.select("a.news_tit");
			Elements imgsrcs = doc.select("img.thumb");
			int no=0;
			int i=0;
			int j=0;
			while(no<10) {
				String title = titles.get(no).text();
				String content = contents.get(no).ownText();
				String company = companies.get(j).text();
				if(company.contains("네이버뉴스")) {
					j++;
					continue;
				}
				String regtime = regtimes.get(i).text();
				if(regtime.contains("면")) {
					i++;
					continue;
				}
				String url =urls.get(no).attr("href");
				String imgsrc =imgsrcs.get(no*2+1).attr("src");
				NewsDto newsDto = new NewsDto(no+1, title, content, regtime, company.contains("언론사 선정")?company.substring(0, company.length()-6):company, url, imgsrc);
				newsMapper.updateNews(newsDto);
				System.out.println(newsDto);
				i++;
				j++;
				no++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}


}