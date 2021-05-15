package com.apistarwars.client.context;

public class ContextClient {

    public static final String OBTER_PLANETAS = "planets/";
    public static final String OBTER_PLANETA = OBTER_PLANETAS + "{numero}/";

    private ContextClient() {
    }
}