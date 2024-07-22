package com.banquemisr.challeng05.mapper;

import com.banquemisr.challeng05.dto.TaskDTO;
import com.banquemisr.challeng05.pojo.Task;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-22T04:46:13+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public TaskDTO mapDto(Task task) {
        if ( task == null ) {
            return null;
        }

        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setId( task.getId() );
        taskDTO.setTitle( task.getTitle() );
        taskDTO.setDescription( task.getDescription() );
        taskDTO.setStatus( task.getStatus() );
        taskDTO.setPriority( task.getPriority() );
        taskDTO.setDuedate( task.getDuedate() );

        return taskDTO;
    }

    @Override
    public List<TaskDTO> mapTaskDTOs(List<Task> tasks) {
        if ( tasks == null ) {
            return null;
        }

        List<TaskDTO> list = new ArrayList<TaskDTO>( tasks.size() );
        for ( Task task : tasks ) {
            list.add( mapDto( task ) );
        }

        return list;
    }

    @Override
    public Task mapTask(TaskDTO taskDTO) {
        if ( taskDTO == null ) {
            return null;
        }

        Task task = new Task();

        if ( taskDTO.getId() != null ) {
            task.setId( taskDTO.getId() );
        }
        task.setTitle( taskDTO.getTitle() );
        task.setDescription( taskDTO.getDescription() );
        task.setStatus( taskDTO.getStatus() );
        task.setPriority( taskDTO.getPriority() );
        task.setDuedate( taskDTO.getDuedate() );

        return task;
    }
}
