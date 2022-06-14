package book_wizards.models;

import javax.persistence.Entity;

@Entity
public class MeetingAttendee {

  private int meetingId;
  private int appUserId;

  public int getMeetingId() {
    return meetingId;
  }

  public void setMeetingId(int meetingId) {
    this.meetingId = meetingId;
  }

  public int getAppUserId() {
    return appUserId;
  }

  public void setAppUserId(int appUserId) {
    this.appUserId = appUserId;
  }
}
