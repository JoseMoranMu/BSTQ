package com.bstq.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Jose on 17/05/2017.
 */

public class UsersDAO {
    private String urlService = "http://provenapps.cat:8080/BSTQService/services/user";

    public UsersDAO(){

    }
    public String login(String email, String pass) {
        String response ="";
        try {
            URL url = new URL(urlService + "/login"+"/"+email+"/"+pass);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            response = getResponseBody(con);

        } catch (MalformedURLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
    private String getResponseBody(HttpURLConnection con) throws IOException {
        BufferedReader br;

        if (con.getResponseCode() >= 400) {
            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        } else {
            //Si el codi és inferior a 400 llavors obtenim una resposta correcte del servidor.
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();
        return sb.toString();
    }
}
