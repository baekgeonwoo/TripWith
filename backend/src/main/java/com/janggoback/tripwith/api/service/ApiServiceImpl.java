package com.janggoback.tripwith.api.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.janggoback.tripwith.attraction.domain.Attraction;
import com.janggoback.tripwith.attraction.repository.AttractionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApiServiceImpl implements ApiService {
	@Value("${data-go.service-key}")
	private String serviceKey;
	private final AttractionRepository attractionRepository;

	private final Long sizePerPage = 20L;
	
	@Override
	public void loadAttractions(Long dataCnt) {
		log.info("serviceKey: " + serviceKey);
		
		Long endLoopCnt = dataCnt / sizePerPage;
		try {
				int pageNo = 1;
				while (true) {
					StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/B551011/KorService1/areaBasedList1");
		            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + URLEncoder.encode(serviceKey, "UTF-8"));
		            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode(Long.toString(sizePerPage), "UTF-8"));
		            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(Integer.toString(pageNo++), "UTF-8"));
		            urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8"));
		            urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8"));
		            urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
		            URL url = new URL(urlBuilder.toString());
		            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		            conn.setRequestMethod("GET");
		            conn.setRequestProperty("Content-type", "application/json");

		            System.out.println("Response code: " + conn.getResponseCode());
		            BufferedReader rd;
		            
		            // 데이터의 끝 테스트용
		            if (conn.getResponseCode() == 03 || pageNo >= endLoopCnt) {
		            	break;
		            }
		            
		            if (200 <= conn.getResponseCode() && conn.getResponseCode() <= 300) {
		                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		            } else {
		                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		            }
		            StringBuilder sb = new StringBuilder();
		            String line;
		            while ((line = rd.readLine()) != null) {
		                sb.append(line);
		            }
		            rd.close();
		            conn.disconnect();
		            
		            log.info("파싱시작");
		            String jsonStr = sb.toString();
		            log.info("Json String: " + jsonStr);
		            ObjectMapper mapper = new ObjectMapper();
		            
		            JsonNode jsonNode = mapper.readTree(jsonStr);
		            
		            JsonNode items = jsonNode.get("response").get("body").get("items").get("item");
		            
		             for (JsonNode item : items) {
		            	 // 상세정보 요청
		            	 String content = loadContent(item.get("contentid").asLong());
		            	 
		            	 Attraction attraction = Attraction.builder()
		            	 .address(item.get("addr1").textValue())
		                 .name(item.get("title").textValue())
		                 .img1(item.get("firstimage").textValue())
		                 .img2(item.get("firstimage2").textValue())
		                 .content(content)
		                 .areaCode(item.get("areacode").asLong())
		                 .sigunguCode(item.get("sigungucode").asLong())
		                 .contentTypeId(item.get("contenttypeid").asLong())
//		                 .modifiedTime(item.get("modifiedtime").textValue())
		                 .longitude(item.get("mapx").asDouble())
		                 .latitude(item.get("mapy").asDouble())
		                 .viewCount(0L)
		                 .build();

		            	 log.info("Attarction을 DB에 저장합니다. " + attraction.toString());
		            	 attractionRepository.save(attraction);
		             }
		            
				}
		} catch (Exception e) {
			log.info("공공데이터 요청 중 에러: " + e.getMessage());
		}
        
    }
	
	private String loadContent(Long contentId) throws Exception {
		StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/B551011/KorService1/detailCommon1");
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + URLEncoder.encode(serviceKey, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("20", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("contentId", "UTF-8") + "=" + URLEncoder.encode(Long.toString(contentId), "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("overviewYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8"));
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        
        
        if (200 <= conn.getResponseCode() && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        
        log.info("파싱시작");
        String jsonStr = sb.toString();
        log.info("Json String: " + jsonStr);
        ObjectMapper mapper = new ObjectMapper();
        
        JsonNode jsonNode = mapper.readTree(jsonStr);
        JsonNode items = jsonNode.get("response").get("body").get("items").get("item");
        
        return items.get(0).get("overview").textValue();
	}
}
