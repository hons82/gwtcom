	package org.gwtcom.server;

import java.util.ArrayList;
import java.util.List;

import org.gwtcom.client.service.NewsService;
import org.gwtcom.shared.NewsDetail;
import org.springframework.security.access.annotation.Secured;

public class NewsServiceImpl implements NewsService {

	public NewsServiceImpl() {
	}
	
	@Secured("ROLE_ADMIN")
	public List<NewsDetail>  getPrivateNews() {
		ArrayList<NewsDetail> ret = getNewsList(13);
		return ret;
	}

	/**
	 * @return
	 */
	private ArrayList<NewsDetail> getNewsList(int num) {
		ArrayList<NewsDetail> ret = new ArrayList<NewsDetail>();
		for (int i = 0; i < num; i++) {
			ret.add(new NewsDetail(String.valueOf(i),"Name "+String.valueOf(i)));
		}
		return ret;
	}

	public List<NewsDetail> getPublicNews() {
		ArrayList<NewsDetail> ret = getNewsList(100);
		return ret;
	}

}
