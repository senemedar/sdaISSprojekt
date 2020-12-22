package ISS.database.dao;

import java.util.List;

public interface Dao<T> {

    void save(String json); //C + U
    T findById(Long id); //R
    List<T> findAll(); //R
    void deleteById(Long id); //D

}
