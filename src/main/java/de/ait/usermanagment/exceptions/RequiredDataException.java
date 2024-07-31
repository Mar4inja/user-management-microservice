package de.ait.usermanagment.exceptions;

public class RequiredDataException extends RuntimeException {

    // Konstruktoram ir parametra teksts, kas apraksta kļūdu
    public RequiredDataException(String message) {
        super(message); // Pārnodod ziņojumu vecākajai klasei
    }

    // Var pievienot papildu konstruktors, kas pieņem cēloņa izņēmumu
    public RequiredDataException(String message, Throwable cause) {
        super(message, cause); // Pārnodod ziņojumu un cēloni vecākajai klasei
    }

    // Var pievienot papildu konstruktors, kas pieņem tikai cēloņa izņēmumu
    public RequiredDataException(Throwable cause) {
        super(cause); // Pārnodod cēloni vecākajai klasei
    }

    // Definē serialVersionUID, kas palīdz nodrošināt serializācijas saderību
    private static final long serialVersionUID = 1L;
}
