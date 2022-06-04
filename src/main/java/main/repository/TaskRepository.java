package main.repository;

import main.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {

    @Query(value = "select * from tasks join users u on u.id = tasks.user_id " +
            "where u.id = ?1", nativeQuery = true)
    public List<TaskEntity> getTasksListByUserId(Integer id);
}
