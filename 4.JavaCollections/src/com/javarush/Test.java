package com.javarush;

import com.fasterxml.jackson.core.JsonProcessingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

public class Test {

    public static void main(String[] args) throws NoSuchAlgorithmException, JsonProcessingException, JAXBException {
        String logProperties = Test.class.getPackage().getName();
        Path path = Paths.get(logProperties).toAbsolutePath();
        System.out.println(path.toString());
    }

    public static String toXmlWithComment(Object obj, String tagName, String comment) throws JAXBException {
        StringBuilder result = new StringBuilder();
        comment = "<!--" + comment + "-->\n";

        StringWriter stringWriter = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(obj, stringWriter);

        if (!stringWriter.toString().contains(tagName)) return stringWriter.toString();

        for (String s : stringWriter.toString().split("\n")) {
            if (s.contains(tagName) && !s.contains("CDATA")) {
                result.append(comment);
                result.append(s);
            } else
                result.append(s);
        }

        return result.toString();
    }
}