// Darshan Prajapati (N01584247)
package darshan.prajapati.n01584247.dp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Darshan1Fragment extends Fragment {

    private EditText courseNameEdt, courseDescEdt;
    private Button addBtn, saveBtn, deleteBtn;
    private RecyclerView courseRV;

    private CourseAdapter adapter;
    private ArrayList<CourseModel> courseModalArrayList;

    public Darshan1Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_darshan1, container, false);

        // initializing UI components
        courseNameEdt = view.findViewById(R.id.darEdtCourseName);
        courseDescEdt = view.findViewById(R.id.darEdtCourseDescription);
        addBtn = view.findViewById(R.id.darBtnAdd);
        saveBtn = view.findViewById(R.id.darBtnSave);
        deleteBtn = view.findViewById(R.id.darBtnDelete);
        courseRV = view.findViewById(R.id.darRVCourses);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadData();
        buildRecyclerView();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                courseModalArrayList.add(new CourseModel(courseNameEdt.getText().toString(), courseDescEdt.getText().toString()));
                adapter.notifyItemInserted(courseModalArrayList.size());

                // Clear the EditText fields after adding
                courseNameEdt.setText(R.string.clear_field);
                courseDescEdt.setText(R.string.clear_field);
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });


        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                if (!courseModalArrayList.isEmpty()) {
                    courseModalArrayList.clear();
                    adapter.notifyDataSetChanged();
                    deleteData();
                    Toast.makeText(getActivity(), getString(R.string.delete_msg), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), getString(R.string.no_data_to_delete), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void buildRecyclerView() {
        adapter = new CourseAdapter(courseModalArrayList, getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        courseRV.setHasFixedSize(true);
        courseRV.setLayoutManager(manager);
        courseRV.setAdapter(adapter);
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.shared_preferences), Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(getString(R.string.courses), null);
        Type type = new TypeToken<ArrayList<CourseModel>>() {}.getType();
        courseModalArrayList = gson.fromJson(json, type);
        if (courseModalArrayList == null) {
            courseModalArrayList = new ArrayList<>();
        }
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.shared_preferences), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(courseModalArrayList);
        editor.putString(getString(R.string.courses), json);
        editor.apply();
        Toast.makeText(getActivity(), getString(R.string.saved_msg), Toast.LENGTH_SHORT).show();
    }

    private void deleteData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.shared_preferences), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(getString(R.string.courses));
        editor.apply();
    }
}