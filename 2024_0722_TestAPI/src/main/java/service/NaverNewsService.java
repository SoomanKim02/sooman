package service;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import vo.NewsItem;

@Service
public class NaverNewsService {

    private static final String CLIENT_ID = "bdzKmgHq95Avytzy4Bky";
    private static final String CLIENT_SECRET = "y4zkjz69ZH";
    private static final String API_URL = "https://openapi.naver.com/v1/search/news.json?query=";

    public NaverNewsService() {
    	System.out.println("==service()==");
	}
    public String searchNews(String query) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(API_URL + query);
        request.addHeader("X-Naver-Client-Id", CLIENT_ID);
        request.addHeader("X-Naver-Client-Secret", CLIENT_SECRET);

        HttpResponse response = httpClient.execute(request);
        String jsonResponse = EntityUtils.toString(response.getEntity());

        httpClient.close();
        return jsonResponse;
    }
	public static List<NewsItem> search(String query) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
