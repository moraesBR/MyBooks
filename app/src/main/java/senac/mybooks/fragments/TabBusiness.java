package senac.mybooks.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import senac.mybooks.R;
import senac.mybooks.adapters.EbookAdapter;
import senac.mybooks.models.Ebook;
import senac.mybooks.models.FirebaseEbook;
import senac.mybooks.models.Genre;

import static senac.mybooks.MainActivity.toolbar;

public class TabBusiness extends Fragment {
    private RecyclerView rvEbooks;
    private View view;
    private List<Ebook> ebookList;
    private FirebaseEbook firebaseEbook = new FirebaseEbook(FirebaseDatabase.getInstance(),"ebook");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_business,container,false);
        /*toolbar.setTitle(R.string.title_negocios);*/

        firebaseEbook.read(view.getContext(), "Carregando...", Genre.NEGÓCIO);
        ebookList = firebaseEbook.getEbookList();
        rvEbooks = view.findViewById(R.id.list_business_ebooks);
        rvEbooks.setHasFixedSize(true);
        rvEbooks.setLayoutManager(new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false));
        rvEbooks.setAdapter(new EbookAdapter(ebookList, view.getContext()));

        return view;
    }
}