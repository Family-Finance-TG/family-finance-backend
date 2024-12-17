package com.fatecmogi.family_finance.application.controller;


import com.fatecmogi.family_finance.application.util.AppResponse;
import com.fatecmogi.family_finance.application.util.AppResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/hello")
public class HelloController {

    @GetMapping
    public AppResponse<AppResponseData> hello() {
        return new AppResponse<>(new AppResponseData(200, "Hello World!"), HttpStatus.OK);
    }

    //todo: Corrigir relacionamentos circulares desnecessarios.
    //todo: Reestruturar DTOs separando dto de request e response.
    //todo: Reestruturar DTOs para retornar somente id das entidades relacionadas.
    //todo: Reestruturar as excecoes criando uma excecao base interna que receba o codigo e a mensagem e as excecoes filhas que herdam dela passando essas informações.
}