package co.com.ceiba.mobile.pruebadeingreso.Util;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.Model.UserModel;

/**
 * Clase Administrativa de la tabla usermoodel de
 * la base de datos
 */
@Dao
public interface UserDao {
    @Query("SELECT * FROM usermodel")
    List<UserModel> getAll();

    @Query("SELECT * FROM usermodel WHERE id = :id")
    UserModel findByUserId (int id);

    @Insert
    Long insertNewUser(UserModel userModels);

    @Query("SELECT COUNT(id) FROM usermodel")
    int numberRecords ();
}
