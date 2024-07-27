package org.example.Rdf;
//import org.apache.jena.rdf.model.*;
//import org.apache.jena.util.FileManager;
import org.apache.jena.rdf.model.*;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class Routes_and_transport {
    public static void main(String[] args) {
        Model model = ModelFactory.createDefaultModel();

        String filePath = "src/main/java/org/example/routes_and_transport.ttl";
        String format = "TTL";


        FileManager.get().readModel(model, filePath, format);

        model.write(System.out, "TURTLE");

        System.out.println("Printing with model.listStatements():");
        StmtIterator iter = model.listStatements();

        while (iter.hasNext()) {
            Statement stmt = iter.nextStatement();
            Resource subject = stmt.getSubject();
            Property predicate = stmt.getPredicate();
            RDFNode object = stmt.getObject();

            System.out.print(subject.toString() + " - " + predicate.toString() + " - ");
            if (object instanceof Resource) {
                System.out.println(object.toString());
            } else {
                System.out.println("\"" + object.toString() + "\"");
            }
        }
    }
}
