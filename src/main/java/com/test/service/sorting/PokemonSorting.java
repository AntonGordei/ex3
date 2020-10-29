package com.test.service.sorting;

import com.test.entity.Pokemon;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class PokemonSorting {
    public static List<Pokemon> sortByFieldName(String fieldName, List<Pokemon> pokemons) throws NoSuchFieldException {
        Field field = Pokemon.class.getDeclaredField(fieldName);

        field.setAccessible(true);

        return pokemons.stream()
                .sorted((first, second) -> {
                    try {
                        String a = field.get(first).toString();
                        String b = field.get(second).toString();
                        return a.compareTo(b);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Error", e);
                    }
                })
                .collect(Collectors.toList());
    }
}
