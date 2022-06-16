import {findById as findMeetingById} from "./meeting";
import {findById as findAppUserById} from "./app_user";
import { bookResult } from "./bookObject"


export function meetingResult(id) {

    let organizerId;
    let bookId;
    const attendeeIds = [];

    const result = {
        "meeting": {
            "meetingId": id,
            "groupName": "",
            "description": "",
            "book": {},
            "organizer": "",
            "startDateTime": "",
            "endDateTime": "",
            "attendees": []

        },
        "errors": []
    }

    findMeetingById(id)
        .then(m => {
            result.meeting.groupName = m.groupName;
            result.meeting.description = m.description;
            bookId = m.BookId;
            organizerId = m.organizerId;
            result.meeting.startDateTime = m.startDateTime;
            result.meeting.endDateTime = m.endDateTime;
            attendeeIds = m.attendeeIds; // check java variable name from api
        })
        .catch(err => result.errors.push(err));

    let tmpBook = bookResult(bookId);
    if (tmpBook.errors.length > 0) {
        result.errors.push(tmpBook.errors);
    } else {
        result.meeting.book = tmpBook.book;
    }

    // need to update attendee list


    return result;

}