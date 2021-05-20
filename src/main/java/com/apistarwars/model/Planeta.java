package com.apistarwars.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "planetas")
public class Planeta implements Serializable {

    @MongoId(FieldType.OBJECT_ID)
    private String id;
    @Field
    private String nome;
    @Field
    private String clima;
    @Field
    private String terreno;
    @Field
    private String qtdAparicaoFilme;
}