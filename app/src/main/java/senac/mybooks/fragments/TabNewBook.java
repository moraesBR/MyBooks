package senac.mybooks.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import senac.mybooks.R;
import senac.mybooks.models.Ebook;
import senac.mybooks.models.Genre;

import static senac.mybooks.MainActivity.ebookList;

public class TabNewBook extends Fragment {

    TextView txtTitulo, txtIsbn, txtUrlCapa, txtAutor, txtSinopse;
    Spinner spTipo;
    Button btnRegistrar;

    View.OnClickListener buttonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            try {
                if (valid()) {
                    throw new Exception("IBSN is empty");
                }
                Ebook ebook = new Ebook();

                ebook.setIsbn(txtIsbn.getText().toString());
                ebook.setGenre(Genre.valueOf(spTipo.getSelectedItem().toString().toUpperCase()));

                if(!txtTitulo.getText().toString().isEmpty())
                    ebook.setTitle(txtTitulo.getText().toString());
                else
                    ebook.setTitle("Sem título");

                if(!txtSinopse.getText().toString().isEmpty())
                    ebook.setResume(txtSinopse.getText().toString());
                else
                    ebook.setResume("Sem resumo");

                if(!txtAutor.getText().toString().isEmpty())
                    ebook.addAuthor(txtAutor.getText().toString());
                else
                    ebook.addAuthor(txtAutor.getText().toString());

                if (!txtUrlCapa.getText().toString().isEmpty())
                    ebook.setUrlImage(txtUrlCapa.getText().toString());
                else
                    ebook.setUrlImage("https://cdn.samsung.com/etc/designs/smg/global/imgs/support/cont/NO_IMG_600x600.png");

                ebookList.add(ebook);

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("ebook/"+
                        ebook.getGenre().toString() + "/" +
                        ebook.getIsbn());
                myRef.setValue(ebook);

                cleanscreen();
            } catch (Exception e) {
                //cleanscreen();
                e.printStackTrace();
            }
        }
    };

    private void cleanscreen() {
        txtTitulo.setText("");
        txtIsbn.setText("");
        txtUrlCapa.setText("");
        txtAutor.setText("");
        txtSinopse.setText("");
        spTipo.setSelection(0);
    }

    private boolean valid(){
        boolean test = false;
        if(txtIsbn.getText().toString().isEmpty()){
            test = true;
            txtIsbn.setError("");
        }
        return test;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newbook, container, false);

        txtTitulo = view.findViewById(R.id.titleEbook);
        txtIsbn = view.findViewById(R.id.isbnEbook);
        txtIsbn.setError("Obrigatório!");
        txtUrlCapa = view.findViewById(R.id.coverEbook);
        txtAutor = view.findViewById(R.id.authorsEbook);
        txtSinopse = view.findViewById(R.id.resumeEbook);
        spTipo = view.findViewById(R.id.genreEbook);
        btnRegistrar = view.findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(buttonOnClickListener);

        return view;
    }
}
