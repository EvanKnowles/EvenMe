package za.co.knonchalant.evenme;

import org.neo4j.driver.*;
import org.neo4j.driver.Record;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Relationship;

import java.util.*;

public class Neo {
    private final Driver driver;

    private Neo(Driver driver) {
        this.driver = driver;
    }

    public static Neo connect(Environment environment) {
        Driver driver = GraphDatabase.driver(environment.neo4jURI, AuthTokens.basic(environment.neo4jUser, environment.neo4jPassword));
        return new Neo(driver);
    }

    public void run(String query) {
        try (Session session = driver.session(SessionConfig.defaultConfig())) {
            session.writeTransaction(transaction -> {
                transaction.run(query);
                return null;
            });
        }
    }

    List<Node> find(String label, Map<String, String> args) {
        StringBuilder query = new StringBuilder("match (n:");
        query.append(label).append(" {");
        for (Map.Entry<String, String> argEntry : args.entrySet()) {
            query.append(argEntry.getKey()).append(":\"").append(argEntry.getValue().replaceAll("\"", "")).append("\",");
        }
        String substring = query.substring(0, query.length() - 1);
        substring += "}) return n";

        final String finalQuery = substring;
        try (Session session = driver.session(forDB())) {
            List<Record> value = session.writeTransaction(transaction -> {
                Result result = transaction.run(finalQuery);
                return result.list();
            });

            ArrayList<Node> nodes = new ArrayList<>();
            for (Record record : value) {
                nodes.add(record.get(0).asNode());
            }

            return nodes;
        }
    }

    private static SessionConfig forDB() {
        return SessionConfig.defaultConfig();
        // I have a suspicion that for local DB, this is needed:
//        return SessionConfig.forDatabase("evenme");
    }

    Node create(String label, Map<String, Object> params) {
        StringBuilder paramQuery = new StringBuilder();
        for (Map.Entry<String, Object> stringObjectEntry : params.entrySet()) {
            paramQuery.append("set n.").append(stringObjectEntry.getKey()).append(" = $").append(stringObjectEntry.getKey()).append(" ");
        }

        String create = String.format("CREATE (n:" + label + ") %s RETURN n;", paramQuery.toString());
        try (Session session = driver.session(forDB())) {
            Value value = session.writeTransaction(transaction -> {
                Result result = transaction.run(create, params);
                return result.single().get(0);
            });
            return value.asNode();
        }
    }

    public Relationship link(Node one, Node two, String relationship) {
        String query = "match (one), (two) where ID(one) = $one and ID(two) = $two \n" +
                "merge (one)-[r:" + relationship + "]->(two)\n" +
                "return r;";

        HashMap<String, Object> params = new HashMap<>();
        params.put("one", one.id());
        params.put("two", two.id());

        try (Session session = driver.session(forDB())) {
            Value value = session.writeTransaction(transaction -> {
                Result result = transaction.run(query, params);
                return result.single().get(0);
            });
            return value.asRelationship();
        }
    }

    public Node update(Node node, Map<String, String> newParams) {
        String queryPattern = "match (one) where ID(one) = $one\n" +
                "%s" +
                "return one;";

        StringBuilder paramQuery = new StringBuilder();
        for (Map.Entry<String, String> stringObjectEntry : newParams.entrySet()) {
            paramQuery.append("set one.").append(stringObjectEntry.getKey()).append(" = $").append(stringObjectEntry.getKey()).append(" ");
        }

        String query = String.format(queryPattern, paramQuery.toString());
        HashMap<String, Object> params = new HashMap<>(newParams);
        params.put("one", node.id());

        try (Session session = driver.session(forDB())) {
            Value value = session.writeTransaction(transaction -> {
                Result result = transaction.run(query, params);
                return result.single().get(0);
            });
            return value.asNode();
        }
    }
}
