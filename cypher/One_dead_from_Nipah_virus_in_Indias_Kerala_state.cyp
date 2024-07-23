```cypher
MERGE (event:NipahOutbreak {description: "Nipah virus outbreak in Kerala, India", date: "2023"})
MERGE (individual1:Individual {name: "14-year-old boy", description: "Infected boy who died from Nipah virus"})
MERGE (individual2:Individual {name: "Veena George", description: "State health minister of Kerala"})
MERGE (individual3:Individual {name: "Anoop Kumar", description: "Director of critical care medicine at Aster MIMS Hospital in Calicut"})
MERGE (organization1:Organization {name: "World Health Organization", abbreviation: "WHO", description: "Global health organization"})
MERGE (organization2:Organization {name: "Aster MIMS Hospital", description: "Hospital in Calicut"})
MERGE (organization3:Organization {name: "Kerala State Government", description: "State government of Kerala, India"})
MERGE (company:Company {name: "Reuters", description: "News agency"})

MERGE (individual1)-[:DIED_FROM]->(event)
MERGE (individual2)-[:REPORTED]->(event)
MERGE (individual3)-[:REPORTED]->(event)
MERGE (organization1)-[:CLASSIFIED_AS_PRIORITY_PATHOGEN]->(event)
MERGE (organization2)-[:INVOLVED_IN]->(event)
MERGE (organization3)-[:ISSUED_ORDERS]->(event)
MERGE (company)-[:REPORTED_ON]->(event)

MERGE (outcome1:Outcome {description: "Death of infected boy", date: "2023-09-10"})
MERGE (outcome2:Outcome {description: "Identification of 60 high-risk individuals", date: "2023"})
MERGE (outcome3:Outcome {description: "Setup of 25 committees for Nipah control", date: "2023"})
MERGE (outcome4:Outcome {description: "Isolation of family members and high-risk individuals", date: "2023"})

MERGE (event)-[:RESULTED_IN]->(outcome1)
MERGE (event)-[:RESULTED_IN]->(outcome2)
MERGE (event)-[:RESULTED_IN]->(outcome3)
MERGE (event)-[:RESULTED_IN]->(outcome4)
```