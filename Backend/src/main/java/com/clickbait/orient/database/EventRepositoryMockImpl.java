package com.clickbait.orient.database;

import com.clickbait.orient.model.Event;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Mock implementation for the event repository. Will be deleted later
 */
public class EventRepositoryMockImpl implements EventRepository {

    // mock some events
    private final List<Event> events = Arrays.asList(
            new Event(),
            new Event(),
            new Event(),
            new Event(),
            new Event()
    );

    @Override
    public Optional<Event> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<Event> findAll() {
        return null;
    }

    @Override
    public <S extends Event> S save(S s) {
        return null;
    }

    @Override
    public <S extends Event> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Iterable<Event> findAllById(Iterable<String> iterable) {
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
    public void delete(Event event) {

    }

    @Override
    public void deleteAll(Iterable<? extends Event> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Event> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Event> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Event> S insert(S s) {
        return null;
    }

    @Override
    public <S extends Event> List<S> insert(Iterable<S> iterable) {
        return null;
    }

    @Override
    public <S extends Event> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Event> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Event> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Event> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Event> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Event> boolean exists(Example<S> example) {
        return false;
    }
}
