package co.com.ceiba.mobile.pruebadeingreso.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase Encargada de crear un objeto por cada
 * Instancia correspondiente  en el
 * web services.
 * @Autor Paula Gaitán
 */
//Declara la tabla de la Base de datos
@Entity
// AutoGeneradores de metodos de acceso (get) y modificación (set)
@Getter
@Setter
//Declaración e Inicialización del constructor de clase
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
public class UserModel  implements Serializable {
    //Declaración de atributos
    @PrimaryKey
    int id; // Identificador del usuario
    @ColumnInfo(name = "name")
    String name;
    @ColumnInfo(name = "email")
    String email;
    @ColumnInfo(name = "phone")
    String phone;

}
