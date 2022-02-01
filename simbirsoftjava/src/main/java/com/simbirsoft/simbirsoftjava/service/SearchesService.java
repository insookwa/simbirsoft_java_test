package com.simbirsoft.simbirsoftjava.service;

import java.net.URL;
import java.util.List;

import com.simbirsoft.simbirsoftjava.model.SearchesModel;

public interface SearchesService {
	
	List<SearchesModel> получитьвесьпоиск();
	
	void сохранитьпоиск(SearchesModel поиски);
	
	SearchesModel получитьпоискпокоду(long код);
	
	void удалитьПоискпокоду(long код);
	
	String прочитатьвебстраницу(URL link);
	
}
