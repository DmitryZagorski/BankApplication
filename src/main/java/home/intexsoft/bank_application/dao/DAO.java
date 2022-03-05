package home.intexsoft.bank_application.dao;

import org.hibernate.query.Query;

public interface DAO<Entity> {

    void create(Entity entity);

    Entity read(Integer value);

    Query<Entity> readAll(Entity entity);

    void update(Entity entity);

    void delete(Entity entity);

    Entity findByName(String value);
}
