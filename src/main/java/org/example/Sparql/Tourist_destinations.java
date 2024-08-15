package org.example.Sparql;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class Tourist_destinations {
    public static void main(String[] args) {
        Model model = ModelFactory.createDefaultModel();
        String filePath = "src/main/java/org/example/tourist_destinations.ttl";
        FileManager.get().readModel(model, filePath, "TTL");

        String query1 =
                "PREFIX schema: <http://schema.org/>\n" +
                        "PREFIX ex: <http://example.org/tourist_destinations/>\n" +
                        "\n" +
                        "SELECT ?attraction ?name ?description\n" +
                        "WHERE {\n" +
                        "  ex:Охрид schema:containsPlace ?attraction .\n" +
                        "  ?attraction a schema:TouristAttraction ;\n" +
                        "              schema:name ?name ;\n" +
                        "              schema:description ?description .\n" +
                        "}";
        executeQuery(query1, model, "Query 1: Пребарување на сите туристички атракции во Охрид");

        String query2 =
                "PREFIX schema: <http://schema.org/>\n" +
                        "PREFIX ex: <http://example.org/tourist_destinations/>\n" +
                        "\n" +
                        "SELECT ?museum ?name ?description\n" +
                        "WHERE {\n" +
                        "  ex:Скопје schema:containsPlace ?museum .\n" +
                        "  ?museum a schema:Museum ;\n" +
                        "          schema:name ?name ;\n" +
                        "          schema:description ?description .\n" +
                        "}";
        executeQuery(query2, model, "Query 2: Пребарување на сите музеи во Скопје");

        String query3 =
                "PREFIX schema: <http://schema.org/>\n" +
                        "PREFIX ex: <http://example.org/tourist_destinations/>\n" +
                        "\n" +
                        "SELECT ?attraction ?name ?description\n" +
                        "WHERE {\n" +
                        "  ex:Битола schema:containsPlace ?attraction .\n" +
                        "  ?attraction a schema:TouristAttraction ;\n" +
                        "              schema:name ?name ;\n" +
                        "              schema:description ?description .\n" +
                        "}";
        executeQuery(query3, model, "Query 3: Пребарување на туристички атракции во Битола");


        String query4 =
                "PREFIX schema: <http://schema.org/>\n" +
                        "PREFIX ex: <http://example.org/tourist_destinations/>\n" +
                        "\n" +
                        "SELECT ?place ?name ?description\n" +
                        "WHERE {\n" +
                        "  ex:Тетово schema:containsPlace ?place .\n" +
                        "  ?place a ?type ;\n" +
                        "         schema:name ?name ;\n" +
                        "         schema:description ?description .\n" +
                        "  FILTER(?type IN (schema:TouristAttraction, schema:Museum))\n" +
                        "}";
        executeQuery(query4, model, "Query 4: Пребарување на сите туристички атракции и музеи во Тетово");

        String query5 =
                "PREFIX schema: <http://schema.org/>\n" +
                        "PREFIX ex: <http://example.org/tourist_destinations/>\n" +
                        "\n" +
                        "SELECT ?attraction ?name ?description\n" +
                        "WHERE {\n" +
                        "  ex:Куманово schema:containsPlace ?attraction .\n" +
                        "  ?attraction a schema:TouristAttraction ;\n" +
                        "              schema:name ?name ;\n" +
                        "              schema:description ?description .\n" +
                        "}";
        executeQuery(query5, model, "Query 5: Пребарување на сите туристички атракции во Куманово\n");

        String query6 =
                "PREFIX schema: <http://schema.org/>\n" +
                        "PREFIX ex: <http://example.org/tourist_destinations/>\n" +
                        "\n" +
                        "SELECT ?attraction ?name ?description ?city\n" +
                        "WHERE {\n" +
                        "  ?city a schema:City ;\n" +
                        "        schema:containsPlace ?attraction .\n" +
                        "  ?attraction a schema:TouristAttraction ;\n" +
                        "              schema:name ?name ;\n" +
                        "              schema:description ?description .\n" +
                        "}";
        executeQuery(query6, model, "Query 5: Пребарување на туристички атракции по име во сите градови\n");

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
