```
CREATE (event1:Event {name: "Election 2023", description: "General elections held in 2023", date: "2023-11-05"})
MERGE (individual1:Individual {name: "John Doe", description: "Candidate for the presidency"})
MERGE (individual2:Individual {name: "Jane Smith", description: "Incumbent president"})
MERGE (party1:Party {name: "Democratic Party", description: "Political party of John Doe"})
MERGE (party2:Party {name: "Republican Party", description: "Political party of Jane Smith"})
MERGE (outcome1:Outcome {description: "John Doe wins the election", date: "2023-11-06"})

MERGE (individual1)-[:MEMBER_OF]->(party1)
MERGE (individual2)-[:MEMBER_OF]->(party2)
MERGE (event1)-[:INVOLVES]->(individual1)
MERGE (event1)-[:INVOLVES]->(individual2)
MERGE (event1)-[:RESULTS_IN]->(outcome1)
MERGE (outcome1)-[:BENEFITS]->(individual1)
```