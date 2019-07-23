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

import senac.mybooks.R;
import senac.mybooks.adapters.EbookAdapter;

import static senac.mybooks.MainActivity.ebookList;

public class TabHome extends Fragment {
    private RecyclerView rvEbooks;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,container,false);
        rvEbooks = view.findViewById(R.id.list_ebooks);
        rvEbooks.setHasFixedSize(true);
        rvEbooks.setLayoutManager(new LinearLayoutManager(view.getContext(),RecyclerView.VERTICAL,false));
        rvEbooks.setAdapter(new EbookAdapter(ebookList,view.getContext()));

        return view;

    }


}
