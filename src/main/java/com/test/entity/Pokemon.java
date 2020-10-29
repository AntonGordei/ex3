package com.test.entity;

import javax.persistence.*;

@Entity
@TableGenerator(name = "tab", initialValue = 1, allocationSize = 50)
public class Pokemon {
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Id
    private long id;

    private String name;
    private int hp;
    private int attack;
    private boolean isLegendary;

    public Pokemon() {
    }

    public Pokemon(String pokemonName, int pokemonHP, int pokemonAttack, boolean isPokemonLegendary) {
        this.name = pokemonName;
        this.hp = pokemonHP;
        this.attack = pokemonAttack;
        this.isLegendary = isPokemonLegendary;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public boolean getIsLegendary() {
        return isLegendary;
    }

    public void setIsLegendary(boolean legendary) {
        isLegendary = legendary;
    }
}
