package senac.mybooks.models;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseEbook {

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private List<Ebook> ebookList = new ArrayList<>();

    public FirebaseEbook(FirebaseDatabase database, String root) {
        this.database = database;
        this.myRef = database.getReference(root);
        this.ebookList = new ArrayList<>();
    }

    public List<Ebook> getEbookList(){
        return ebookList;
    }

    public void read(Context context, String msg) {

        if(!ebookList.isEmpty())
            ebookList.clear();

        final ProgressDialog progressDialog = new ProgressDialog(context);

        progressDialog.setMessage(msg);

        progressDialog.show();

        for (Genre g : Genre.values()) {
            myRef.child(g.toString()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Ebook ebook = ds.getValue(Ebook.class);
                        ebookList.add(ebook);
                    }

                    progressDialog.dismiss();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    progressDialog.dismiss();
                }
            });
        }

    }

    public List<Ebook> read(Context context, String msg, Genre genre) {

        if(!ebookList.isEmpty())
            ebookList.clear();

        final ProgressDialog progressDialog = new ProgressDialog(context);

        progressDialog.setMessage(msg);

        progressDialog.show();

        myRef.child(genre.toString()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Ebook ebook = ds.getValue(Ebook.class);
                        ebookList.add(ebook);
                    }

                    progressDialog.dismiss();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    progressDialog.dismiss();
                }
            });
        return ebookList;
    }

    public void insert(Ebook ebook) {
        myRef.child(ebook.getGenre().toString()).child(ebook.getIsbn()).setValue(ebook);
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("ebooks").child(ebook.getIsbn());
        //myRef.setValue(ebook);
    }

}
