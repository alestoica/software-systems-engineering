package repository;

import domain.Entity;

import java.util.Collection;

public interface IRepository<ID, E extends Entity<ID>> {
    E findOne(ID id);
    Collection<E> findAll();
    void add(E entity);
    void delete(ID id);
    void update(ID id, E entity);
}
