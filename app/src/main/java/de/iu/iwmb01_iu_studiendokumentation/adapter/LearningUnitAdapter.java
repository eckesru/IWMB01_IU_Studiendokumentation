package de.iu.iwmb01_iu_studiendokumentation.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.iu.iwmb01_iu_studiendokumentation.R;
import de.iu.iwmb01_iu_studiendokumentation.model.LearningUnit;
import de.iu.iwmb01_iu_studiendokumentation.ui.CourseDetails;
import de.iu.iwmb01_iu_studiendokumentation.ui.LearningUnitDetails;

public class LearningUnitAdapter extends RecyclerView.Adapter<LearningUnitAdapter.LearningUnitViewHolder> {

    private ArrayList<LearningUnit> learningUnits;

    public LearningUnitAdapter(ArrayList<LearningUnit> learningUnits) {
        this.learningUnits = learningUnits;
    }

    @NonNull
    @Override
    public LearningUnitAdapter.LearningUnitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_learning_unit, parent, false);
        return new LearningUnitAdapter.LearningUnitViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LearningUnitAdapter.LearningUnitViewHolder holder, int position) {
        LearningUnit learningUnit = learningUnits.get(position);
        holder.titleTextView.setText(learningUnit.getLearningUnitTitle());

        String plannedLearningEffort= String.format(holder.itemView.getContext().getString(R.string.learning_effort_time_dp), learningUnit.getPlannedLearningEffortHours(), learningUnit.getPlannedLearningEffortMinutes());
        holder.plannedLearningEffortTextView.setText(plannedLearningEffort);

        // Der OnClickListener funktioniert so wie das onClick-Attribut bei den Buttons.
        // Hierbei beziehe ich mich jedoch auf die ImageView innerhalb des Items.
        holder.learningUnitDetailsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), LearningUnitDetails.class);
                intent.putExtra("LEARNING_UNIT_OBJECT", learningUnit);
                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return learningUnits.size();
    }
    public static class LearningUnitViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleTextView;
        private final TextView plannedLearningEffortTextView;
        private final ImageView learningUnitDetailsImageView;

        public LearningUnitViewHolder(View view) {
            super(view);

            titleTextView =  view.findViewById(R.id.learningUnitTitleTextView);
            plannedLearningEffortTextView = view.findViewById(R.id.learningUnitPlannedEffortTextView);
            learningUnitDetailsImageView = view.findViewById(R.id.itemLearningUnitDetailsImageView);
        }
    }
}
