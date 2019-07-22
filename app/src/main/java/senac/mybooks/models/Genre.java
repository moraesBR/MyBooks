package senac.mybooks.models;

public enum Genre {
    NOVEL("novel"),
    BUSINESS("business"),
    TECHNICIAN("technician");

    private final String genre;

    private Genre(String genre){
        this.genre = genre;
    }

}
