package com.example.pokemon.controller;

import com.example.pokemon.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class PokemonController {
    @Autowired
    private PokemonService pokemonService;

    @GetMapping("/catch")
    public Map<String, Object> catchPokemon() {
        Map<String, Object> result = new HashMap<>();

        if (new Random().nextDouble() < 0.5) {
            result.put("success", true);
            result.put("message", "You caught the Pokémon!");
        } else {
            result.put("success", false);
            result.put("message", "The Pokémon escaped...");
        }
        return result;
    }

    @GetMapping("/release")
    public Map<String, Object> releasePokemon(){
        Map<String, Object> result = new HashMap<>();

        int number = new Random().nextInt(100) + 1;
        if (pokemonService.isPrime(number)) {
            result.put("success", true);
            result.put("message", "You release the pokemon");
        } else {
            result.put("success", false);
            result.put("message", "Pokemon can't release");
        }
        return result;
    }

    @PostMapping("/rename")
    public Map<String, Object> renamePokemon(@RequestParam String name) {
        Map<String, Object> result = new HashMap<>();
        try {
            String rename = pokemonService.renamePokemon(name);
            result.put("success", true);
            result.put("message", rename);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "An error occured");
        }

        return result;
    }

}
