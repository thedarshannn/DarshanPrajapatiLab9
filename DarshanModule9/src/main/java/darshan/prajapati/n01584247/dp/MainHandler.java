// Darshan Prajapati (N01584247)
package darshan.prajapati.n01584247.dp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
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
        } else if (id == R.id.darSearch) {
            showSearchDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showSearchDialog() {
        // Create an AlertDialog to prompt the user to enter search phrase
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.search);
        final EditText input = new EditText(this);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton(R.string.search, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String searchPhrase = input.getText().toString().trim();
                if (!searchPhrase.isEmpty()) {
                    launchGoogleSearch(searchPhrase);
                } else {
                    Toast.makeText(MainHandler.this, getString(R.string.search_msg), Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Show the AlertDialog
        builder.show();
    }

    private void launchGoogleSearch(String searchPhrase) {

        // Create an Intent to perform a web search
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(getString(R.string.query), searchPhrase);
        startActivity(intent);

        // Hide the keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }
}
