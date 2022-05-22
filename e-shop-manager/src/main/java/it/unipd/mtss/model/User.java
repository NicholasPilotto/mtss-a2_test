////////////////////////////////////////////////////////////////////
// [NICHOLAS] [PILOTTO] [1230237]
// [GIOVANNI] [GARDIN] [2010003]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

public class User {
    String username = "", firstname = "", lastname = "";

    public User(String username, String firstname, String lastname) {

        if(username == null || username.length() == 0) {
            throw new IllegalArgumentException("Nome utente non valido: è nullo o vuoto.");
        }
        if(firstname == null || firstname.length() == 0) {
            throw new IllegalArgumentException("Nome non valido: è nullo o vuoto.");
        }
        if(lastname == null || lastname.length() == 0) {
            throw new IllegalArgumentException("Cognome non valido: è nullo o vuoto.");
        }
        this.username  = username;
        this.firstname = firstname;
        this.lastname  = lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
