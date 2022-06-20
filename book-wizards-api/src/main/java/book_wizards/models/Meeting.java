package book_wizards.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Meeting {

  @Id
  private int meetingId;
  private String groupName;
  private String description;
  private int bookId;
  private int organizerId;
  private LocalDateTime startDateTime;
  private LocalDateTime endDateTime;

  @Transient
  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  private List<Integer> meetingAttendeeIDs;

  @Transient
  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  private Book book;

  @Transient
  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  private PublicUser organizer;


  public Meeting(){}

  public Meeting(int meetingId, String groupName, String description, int bookId, int organizerId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
    this.meetingId = meetingId;
    this.groupName = groupName;
    this.description = description;
    this.bookId = bookId;
    this.organizerId = organizerId;
    this.startDateTime = startDateTime;
    this.endDateTime = endDateTime;
  }

  public int getMeetingId() {
    return meetingId;
  }

  public void setMeetingId(int meetingId) {
    this.meetingId = meetingId;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getBookId() {
    return bookId;
  }

  public void setBookId(int bookId) {
    this.bookId = bookId;
  }

  public int getOrganizerId() {
    return organizerId;
  }

  public void setOrganizerId(int organizerId) {
    this.organizerId = organizerId;
  }

  public LocalDateTime getStartDateTime() {
    return startDateTime;
  }

  public void setStartDateTime(LocalDateTime startDateTime) {
    this.startDateTime = startDateTime;
  }

  public LocalDateTime getEndDateTime() {
    return endDateTime;
  }

  public void setEndDateTime(LocalDateTime endDateTime) {
    this.endDateTime = endDateTime;
  }

  public List<Integer> getMeetingAttendeeIDs() {
    return meetingAttendeeIDs;
  }

  public void setMeetingAttendees(List<Integer> meetingAttendeeIDs) {
    this.meetingAttendeeIDs = meetingAttendeeIDs;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  public PublicUser getOrganizer() {
    return organizer;
  }

  public void setOrganizer(PublicUser organizer) {
    this.organizer = organizer;
  }
}
