package darshan.prajapati.n01584247.dp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainHandler extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // Retrieve the mode from SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.mysharedpref), MODE_PRIVATE);
            int mode = sharedPreferences.getInt(getString(R.string.mode), AppCompatDelegate.MODE_NIGHT_NO);
            AppCompatDelegate.setDefaultNightMode(mode);
        }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.darToggle) {
            // Handle Joh menu item click
            int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
            if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }

            // Save the mode in SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.mysharedpref), MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(getString(R.string.mode), AppCompatDelegate.getDefaultNightMode());
            editor.apply();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
