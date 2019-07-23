package senac.mybooks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import senac.mybooks.adapters.TabAdapter;
import senac.mybooks.fragments.TabHome;
import senac.mybooks.fragments.TabNewBook;
import senac.mybooks.models.Ebook;

public class MainActivity extends AppCompatActivity {
    public static List<Ebook> ebookList = new ArrayList<Ebook>();
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabAdapter tabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.pager);
        tabAdapter = new TabAdapter(getSupportFragmentManager());

        tabAdapter.addFragment(new TabHome(),"");
        tabAdapter.addFragment(new TabNewBook(),"");
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_menu_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_menu_newbook);



        /*TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setText("New Book"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pager);
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_menu_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_menu_newbook);*/



        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
}
