package com.bt.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class Connection {
	public static Socket client;
	public static int port = 6606;
	public static String ip = "192.168.1.38";
	public static String macAddress;
	public static String username;
	public static String aadhar;


	public DataOutputStream dos;
	public DataInputStream dis;

}
