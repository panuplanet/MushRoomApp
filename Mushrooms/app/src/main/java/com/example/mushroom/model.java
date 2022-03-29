package com.example.mushroom;

public class model {
    model()
    {
    }
    String MushroomName;
    String highttemp;
    String highthum;
    String lowtemp;
    String lowhum;
    String startdate;
    String enddate;
    String starttime;
    String endtime;

    public model(String mushroomName, String highttemp, String highthum, String lowtemp, String lowhum, String startdate, String enddate, String starttime, String endtime) {
        MushroomName = mushroomName;
        this.highttemp = highttemp;
        this.highthum = highthum;
        this.lowtemp = lowtemp;
        this.lowhum = lowhum;
        this.startdate = startdate;
        this.enddate = enddate;
        this.starttime = starttime;
        this.endtime = endtime;
    }

    public String getMushroomName() {
        return MushroomName;
    }

    public void setMushroomName(String mushroomName) {
        MushroomName = mushroomName;
    }

    public String getHighttemp() {
        return highttemp;
    }

    public void setHighttemp(String highttemp) {
        this.highttemp = highttemp;
    }

    public String getHighthum() {
        return highthum;
    }

    public void setHighthum(String highthum) {
        this.highthum = highthum;
    }

    public String getLowtemp() {
        return lowtemp;
    }

    public void setLowtemp(String lowtemp) {
        this.lowtemp = lowtemp;
    }

    public String getLowhum() {
        return lowhum;
    }

    public void setLowhum(String lowhum) {
        this.lowhum = lowhum;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
}