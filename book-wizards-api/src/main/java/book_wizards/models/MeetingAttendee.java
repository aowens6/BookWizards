package book_wizards.models;

public class MeetingAttendee {

  private int meetingId;
  private int attendeeId;

  public MeetingAttendee(){}

  public MeetingAttendee(int meetingId, int attendeeId) {
    this.meetingId = meetingId;
    this.attendeeId = attendeeId;
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
