package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import service.NaverNewsService;
import vo.NewsItem;

@Controller
public class NaverNewsController {

	
	@Autowired
	NaverNewsService naverNewsService;
	
	
	
	public NaverNewsController() {
		// TODO Auto-generated constructor stub
		//System.out.println("newscontroller");
	}
	

	@RequestMapping("/search.do")
    public String getNews(@RequestParam("query") String query, Model model) {
        List<NewsItem> newsItems = NaverNewsService.searchJson(query);
        model.addAttribute("newsItems", newsItems);
        
        return "news/testview";
    }
}
	
	

	/*
	 * // search.do?query=
	 * 
	 * @RequestMapping(value="/search.do") public String searchNews(String query,
	 * Model model) {
	 * 
	 * System.out.println(query); List<NewsItem> list =
	 * NaverNewsService.searchJson(query);
	 * 
	 * System.out.println(list.size());
	 * 
	 * model.addAttribute("list",list);
	 * 
	 * return"news/testview"; }
	 */
	
	/*
	 * @RequestMapping("/search/news") public String searchNews(@RequestParam String
	 * query, Model model) throws Exception { // Call the service to fetch news
	 * items
	 * 
	 * String jsonResponse = naverNewsService.searchNews(query);
	 * 
	 * // Parse the JSON response ObjectMapper objectMapper = new ObjectMapper();
	 * JsonNode jsonNode = objectMapper.readTree(jsonResponse); JsonNode items =
	 * jsonNode.get("items");
	 * 
	 * List<NewsItem> list = new ArrayList<>();
	 * 
	 * for(int i =0; i<total; i++) { // jsonNode 를 jsonObject
	 * 
	 * list.add(newsItem); }
	 * 
	 * 
	 * 
	 * // Add news items and query to the model model.addAttribute("newsItems",
	 * list); model.addAttribute("query", query);
	 * 
	 * // Return the view name return "news/testview"; }
	 */
	
	