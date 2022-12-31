package com.mztalk.main.domain.message;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class EventMessage {

    private Long timestamp;
    private String message;

    public EventMessage(String message){
        //this.timestamp = System.currenTimeMillis();
        this.message = message;
    }

    @JsonCreator
    public EventMessage(@JsonProperty("timestamp") Long timestamp,
                        @JsonProperty("message") String message){
        this.timestamp = timestamp;
        this.message = message;

    }

}
