package com.example.pokemon.model;

import java.util.List;

public class PokemonResponse {
	private String next;
	private Object previous;
	private int count;
	private List<PokemonResultsItem> results;

	public String getNext(){
		return next;
	}

	public Object getPrevious(){
		return previous;
	}

	public int getCount(){
		return count;
	}

	public List<PokemonResultsItem> getResults(){
		return results;
	}
}