package senac.mybooks.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Ebook implements Parcelable {
    private String title;
    private String urlImage;
    private List<String> authors;
    private String resume;
    private Genre genre;
    private String isbn;
    private boolean selected = false;

    public Ebook() {
        this.authors = new ArrayList<>();
    }


    public String getTitle() {
        return title;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getResume() {
        return resume;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public void addAuthor(String author) {
        if (!author.isEmpty())
            this.authors.add(author);
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return  "\n" + title +
                "\n" +  urlImage +
                "\n" + authors.toString() +
                "\n" + resume +
                "\n" + genre.toString() +
                "\n" + isbn;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.urlImage);
        dest.writeStringList(this.authors);
        dest.writeString(this.resume);
        dest.writeInt(this.genre == null ? -1 : this.genre.ordinal());
        dest.writeString(this.isbn);
        dest.writeByte(this.selected ? (byte) 1 : (byte) 0);
    }

    protected Ebook(Parcel in) {
        this.title = in.readString();
        this.urlImage = in.readString();
        this.authors = in.createStringArrayList();
        this.resume = in.readString();
        int tmpGenre = in.readInt();
        this.genre = tmpGenre == -1 ? null : Genre.values()[tmpGenre];
        this.isbn = in.readString();
        this.selected = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Ebook> CREATOR = new Parcelable.Creator<Ebook>() {
        @Override
        public Ebook createFromParcel(Parcel source) {
            return new Ebook(source);
        }

        @Override
        public Ebook[] newArray(int size) {
            return new Ebook[size];
        }
    };
}

