package org.example.Sparql;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

public class Cultural_heritage {
    public static void main(String[] args) {
        Model model = ModelFactory.createDefaultModel();
        String filePath = "src/main/java/org/example/cultural_heritage.ttl";
        FileManager.get().readModel(model, filePath, "TTL");

        String query1 =
                "PREFIX schema: <http://schema.org/>\n" +
                        "PREFIX geo: <http://www.geonames.org/ontology#>\n" +
                        "PREFIX ex: <http://example.org/cultural_heritage/>\n" +
                        "\n" +
                        "SELECT ?heritage ?name ?description ?location\n" +
                        "WHERE {\n" +
                        "  ?heritage a schema:Place ;\n" +
                        "            schema:name ?name ;\n" +
                        "            schema:description ?description ;\n" +
                        "            geo:location ?location .\n" +
                        "}";
        executeQuery(query1, model, "Query 1: Пребарување на сите културни објекти");

        String query2 =
                "PREFIX schema: <http://schema.org/>\n" +
                        "PREFIX geo: <http://www.geonames.org/ontology#>\n" +
                        "PREFIX ex: <http://example.org/cultural_heritage/>\n" +
                        "\n" +
                        "SELECT ?event ?name ?description ?location\n" +
                        "WHERE {\n" +
                        "  ?event a schema:Event ;\n" +
                        "         schema:name ?name ;\n" +
                        "         schema:description ?description ;\n" +
                        "         geo:location ?location .\n" +
                        "}";
        executeQuery(query2, model, "Query 2: Пребарување на сите настани");

        String query3 =
                "PREFIX schema: <http://schema.org/>\n" +
                        "PREFIX geo: <http://www.geonames.org/ontology#>\n" +
                        "PREFIX ex: <http://example.org/cultural_heritage/>\n" +
                        "\n" +
                        "SELECT ?custom ?name ?description ?location\n" +
                        "WHERE {\n" +
                        "  ?custom a schema:IntangibleCulturalHeritage ;\n" +
                        "          schema:name ?name ;\n" +
                        "          schema:description ?description ;\n" +
                        "          geo:location ?location .\n" +
                        "}";
        executeQuery(query3, model, "Query 3: Пребарување на сите нематеријални културни обичаи");

        String query4 =
                "PREFIX schema: <http://schema.org/>\n" +
                        "PREFIX geo: <http://www.geonames.org/ontology#>\n" +
                        "PREFIX ex: <http://example.org/cultural_heritage/>\n" +
                        "\n" +
                        "SELECT ?heritage ?name ?description\n" +
                        "WHERE {\n" +
                        "  ?heritage a schema:Place ;\n" +
                        "            schema:name ?name ;\n" +
                        "            schema:description ?description ;\n" +
                        "            geo:location \"Охрид\" .\n" +
                        "}";
        executeQuery(query4, model, "Query 4: Пребарување на културни објекти по град");

        String query5 =
                "PREFIX schema: <http://schema.org/>\n" +
                        "PREFIX geo: <http://www.geonames.org/ontology#>\n" +
                        "PREFIX ex: <http://example.org/cultural_heritage/>\n" +
                        "\n" +
                        "SELECT ?event ?name ?description\n" +
                        "WHERE {\n" +
                        "  ?event a schema:Event ;\n" +
                        "         schema:name ?name ;\n" +
                        "         schema:description ?description ;\n" +
                        "         geo:location \"Скопје\" .\n" +
                        "}";
        executeQuery(query5, model, "Query 5: Пребарување на настани по град");

        String query6 =
                "PREFIX schema: <http://schema.org/>\n" +
                        "PREFIX geo: <http://www.geonames.org/ontology#>\n" +
                        "PREFIX ex: <http://example.org/cultural_heritage/>\n" +
                        "\n" +
                        "SELECT ?custom ?name ?description\n" +
                        "WHERE {\n" +
                        "  ?custom a schema:IntangibleCulturalHeritage ;\n" +
                        "          schema:name ?name ;\n" +
                        "          schema:description ?description ;\n" +
                        "          geo:location \"Разни региони\" .\n" +
                        "}";
        executeQuery(query6, model, "Query 6: Пребарување на нематеријални културни обичаи по регион");

        String query7 =
                "PREFIX schema: <http://schema.org/>\n" +
                        "PREFIX geo: <http://www.geonames.org/ontology#>\n" +
                        "PREFIX ex: <http://example.org/cultural_heritage/>\n" +
                        "\n" +
                        "SELECT ?item ?name ?description\n" +
                        "WHERE {\n" +
                        "  {\n" +
                        "    ?item a schema:Place ;\n" +
                        "          schema:name ?name ;\n" +
                        "          schema:description ?description ;\n" +
                        "          geo:location \"Битола\" .\n" +
                        "  }\n" +
                        "  UNION\n" +
                        "  {\n" +
                        "    ?item a schema:Event ;\n" +
                        "          schema:name ?name ;\n" +
                        "          schema:description ?description ;\n" +
                        "          geo:location \"Битола\" .\n" +
                        "  }\n" +
                        "}";
        executeQuery(query7, model, "Query 7: Пребарување на сите културни објекти и настани во одреден град");
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
