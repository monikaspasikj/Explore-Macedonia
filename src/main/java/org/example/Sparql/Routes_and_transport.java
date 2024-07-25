package org.example.Sparql;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

public class Routes_and_transport {
    public static void main(String[] args) {
        Model model = ModelFactory.createDefaultModel();
        String filePath = "src/main/java/org/example/routes_and_transport.ttl";
        FileManager.get().readModel(model, filePath, "TTL");

        String query1 =
                "PREFIX schema: <http://schema.org/> " +
                        "PREFIX ex: <http://example.org/routes_and_transport/> " +
                        "SELECT ?route ?name ?description ?location WHERE { " +
                        "  ?route a schema:Route ; " +
                        "         schema:name ?name ; " +
                        "         schema:description ?description ; " +
                        "         schema:location ?location . " +
                        "}";
        executeQuery(query1, model, "Query 1: Пребарување на сите патни маршути");

        String query2 =
                "PREFIX schema: <http://schema.org/> " +
                        "PREFIX ex: <http://example.org/routes_and_transport/> " +
                        "SELECT ?route ?name ?description WHERE { " +
                        "  ?route a schema:Route ; " +
                        "         schema:name ?name ; " +
                        "         schema:description ?description . " +
                        "  VALUES ?name { \"Скопје - Охрид\" \"Битола - Прилеп - Кавадарци\" \"Скопје - Штип\" \"Тетово - Маврово\" } " +
                        "}";
        executeQuery(query2, model, "Query 2: Пребарување на препорачани патни правци со конкретни имиња");

        String query3 =
                "PREFIX schema: <http://schema.org/> " +
                        "PREFIX ex: <http://example.org/routes_and_transport/> " +
                        "SELECT ?route ?name ?description WHERE { " +
                        "  ?route a schema:Route ; " +
                        "         schema:name ?name ; " +
                        "         schema:description ?description ; " +
                        "         schema:location \"Скопје - Тетово\" . " +
                        "}";
        executeQuery(query3, model, "Query 3: Пребарување на патни маршути по локација");

        String query4 =
                "PREFIX schema: <http://schema.org/> " +
                        "PREFIX ex: <http://example.org/routes_and_transport/> " +
                        "SELECT ?transport ?mode ?description WHERE { " +
                        "  ?transport a schema:Transportation ; " +
                        "             schema:transportationMode ?mode ; " +
                        "             schema:description ?description . " +
                        "}";
        executeQuery(query4, model, "Query 4: Пребарување на сите транспортни можности");

        String query5 =
                "PREFIX schema: <http://schema.org/> " +
                        "PREFIX ex: <http://example.org/routes_and_transport/> " +
                        "SELECT ?transport ?description WHERE { " +
                        "  ?transport a schema:Transportation ; " +
                        "             schema:transportationMode \"Автобус\" ; " +
                        "             schema:description ?description . " +
                        "}";
        executeQuery(query5, model, "Query 5: Пребарување на транспортни можности по тип");
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
