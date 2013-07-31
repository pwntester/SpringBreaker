package com.company;

import com.company.model.Contact;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

public class SerializationServer {
	
	public static void main (String[] args) {

		try {
			FileInputStream fileIn =new FileInputStream("proxy.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Contact contact = (Contact) in.readObject();
			System.out.println("[+] Running method in deserialized object");
			System.out.println("[+] Payload: " + contact.getName());
			in.close();
			fileIn.close();
       	} catch(IOException i) {
			i.printStackTrace();
			System.exit(-1);
        } catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();
			System.exit(-1);
        }

	}
}

