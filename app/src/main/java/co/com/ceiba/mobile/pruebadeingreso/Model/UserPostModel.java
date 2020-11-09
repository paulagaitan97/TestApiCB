package co.com.ceiba.mobile.pruebadeingreso.Model;

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

// AutoGeneradores de metodos de acceso (get) y modificación (set)
@Getter
@Setter
//Declaración e Inicialización del constructor de clase
@NoArgsConstructor
@AllArgsConstructor (suppressConstructorProperties = true)
public class UserPostModel  implements Serializable {
    private  int userId;
    private  int id;
    private  String title;
    private  String body;
}
