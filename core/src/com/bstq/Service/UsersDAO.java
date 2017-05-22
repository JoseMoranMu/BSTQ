package com.bstq.Service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import jdk.nashorn.internal.parser.JSONParser;

/**
 * Created by Jose on 17/05/2017.
 */

public class UsersDAO {
    private String urlService = "http://provenapps.cat:8080/BSTQService/services/user";

    public UsersDAO(){

    }
    public User login(String email, String pass) {
        User u = null;
        String response ="";
        try {
            URL url = new URL(urlService + "/login"+"/"+email+"/"+pass);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            response = getResponseBody(con);
            u = getUser(response);
        } catch (UnknownHostException ex){
            u = new User();
            u.setId(0);
        } catch (MalformedURLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
    public String singUp(String userName, String email, String password){
        String response ="";
        try {
            URL url = new URL(urlService + "/insert"+"/"+userName+"/"+email+"/"+password);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.connect();
            response = getResponseBody(con);
        }  catch (UnknownHostException ex){
            response = "connectionError";
        }catch (MalformedURLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;

    }


    public void registPoints(int userId, int maxScore) throws UnknownHostException{
        try {
            URL url = new URL(urlService + "/MaxScore"+"/"+userId+"/"+maxScore);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.connect();
            getResponseBody(con);
        } catch (MalformedURLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private User getUser(String response) {
        User u = null;
        Gson gson = new Gson();

        if(!response.equals("null")) {
            u = gson.fromJson(response, User.class);
        }
        return u;
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
            sb.append(line);
        }
        br.close();
        return sb.toString();

    }
}
