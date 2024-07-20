```cypher
MERGE (e:Event {name: "Capitec Technical Issues", description: "Capitec experienced nationwide technical issues with ATMs and online transactions not working.", date: "2023-10-06"})
MERGE (c:Company {name: "Capitec", description: "A South African bank"})
MERGE (o:Outcome {name: "Service Disruption", description: "Nationwide service issues including cards, ATMs, and online transactions not working."})
MERGE (l:Location {name: "Johannesburg CBD", description: "Central Business District of Johannesburg"})
MERGE (a:Application {name: "Bolt", description: "E-hailing service application"})
MERGE (b:Application {name: "Uber", description: "E-hailing service application"})

MERGE (c)-[:EXPERIENCED]->(e)
MERGE (e)-[:RESULTED_IN]->(o)
MERGE (o)-[:AFFECTED]->(l)
MERGE (o)-[:AFFECTED]->(a)
MERGE (o)-[:AFFECTED]->(b)
```