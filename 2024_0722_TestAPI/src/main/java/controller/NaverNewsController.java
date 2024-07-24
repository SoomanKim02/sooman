package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import service.NaverNewsService;
import vo.NewsItem;

@Controller
public class NaverNewsController {

    @Autowired
    NaverNewsService naverNewsService;
    
  
    public NaverNewsController() {
		// TODO Auto-generated constructor stub
    	System.out.println("==navernewscontroller==");
	}
    
    @RequestMapping("/search/news")
    public String searchNews(@RequestParam String query, Model model) throws Exception {
    	
    	List<NewsItem> newsItems = NaverNewsService.search(query);
    	
        String jsonResponse = naverNewsService.searchNews(query);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);
        JsonNode items = jsonNode.get("items");

        model.addAttribute("newsItems", items);
        model.addAttribute("query", query);
        return "news";
    }
    
    
    
}
