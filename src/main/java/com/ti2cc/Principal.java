package com.ti2cc;

import static spark.Spark.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Principal {
    private static UsuarioService usuarioService = new UsuarioService();

    public static void main(String[] args) {
        port(6789);

        System.out.println("Servidor iniciado em http://localhost:6789/formulario");

        get("/formulario", (request, response) -> {
            return new String(Files.readAllBytes(Paths.get("src/main/resources/formulario.html")));
        });

        post("/usuario/insert", (request, response) -> {
            int codigo = Integer.parseInt(request.queryParams("codigo"));
            String descricao = request.queryParams("descricao");
            String senha = request.queryParams("senha");

            String resultadoExecucao = usuarioService.processarEInserir(codigo, descricao, senha);

            return "<h2>Resultado do Processamento</h2><p>" + resultadoExecucao + "</p><br><a href='/formulario'>Voltar</a>";
        });
    }
}