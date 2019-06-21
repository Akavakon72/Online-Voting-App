/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bt;

import static com.bt.VotingClient.client;
import com.bt.utils.AllDepartments;
import com.bt.utils.DepartmentBean;
import com.bt.utils.PostBean;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.btv.conn.ConnectionClass;

/**
 *
 * @author Xtreme 3.0
 */
public class VotingServer extends Thread {

    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static DataInputStream din;
    private static DataOutputStream dout;
    private static int port = 6606;
    private boolean isRunning = true;

    @Override
    public void run() {
        int j = 0;
        try {
            while (isRunning) {
                serverSocket = new ServerSocket(port);  //Server socket
                System.out.println("Server started. Listening to the port 6600");
                clientSocket = serverSocket.accept();

                din = new DataInputStream(clientSocket.getInputStream());
                String requestType = din.readUTF();

                System.out.println("Server recieved the request=" + requestType);

                switch (requestType) {
                    case "LOGIN":
                        System.out.println("LOGIN REQ REC...");
                        doLogin();
//                    sendRsponse("Login succ");
                        break;
                    case "REGISTER":
                        System.out.println("Registering.....");
                        doRegister();
                        break;
                    case "VOTE":
                        System.out.println("Voting.....");
                        doVote();
                        break;
                    case "FORGOT_PASS":
                        System.out.println("Forgot Password.....");
                        forgotpass();
                        break;
                    case "GET_ALL_DATA":
                        System.out.println("Get All Data.....");
                        doSendAllData();
                        break;
                }

                din.close();
                clientSocket.close();
                serverSocket.close();
                //Send Response
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void destroy() {
//        super.destroy();
        try {
            if (client != null) {
                client.close();
            }
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(VotingServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public void stop() {
//        try {
//
//            System.out.println("Server Stop");
//            clientSocket.close();
//            serverSocket.close();
//        } catch (Exception e) {
//        }
//    }
    //IMP_BACKUP TO SENDING
    public void sendRsponse(String response) throws IOException {
        serverSocket = new ServerSocket(port);  //Server socket
        System.out.println("Server sending to port 6600");
        clientSocket = serverSocket.accept();
        System.out.println("Socket Accepted");
        try (DataOutputStream dos = new DataOutputStream(
                clientSocket.getOutputStream())) {
            dos.writeUTF("" + response);
            dos.flush();
        }
        clientSocket.close();
        serverSocket.close();
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    private void doLogin() throws IOException {

        String user = din.readUTF();
        String pass = din.readUTF();

//        din.close();
        System.out.println(user + "    " + pass);
        dout = new DataOutputStream(clientSocket.getOutputStream());

//        if (type.equals("Admin")) {
//            if (user.equals("admin") && pass.equals("admin")) {
//                dout.writeUTF("Access Granted..!!");
//                System.out.println(String.format("Sending %s", "Access Granted..!!"));
//            } else {
//                dout.writeUTF("Access Denied..!!");
//                System.out.println(String.format("Sending %s", "Access Denied..!!"));
//            }
//        } else {
        //chk from database with type user
        String login = new ConnectionClass().login(user, pass, "user");
        if (login.equals("TRUE")) {
            dout.writeUTF("Access Granted");
            System.out.println(String.format("Sending %s", "Access Granted..!!"));
        } else {
            dout.writeUTF("Access Denied");
            System.out.println(String.format("Sending %s", "Access Denied..!!"));
        }
//        }//elseclose
        dout.flush();
        dout.close();
    }

    private void forgotpass() throws IOException {

        String user = din.readUTF();
        String pass = din.readUTF();
        String question = din.readUTF();
        String answer = din.readUTF();

//        din.close();
        System.out.println(user + "    " + pass + "   " + answer);
        dout = new DataOutputStream(clientSocket.getOutputStream());
        String response = new ConnectionClass().changePassword(user, pass, question, answer);
        if (response.equals("TRUE")) {
            dout.writeUTF("Password Change Succssfully");
            System.out.println(String.format("Sending %s", "Password Change Succssfully..!!"));
        } else {
            dout.writeUTF("Password Change Failed");
            System.out.println(String.format("Sending %s", "Password Change Failed..!!"));
        }
//        }//elseclose
        dout.flush();
        dout.close();
    }

    private void doRegister() throws IOException {

        String name = din.readUTF();
        System.out.println("name = " + name);
        String username = din.readUTF();
        System.out.println("username = " + username);
        String email = din.readUTF();
        System.out.println("email = " + email);
        String father = din.readUTF();
        System.out.println("father = " + father);
        String mother = din.readUTF();
        System.out.println("mother = " + mother);
        String aadhar = din.readUTF();
        System.out.println("aadhar = " + aadhar);
        String voter = din.readUTF();
        System.out.println("voter = " + voter);
        String address = din.readUTF();
        System.out.println("address = " + address);
        String dob = din.readUTF();
        System.out.println("dob = " + dob);
        String password = din.readUTF();
        System.out.println("password = " + password);
        String macAddress = din.readUTF();
        System.out.println("macAddress = " + macAddress);
        String question = din.readUTF();
        System.out.println("Question = " + question);
        String answer = din.readUTF();
        System.out.println("Answer = " + answer);

        //insert into db
        String regVoter = new ConnectionClass().regVoter(name, username, email, father, mother,
                aadhar, voter, address, dob, password, "user", "offline", macAddress,
                question, answer);
        dout = new DataOutputStream(clientSocket.getOutputStream());
        if (regVoter.equals("TRUE")) {
            dout.writeUTF("Registration Successfull".trim());
            System.out.println(String.format("Sending %s", "VRegistration Successfull..!!"));
        } else {
            dout.writeUTF("Registration Failed\nAllresdy Used UserName/Email/Aadhar/VoterID");
            System.out.println(String.format("Sending %s", "Registration Failed \n"
                    + "Allresdy Used UserName/Email/Aadhar/VoterID.!!"));
        }
        dout.flush();
        dout.close();
    }
//

    private void doVote() throws IOException {

        String dept = din.readUTF();
        System.out.println("dept = " + dept);
        String post = din.readUTF();
        System.out.println("post = " + post);
        String option = din.readUTF();
        System.out.println("option = " + option);
        String username = din.readUTF();
        System.out.println("macaddr = " + username);
        //insert into db
        String vote = new ConnectionClass().vote(username, dept, post, option);
        System.out.println("vote = " + vote);

        dout = new DataOutputStream(clientSocket.getOutputStream());
        if (vote.equals("TRUE")) {
            dout.writeUTF("Voting Success");
            System.out.println(String.format("Sending %s", "Vote Success..!!"));
        } else {
            dout.writeUTF("Voting Failed");
            System.out.println(String.format("Sending %s", "Vote Failed..!!"));
        }
        dout.flush();
        dout.close();
    }

    private void doSendAllData() throws IOException {

        AllDepartments deptList = new AllDepartments();
        try {
            //get all depts from db
            ResultSet resultSet = new ConnectionClass().getDepartments();
//id dept post opt1 opt2 opt3
            while (resultSet.next()) {
                DepartmentBean deptBean = new DepartmentBean();
                String dept = resultSet.getString(2);
                deptBean.setDepartmentName(dept);
                //get posts for each dept
                ResultSet innerRs = new ConnectionClass().getCandidates(dept);
                while (innerRs.next()) {
                    String post = innerRs.getString(3);
                    String candidate1 = innerRs.getString(4);
                    String candidate2 = innerRs.getString(5);
                    String candidate3 = innerRs.getString(6);
                    PostBean postBean = new PostBean(post, new ArrayList<String>(Arrays.asList(new String[]{candidate1, candidate2, candidate3})));
                    deptBean.add(postBean);
                }
                deptList.add(deptBean);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VotingServer.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("deptList = " + deptList);
        try (ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            oos.writeObject(deptList);
            oos.flush();
        }
    }

    public static void main(String[] args) throws IOException {
        new VotingServer().start();
    }

}
