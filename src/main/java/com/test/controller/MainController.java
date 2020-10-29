package com.test.controller;

import javax.annotation.Resource;

import com.test.crud.PokemonCRUD;
import com.test.dao.PokemonDAO;
import com.test.entity.Pokemon;
import com.test.service.sorting.PokemonSorting;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    private static Logger logger = Logger.getLogger(MainController.class);

    @Resource(name = "pokemonDAO")
    private PokemonDAO pokemonDAO;

    @Resource(name = "pokemonCRUD")
    private PokemonCRUD pokemonCRUD;

    @GetMapping({"/", "/list"})
    public String hello(Model model) {
        if (model.containsAttribute("pokemons")) {
            return "main";
        }

        List pokemons = new ArrayList<Pokemon>();

        try {
            pokemons = pokemonDAO.getAllPokemons();
        } catch (Exception ex) {
            logger.debug(ex);
        }

        model.addAttribute("pokemons", pokemons);
        return "main";
    }

    @GetMapping({"/add"})
    public String addPokemon(Model model) {
        logger.debug("Request to open the new user form page");
        model.addAttribute("pokemonAttr", new Pokemon());
        return "form";
    }

    @PostMapping({"/save"})
    public String save(@ModelAttribute("pokemonAttr") Pokemon pokemon) {
        try {
            if (pokemon.getId() != 0) {
                pokemonCRUD.update(pokemon);
            } else {
                pokemonCRUD.add(pokemon);
            }
        } catch (Exception ex) {
            logger.debug(ex);
        }

        return "redirect:list";
    }

    @GetMapping({"/edit"})
    public String editUser(@RequestParam(value="id", required=true) String id, Model model) {
        try {
            model.addAttribute("pokemonAttr", pokemonDAO.getPokemonById(Long.parseLong(id)));
        } catch (Exception ex) {
            logger.debug(ex);
        }
        return "form";
    }

    @GetMapping({"/delete"})
    public String delete(@RequestParam(value="id", required=true) String id, Model model) {
        try {
            pokemonCRUD.delete(pokemonDAO.getPokemonById(Long.parseLong(id)));
        } catch (Exception ex) {
            logger.debug(ex);
        }
        return "redirect:list";
    }

    @GetMapping({"/sort"})
    public RedirectView sort(@RequestParam(value="field", required=true) String field, RedirectAttributes redirectAttributes) {
        List pokemons = new ArrayList<Pokemon>();

        try {
            pokemons = PokemonSorting.sortByFieldName(field, pokemonDAO.getAllPokemons());
        } catch (Exception ex) {
            logger.debug(ex);
        }

        RedirectView redirectView = new RedirectView("/list", true);
        redirectAttributes.addFlashAttribute("pokemons", pokemons);

        return redirectView;
    }
}
