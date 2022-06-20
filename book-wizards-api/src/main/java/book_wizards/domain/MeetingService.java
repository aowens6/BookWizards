package book_wizards.domain;

import book_wizards.data.AppUserRepository;
import book_wizards.data.BookJPARepository;
import book_wizards.data.MeetingAttendeeRepository;
import book_wizards.data.MeetingJPARepository;
import book_wizards.models.Meeting;
import book_wizards.models.MeetingAttendee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeetingService {

  @Autowired
  private final MeetingJPARepository repository;

  @Autowired
  private final MeetingAttendeeRepository attendeeRepository;

  @Autowired
  private final BookService bookService;

  @Autowired
  private final AppUserRepository appUserRepository;


  public MeetingService(MeetingJPARepository repository, MeetingAttendeeRepository attendeeRepository, BookJPARepository bookRepo, BookService bookService, AppUserRepository appUserRepository) {
    this.repository = repository;
    this.attendeeRepository = attendeeRepository;
    this.bookService = bookService;
    this.appUserRepository = appUserRepository;
  }

  public List<Meeting> findAll(){

    List<Meeting> meetings = repository.findAll();

    for(Meeting m : meetings){

      List<Integer> attendeesIDs = attendeeRepository.findAttendeesByMeetingId(m.getMeetingId());
      m.setMeetingAttendees(attendeesIDs);
      m.setOrganizer(appUserRepository.findById(m.getOrganizerId()));
      m.setBook(bookService.findById(m.getBookId()));
    }

    return meetings;
  }


  public Meeting findById(int id){

    Meeting meeting = repository.findById(id).orElse(null);

    List<Integer> attendeesIDs = attendeeRepository.findAttendeesByMeetingId(id);
    meeting.setMeetingAttendees(attendeesIDs);
    meeting.setOrganizer(appUserRepository.findById(meeting.getOrganizerId()));
    meeting.setBook(bookService.findById(meeting.getBookId()));

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

  public Result<Meeting> addAttendeeToMeeting(Meeting meeting, int attendeeId){

    List<Integer> allAttendees = attendeeRepository.findAttendeesByMeetingId(meeting.getMeetingId());

    MeetingAttendee meetingAttendee = new MeetingAttendee(meeting.getMeetingId(), attendeeId);

    Result<Meeting> updatedMeeting = new Result<>();

    for(int a : allAttendees){
      if(a == attendeeId){
        updatedMeeting.addMessage("That user is already signed up for this meeting", ResultType.INVALID);
      }
    }

    if(attendeeRepository.create(meetingAttendee)){
      updatedMeeting = update(meeting);
    }

    return updatedMeeting;
  }

  public Result<Meeting> removeUserFromMeeting(Meeting meeting, int attendeeId){

    MeetingAttendee meetingAttendee = new MeetingAttendee(meeting.getMeetingId(), attendeeId);

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
