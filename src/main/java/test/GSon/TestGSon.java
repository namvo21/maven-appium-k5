package test.GSon;

import com.google.gson.Gson;
import utils.data.DataObjectBuilder;

public class TestGSon {
    public static void main(String[] args) {
       /* String jsonObject = "[\n" +
                "  {\n" +
                "    \"username\": \"teo\",\n" +
                "    \"password\": \"123456\",\n" +
                "    \"job\": {\n" +
                "      \"position\": \"SWE Level 2\"\n" +
                "    }\n" +
                "  },\n" +
                "  {\n" +
                "    \"username\": \"ti\",\n" +
                "    \"password\": \"123456\",\n" +
                "    \"job\": {\n" +
                "      \"position\": \"SWE Level 1\"\n" +
                "    }\n" +
                "  }\n" +
                "]\n";

        Gson gson = new Gson();*/
        //LoginCred loginCred = gson.fromJson(jsonObject, LoginCred.class);
        //System.out.println(loginCred);
        //System.out.println(gson.toJson(loginCred));

        //LoginCred[] loginCreds = gson.fromJson(jsonObject, LoginCred[].class);
        //for (LoginCred cred : loginCreds) {
        //    //System.out.println(cred);
        //    System.out.println(cred.getJob().getPosition());
        //}

        /*String jsonFilePath = "/src/main/java/test/GSon/LoginCred.json";
        LoginCred[] loginCreds = DataObjectBuilder.buildDataObject(jsonFilePath,LoginCred[].class);

        for (LoginCred loginCred : loginCreds) {
            System.out.println(loginCred);
            //loginCred.getJob().getPosition();
        }*/

        String animalJsonFilePath = "/src/main/java/test/GSon/Animal.json";
        Animal animal = DataObjectBuilder.buildDataObject(animalJsonFilePath, Animal.class);

        System.out.println(animal);
    }
}
