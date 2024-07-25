package org.example.Sparql;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

public class Culinary_experiences {
    public static void main(String[] args) {
        Model model = ModelFactory.createDefaultModel();
        String filePath = "src/main/java/org/example/culinary_experiences.ttl";
        FileManager.get().readModel(model, filePath, "TTL");

        String query1 =
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                        "PREFIX schema: <http://schema.org/>\n" +
                        "SELECT ?experience ?name ?description ?location\n" +
                        "WHERE {\n" +
                        "  ?experience rdf:type schema:Restaurant ;\n" +
                        "              schema:name ?name ;\n" +
                        "              schema:description ?description ;\n" +
                        "              schema:location ?location .\n" +
                        "}\n" +
                        "LIMIT 10";
        executeQuery(query1, model, "Query 1: Пребарување на кулинарско искуство со име, опис и локација:");

        String query2 =
                "PREFIX schema: <http://schema.org/>\n" +
                        "SELECT ?experience ?name ?description\n" +
                        "WHERE {\n" +
                        "  ?experience a schema:Restaurant ;\n" +
                        "              schema:name ?name ;\n" +
                        "              schema:description ?description ;\n" +
                        "              schema:location \"Скопје\" .\n" +
                        "}\n" +
                        "LIMIT 10";
        executeQuery(query2, model, "Query 2: Пребарување на кулинарски искуства по локација");

        String query3 =
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                        "PREFIX schema: <http://schema.org/>\n" +
                        "SELECT ?name ?location\n" +
                        "WHERE {\n" +
                        "  ?experience rdf:type schema:Restaurant ;\n" +
                        "              schema:name ?name ;\n" +
                        "              schema:location ?location .\n" +
                        "}";
        executeQuery(query3, model, "Query 3: Пребарување на сите ресторани и нивните локации");

        String query4 =
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                        "PREFIX schema: <http://schema.org/>\n" +
                        "SELECT ?description ?location\n" +
                        "WHERE {\n" +
                        "  ?experience rdf:type schema:Restaurant ;\n" +
                        "              schema:name \"Ресторан Мора\" ;\n" +
                        "              schema:description ?description ;\n" +
                        "              schema:location ?location .\n" +
                        "}";
        executeQuery(query4, model, "Query 4: Пребарување на ресторан според име");

    }

    private static void executeQuery(String queryString, Model model, String queryDescription) {
        System.out.println(queryDescription);
        Query query = QueryFactory.create(queryString);
        try (QueryExecution qe = QueryExecutionFactory.create(query, model)) {
            ResultSet results = qe.execSelect();
            ResultSetFormatter.out(System.out, results, query);
        }
        System.out.println();
    }
}
