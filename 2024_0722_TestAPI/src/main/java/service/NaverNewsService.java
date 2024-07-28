package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.MyOpenAPIKey;
import vo.NewsItem;

@Service
public class NaverNewsService {

	public NaverNewsService() {
		// TODO Auto-generated constructor stub
		//System.out.println("navernewsservice()");
	}
	@Autowired
	HttpServletRequest request;
	
	
	
public static List<NewsItem> searchJson(String query) {
		
		List<NewsItem> list = new ArrayList<NewsItem>();
		
		try {
			//query Encoding
			query = URLEncoder.encode(query,"utf-8"); 
			
			String str_url=String.format("https://openapi.naver.com/v1/search/news.json?query=%s",
																							  query);
			String naver_client_id = MyOpenAPIKey.Naver.CLIENT_ID;
			String naver_client_secret = MyOpenAPIKey.Naver.CLIENT_SECRET;
			
			URL url = new URL(str_url);
			HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
			urlConn.setRequestProperty("X-Naver-Client-Id", naver_client_id);
			urlConn.setRequestProperty("X-Naver-Client-Secret", naver_client_secret);
			urlConn.setRequestProperty("content-Type", "apllication/json");
			urlConn.connect();
			
			
			InputStreamReader isr = new InputStreamReader(urlConn.getInputStream(),"utf-8");
			BufferedReader br = new BufferedReader(isr);
			
			StringBuilder sb = new StringBuilder();
			
			while(true) {
				String data = br.readLine();
				if(data==null) break;
				sb.append(data);
				
			}// end while
			
//			System.out.println(sb.toString());
			
			JSONObject json = new JSONObject(sb.toString());
            JSONArray itemsArray = json.getJSONArray("items");
            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject news = itemsArray.getJSONObject(i);

                String link = news.getString("link");
                String title = news.getString("title");
                String description = news.getString("description");

                NewsItem vo = new NewsItem();
                vo.setTitle(title);
                vo.setLink(link);
                vo.setDescription(description);
                list.add(vo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
	} // end : getJson();