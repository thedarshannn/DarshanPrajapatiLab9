// Darshan Prajapati - n01584247
package darshan.prajapati.n01584247.dp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class PrajapatiActivity9 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.darToolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.darNavView);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.darFragmentContainer, new Darshan1Fragment()).commit();
            navigationView.setCheckedItem(R.id.darNavHome);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.darNavHome) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.darFragmentContainer, new Darshan1Fragment())
                    .commit();
        } else if (itemId == R.id.darNavSettings) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.darFragmentContainer, new Prajapati2Fragment())
                    .commit();
        }

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        MenuItem darMenuItem = menu.findItem(R.id.darToggle);
        MenuItem praMenuItem = menu.findItem(R.id.darSearch);

        SpannableString darText = new SpannableString(darMenuItem.getTitle());
        SpannableString praText = new SpannableString(praMenuItem.getTitle());
        darText.setSpan(new ForegroundColorSpan(Color.RED), 0, darText.length(), 0);
        praText.setSpan(new ForegroundColorSpan(Color.RED), 0, praText.length(), 0);
        darMenuItem.setTitle(darText);
        praMenuItem.setTitle(praText);

        return true;
    }
}