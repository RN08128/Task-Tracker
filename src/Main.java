import service.JsonIO;

import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JsonIO jsonIO = new JsonIO();
        int id;
        int opt02;
        String name;
        while (true) {
            System.out.println(
                    "1 - Criar task" +
                            "\n2 - Mostrar tasks" +
                            "\n3 - Atualizar task" +
                            "\n4 - Deletar task" +
                            "\n0 - Finalizar a aplicação");
            int opt01 = scanner.nextInt();
            scanner.nextLine();
            switch (opt01) {
                case 1:
                    System.out.print("Nome da task: ");
                    name = scanner.nextLine();
                    jsonIO.criarTask(name);
                    break;
                case 2:
                    System.out.println("-=-Tasks-=-");
                    System.out.println("1 - Listar todas" +
                            "\n2 - Listar Pendentes" +
                            "\n3 - Listar Em andamento" +
                            "\n4 - Listar Concluidas");
                    System.out.println("-->");
                    opt02 = scanner.nextInt();
                    jsonIO.mostrarTasks(opt02);
                    break;
                case 3:
                    jsonIO.mostrarTasks(1);
                    System.out.println("Escolha o id da task que deseja atualizar:");
                    System.out.print("-->");
                    id = scanner.nextInt();
                    System.out.println("[1] Em andamento" +
                            "\n[2] Concluida");
                    opt02 = scanner.nextInt();
                    jsonIO.atualizarStatus(id, opt02);
                    break;
                case 4:
                    jsonIO.mostrarTasks(1);
                    System.out.println("Escolha o id da task que deseja deletar:");
                    System.out.print("-->");
                    id = scanner.nextInt();
                    jsonIO.deletarTask(id);
                    break;
                case 0:
                    System.out.println("Finalizando a aplicação...");
                    return;
                default:
                    System.out.println("--------------------------------------");
                    System.out.println("Opção inválida!");
                    System.out.println("--------------------------------------");
                    break;
            }
        }
    }
}
