package co.com.ceiba.mobile.pruebadeingreso.Util;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import co.com.ceiba.mobile.pruebadeingreso.Model.UserModel;

@Database(entities = {UserModel.class}, version = 1, exportSchema = false)
public abstract class DataBase extends RoomDatabase {
    public  abstract  UserDao userDao();
}
