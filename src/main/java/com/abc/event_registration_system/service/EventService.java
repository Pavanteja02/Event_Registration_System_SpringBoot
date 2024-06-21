package com.abc.event_registration_system.service;

import com.abc.event_registration_system.model.Event;
import com.abc.event_registration_system.model.User;
import com.abc.event_registration_system.repository.EventRepository;
import com.abc.event_registration_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    UserRepository userRepository;
    public Event geteventbyid(Long eventid) throws Exception{

        Optional<Event> optionalEvent =  eventRepository.findById(eventid);
        if(!optionalEvent.isPresent()){
            throw  new Exception("event does not exist ");
        }
        return optionalEvent.get();
    }
    public List<Event> getallEventbyusername(String username) throws Exception{
        User optionalUser =  userRepository.findByUsername(username);
        if(optionalUser==null){
            throw  new Exception("user does not exist");
        }
        return eventRepository.findAllEventsWithUsername(username);
    }
    public Event createEvent(Event event , String username) throws Exception{
        User optionalUser =  userRepository.findByUsername(username);
        if(optionalUser==null){
            throw  new Exception("unable to create as User does not exit");
        }
        return eventRepository.save(event);
    }
    public Event updateEvent(Long eventid , Event event,String username) throws Exception{
        Optional<Event> optionalEvent =  eventRepository.findById(eventid);
        if(!optionalEvent.isPresent() ) {
            throw  new Exception("event does not exist");
        }
        if(!optionalEvent.get().getUsername().equals(username)){
            throw  new Exception("user is not authorized to update");
        }
        Event eve = optionalEvent.get();
        event(eve,event);

        return eventRepository.save(eve);
    }
    public void event(Event e1 , Event e2) throws IllegalAccessException {
        Class<?> o1 = Event.class;
        Field[] f1 =  o1.getDeclaredFields();
        for (Field f2 : f1){
            f2.setAccessible(true);
            Object o = f2.get(e2);
            if(o!=null){
                f2.set(e1,o);
            }

        }
    }
    public boolean deleteuser(Long eventid,String username) throws Exception{
        Optional<Event> optionalEvent =  eventRepository.findById(eventid);
        if(!optionalEvent.isPresent() ){
            throw  new Exception("event does not exist unable to delete");
        }
        if(!optionalEvent.get().getUsername().equals(username)){
            throw  new Exception("user is not authorized to delete");
        }
        eventRepository.delete(optionalEvent.get());
        return true;
    }
}
