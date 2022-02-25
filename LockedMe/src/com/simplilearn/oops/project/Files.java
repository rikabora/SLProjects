package com.simplilearn.oops.project;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author Rika Bora
 * This class performs file operations
 *
 */
public class Files {
	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public void editFile(File f, Scanner sc) {
		FileOutputStream fs =null;
		try {
		fs = new FileOutputStream(f);
		System.out.println("Enter file content");
		sc = new Scanner(System.in);
			String data = sc.nextLine();
			fs.write(data.getBytes());
			System.out.println("File Content saved successfully");
		}catch(Exception e) {
			
		}finally {
			try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	 
	public void appendFile(File f, Scanner sc) {

		FileWriter fw =null;
		try {
		fw = new FileWriter(f,true);
		System.out.println("Enter file content");
		sc = new Scanner(System.in);
			String data = sc.nextLine();
			fw.write(data);  
			System.out.println("File Content saved successfully");
		}catch(Exception e) {
			
		}finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	
	}

	
	
}
