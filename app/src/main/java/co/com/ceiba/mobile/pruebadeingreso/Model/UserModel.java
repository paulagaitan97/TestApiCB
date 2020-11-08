package co.com.ceiba.mobile.pruebadeingreso.Model;

/**
 * Clase Encargada de crear un objeto por cada
 * Instancia correspondiente  en el
 * web services.
 * @Autor Paula Gaitán
 */
public class UserModel {
    //Declaración de atributos
    int id; // Identificador del usuario
    String name, email,phone; // Atributos de clase

    //Declaración e Inicialización del constructor de clase
    public UserModel(int id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    // Declaración e Inicialización de getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
