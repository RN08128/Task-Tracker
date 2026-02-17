package service;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonIO {
    Path path = Paths.get("C:\\Users\\RN\\Documents\\ProjetoDEV\\Task-Tracker\\src\\util\\tasks.json");

    public void criarTask(String nome) {
        JSONArray tasks;
        if (conteudo().isBlank()) {
            tasks = new JSONArray();
        } else {
            tasks = new JSONArray(conteudo());
        }
        JSONObject task = new JSONObject();
        task.put("taskstatus", 0);
        task.put("taskname", nome);
        task.put("taskid", newId());
        tasks.put(task);
        try (FileWriter writer = new FileWriter(path.toFile())) {
            writer.write(tasks.toString(2));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showTasks() {
        if (conteudo().isBlank()){
            System.out.println("Não há tasks");
        } else {
            JSONArray tasks = new JSONArray(conteudo());
            for (Object task : tasks) {
                System.out.println(task);
            }

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
        if (conteudo().isBlank()) return 1;
        JSONArray tasks = new JSONArray(conteudo());
        JSONObject task = tasks.getJSONObject(tasks.length() - 1);
        return task.getInt("taskid") + 1;
    }
}
