package service;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonIO {
    Path path = Paths.get("C:\\Users\\RN\\Documents\\ProjetoDEV\\Task-Tracker\\src\\util\\tasks.json");

    public void criarTask(String nome) {
        try {
            String conteudo = Files.exists(path) ?
                    Files.readString(path) :
                    "";
            if (conteudo.isBlank()) {
                JSONArray tasks = new JSONArray();
            } else {
                JSONArray tasks = new JSONArray(conteudo);
            }
            JSONObject task = new JSONObject();
            task.put("taskstatus","");
            task.put("taskname","");
            task.put("taskid","");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
