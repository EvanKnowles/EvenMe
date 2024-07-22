```cypher
MERGE (event1:Event {description: "Abduction of Kelvin Naidoo", date: "2023-10-06"})
MERGE (individual1:Individual {name: "Kelvin Naidoo", age: 51, description: "Senior employee at Auto-X"})
MERGE (company1:Company {name: "Auto-X"})
MERGE (organization1:Organization {name: "Eastern Cape Police"})
MERGE (individual2:Individual {name: "Sandra Janse van Rensburg", title: "Captain", description: "Police spokesperson"})
MERGE (vehicle1:Outcome {description: "Mercedes-Benz"})
MERGE (vehicle2:Outcome {description: "Nissan NP200", status: "Hijacked", location: "Motherwell", date: "2023-09"})
MERGE (location1:Outcome {description: "Lindsey Road, Korsten"})
MERGE (location2:Outcome {description: "Nelson Mandela Bay metro"})
MERGE (individual3:Individual {name: "Errol Kleinhans", title: "Detective Warrant Officer", contact: "083-243-4567"})
MERGE (organization2:Organization {name: "SAPS Algoa Park"})
MERGE (organization3:Organization {name: "Crime Stop", contact: "08600-10111"})
MERGE (individual4:Individual {name: "Mnyamezeli Tete", description: "Suspected kingpin"})
MERGE (individual5:Individual {name: "Running Chen", description: "North End windscreen repair businessman"})
MERGE (individual6:Individual {name: "Sonam Gajjar", description: "Kariega mother"})

MERGE (event1)-[:INVOLVES]->(individual1)
MERGE (individual1)-[:WORKS_AT]->(company1)
MERGE (event1)-[:INVOLVES]->(organization1)
MERGE (organization1)-[:HAS_SPOKESPERSON]->(individual2)
MERGE (event1)-[:INVOLVES]->(vehicle1)
MERGE (event1)-[:INVOLVES]->(vehicle2)
MERGE (event1)-[:LOCATION]->(location1)
MERGE (event1)-[:LOCATION]->(location2)
MERGE (organization1)-[:HAS_DETECTIVE]->(individual3)
MERGE (individual3)-[:WORKS_AT]->(organization2)
MERGE (organization3)-[:CONTACT]->(organization1)
MERGE (location2)-[:HAS_EVENT]->(event1)
MERGE (location2)-[:HAS_EVENT]->(event2:Event {description: "Abduction of Running Chen", date: "2023-04"})
MERGE (location2)-[:HAS_EVENT]->(event3:Event {description: "Abduction of Sonam Gajjar", date: "2023-03"})
MERGE (event2)-[:INVOLVES]->(individual4)
MERGE (event2)-[:INVOLVES]->(individual5)
MERGE (event3)-[:INVOLVES]->(individual4)
MERGE (event3)-[:INVOLVES]->(individual6)
```