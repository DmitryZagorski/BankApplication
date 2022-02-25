package home.intexsoft.bank_application.dima.dao;

public interface DAO<Entity, Key> {
    void create(Entity entity);
    Entity read(Key key);
    Entity readAll(Entity entity);
    void update(Entity entity);
    void delete(Entity entity);
}
