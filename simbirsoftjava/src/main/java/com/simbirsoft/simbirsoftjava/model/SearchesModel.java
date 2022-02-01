package com.simbirsoft.simbirsoftjava.model;

import java.net.URL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "поиски")
public class SearchesModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "link")
	private URL link;
	
	@Column(name = "words")
	private String words;
	
	@Column(name = "totalWords")
	private long totalWords;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public URL getLink() {
		return link;
	}
	
	public void setLink(URL link) {
		this.link = link;
	}
	
	public String getWords() {
		return words;
	}
	
	public void setWords(String words) {
		this.words = words;
	}
	
	public long getTotalWords() {
		return totalWords;
	}
	
	public void setTotalWords(long totalWords) {
		this.totalWords = totalWords;
	}
	
}
