package com.simplilearn.oops.project;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author Rika Bora
 * 
 *
 */

public class LockedMeMenu {

	private static Scanner sc = new Scanner(System.in);
	public static void main(String args[]) {

		Users usr = new Users();

		try {
			Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
			File directoryPath = new File(path.toString()+"//Users");
			String [] userArray = directoryPath.list();

			usr.setDrivePath(directoryPath.toString()+ "\\");
			usr.setUserList(Arrays.asList(userArray));
			System.out.println("WELCOME TO LOCKED ME...");
			System.out.println("");
			userMenu(usr);
		}
		finally {
			sc.close();
			usr.closeResources();

		}

	}
	

	public static String userMenu(Users usr,List <String> userList, String str) {

		try {
			 String userName=str;
				usr.setUserName(userName);
				boolean b = usr.createFolder();
				if (b==true) {
					fileOptions(userName, usr);	
				}else 
					userOptions(userList,usr);
				
			}
		catch(Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	
	public static void userMenu(Users usr) {
		System.out.println("List Of Users");
		List <String> userList = usr.getUserList();

		for(String names: userList) {
			System.out.println(names);
		}

		try {
			String userName = userOptions(userList, usr);
		
			
			if (userList.contains(userName)) {
				usr.setUserName(userName);
				fileOptions(userName, usr);

			}
			else {
				usr.setUserName(userName);
				boolean b = usr.createFolder();
				if (b==true) {
					fileOptions(userName, usr);	
				}else {
					userOptions(userList, usr);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}


	public static String userOptions(List <String> usrLst, Users usr) {
		System.out.println("__________________________________________________");
		System.out.println("Choose User Directory Operation");
		System.out.println("1. Enter User Name");
		System.out.println("2. Create New User");
		System.out.println("3. Exit");

		int option =0;

		try {
			sc = new Scanner(System.in);
			option = sc.nextInt();
		}
		catch(Exception e) {
			System.out.println("Invalid input");
		}

		return selectUserOptions(option,usrLst,usr);

	}

	public static String selectUserOptions(int option, List <String> usrLst, Users user) {
		switch(option){
		case 1:
			System.out.println("Enter Existing User Name :");
			sc= new Scanner(System.in);
			String usr  = sc.nextLine();

			if(!usrLst.contains(usr)) {
				System.out.println("User Does not exists");
				return userOptions(usrLst, user);
			}
			else  
				return usr;


		case 2:
			System.out.println("Enter New User Name :");
			sc=new Scanner(System.in);
			String usrNew  = sc.nextLine();
			if(usrLst.contains(usrNew)) {
				System.out.println("User already exists");
				return userOptions(usrLst,user);
			}
			else
				return userMenu(user,usrLst, usrNew);
			
			
		case 3:
			System.out.println("YOU HAVE EXITED THE APPLICATION SUCCESSFULLY");
			System.exit(0);

		default:
		{
			System.out.println("Please select option 1,2 or 3");
			return userOptions(usrLst, user);

		}

		}

	}
	public static String fileOptions(String userName, Users usr) {
		System.out.println("__________________________________________________");
		System.out.println("Choose File Operation");
		System.out.println("1. Get file names");
		System.out.println("2. Add file");
		System.out.println("3. Delete file");
		System.out.println("4. Search file");
		System.out.println("5. Return to previous menu");
		System.out.println("6. Exit");
		int option=0;
		sc= new Scanner(System.in);
		try {
			option = sc.nextInt();
		}
		catch(Exception e) {
			System.out.println("Invalid Input");
		}

		return selectFileOptions(option, userName, usr);

	}

	public static String selectFileOptions(int option, String userName, Users user) {
		switch(option){
		case 1:
			List <File> files = user.getFileNames();
			System.out.println("List of files under user folder " +userName);
			if (files.isEmpty())
				System.out.println(" *No files present under user "+userName+"*");
			else
				for(File file: files)
					System.out.println(file.getName());
			fileOptions(userName, user);

		case 2:
			Files fl = new Files();
			System.out.println("Enter file name");
			sc = new Scanner(System.in);
			String fileName = sc.nextLine();
			if(fileName.contains(" "))
				fileName.replaceAll(" ", "//");
			fl.setFileName(fileName);
			user.addFiles(fl,sc);
			fileOptions(userName, user);

		case 3:
			Files f2 = new Files();
			System.out.println("Enter file name");
			sc = new Scanner(System.in);
			String fileName1 = sc.nextLine();
			if(fileName1.contains(" "))
				fileName1.replaceAll(" ", "//");

			f2.setFileName(fileName1);
			user.deleteFile(f2);
			fileOptions(userName, user);

		case 4:
			Files f3 = new Files();
			System.out.println("Enter file name");
			sc = new Scanner(System.in);
			String fileName3 = sc.nextLine();
			if(fileName3.contains(" "))
				fileName3.replaceAll(" ", "//");

			f3.setFileName(fileName3);
			user.searchFile(f3,sc);
			fileOptions(userName, user);
		case 5:
			userMenu(user);

		case 6:
			System.out.println("YOU HAVE EXITED THE APPLICATION SUCCESSFULLY");
			System.exit(0);

		default :{
			System.out.println("Please provide inputs between 1,2,3,4 and 5");
			fileOptions(userName, user);
		}

		return null;
		}
	}

}
