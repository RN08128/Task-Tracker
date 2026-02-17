import service.JsonIO;

import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JsonIO jsonIO = new JsonIO();
        while (true) {
            System.out.println("1 - Criar task\n2 - Mostrar tasks");
            int opt = scanner.nextInt();
            scanner.nextLine();
            String name;
            switch (opt) {
                case 1:
                    System.out.print("Nome da task: ");
                    name = scanner.nextLine();
                    jsonIO.criarTask(name);
                    break;
                case 2:
                    jsonIO.showTasks();
                    break;
                case 0:
                    System.out.println("Finalizando a aplicação...");
                    return;
            }
        }
    }
}
