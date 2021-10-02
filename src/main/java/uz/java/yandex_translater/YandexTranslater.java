package uz.java.yandex_translater;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import uz.java.yandex_translater.model.Respons;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

public class YandexTranslater {
    public static void main(String[] args) throws IOException {
        String key = "trnsl.1.1.20200226T052639Z.8b3d0227397924e9.451b250bcf4f2429a04ae88567e07d3cddb72df9";
        String key2 = "dict.1.1.20191209T144021Z.5071a2e3a805d402.22e5a68009510f2b6217404b6deab167cbf9e6fc";
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> langs = getLangs(key2);
        System.out.println("Available langs: ");
        System.out.println("--------------------------------------------");
        for (String lang : langs) {
            System.out.print(lang + " ");
        }
        System.out.println("\n--------------------------------------------");
        while (true) {
            System.out.println("0 :=> Exit                    1 :=> Translate");
            switch (scanner.nextInt()) {
                case 0:
                    return;
                case 1:
                    scanner = new Scanner(System.in);
                    System.out.print("Enter a pair of lang : ");
                    String lang = scanner.nextLine();
                    System.out.print("Enter text : ");
                    String text = scanner.nextLine();
                    translate(key, text, lang);
                    break;
                default:
                    System.err.println("Wrong options!!!");
            }
        }
    }

    public static void translate(String key, String text, String lang) {
        try {
            URL url = new URL("https://translate.yandex.net/api/v1.5/tr.json/translate?key="
                    + key + "&text=" + text.replaceAll(" ", "+")
                    + "&lang=" + lang);
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            Gson gson = new Gson();
            Respons respons = gson.fromJson(reader, Respons.class);
            System.out.println("Translate: ");
            for (String string : respons.getText()) {
                System.out.println((respons.getText().indexOf(string) + 1) + ". " + string);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<String> getLangs(String key2) throws IOException {
        Gson gson = new Gson();
        URL url = new URL("https://dictionary.yandex.net/api/v1/dicservice.json/getLangs?key=" + key2);
        URLConnection connection = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();

        ArrayList<String> langsList = gson.fromJson(reader, type);
        return langsList;
    }
}