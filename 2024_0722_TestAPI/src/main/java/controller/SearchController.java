package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class SearchController {
	private static final String API_URL = "http://api.data.go.kr/openapi/tn_pubr_public_pet_loc_plc_api?serviceKey=l6%2BGwn%2F2gBKrIhyCjPzg0YculDtnU8GMR2Ksd%2BuMTaIiiBCpb9miCrTjfugwbrSgSe9giUa%2BWw1ad5qfbZequA%3D%3D&numOfRows=100&type=json";

    @GetMapping("/")
    public String index(Model model) {
        List<String> locations = List.of("서울특별시", "부산광역시", "인천광역시");
        List<String> types = List.of("카페", "펜션", "병원");

        model.addAttribute("locations", locations);
        model.addAttribute("types", types);

        return "index";
    }

    @GetMapping("/result")
    public String search(
            @RequestParam("location") String location,
            @RequestParam("type") String type,
            Model model) throws IOException {

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(API_URL, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(response);
        JsonNode itemsNode = rootNode.path("response").path("body").path("items");

        List<String> results = new ArrayList<>();
        if (itemsNode.isArray()) {
            for (JsonNode item : itemsNode) {
                String itemLocation = item.path("ctprvnNm").asText();
                String itemType = item.path("type").asText();
                if (itemLocation.equals(location) && itemType.equals(type)) {
                    results.add(item.path("name").asText());
                }
            }
        }

        model.addAttribute("location", location);
        model.addAttribute("type", type);
        model.addAttribute("results", results);

        return "result";
    }
}

