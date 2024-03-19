// Darshan Prajapati (N01584247)
package darshan.prajapati.n01584247.dp;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Prajapati2Fragment extends Fragment {
    private ToggleButton fileType;
    private EditText fileName, fileContents;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prajapati2, container, false);

        fileName = view.findViewById(R.id.darfragment_internalstorage_filename);
        fileContents = view.findViewById(R.id.darfragment_internalstorage_filecontents);

        fileType = view.findViewById(R.id.darfragment_internalstorage_filetype);
        fileType.setChecked(true);

        view.findViewById(R.id.darfragment_internalstorage_create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createFile(requireContext(), fileType.isChecked());
            }
        });

        view.findViewById(R.id.darfragment_internalstorage_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFile(requireContext(), fileType.isChecked());
            }
        });

        view.findViewById(R.id.darfragment_internalstorage_write).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeFile(requireContext(), fileType.isChecked());
            }
        });

        view.findViewById(R.id.darfragment_internalstorage_read).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFile(requireContext(), fileType.isChecked());
            }
        });

        return view;
    }

    private void createFile(Context context, boolean isPersistent) {
        File file;
        if (isPersistent) {
            file = new File(context.getFilesDir(), fileName.getText().toString());
        } else {
            file = new File(context.getCacheDir(), fileName.getText().toString());
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
                Toast.makeText(context, String.format("File %s has been created", fileName.getText().toString()), Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(context, String.format("File %s creation failed", fileName.getText().toString()), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, String.format("File %s already exists", fileName.getText().toString()), Toast.LENGTH_SHORT).show();
        }
    }

    private void writeFile(Context context, boolean isPersistent) {
        try {
            FileOutputStream fileOutputStream;
            if (isPersistent) {
                fileOutputStream = context.openFileOutput(fileName.getText().toString(), Context.MODE_PRIVATE);
            } else {
                File file = new File(context.getCacheDir(), fileName.getText().toString());
                fileOutputStream = new FileOutputStream(file);
            }
            fileOutputStream.write(fileContents.getText().toString().getBytes(Charset.forName("UTF-8")));
            Toast.makeText(context, String.format("Write to %s successful", fileName.getText().toString()), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, String.format("Write to file %s failed", fileName.getText().toString()), Toast.LENGTH_SHORT).show();
        }
    }

    private void readFile(Context context, boolean isPersistent) {
        try {
            FileInputStream fileInputStream;
            if (isPersistent) {
                fileInputStream = context.openFileInput(fileName.getText().toString());
            } else {
                File file = new File(context.getCacheDir(), fileName.getText().toString());
                fileInputStream = new FileInputStream(file);
            }

            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, Charset.forName("UTF-8"));
            List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
            fileContents.setText(TextUtils.join("\n", lines));
            Toast.makeText(context, String.format("Read from file %s successful", fileName.getText().toString()), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, String.format("Read from file %s failed", fileName.getText().toString()), Toast.LENGTH_SHORT).show();
            fileContents.setText("");

        }
    }

    private void deleteFile(Context context, boolean isPersistent) {
        File file;
        if (isPersistent) {
            file = new File(context.getFilesDir(), fileName.getText().toString());
        } else {
            file = new File(context.getCacheDir(), fileName.getText().toString());
        }
        if (file.exists()) {
            file.delete();
            Toast.makeText(context, String.format("File %s has been deleted", fileName.getText().toString()), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, String.format("File %s doesn't exist", fileName.getText().toString()), Toast.LENGTH_SHORT).show();
        }
    }
}
