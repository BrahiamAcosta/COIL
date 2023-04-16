/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minijuegos.ordenamiento.Info;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import org.json.JSONArray;

/**
 *
 * @author Alisser
 */
public class GetSentences {

    private JSONArray JsonInfo() throws IOException {
        String jsonString = new String(Files.readAllBytes(Paths.get("Sentences.json")));
        JSONArray jsonArray = new JSONArray(jsonString);
        return jsonArray;
    }

    public JSONArray Words() throws IOException {
        JSONArray words = new JSONArray();
        JSONArray info = this.JsonInfo();
        Random random = new Random();
        int i = random.nextInt(info.length());
        String sentence = info.getJSONObject(i).getString("sentence");
        String[] sentenceArr = sentence.split(" ");
        for (int j = 0; j < sentenceArr.length; j++) {
            words.put(sentenceArr[j]);
        }
        return words;
    }
}
