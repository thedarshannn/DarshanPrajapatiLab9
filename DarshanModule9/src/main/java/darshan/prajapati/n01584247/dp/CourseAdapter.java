package darshan.prajapati.n01584247.dp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    // creating a variable for array list and context.
    private final ArrayList<CourseModel> courseModalArrayList;

    // creating a constructor for our variables.
    public CourseAdapter(ArrayList<CourseModel> courseModalArrayList, Context context) {
        this.courseModalArrayList = courseModalArrayList;
    }

    @NonNull
    @Override
    public CourseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.ViewHolder holder, int position) {
        // setting data to our views of recycler view.
        CourseModel modal = courseModalArrayList.get(position);
        holder.courseNameTV.setText(modal.getCourseName());
        holder.courseDescTV.setText(modal.getCourseDescription());
    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return courseModalArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our views.
        private final TextView courseNameTV, courseDescTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // initializing our views with their ids.
            courseNameTV = itemView.findViewById(R.id.darTVCourseName);
            courseDescTV = itemView.findViewById(R.id.darTVCourseDescription);
        }
    }
}
