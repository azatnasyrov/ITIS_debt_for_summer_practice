package ru.itis.taxi.repositories;

import ru.itis.taxi.models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

public class UsersRepositoryFilesImpl implements UsersRepository {

    private final String fileName;
    private ArrayList<UUID> uuids;

    private static final Function<User, String> userToString = user ->
            user.getId()
                    + "|" + user.getFirstName()
                    + "|" + user.getLastName()
                    + "|" + user.getEmail()
                    + "|" + user.getPassword();

    public UsersRepositoryFilesImpl(String fileName) {
        this.fileName = fileName;
        this.uuids = new ArrayList<>();
    }

    @Override
    public List<User> findAll() {
        ArrayList<User> users = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String b = bufferedReader.readLine();
            String[] str;

            while (b != null) {
                str = b.split("\\|");
                users.add(new User(UUID.fromString(str[0]), str[1], str[2], str[3], str[4]));
                b = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return users;
    }

    @Override
    public void save(User entity) {
        while (entity.getId() == null) {
            UUID uuid = UUID.randomUUID();
            if (!uuids.contains(uuid)) {
                this.uuids.add(uuid);
                entity.setId(uuid);
            }
        }

        try (Writer writer = new FileWriter(fileName, true);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {

            String userAsString = userToString.apply(entity);
            bufferedWriter.write(userAsString);
            bufferedWriter.newLine();

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void update(User entity) {
        UUID uuid = entity.getId();
        List<User> users = findAll();

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, false))) {
            int count = 0;

            while (count < users.size()) {
                if (users.get(count).getId().equals(uuid)) {
                    String userAsString = userToString.apply(entity);
                    bufferedWriter.write(userAsString);
                    bufferedWriter.newLine();

                    count++;
                    continue;
                }
                String userAsString = userToString.apply(users.get(count));
                bufferedWriter.write(userAsString);
                bufferedWriter.newLine();

                count++;
            }

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void delete(User entity) {
        UUID uuid = entity.getId();
        deleteById(uuid);
    }

    @Override
    public void deleteById(UUID uuid) {
        List<User> users = findAll();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, false))) {
            int count = 0;

            while (count < users.size()) {
                if (users.get(count).getId().equals(uuid)) {
                    count++;
                    continue;
                }
                String userAsString = userToString.apply(users.get(count));
                bufferedWriter.write(userAsString);
                bufferedWriter.newLine();

                count++;
            }

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public User findByID(UUID id) {
        User user = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String b = bufferedReader.readLine();
            while (b != null && !b.contains(id.toString())) {
                b = bufferedReader.readLine();
            }
            if (b != null) {
                String[] strings = b.split("\\|");
                user = new User(UUID.fromString(strings[0]), strings[1], strings[2], strings[3], strings[4]);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return user;

    }
}
