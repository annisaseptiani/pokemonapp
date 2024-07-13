package com.example.pokemon.service;

import com.example.pokemon.model.PokemonResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PokemonService {
    private Map<String, Integer> pokemonFibIndex = new HashMap<>();


    public String renamePokemon(String originalName) {
        int currentIndex = pokemonFibIndex.getOrDefault(originalName, -1) + 1;
        pokemonFibIndex.put(originalName, currentIndex);
        return originalName + "-" + fibonacci(currentIndex);
    }

    private int fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public Integer getPrimeNumber(int start) {
        int num = start;
        while (!isPrime(num)) {
            num++;
        }
        return num;
    }

}
