package com.banquemisr.challeng05.mapper;

import com.banquemisr.challeng05.dto.TaskDTO;
import com.banquemisr.challeng05.pojo.Task;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-20T21:08:22+0300",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240524-2033, environment: Java 22.0.1 (Eclipse Adoptium)"
)
@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public TaskDTO mapDto(Task task) {
        if ( task == null ) {
            return null;
        }

        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setDescription( task.getDescription() );
        taskDTO.setDuedate( task.getDuedate() );
        taskDTO.setId( task.getId() );
        taskDTO.setPriority( task.getPriority() );
        taskDTO.setStatus( task.getStatus() );
        taskDTO.setTitle( task.getTitle() );

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

        task.setDescription( taskDTO.getDescription() );
        task.setDuedate( taskDTO.getDuedate() );
        if ( taskDTO.getId() != null ) {
            task.setId( taskDTO.getId() );
        }
        task.setPriority( taskDTO.getPriority() );
        task.setStatus( taskDTO.getStatus() );
        task.setTitle( taskDTO.getTitle() );

        return task;
    }
}
