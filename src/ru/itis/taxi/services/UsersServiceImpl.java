package ru.itis.taxi.services;

import ru.itis.taxi.dto.SignUpForm;
import ru.itis.taxi.models.User;
import ru.itis.taxi.repositories.UsersRepository;
import java.util.List;
import java.util.function.Function;

public class UsersServiceImpl implements UsersService{
    private final UsersRepository usersRepository;

    private final Function<SignUpForm, User> toUserMapper;

    public UsersServiceImpl(UsersRepository usersRepository, Function<SignUpForm, User> toUserMapper) {
        this.usersRepository = usersRepository;
        this.toUserMapper = toUserMapper;
    }

    @Override
    public void signUp(SignUpForm signUpForm) {
        User user = toUserMapper.apply(signUpForm);
        List<User> users = usersRepository.findAll();
        boolean flag = true;

        for (User us : users){
            if (us.getEmail().equals(user.getEmail())){
                flag = false;
                break;
            }
        }
        if (flag){
            usersRepository.save(user);
        }
    }

}
