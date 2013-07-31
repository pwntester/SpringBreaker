package com.company;

import com.company.model.Contact;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.io.IOException;

public class CastorServer {
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("castor-server.xml");
        CastorServer application = (CastorServer) appContext.getBean("application");

        System.out.println("[+] Spring context initialized...");

        // Unmarshall
        FileInputStream is = null;
        try {
            is = new FileInputStream("contact.xml");
            Contact contact = (Contact) application.unmarshaller.unmarshal(new StreamSource(is));
            System.out.println(contact.getName());
        } catch (Exception e){ 
            e.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
            }
        }

        System.out.println("[+] Object unmarshalled");

    }
}