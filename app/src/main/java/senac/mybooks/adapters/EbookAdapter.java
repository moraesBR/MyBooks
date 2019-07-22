package senac.mybooks.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import senac.mybooks.R;
import senac.mybooks.models.Ebook;

public class EbookAdapter extends RecyclerView.Adapter<EbookAdapter.EbookViewHolder> {
    private List<Ebook> ebooks;
    private Context context;

    public EbookAdapter(List<Ebook> ebooks, Context context) {
        this.ebooks = ebooks;
        this.context = context;
    }

    @NonNull
    @Override
    public EbookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ebooks, parent, false);
        return new EbookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EbookViewHolder holder, int position) {
        Ebook ebook = ebooks.get(position);
        /* Link: https://stackoverflow.com/questions/2471935/how-to-load-an-imageview-by-url-in-android
         * answered May 26 '14 at 8:06, chiragkyada
         * 1. Picasso allows for hassle-free image loading in your application—often in one line of code!
         *    Use Gradle:
         *       dependencies {
         *           implementation 'com.squareup.picasso:picasso:2.71828'
         *       }
         *    For a simple view:
         *       Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(imageView);
         *
         * 2. Glide An image loading and caching library for Android focused on smooth scrolling
         *    Use Gradle:
         *       repositories {
         *           mavenCentral()
         *           google()
         *       }
         *       dependencies {
         *           implementation 'com.github.bumptech.glide:glide:4.7.1'
         *           annotationProcessor 'com.github.bumptech.glide:compiler:4.7.1'
         *       }
         *    For a simple view:
         *       Glide.with(this).load("http://i.imgur.com/DvpvklR.png").into(imageView);
         */
        Picasso.get().load(ebook.getUrlImage()).into(holder.ivImageBook);
        holder.tvTitleBook.setText(ebook.getTitle());
        holder.tvIsbnBook.setText(ebook.getIsbn());
        holder.tvGenreBook.setText(ebook.getGenre().toString());
        holder.tvResumeBook.setText(ebook.getResume());
        holder.rvAuthorsBook.setHasFixedSize(true);
        holder.rvAuthorsBook.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));
        holder.rvAuthorsBook.setAdapter(new AuthorAdapter(ebook.getAuthors(),context));
    }

    @Override
    public int getItemCount() {
        return ebooks.size();
    }

    class EbookViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImageBook;
        TextView tvTitleBook, tvGenreBook, tvIsbnBook, tvResumeBook;
        RecyclerView rvAuthorsBook;

        public EbookViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImageBook = itemView.findViewById(R.id.imageBook);
            tvTitleBook = itemView.findViewById(R.id.titleBook);
            tvGenreBook = itemView.findViewById(R.id.genreBook);
            tvIsbnBook = itemView.findViewById(R.id.isbnBook);
            tvResumeBook = itemView.findViewById(R.id.resumeBook);
            rvAuthorsBook = itemView.findViewById(R.id.authorsBook);
        }
    }
}

/**/