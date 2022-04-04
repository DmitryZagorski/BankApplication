package home.intexsoft.bank_application.dao;

import java.sql.SQLException;
import java.util.List;

abstract class DAO<Entity> {

    void create(Entity entity) throws SQLException {
    }

    Entity findById(Integer value) {
        return null;
    }

    List<Entity> findAll() {
        return null;
    }

    void update(Entity entity) {
    }

    void deleteByName(String entityName) {
    }

    Entity findByName(String value) {
        return null;
    }
}