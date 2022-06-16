package book_wizards.models;

public class MeetingAttendee {

  private int meetingId;
  private int attendeeId;

  public MeetingAttendee(int meeting_id, int app_user_id) {
  }

  public int getMeetingId() {
    return meetingId;
  }

  public void setMeetingId(int meetingId) {
    this.meetingId = meetingId;
  }

  public int getAttendeeId() {
    return attendeeId;
  }

  public void setAttendeeId(int attendeeId) {
    this.attendeeId = attendeeId;
  }

}
