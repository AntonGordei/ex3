package com.test.dao;

import com.test.entity.Pokemon;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.*;
import java.util.List;

public class PokemonDAO {
    private EntityManagerFactory entityManagerFactory;
    private TransactionManager transactionManager;
    private EntityManager entityManager;

    public PokemonDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("ogm-mongodb");
        transactionManager = com.arjuna.ats.jta.TransactionManager.transactionManager();
    }

    public List<Pokemon> getAllPokemons() throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        transactionManager.begin();
        entityManager = entityManagerFactory.createEntityManager();
        List<Pokemon> pokemons = entityManager.createNativeQuery("db.Pokemon.find({})", Pokemon.class).getResultList();
        entityManager.close();
        transactionManager.commit();

        return pokemons;
    }

    public Pokemon getPokemonById(long id) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        transactionManager.begin();
        entityManager = entityManagerFactory.createEntityManager();
        Pokemon loadedPokemon = entityManager.find(Pokemon.class, id);
        entityManager.close();
        transactionManager.commit();

        return loadedPokemon;
    }

    public List<Pokemon> getFilteredPokemons(String fieldName, String value) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        transactionManager.begin();
        entityManager = entityManagerFactory.createEntityManager();
        List<Pokemon> pokemons = entityManager.createNativeQuery(String.format("db.Pokemon.find({%s: %s})", fieldName, value), Pokemon.class).getResultList();
        entityManager.close();
        transactionManager.commit();

        return pokemons;
    }
}
