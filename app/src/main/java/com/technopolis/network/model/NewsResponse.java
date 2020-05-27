package com.technopolis.network.model;

public class NewsResponse {
    public Long date;
    public String agent;
    public String logo;
    public long id;
    public String title;
    public String body;
    public String url;

    public NewsResponse() {
    }

    public NewsResponse(Long date, String agent, String logo, long id, String title, String body, String url) {
        this.date = date;
        this.agent = agent;
        this.logo = logo;
        this.id = id;
        this.title = title;
        this.body = body;
        this.url = url;
    }
}
