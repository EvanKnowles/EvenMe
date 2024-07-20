```cypher
MERGE (anc:Party {name: "ANC", description: "African National Congress"})
MERGE (fikile:Individual {name: "Fikile Mbalula", description: "ANC secretary-general"})
MERGE (event1:Event {name: "Membership Drop", description: "ANC membership dropped by almost 180,000 people", date: "2021-12 to 2023-12"})
MERGE (event2:Event {name: "Branch Drop", description: "ANC branches dropped by 14%", date: "2021-12 to 2023-12"})
MERGE (event3:Event {name: "Inflation of Membership", description: "Possible bogus membership to inflate numbers before the national conference", date: "2022-12"})
MERGE (event4:Event {name: "General Election", description: "General election where ANC support fell", date: "2024-05-29"})
MERGE (event5:Event {name: "Government of National Unity", description: "GNU formed with 10 other political parties", date: "2024"})
MERGE (cyril:Individual {name: "Cyril Ramaphosa", description: "President installed by GNU"})

MERGE (anc)-[:EXPERIENCED]->(event1)
MERGE (anc)-[:EXPERIENCED]->(event2)
MERGE (anc)-[:EXPERIENCED]->(event3)
MERGE (anc)-[:PARTICIPATED_IN]->(event4)
MERGE (anc)-[:PARTICIPATED_IN]->(event5)
MERGE (event5)-[:INSTALLED]->(cyril)

MERGE (fikile)-[:REPORTED]->(event1)
MERGE (fikile)-[:REPORTED]->(event2)
MERGE (fikile)-[:REPORTED]->(event3)

MERGE (outcome1:Outcome {description: "ANC membership dropped from 866,511 to 688,454", date: "2021-12 to 2023-12"})
MERGE (outcome2:Outcome {description: "ANC branches dropped from 4,417 to 3,425", date: "2021-12 to 2023-12"})
MERGE (outcome3:Outcome {description: "ANC support fell from 57% in 2019 to 40% in 2024", date: "2024-05-29"})

MERGE (event1)-[:RESULTED_IN]->(outcome1)
MERGE (event2)-[:RESULTED_IN]->(outcome2)
MERGE (event4)-[:RESULTED_IN]->(outcome3)

MERGE (province1:Organization {name: "KwaZulu-Natal", description: "Province with significant membership drop"})
MERGE (province2:Organization {name: "Eastern Cape", description: "Province with significant membership drop"})
MERGE (province3:Organization {name: "Gauteng", description: "Province with significant membership drop"})

MERGE (province1)-[:EXPERIENCED]->(event1)
MERGE (province2)-[:EXPERIENCED]->(event1)
MERGE (province3)-[:EXPERIENCED]->(event1)
```