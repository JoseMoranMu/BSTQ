package com.bstq.Model;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Jose on 17/05/2017.
 */

public class UsersDAO {
    private String urlService = "http://provenapps.cat:8080/BSTQService/services/user";

    public UsersDAO(){

    }

    /**
     * Method to connect with WebService for a login
     * @param email email of user to login
     * @param pass password
     * @return User loged if login OK, User null if exception, and User if 0 if error connecting
     */
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

    /**
     * Method to register a User into WebService database
     * @param userName user name of the user
     * @param email email of the user
     * @param password password of the user
     * @return a string with response, "connectionError" in case of connection lost
     */
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

    /**
     * Method to register user max score in Webservice database
     * @param userId id of user
     * @param maxScore new max score
     * @throws UnknownHostException throws exception in case of connection lost
     */
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

    /**
     * Get a User object from a response in Json format
     * @param response response
     * @return User object
     */
    private User getUser(String response) {
        User u = null;
        Gson gson = new Gson();

        if(!response.equals("null")) {
            u = gson.fromJson(response, User.class);
        }
        return u;
    }

    /**
     * Get the response string of the Webservice petition
     * @param con Http connection
     * @return String response
     * @throws IOException
     */
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

    /**
     * Method to load list of Ranking
     * @return Ranking object with list of 10 users
     */
    public Ranking loadRanking() {
        Ranking list = new Ranking();
        try {
            URL url = new URL(urlService + "/listRanking");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            String response =getResponseBody(con);
            list = loadList(response);
        } catch (MalformedURLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     * Method to get a Ranking list of the response in Json format
     * @param response response
     * @return Ranking object
     */
    private Ranking loadList(String response) {
        Ranking list = new Ranking();
        Gson gson = new Gson();
        if(!response.equals("null")) {
            list = gson.fromJson(response, Ranking.class);
        }
        return list;
    }
}
