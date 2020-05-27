package com.technopolis.network.model;

public class AgentsResponse {
    public int id;
    public String title;
    public String previewImageUrl;

    public AgentsResponse() {

    }

    public AgentsResponse(int id, String name, String previewImageUrl) {
        this.id = id;
        this.title = title;
        this.previewImageUrl = previewImageUrl;
    }
}
