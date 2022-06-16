package book_wizards.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

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
}
