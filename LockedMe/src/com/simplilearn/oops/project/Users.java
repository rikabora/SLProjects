package com.simplilearn.oops.project;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author Rika Bora
 * This class performs user directory operations and operations on the files it contains
 *
 */
public class Users {

	private String userName;
	private String drivePath;
	private Files files;
	private List<String> userList;
    
	private Scanner sc=  new Scanner(System.in);
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Files getFiles() {
		return files;
	}

	public void setFiles(Files files) {
		this.files = files;
	}

	public List<String> getUserList() {
		return userList;
	}
	public void setUserList(List<String> list) {
		this.userList = list;
	}

	public String getDrivePath() {
		return drivePath;
	}

	public void setDrivePath(String drivePath) {
		
		this.drivePath = drivePath;
		
	}

	public boolean createFolder(){

		File newUserDirectory=new File(getDrivePath() + getUserName() );
		boolean b = newUserDirectory.mkdir();
		if (b == false) {
			System.out.println("Invalid directory name or Directory already exists");
			return b;
		}
		else {
			System.out.println("Directory Created Successfully");

		}
		return b;

	}

	public List <File> getFileNames() {
		File fileDirectory=new File(getDrivePath() + getUserName() );

		File[] files = fileDirectory.listFiles();
		List<File> fileList=Arrays.asList(files);
		Collections.sort(fileList);
		return fileList;


	}

	public void addFiles(Files file, Scanner sc) {
			
		File f=new File(getDrivePath() + getUserName()+ "\\" + file.getFileName());
		try {
		if (f.createNewFile()) {
			System.out.println("File "+file.getFileName()+" Created");
			
			Files fl = new Files();
			fl.editFile(f,sc);

		}else
			System.out.println("File exists already");
		}catch(Exception e) {
			System.out.println("File name pattern is incorrect, please refrain from using special characters.");
		}
		
	}

public void deleteFile(Files file) {
	File f=new File(getDrivePath() + getUserName()+ "\\" + file.getFileName());
	if(f.delete()) {
		System.out.println("File deleted successfully");
	}else
		System.out.println("File does not exists");
}

public void searchFile(Files file, Scanner sc) {
	File f=new File(getDrivePath() + getUserName()+ "\\" + file.getFileName());
	try {
	if(f.exists()) {
		System.out.println("File "+ file.getFileName() +" Found");
		
	    System.out.println("Do you wish to edit/rewrite the file (Y/N):");
	    this.sc=sc;
	    sc=new Scanner(System.in);
	    String c= sc.next();
	    if (c.equalsIgnoreCase("Y")) {
	    	Files fl = new Files();
			fl.editFile(f,sc);
			return;
	    }
	    if (! (c.equalsIgnoreCase("Y") || c.equalsIgnoreCase("N"))) {
	    	System.out.println("Invalid Entry");
	    	return;
	    }
	    	
	    System.out.println("Do you wish to append the file (Y/N):");
	    this.sc=sc;
	    sc=new Scanner(System.in);
	     c= sc.next();
	    if (c.equalsIgnoreCase("Y")) {
	    	Files fl = new Files();
			fl.appendFile(f,sc);
			return;
	    }
	    if (! (c.equalsIgnoreCase("Y") || c.equalsIgnoreCase("N"))) {
	    	System.out.println("Invalid Entry");
	    	return;
	    }    
	   
	}
	else
		System.out.println("File does not exists");
	}catch(Exception e) {
		System.out.println("Invalid Input");
	}
}

public void closeResources() {
	sc.close();
}

}
