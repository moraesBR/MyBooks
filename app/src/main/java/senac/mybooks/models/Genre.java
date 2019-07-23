package senac.mybooks.models;

public enum Genre {
    ROMANCE("Romance"),
    NEGÓCIO("Negócio"),
    TÉCNICO("Técnico");

    private final String genre;

    private Genre(String genre){
        this.genre = genre;
    }

}
