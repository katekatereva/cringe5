package dao;

import java.util.ArrayDeque;
import java.util.List;

public interface DAO<T> {

    void create(T t) throws CloneNotSupportedException;
    void update(int id, T t) throws CloneNotSupportedException;
    void delete(int id);
    void delete(T t);
    void clear();
    T get(int id) throws CloneNotSupportedException;
    ArrayDeque<T> getAll() throws CloneNotSupportedException;

}
