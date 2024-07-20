```cypher
MERGE (event1:Event {name: "Opening of Parliament Address", date: "Thursday evening"})
  ON CREATE SET event1.description = "Cyril Ramaphosa's Opening of Parliament Address"

MERGE (individual1:Individual {name: "Cyril Ramaphosa"})
  ON CREATE SET individual1.description = "President of South Africa"

MERGE (individual2:Individual {name: "John Steenhuisen"})
  ON CREATE SET individual2.description = "DA leader and member of Ramaphosa's Cabinet"

MERGE (individual3:Individual {name: "Aaron Motsoaledi"})
  ON CREATE SET individual3.description = "Health Minister"

MERGE (organization1:Organization {name: "GNU"})
  ON CREATE SET organization1.description = "Government of National Unity"

MERGE (party1:Party {name: "DA"})
  ON CREATE SET party1.description = "Democratic Alliance"

MERGE (party2:Party {name: "FF Plus"})
  ON CREATE SET party2.description = "Freedom Front Plus"

MERGE (company1:Company {name: "NHI"})
  ON CREATE SET company1.description = "National Health Insurance"

MERGE (event2:Event {name: "First debate of seventh Parliament", date: "Last week"})
  ON CREATE SET event2.description = "First debate of seventh Parliament on the health budget"

MERGE (event3:Event {name: "NHI Act signed into law", date: "Two weeks before the elections"})
  ON CREATE SET event3.description = "NHI Act signed into law by Cyril Ramaphosa"

MERGE (event4:Event {name: "DA leader's statement on NHI", date: "Wednesday"})
  ON CREATE SET event4.description = "John Steenhuisen's statement on NHI"

MERGE (outcome1:Outcome {name: "DA opposition to NHI"})
  ON CREATE SET outcome1.description = "DA remains staunchly opposed to the NHI in its current form"

MERGE (outcome2:Outcome {name: "GNU parties seek consensus"})
  ON CREATE SET outcome2.description = "GNU parties would seek consensus on NHI"

MERGE (outcome3:Outcome {name: "NHI Act resistance"})
  ON CREATE SET outcome3.description = "Resistance from medical practitioners, business community, and opposition parties"

MERGE (outcome4:Outcome {name: "GNU partners opposition"})
  ON CREATE SET outcome4.description = "GNU partners DA and FF Plus expressed opposition to NHI"

MERGE (outcome5:Outcome {name: "Health Minister's plea for NHI"})
  ON CREATE SET outcome5.description = "Aaron Motsoaledi's impassioned plea for NHI"

MERGE (outcome6:Outcome {name: "Steenhuisen's statement on NHI"})
  ON CREATE SET outcome6.description = "John Steenhuisen's statement that NHI will frustrate universal access to basic healthcare"

MERGE (individual1)-[:ADDRESSED]->(event1)
MERGE (individual1)-[:COMMENTED_ON]->(company1)
MERGE (individual1)-[:SIGNED]->(event3)
MERGE (individual2)-[:MADE_STATEMENT]->(event4)
MERGE (individual2)-[:OPPOSES]->(company1)
MERGE (individual3)-[:MADE_PLEA]->(outcome5)
MERGE (organization1)-[:SEEKS_CONSENSUS_ON]->(company1)
MERGE (party1)-[:OPPOSES]->(company1)
MERGE (party2)-[:OPPOSES]->(company1)
MERGE (event1)-[:RESULTED_IN]->(outcome2)
MERGE (event2)-[:RESULTED_IN]->(outcome4)
MERGE (event3)-[:RESULTED_IN]->(outcome3)
MERGE (event4)-[:RESULTED_IN]->(outcome6)
```