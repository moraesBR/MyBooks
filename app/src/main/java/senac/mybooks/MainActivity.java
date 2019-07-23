package senac.mybooks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import senac.mybooks.adapters.TabAdapter;
import senac.mybooks.fragments.TabBusiness;
import senac.mybooks.fragments.TabHome;
import senac.mybooks.fragments.TabNewBook;
import senac.mybooks.fragments.TabNovel;
import senac.mybooks.fragments.TabTechnical;
import senac.mybooks.models.Ebook;
import senac.mybooks.models.FirebaseEbook;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabAdapter tabAdapter;
    public static Toolbar toolbar;
    public static FirebaseEbook firebaseEbook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setActionBar(toolbar);

        firebaseEbook = new FirebaseEbook(FirebaseDatabase.getInstance(),"ebook");

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.pager);
        tabAdapter = new TabAdapter(getSupportFragmentManager());

        tabAdapter.addFragment(new TabHome(),"");
        tabAdapter.addFragment(new TabNovel(),"");
        tabAdapter.addFragment(new TabBusiness(),"");
        tabAdapter.addFragment(new TabTechnical(),"");
        tabAdapter.addFragment(new TabNewBook(),"");

        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_menu_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_menu_novels);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_menu_business);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_menu_technical);
        tabLayout.getTabAt(4).setIcon(R.drawable.ic_menu_newbook);

    }
}
