package ISS.database.dao;

import java.util.List;

public interface Dao<T> {

    void save(T object); //C + U
    T findFromTheEnd(int count); //R
    T findById(Long id); //R
    List<T> findAll(); //R
    void deleteById(Long id); //D

}
