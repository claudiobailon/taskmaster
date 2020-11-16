package com.claudiobailon.taskmaster;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.amplifyframework.datastore.generated.model.Task;

import java.util.List;

@Dao
public interface TaskDAO {
    @Insert
    public void saveTask(Task task);

//    @Query("SELECT * FROM task")
//    public List<Task> getAllTasks();
//
//    @Query("SELECT * FROM task ORDER BY id DESC")
//    public List<Task> getRecentTasks();


}
