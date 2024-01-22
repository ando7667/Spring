package org.ignatov.seminar2;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

 @Repository
// @Component
// @Service
// @Lazy - используется когда необходимо создать экземпляр класса непосредственно перед
//         его использованием, а не во время создания Bean (для тяжеловесных и редко
//         вызываемых объектов)
@Scope(ConfigurableListableBeanFactory.SCOPE_SINGLETON)  // используется по умолчанию
// @Scope(ConfigurableListableBeanFactory.SCOPE_PROTOTYPE) - для создания одноразовых бинов
public class UserRepository {
    private final List<User> users;

    public UserRepository() {
        this.users = new ArrayList<>();
        users.add(new User("Egor"));
        users.add(new User("Ivan"));
        users.add(new User("Elena"));
        users.add(new User("Oleg"));
        users.add(new User("Alex"));
    }

    public  List<User> getAll() {
        return List.copyOf(users);
    }

    public User getById(long id) {
        return users.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }

     public User getByName(String name) {
         return users.stream()
                 .filter(it -> Objects.equals(it.getName(), name))
                 .findFirst()
                 .orElse(null);
     }

 }
