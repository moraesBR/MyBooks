package senac.mybooks.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import senac.mybooks.R;

public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.AuthorViewHolder> {
    private List<String> authors;
    private Context context;

    public AuthorAdapter(List<String> authors, Context context) {
        this.authors = authors;
        this.context = context;
    }

    @NonNull
    @Override
    public AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_author,parent,false);
        return new AuthorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorViewHolder holder, int position) {
        holder.tvNameAuthor.setText(authors.get(position));
    }

    @Override
    public int getItemCount() {
        return authors.size();
    }


    class AuthorViewHolder extends RecyclerView.ViewHolder{
        TextView tvNameAuthor;
        public AuthorViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameAuthor = itemView.findViewById(R.id.nameAuthor);
        }
    }
}
