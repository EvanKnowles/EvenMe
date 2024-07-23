```cypher
MERGE (event1:Event {description: "Bail proceedings for Gauteng nurse Sithembile Xulu", date: "2023-10-02"})
MERGE (individual1:Individual {name: "Sithembile Xulu", description: "Gauteng nurse"})
MERGE (individual2:Individual {name: "Simon Mogale", description: "Gardener"})
MERGE (individual3:Individual {name: "Busiswa Nxumalo", description: "21-year-old domestic worker"})
MERGE (individual4:Individual {name: "Njabulo Nxumalo", description: "Busiswa's uncle"})
MERGE (organization1:Organization {name: "Lenasia magistrate's court"})
MERGE (event2:Event {description: "Service delivery protest on the N12", date: "2023-10-01"})
MERGE (organization2:Organization {name: "Johannesburg metro police"})
MERGE (individual5:Individual {name: "Xolani Fihla", description: "Johannesburg metro police spokesperson"})

MERGE (individual1)-[:INVOLVED_IN]->(event1)
MERGE (individual2)-[:INVOLVED_IN]->(event1)
MERGE (individual3)-[:VICTIM_OF]->(event1)
MERGE (individual4)-[:ATTENDED]->(event1)
MERGE (organization1)-[:HELD]->(event1)
MERGE (event2)-[:DISRUPTED]->(event1)
MERGE (organization2)-[:CONTAINED]->(event2)
MERGE (individual5)-[:SPOKESPERSON_FOR]->(organization2)

MERGE (individual1)-[:CHARGED_WITH {charges: "murder, fraud, defeating the ends of justice"}]->(event1)
MERGE (individual2)-[:CHARGED_WITH {charges: "murder, fraud, defeating the ends of justice"}]->(event1)
MERGE (individual1)-[:ACCUSED_OF {accusation: "orchestrating the murder of Busiswa Nxumalo to cash in R6m from three life policies"}]->(event1)
MERGE (event1)-[:OUTCOME {description: "Postponed due to protest"}]->(event2)
```