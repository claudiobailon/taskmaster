package com.claudiobailon.taskmaster;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Task;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    public ArrayList<Task> tasks;
    public InteractWithTaskListener listener;


    public TaskAdapter(ArrayList<Task> tasks, InteractWithTaskListener listener) {
        this.tasks = tasks;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.fragment_task, parent, false);
        TaskViewHolder taskViewHolder = new TaskViewHolder(view);

        view.setOnClickListener((newView) -> {
            listener.listener(taskViewHolder.task);
        });

        return taskViewHolder;
    }

    public static interface InteractWithTaskListener {
        public void listener(Task task);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.task =tasks.get(position);

        TextView titleView = holder.itemView.findViewById(R.id.titleView);
//        TextView bodyView = holder.itemView.findViewById(R.id.bodyView);
        TextView stateView = holder.itemView.findViewById(R.id.stateView);

        titleView.setText(holder.task.title);
//        bodyView.setText(holder.task.body);
        stateView.setText(holder.task.state);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder{
        public Task task;
        public View itemView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }




}
