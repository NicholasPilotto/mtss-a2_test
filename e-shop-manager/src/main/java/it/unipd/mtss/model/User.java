////////////////////////////////////////////////////////////////////
// [NICHOLAS] [PILOTTO] [1230237]
// [GIOVANNI] [GARDIN] [2010003]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

public class User {
    String username = "", firstname = "", lastname = "";

    public User(String username, String firstname, String lastname) {

        if(username == null) {
            throw new IllegalArgumentException("Il nome utente non può essere nullo.");
        }
        if(username.length() == 0) {
            throw new IllegalArgumentException("Il nome utente non può essere vuoto.");
        }
        if(firstname == null) {
            throw new IllegalArgumentException("Il nome non può essere nullo.");
        }
        if(firstname.length() == 0) {
            throw new IllegalArgumentException("Il nome non può essere vuoto.");
        }
        if(lastname == null) {
            throw new IllegalArgumentException("Il cognome non può essere nullo.");
        }
        if(lastname.length() == 0) {
            throw new IllegalArgumentException("Il cognome non può essere vuoto.");
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
