package fr.univrouen.cv24.services;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.InputStream;

@Service
public class XmlValidationService {

    private final Schema schema;

    public XmlValidationService() throws SAXException, IOException {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try (InputStream xsdInputStream = new ClassPathResource("xsd/cv24.xsd").getInputStream()) {
            this.schema = factory.newSchema(new StreamSource(xsdInputStream));
        } catch (IOException e) {
            throw new IOException("Could not load XSD file: " + e.getMessage(), e);
        }
    }

    public boolean validateXml(StreamSource xmlSource) {
        try {
            Validator validator = schema.newValidator();
            validator.validate(xmlSource);
            return true;
        } catch (IOException | SAXException e) {
            System.out.println("Validation error: " + e.getMessage());
            return false;
        }
    }
}
