package com.abc.event_registration_system.controller;

import com.abc.event_registration_system.model.Event;
import com.abc.event_registration_system.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/events", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventController {
    @Autowired
    EventService eventService;
    @GetMapping("/getevent")
    public ResponseEntity getevent(@RequestParam Long eventid , @RequestParam String username) {

        try {
            return new ResponseEntity(eventService.geteventbyid(eventid), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(null,HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
    @GetMapping("/geteventsbyusername")
    public ResponseEntity geteventsabyusername(@RequestParam String username) {

        try {
            return new ResponseEntity(eventService.getallEventbyusername(username),HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(null,HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
    @PostMapping("/createevent")
    public ResponseEntity createevent(@RequestBody Event event , @RequestParam String username) {

        try {
            return new ResponseEntity(eventService.createEvent(event,username),HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(null,HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
    @PutMapping("/updateevent")
    public ResponseEntity updateevent(@RequestBody Event event ,@RequestParam Long eventid, @RequestParam String username ) {

        try {
            return new ResponseEntity(eventService.updateEvent(eventid,event,username),HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(null,HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
    @DeleteMapping("/deleteevent")
    public ResponseEntity delete(@RequestParam Long eventid , @RequestParam String username) {

        try {

            return new ResponseEntity(eventService.deleteuser(eventid,username),HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}

