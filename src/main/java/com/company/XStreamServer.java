package com.company;

import java.io.IOException;
import java.io.FileInputStream;
import javax.xml.transform.stream.StreamSource;

import com.thoughtworks.xstream.converters.ConverterMatcher;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

import org.springframework.oxm.xstream.XStreamMarshaller;
import com.thoughtworks.xstream.converters.basic.BooleanConverter;
import com.thoughtworks.xstream.converters.basic.StringConverter;

import com.company.model.*;

public class XStreamServer {
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("xstream-server.xml");
        XStreamServer application = (XStreamServer) appContext.getBean("application");

        System.out.println("[+] Spring context initialized");

        // Unmarshall
        FileInputStream is = null;
        try {
            is = new FileInputStream("contact.xml");

            // No converter "protection"
            //Contact contact1 = (Contact) application.unmarshaller.unmarshal(new StreamSource(is));
            //System.out.println(contact1.getName());

            // Using converter "protection"
            XStreamMarshaller unmarshaller = (XStreamMarshaller) application.unmarshaller;
            ConverterMatcher[] converters = new ConverterMatcher[2];
            converters[0] = new StringConverter();
            converters[1] = new BooleanConverter();
            unmarshaller.setConverters(converters);
            Contact contact2 = (Contact) unmarshaller.unmarshal(new StreamSource(is));
            System.out.println(contact2.getName());

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