package com.br.rest;

import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

public class CepTest {

    @Test
    public void searchAdressByZipCode(){
        given()
                .when()
                .get("https://viacep.com.br/ws/88010970/json/")
                .then()
                .log()
                .all()
                .statusCode(200)
                .body("logradouro", is(containsString("Praça Quinze de Novembro")))
                .body("bairro", is(containsString("Centro")))
                .body("localidade", is(containsString("Florianópolis")));
    }

    @Test
    public void searchAdressByStreetName(){
        given()
                .when()
                .get("https://viacep.com.br/ws/SC/Florianopolis/Avenida + Itamarati/json/")
                .then()
                .log()
                .all()
                .statusCode(200)
                .body("cep[0]", is(containsString("88034-400")))
                .body("logradouro[0]", is(containsString("Avenida Itamarati")))
                .body("bairro[0]", is(containsString("Itacorubi")))
                .body("localidade[0]", is(containsString("Florianópolis")))
                .body("cep[1]", is(containsString("88034-900")))
                .body("logradouro[1]", is(containsString("Avenida Itamarati")))
                .body("bairro[1]", is(containsString("Itacorubi")))
                .body("localidade[1]", is(containsString("Florianópolis")));
    }

    @Test
    public void searchAdressByWrongZipCode(){
        given()
                .when()
                .get("https://viacep.com.br/ws/11111111/json/")
                .then()
                .log()
                .all()
                .body("erro",is(true));
    }

    @Test
    public void searchAdressByZipCodeXML(){
        given()
                .when()
                .get("https://viacep.com.br/ws/88010970/xml/")
                .then()
                .log()
                .all()
                .body("xmlcep.logradouro",is("Praça Quinze de Novembro"))
                .body("xmlcep.bairro",is("Centro"))
                .body("xmlcep.localidade",is("Florianópolis"));
    }

    @Test
    public void searchAdressByStreetNameXML(){
        given()
                .when()
                .get("https://viacep.com.br/ws/SC/Florianopolis/Avenida + Itamarati/xml/")
                .then()
                .log()
                .all()
                .body("xmlcep.enderecos.endereco[0].cep",is("88034-400"))
                .body("xmlcep.enderecos.endereco[0].logradouro",is("Avenida Itamarati"))
                .body("xmlcep.enderecos.endereco[0].bairro",is("Itacorubi"))
                .body("xmlcep.enderecos.endereco[0].localidade",is("Florianópolis"))
                .body("xmlcep.enderecos.endereco[1].cep",is("88034-900"))
                .body("xmlcep.enderecos.endereco[1].logradouro",is("Avenida Itamarati"))
                .body("xmlcep.enderecos.endereco[1].bairro",is("Itacorubi"))
                .body("xmlcep.enderecos.endereco[1].localidade",is("Florianópolis"));
    }

    @Test
    public void searchAdressByWrongZipCodeXML(){
        given()
                .when()
                .get("https://viacep.com.br/ws/11111111/xml/")
                .then()
                .log()
                .all()
                .body("xmlcep.erro", is("true"));
    }
}
