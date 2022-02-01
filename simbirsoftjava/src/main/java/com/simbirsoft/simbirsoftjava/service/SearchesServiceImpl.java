package com.simbirsoft.simbirsoftjava.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simbirsoft.simbirsoftjava.model.SearchesModel;
import com.simbirsoft.simbirsoftjava.repository.SearchesRepository;

@Service
public class SearchesServiceImpl implements SearchesService {
	
	Logger logger = LoggerFactory.getLogger(SearchesServiceImpl.class);
	
	@Override
	public String прочитатьвебстраницу(URL link) {
		String text = null;
		logger.info("URL-адрес был получен");
		try {
			Document document = Jsoup.parse(link, 5000);
			text = document.text();
			logger.info("Слова были извлечены");
			logger.info(text);
		}
		catch (MalformedURLException e) {
			
			logger.error(e.getMessage());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		catch (IOException e) {
			logger.error(e.getMessage());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		text = text.replace("^\\\\[|]$\"", "").replace("\"", "").replace(",", "").toUpperCase();
		List<String> items = Arrays.asList(text.split(" "));
		Map<String, Long> couterMap = items.stream()
		        .collect(Collectors.groupingBy(e -> e.toString(), Collectors.counting()));
		System.out.print(couterMap);
		logger.info("Processing Done " + couterMap.toString());
		return couterMap.toString();
	}
	
	@Autowired
	private SearchesRepository searchesrepository;
	
	@Override
	public List<SearchesModel> получитьвесьпоиск() {
		
		return searchesrepository.findAll();
	}
	
	@Override
	public void сохранитьпоиск(SearchesModel поиски) {
		this.searchesrepository.save(поиски);
		
	}
	
	@Override
	public SearchesModel получитьпоискпокоду(long код) {
		Optional<SearchesModel> optional = searchesrepository.findById(код);
		SearchesModel поиски = null;
		if (optional.isPresent()) {
			
			поиски = optional.get();
			logger.info("Search found " + поиски);
			
		} else {
			throw new RuntimeException("Поиск не найден по треске :: " + код);
		}
		
		return поиски;
	}
	
	@Override
	public void удалитьПоискпокоду(long код) {
		this.searchesrepository.deleteById(код);
	}
	
}
