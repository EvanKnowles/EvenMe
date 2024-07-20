```cypher
MERGE (e:Event {name: "Government Shutdown", description: "A partial shutdown of the federal government"})
MERGE (i:Individual {name: "John Doe", description: "Senator from State X"})
MERGE (p:Party {name: "Democratic Party", description: "One of the two major political parties in the United States"})
MERGE (p2:Party {name: "Republican Party", description: "One of the two major political parties in the United States"})
MERGE (o:Organization {name: "Congress", description: "The legislative branch of the United States federal government"})
MERGE (e)-[:INVOLVES]->(i)
MERGE (i)-[:MEMBER_OF]->(p)
MERGE (o)-[:INCLUDES]->(i)
MERGE (e)-[:INVOLVES]->(p)
MERGE (e)-[:INVOLVES]->(p2)
MERGE (e)-[:OCCURRED_ON]->(d:Date {date: "2023-10-01"})
```