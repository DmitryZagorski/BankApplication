package home.intexsoft.bank_application.dima.dao;

import com.sun.istack.NotNull;
import home.intexsoft.bank_application.models.Bank;

public interface DAO<Entity, Key> {
    void create(Entity entity);
    Entity read(Key key);
    void update(Entity entity);
    void delete(Entity entity);

    void create(@NotNull Bank bank);

    Entity read(@NotNull String model);

    void update(@NotNull Bank engine);

    void delete(@NotNull Bank engine);
}
