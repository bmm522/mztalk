package com.mztalk.mentor.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Mentor {
    @Id
    @GeneratedValue
    private Long id;
}