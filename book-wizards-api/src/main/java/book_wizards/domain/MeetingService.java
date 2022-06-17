package book_wizards.domain;

import book_wizards.data.MeetingAttendeeRepository;
import book_wizards.data.MeetingJPARepository;
import book_wizards.models.Meeting;
import book_wizards.models.MeetingAttendee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class MeetingService {

  @Autowired
  private final MeetingJPARepository repository;

  @Autowired
  private final MeetingAttendeeRepository attendeeRepository;


  public MeetingService(MeetingJPARepository repository, MeetingAttendeeRepository attendeeRepository) {
    this.repository = repository;
    this.attendeeRepository = attendeeRepository;
  }

  public List<Meeting> findAll(){

    List<Meeting> meetings = repository.findAll();

    for(Meeting m : meetings){

      List<Integer> attendeesIDs = attendeeRepository.findAttendeesByMeetingId(m.getMeetingId());
      m.setMeetingAttendees(attendeesIDs);
    }

    return meetings;
  }


  public Meeting findById(int id){

    Meeting meeting = repository.findById(id).orElse(null);

    List<Integer> attendeesIDs = attendeeRepository.findAttendeesByMeetingId(id);
    meeting.setMeetingAttendees(attendeesIDs);

    return meeting;

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

    List<Integer> attendeesIDs = attendeeRepository.findAttendeesByMeetingId(meeting.getMeetingId());
    meeting.setMeetingAttendees(attendeesIDs);

    meeting = repository.save(meeting);
    result.setPayload(meeting);

    return result;
  }

  public Result<Meeting> removeUserFromMeeting(Meeting meeting, MeetingAttendee meetingAttendee){

    Result<Meeting> updatedMeeting = new Result<>();

    if(attendeeRepository.removeAttendeeFromMeeting(meetingAttendee)){
      updatedMeeting = update(meeting);
    }else{
      updatedMeeting.addMessage("That user is not signed up for this meeting", ResultType.INVALID);
    }

    return updatedMeeting;
  }

  public boolean deleteById(int id){

    if(repository.existsById(id)){
      attendeeRepository.deleteAllByMeetingId(id);
      repository.deleteById(id);
    }

    return repository.existsById(id);
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
