package ru.itis.taxi.repositories;

import ru.itis.taxi.models.User;
import java.util.List;
import java.util.UUID;
public interface UsersRepository {
    List<User> findAll();
    void save(User user);
    void update(User user);
    void delete(User user);
    void deleteById(UUID id);
    User findByID(UUID id);

}
