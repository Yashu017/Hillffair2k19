package com.appteam.hillfair2k19.model;

/**
 * Created by naman on 20-09-2018.
 */

public class Team {

    String name;
    String image;
    String position;
    String id;
    String profile;



    public Team(String name, String image, String position, String id) {
        this.name = name;
        this.image = image;
        this.position = position;
        this.id=id;


    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Team(String name, String profile, String position) {
        this.name=name;
        this.position=position;
        this.profile=profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }}