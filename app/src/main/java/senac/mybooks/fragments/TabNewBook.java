package senac.mybooks.fragments;

import android.os.Bundle;
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
                if (txtIsbn.getText().toString().isEmpty()) {
                    throw new Exception("IBSN is empty");
                }
                Ebook ebook = new Ebook();
                ebook.setIsbn(txtIsbn.getText().toString());
                ebook.setTitle(txtTitulo.getText().toString());
                ebook.setGenre(Genre.valueOf(spTipo.getSelectedItem().toString().toUpperCase()));
                ebook.setResume(txtSinopse.getText().toString());
                ebook.addAuthor(txtAutor.getText().toString());
                ebook.setUrlImage(txtUrlCapa.getText().toString());
                ebookList.add(ebook);

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("ebook/"+
                        ebook.getGenre().toString() + "/" +
                        ebook.getIsbn());
                myRef.setValue(ebook);
                //cleanscreen();
                //getFragmentManager().beginTransaction().hide(getFragmentManager().findFragmentByTag("5")).show(getFragmentManager().findFragmentByTag("6")).commit();
            } catch (Exception e) {
                //cleanscreen();
                e.printStackTrace();
            }
            cleanscreen();
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newbook, container, false);
        //FragmentActivity fragmentActivity = getActivity();

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
