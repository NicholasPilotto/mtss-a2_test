////////////////////////////////////////////////////////////////////
// [NICHOLAS] [PILOTTO] [1230237]
// [GIOVANNI] [GARDIN] [2010003]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

public class User {
    String username = "", firstname = "", lastname = "";
    int age;

    public User(String username, String firstname, String lastname, int age) {

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
        if(age < 0) {
            throw new IllegalArgumentException("L'età non può essere negativa.");
        }
        if(age == 0) {
            throw new IllegalArgumentException("L'età non può essere uguale a 0.");
        }
        this.username  = username;
        this.firstname = firstname;
        this.lastname  = lastname;
        this.age = age;
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

    public int getAge() {
        return age;
    }
}
