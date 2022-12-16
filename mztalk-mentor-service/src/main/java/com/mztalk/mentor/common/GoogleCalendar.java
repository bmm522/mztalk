package com.mztalk.mentor.common;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import com.google.common.reflect.ClassPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class GoogleCalendar {

    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String CREDENTIALS_FOLDER = "credentials"; // Directory to store user credentials.
    private static final String CALENDAR_ID = "bha3394";
    private Logger logger = LoggerFactory.getLogger("GoogleCalendar.class");


    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved credentials/ folder.
     */

    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);
    private static final String CLIENT_SECRET_DIR = "/client_secret.json";

    public static Event addEvent(Event event) throws IOException, GeneralSecurityException, GoogleJsonResponseException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        return service.events().insert(CALENDAR_ID, event).execute();
    }


    public static void delEvent(String eventKey) throws IOException, GeneralSecurityException, GoogleJsonResponseException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        service.events().delete(CALENDAR_ID, eventKey).execute();
    }


    /**
     * Creates an authorized Credential object.
     *
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If there is no client_secret.
     */

    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {

        // Load client secrets.
        InputStream in = GoogleCalendar.class.getResourceAsStream(CLIENT_SECRET_DIR);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));


        // Build flow and trigger user authorization request.

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(CREDENTIALS_FOLDER)))
                .setAccessType("offline")
                .build();

        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }

   /* public Event makeEvent(ClassPath.ResourceInfo resourceInfo, List<ResourceSubscriber> subList) throws ParseException, IOException, GeneralSecurityException {
        Event event = new Event()
                .setSummary(resourceInfo.getReqText())
                .setLocation(getMeetingRoom(resourceInfo.getResSeq()).getResName()) //장소이름은 따로 불러오길..
                .setDescription(resourceInfo.getDescText());


        DateTime startDateTime = new DateTime(dateTimeTzFormat.format(dateTimeFormat.parse(resourceInfo.getStartDate())));

        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone("Asia/Seoul");
        event.setStart(start);

        DateTime endDateTime = new DateTime(dateTimeTzFormat.format(dateTimeFormat.parse(resourceInfo.getEndDate())));

        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone("Asia/Seoul");
        event.setEnd(end);


        EventReminder[] reminderOverrides = new EventReminder[]{
                new EventReminder().setMethod("popup").setMinutes(10),
        };

        Event.Reminders reminders = new Event.Reminders()
                .setUseDefault(false)
                .setOverrides(Arrays.asList(reminderOverrides));
        event.setReminders(reminders);


        List<EventAttendee> attendList = new ArrayList();

        for (ResourceSubscriber subObj : subList) {
            logger.info("SUB ADD OBJ : " + subObj.toString());
            EmpInfo empInfo = getEmpInfo(subObj.getEmpSeq());

            if (empInfo.getOutMail() != null && empInfo.getOutMail().length() > 0 && empInfo.getOutDomain() != null && empInfo.getOutDomain().length() > 0) {
                if (empInfo.getOutDomain().equals("gmail.com")) {
                    attendList.add(new EventAttendee().setEmail(empInfo.getOutMail() + "@" + empInfo.getOutDomain()));
                } else {
                    //이메일주소가 gmail.com 이 아닌 케이스. 일단은 아무것도 안함.
                }
            } else {
                //메일주소가 없는 케이스.
            }
        }
        event.setAttendees(attendList);

        try {
            event = GoogleCalendar.addEvent(event);
        } catch (Exception ex) {
            logger.info("Calendar Exception in insert");
        }

        logger.info("EVENT : " + event.toPrettyString());
        return event;

    }*/
}