package com.simbirsoft.simbirsoftjava.controller;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.simbirsoft.simbirsoftjava.model.SearchesModel;
import com.simbirsoft.simbirsoftjava.service.SearchesService;

@Controller
public class SearchesController {
	
	Logger logger = LoggerFactory.getLogger(SearchesController.class);
	
	@Autowired
	private SearchesService searchesservice;
	
	@GetMapping("/")
	public String домашняястраница(Model model) {
		model.addAttribute("search", new SearchesModel());
		logger.info("открытая страница поиска");
		return "index";
	}
	
	@PostMapping("/сохранитьпоиск")
	public String сохранитьпоиск(@ModelAttribute("поиски") SearchesModel поиски, Model model) {
		logger.info("Начат новый поиск");
		URL url = поиски.getLink();
		logger.info("Ссылка получена для обработки: URL = " + url);
		
		System.out.print(url);
		String raw = searchesservice.прочитатьвебстраницу(url);
		
		System.out.println(raw);
		поиски.setWords(raw);
		String[] counter = raw.split(" ");
		int countersie = counter.length;
		поиски.setTotalWords(countersie);
		searchesservice.сохранитьпоиск(поиски);
		model.addAttribute("search", поиски);
		logger.info("Обработка завершена ");
		
		return "result";
	}
	
	@GetMapping("/история")
	public String поискаДетали(Model model) {
		logger.info("Доступ к истории поиска был получен");
		model.addAttribute("listSearches", searchesservice.получитьвесьпоиск());
		return "history";
	}
	
	@GetMapping("/поискаДетали/{id}")
	public String поискаДетали(@PathVariable(value = "id") long id, Model model) {
		logger.info("Обработанные данные поиска");
		SearchesModel поиски = searchesservice.получитьпоискпокоду(id);
		model.addAttribute("search", поиски);
		
		return "words";
	}
	
}
