package de.ait.usermanagment.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    // Konstruktoram ir parametra teksts, kas apraksta kļūdu
    public UserAlreadyExistsException(String message) {
        super(message); // Pārnodod ziņojumu vecākajai klasei
    }

    // Var pievienot papildu konstruktoru, kas pieņem cēloņa izņēmumu
    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause); // Pārnodod ziņojumu un cēloni vecākajai klasei
    }

    // Var pievienot papildu konstruktoru, kas pieņem tikai cēloņa izņēmumu
    public UserAlreadyExistsException(Throwable cause) {
        super(cause); // Pārnodod cēloni vecākajai klasei
    }

    // Definē serialVersionUID, kas palīdz nodrošināt serializācijas saderību
    private static final long serialVersionUID = 1L;
}
