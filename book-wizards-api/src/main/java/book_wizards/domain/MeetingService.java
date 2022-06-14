package book_wizards.domain;

import book_wizards.data.MeetingJPARepository;
import book_wizards.models.Meeting;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeetingService {

  private final MeetingJPARepository repository;


  public MeetingService(MeetingJPARepository repository) {
    this.repository = repository;
  }

  public List<Meeting> findAll(){
    return repository.findAll();
  }

  public Meeting findById(int id){
    return repository.findById(id).orElse(null);
  }

  public Result<Meeting> add(Meeting meeting){
    Result<Meeting> result = validate(meeting);

    if(!result.isSuccess()){
      return result;
    }

    if(meeting.getMeetingId() != 0) {
      result.addMessage("Meeting id cannot be set for `add` operation", ResultType.INVALID);
      return result;
    }

    meeting = repository.save(meeting);
    result.setPayload(meeting);
    return result;
  }

  public Result<Meeting> update(Meeting meeting){
    Result<Meeting> result = validate(meeting);

    if (!result.isSuccess()) {
      return result;
    }

    if (meeting.getMeetingId() <= 0) {
      result.addMessage("id must be set for `update` operation", ResultType.INVALID);
      return result;
    }

    if (!repository.existsById(meeting.getMeetingId())) {
      String msg = String.format("Genre Id: %s, not found", meeting.getMeetingId());
      result.addMessage(msg, ResultType.NOT_FOUND);
    }

    meeting = repository.save(meeting);
    result.setPayload(meeting);

    return result;
  }

  public boolean deleteById(int id){

    if(repository.existsById(id)){
      repository.deleteById(id);
      return true;
    }else{
      return false;
    }

  }

  private Result<Meeting> validate(Meeting meeting){

    Result<Meeting> result = new Result<>();

    if(meeting == null){
      result.addMessage("Meeting cannot be null", ResultType.INVALID);
    }

    if (Validations.isNullOrBlank(meeting.getGroupName())) {
      result.addMessage("Group name is required!", ResultType.INVALID);
    }

    if(meeting.getBookId() == 0){
      result.addMessage("Book id is required!", ResultType.INVALID);
    }

    if(meeting.getOrganizerId() == 0){
      result.addMessage("Organizer Id is required", ResultType.INVALID);
    }

    if(meeting.getEndDateTime().isBefore(LocalDateTime.now())){
      result.addMessage("This meeting has been archived", ResultType.INVALID);
    }

    if(meeting.getStartDateTime().isBefore(LocalDateTime.now()) &&
      meeting.getEndDateTime().isAfter(LocalDateTime.now())){
      result.addMessage("This meeting is in progress. Cannot join", ResultType.INVALID);
    }


    return result;
  }
}
