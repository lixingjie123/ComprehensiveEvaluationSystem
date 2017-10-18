package cn.ces.entity;

public class Courseware {
    private Integer cwid;

    private String cwname;

    private String cid;

    private Integer credit;

    private String url;

    public Integer getCwid() {
        return cwid;
    }

    public void setCwid(Integer cwid) {
        this.cwid = cwid;
    }

    public String getCwname() {
        return cwname;
    }

    public void setCwname(String cwname) {
        this.cwname = cwname == null ? null : cwname.trim();
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}