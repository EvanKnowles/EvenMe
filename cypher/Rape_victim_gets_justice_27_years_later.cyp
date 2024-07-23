```cypher
MERGE (event1:Event {description: "Rape of Siphokazi Nonjobe", date: "1997"})
MERGE (event2:Event {description: "Case opened by Siphokazi Nonjobe", date: "2021"})
MERGE (event3:Event {description: "Trial of Xolani Qanqane", date: "2023"})
MERGE (event4:Event {description: "Sentencing of Xolani Qanqane", date: "2023"})

MERGE (individual1:Individual {name: "Siphokazi Nonjobe", description: "Rape victim", birth_year: 1983})
MERGE (individual2:Individual {name: "Xolani Qanqane", description: "Rapist and former photographer"})
MERGE (individual3:Individual {name: "Luxolo Tyali", description: "National Prosecuting Authority spokesperson in the Eastern Cape"})

MERGE (outcome1:Outcome {description: "Justice served for Siphokazi Nonjobe", date: "2023"})
MERGE (outcome2:Outcome {description: "Xolani Qanqane sentenced to 11 years for rape", date: "2023"})

MERGE (organization1:Organization {name: "National Prosecuting Authority", description: "Prosecuting authority in South Africa"})

MERGE (individual1)-[:VICTIM_OF]->(event1)
MERGE (individual2)-[:PERPETRATOR_OF]->(event1)
MERGE (individual1)-[:OPENED_CASE]->(event2)
MERGE (event2)-[:LED_TO]->(event3)
MERGE (event3)-[:RESULTED_IN]->(event4)
MERGE (event4)-[:OUTCOME]->(outcome1)
MERGE (event4)-[:OUTCOME]->(outcome2)
MERGE (individual2)-[:SENTENCED_IN]->(event4)
MERGE (individual3)-[:SPOKESPERSON_FOR]->(organization1)
MERGE (organization1)-[:INVOLVED_IN]->(event3)
MERGE (organization1)-[:INVOLVED_IN]->(event4)
```