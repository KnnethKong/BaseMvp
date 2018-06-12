package com.base.message;

public class EventMes {
    private String msg;
    private Integer size;
    private String ext;
    private Double div;

    public Double getDiv() {
        return div;
    }

    public void setDiv(Double div) {
        this.div = div;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "EventMes{" +
                "msg='" + msg + '\'' +
                ", size=" + size +
                ", ext='" + ext + '\'' +
                ", div=" + div +
                '}';
    }
}
