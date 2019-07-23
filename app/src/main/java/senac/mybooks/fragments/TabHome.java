package senac.mybooks.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import senac.mybooks.R;
import senac.mybooks.adapters.EbookAdapter;
import senac.mybooks.models.Ebook;
import senac.mybooks.models.FirebaseEbook;

/*public class TabHome extends Fragment  implements LoaderManager.LoaderCallbacks<List<Ebook>>{*/
public class TabHome extends Fragment {
    /*public static final int OPERATION_SEARCH_LOADER = 15;
    private ProgressBar loading;
    private LoaderManager loaderManager;*/

    private RecyclerView rvEbooks;
    private View view;
    private List<Ebook> ebookList;
    private FirebaseEbook firebaseEbook = new FirebaseEbook(FirebaseDatabase.getInstance(), "ebook");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        /*toolbar.setTitle(R.string.title_home);*/

        /*
        loading = view.findViewById(R.id.loading);
        loaderManager = getLoaderManager();
        */

        /*comentar*/
        firebaseEbook.read(view.getContext(), "Carregando...");
        ebookList = firebaseEbook.getEbookList();


        rvEbooks = view.findViewById(R.id.list_ebooks);
        rvEbooks.setHasFixedSize(true);
        rvEbooks.setLayoutManager(new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false));

        /*comentar*/
        rvEbooks.setAdapter(new EbookAdapter(ebookList, view.getContext()));

        return view;

    }

/*
    @NonNull
    @Override
    public Loader<List<Ebook>> onCreateLoader(int id, @Nullable Bundle args) {
        return new AsyncTaskLoader<List<Ebook>>(view.getContext()) {
            @Nullable
            @Override
            public List<Ebook> loadInBackground() {
                firebaseEbook.read(view.getContext(), "Carregando...");
                return firebaseEbook.getEbookList();
            }

            @Override
            protected void onStartLoading() {
                loading.setVisibility(View.VISIBLE);
                forceLoad();
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Ebook>> loader, List<Ebook> data) {
        loading.setVisibility(View.GONE);
        rvEbooks.setAdapter(new EbookAdapter(ebookList, view.getContext()));
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Ebook>> loader) {

    }


    @Override
    public void onResume() {
        super.onResume();

        Loader<List<Ebook>> loader = loaderManager.getLoader(OPERATION_SEARCH_LOADER);

        if (loader == null) {
            loaderManager.initLoader(OPERATION_SEARCH_LOADER, null, this);
        } else {
            loaderManager.restartLoader(OPERATION_SEARCH_LOADER, null, this);
        }
    }
*/
}
