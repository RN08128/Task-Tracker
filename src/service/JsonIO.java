package service;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class JsonIO {
    Path path = Paths.get("C:\\Users\\RN\\Documents\\ProjetoDEV\\Task-Tracker\\src\\util\\tasks.json");
    String taskstatus;

    public void criarTask(String nome) {
        JSONArray tasks;
        if (conteudo().isBlank()) {
            tasks = new JSONArray();
        } else {
            tasks = new JSONArray(conteudo());
        }
        JSONObject task = new JSONObject();
        LocalDateTime ldt = LocalDateTime.now();
        task.put("taskstatus", 0);
        task.put("taskname", nome);
        task.put("taskid", newId());
        task.put("CreatedAt", ldt);
        task.put("UpdatedAt", 0);
        tasks.put(task);
        try (FileWriter writer = new FileWriter(path.toFile())) {
            writer.write(tasks.toString(2));
            System.out.println("--------------------------------------");
            System.out.println("Task criada com sucesso!");
            System.out.println("--------------------------------------");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarTasks(int opt) {
        System.out.println("--------------------------------------");
        if (conteudo().isBlank() || conteudo().isEmpty() || conteudo().equals("[]")) {
            System.out.println("-------------Não há tasks-------------");
        } else {
            JSONArray tasks = new JSONArray(conteudo());
            for (int i = 0; i < tasks.length(); i++) {
                JSONObject jsonObject = tasks.getJSONObject(i);
                switch (jsonObject.getInt("taskstatus")) {
                    case 0:
                        taskstatus = "Pendente";
                        break;
                    case 1:
                        taskstatus = "Em andamento";
                        break;
                    case 2:
                        taskstatus = "Concluida";
                        break;
                }
                if (opt == 1) {
                    System.out.println("| [ " + jsonObject.getInt("taskid") + " ] " + jsonObject.getString("taskname") + " | " + taskstatus + " |");
                } else if (opt == 2 && taskstatus.equals("Pendente")) {
                    System.out.println("| [ " + jsonObject.getInt("taskid") + " ] " + jsonObject.getString("taskname") + " | " + taskstatus + " |");
                } else if (opt == 3 && taskstatus.equals("Em andamento")) {
                    System.out.println("| [ " + jsonObject.getInt("taskid") + " ] " + jsonObject.getString("taskname") + " | " + taskstatus + " |");
                } else if (opt == 4 && taskstatus.equals("Concluida")) {
                    System.out.println("| [ " + jsonObject.getInt("taskid") + " ] " + jsonObject.getString("taskname") + " | " + taskstatus + " |");
                }
            }
        }
        System.out.println("--------------------------------------");
    }

    public void atualizarStatus(int id, int opt) {
        if (conteudo().isBlank()) {
            System.out.println("Ainda não exise nenhuma task!");
        }
        JSONArray tasks = new JSONArray(conteudo());
        LocalDateTime ldt = LocalDateTime.now();
        for (int i = 0; i < tasks.length(); i++) {
            JSONObject object = tasks.getJSONObject(i);
            if (object.getInt("taskid") == id) {
                object.put("taskstatus", opt);
                object.put("UpdatedAt", ldt);
            }
        }
        try (FileWriter writer = new FileWriter(path.toFile())) {
            writer.write(tasks.toString(2));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Task atualizada com sucesso!");
    }

    public void deletarTask(int id) {
        if (conteudo().isBlank()) {
            System.out.println("Ainda não exise nenhuma task!");
        }
        JSONArray tasks = new JSONArray(conteudo());
        for (int i = 0; i < tasks.length(); i++) {
            JSONObject object = tasks.getJSONObject(i);
            if (object.getInt("taskid") == id) {
                tasks.remove(i);
            }
        }
        try (FileWriter writer = new FileWriter(path.toFile())) {
            writer.write(tasks.toString(2));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String conteudo() {
        try {
            return Files.exists(path) ? Files.readString(path) : "";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int newId() {
        if (conteudo().isBlank() || conteudo().isEmpty() || conteudo().equals("[]")) return 1;
        JSONArray tasks = new JSONArray(conteudo());
        JSONObject task = tasks.getJSONObject(tasks.length() - 1);
        return task.getInt("taskid") + 1;
    }
}
