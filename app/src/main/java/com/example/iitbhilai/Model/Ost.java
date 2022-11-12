package com.example.iitbhilai.Model;

public class Ost {
    String ostid,ostedby,ostimg;
    long ostedat;

    public Ost(){

    }
    public Ost(String ostid, String ostedby, String ostimg, long ostedat) {
        this.ostid = ostid;
        this.ostedby = ostedby;
        this.ostimg = ostimg;
        this.ostedat = ostedat;
    }

    public String getOstid() {
        return ostid;
    }

    public void setOstid(String ostid) {
        this.ostid = ostid;
    }

    public String getOstedby() {
        return ostedby;
    }

    public void setOstedby(String ostedby) {
        this.ostedby = ostedby;
    }

    public String getOstimg() {
        return ostimg;
    }

    public void setOstimg(String ostimg) {
        this.ostimg = ostimg;
    }

    public long getOstedat() {
        return ostedat;
    }

    public void setOstedat(long ostedat) {
        this.ostedat = ostedat;
    }
}
