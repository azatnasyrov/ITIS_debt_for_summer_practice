package ru.itis.taxi.app;

import ru.itis.taxi.dto.SignUpForm;
import ru.itis.taxi.mappers.Mappers;
import ru.itis.taxi.models.User;
import ru.itis.taxi.repositories.UsersRepository;
import ru.itis.taxi.repositories.UsersRepositoryFilesImpl;
import ru.itis.taxi.services.UsersService;
import ru.itis.taxi.services.UsersServiceImpl;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        UsersRepository usersRepository = new UsersRepositoryFilesImpl("users.txt");
        UsersService usersService = new UsersServiceImpl(usersRepository, Mappers::signUpFormUser);

        //signUp test
        usersService.signUp(new SignUpForm("aznas2000@gmail.com", "givemeyourpassword",
                "Азат", "Насыров"));

        //findById test
        UUID uuid = UUID.fromString("121f9c39-5976-4e95-a1b6-71c5fb7cb5a2");
        System.out.println(usersRepository.findByID(uuid));

        //deteleById test
        UUID uuid1 = UUID.fromString("a354510b-235d-4e88-920b-597412f985c3");
        usersRepository.deleteById(uuid1);

        //delete test
        User user = new User(UUID.fromString("cff1b90d-4f83-4587-a5a1-5ba8a81dd1de"), "aznas2000@gmail.com", "givemeyourpassword", "Азат", "Насыров");
        usersRepository.delete(user);

        //update test
        User user1 = new User(UUID.fromString("6bd35c6d-f904-463d-9cb2-8a7d5d25accc"), "voidspirit2010@gmail.com", "dota2onelove", "Валерий", "Опаневич");
        usersRepository.update(user1);

        //findAll test
        System.out.println(usersRepository.findAll().toString());
    }
}
