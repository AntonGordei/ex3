package com.test.crud;

import com.test.entity.Pokemon;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.*;

public class PokemonCRUD {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ogm-mongodb");
    private TransactionManager transactionManager = com.arjuna.ats.jta.TransactionManager.transactionManager();
    private EntityManager entityManager;;

    public void add(Pokemon pokemon) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        transactionManager.begin();
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.persist(pokemon);
        entityManager.close();
        transactionManager.commit();
    }

    public void update(Pokemon pokemon) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        transactionManager.begin();
        entityManager = entityManagerFactory.createEntityManager();
        Pokemon loadedEditor = entityManager.find(Pokemon.class, pokemon.getId());
        loadedEditor.setName(pokemon.getName());
        loadedEditor.setAttack(pokemon.getAttack());
        loadedEditor.setHp(pokemon.getHp());
        loadedEditor.setIsLegendary(pokemon.getIsLegendary());
        entityManager.close();
        transactionManager.commit();
    }

    public void delete(Pokemon pokemon) throws HeuristicRollbackException, RollbackException, HeuristicMixedException, SystemException, NotSupportedException {
        transactionManager.begin();
        entityManager = entityManagerFactory.createEntityManager();
        Pokemon loadedEditor = entityManager.find(Pokemon.class, pokemon.getId());
        entityManager.remove(loadedEditor);
        entityManager.close();
        transactionManager.commit();
    }
}
