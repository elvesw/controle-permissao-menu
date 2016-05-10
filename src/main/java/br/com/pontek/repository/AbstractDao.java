package br.com.pontek.repository;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * Basic DAO interface.
 * @param <T> tipo do Entity type
 * @param <PK> chave principal do Entity
 */
public interface AbstractDao<T extends Serializable, PK extends Serializable> {
 
    T buscarEntity(PK id);
    List<T> listaDeEntitys();
    void salvarEntity(T entity);
    void atualizarEntity(T entity);
  
    void deletarEntity(T entity);
    void deletarEntityPorId(PK id);
    Long quantidadeDeEntitys();
    Integer ultimoIdDeEntity();
    void detachEntity(T entity);
    
    EntityManager getEm();
    T salvarReturnEntity(T entity);
    List<T> salvarAllEntitys(List<T> entitys);
    void deleteAllEntitys(List<T> entitys);
}
