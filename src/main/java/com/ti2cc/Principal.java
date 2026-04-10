package com.ti2cc;

import java.util.Scanner;

public class Principal {
    
    public static void main(String[] args) {
        // 1. Instanciar o DAO e o Scanner
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Scanner sc = new Scanner(System.in);
        int opcao = 0;

        // 2. Criar o laço do menu
        do {
            System.out.println("\n\n==== MENU USUÁRIO (POSTGRESQL) ====");
            System.out.println("1) Listar");
            System.out.println("2) Inserir");
            System.out.println("3) Excluir");
            System.out.println("4) Atualizar");
            System.out.println("5) Sair");
            System.out.print("Opção: ");
            
            opcao = sc.nextInt();
            sc.nextLine(); // Limpar buffer do teclado

            switch (opcao) {
                case 1: // LISTAR
                    System.out.println("\n--- Lista de Usuários ---");
                    Usuario[] usuarios = usuarioDAO.get(); // Busca do banco
                    for (int i = 0; i < usuarios.length; i++) {
                        System.out.println("ID: " + usuarios[i].getCodigo() + 
                                           " | Login: " + usuarios[i].getLogin() + 
                                           " | Sexo: " + usuarios[i].getSexo());
                    }
                    break;

                case 2: // INSERIR
                    System.out.print("Digite o código: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Digite o login: ");
                    String login = sc.nextLine();
                    System.out.print("Digite a senha: ");
                    String senha = sc.nextLine();
                    System.out.print("Digite o sexo (M/F): ");
                    char sexo = sc.next().charAt(0);

                    Usuario novoUsuario = new Usuario();
                    // Use os SETTERS que você gerou na classe Usuario
                    novoUsuario.setCodigo(id);
                    novoUsuario.setLogin(login);
                    novoUsuario.setSenha(senha);
                    novoUsuario.setSexo(sexo);

                    if (usuarioDAO.inserir(novoUsuario)) {
                        System.out.println("Usuário inserido com sucesso!");
                    }
                    break;

                case 3: // EXCLUIR
                    System.out.print("Digite o código do usuário para excluir: ");
                    int idExcluir = sc.nextInt();
                    if (usuarioDAO.excluir(idExcluir)) {
                        System.out.println("Usuário removido!");
                    }
                    break;

                case 4: // ATUALIZAR
                    System.out.print("Digite o código do usuário que deseja atualizar: ");
                    int idAtu = sc.nextInt();
                    sc.nextLine(); // Limpar buffer

                    Usuario usuarioAtu = new Usuario();
                    usuarioAtu.setCodigo(idAtu);

                    System.out.print("Novo login: ");
                    usuarioAtu.setLogin(sc.nextLine());
                    System.out.print("Nova senha: ");
                    usuarioAtu.setSenha(sc.nextLine());
                    System.out.print("Novo sexo (M/F): ");
                    usuarioAtu.setSexo(sc.next().charAt(0));

                    if (usuarioDAO.atualizar(usuarioAtu)) {
                        System.out.println("Usuário atualizado com sucesso!");
                    } else {
                        System.out.println("Erro ao atualizar usuário.");
                    }
                    break;

                case 5: // SAIR
                    System.out.println("Encerrando aplicação...");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        } while (opcao != 5);

        sc.close();
    }
}