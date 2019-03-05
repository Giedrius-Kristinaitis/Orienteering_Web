package com.clickbait.orient.database;

import com.clickbait.orient.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

/**
 * Mock implementation of the user repository. Will be deleted later
 */
public class UserRepositoryMockImpl implements UserRepository {

    // mock some users for now
    private User[] users = new User[] {
            new User("id1", "le_email@email.com", "password", "QWERTY", "ASDFGH"),
            new User("id2", "karpis@gmail.com", "password", "Karpis", "Karsis"),
            new User("id3", "stotele@inbox.lt", "password", "Stoteles", "Darbininke"),
            new User("id4", "bulka@ktu.edu", "password", "Flex", "Tape")
    };

    @Override
    public Optional<User> findByEmail(String email) {
        for (User user: users) {
            if (user.getEmail().equals(email)) {
                return Optional.of(user);
            }
        }

        return Optional.ofNullable(null);
    }

    @Override
    public boolean existsByEmail(String email) {
        for (User user: users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public <S extends User> S save(S s) {
        return null;
    }

    @Override
    public <S extends User> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<User> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Iterable<User> findAllById(Iterable<String> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void deleteAll(Iterable<? extends User> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<User> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends User> S insert(S s) {
        return null;
    }

    @Override
    public <S extends User> List<S> insert(Iterable<S> iterable) {
        return null;
    }

    @Override
    public <S extends User> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends User> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends User> boolean exists(Example<S> example) {
        return false;
    }
}
