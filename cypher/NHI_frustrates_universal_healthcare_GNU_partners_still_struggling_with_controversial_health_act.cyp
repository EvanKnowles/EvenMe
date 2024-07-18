```cypher
MERGE (event1:Event {name: "NHI Act Signing", description: "President Cyril Ramaphosa signed the NHI Act into law ahead of the 29 May general elections.", date: "2024-05-29"})
MERGE (event2:Event {name: "GNU Formation", description: "Formation of Government of National Unity after the 2024 General Election.", date: "2024"})
MERGE (event3:Event {name: "Press Briefing", description: "John Steenhuisen addressed a press briefing to outline the party's expectations ahead of the Opening of Parliament Address.", date: "2024-07-17"})

MERGE (individual1:Individual {name: "John Steenhuisen", description: "DA leader and Agriculture Minister"})
MERGE (individual2:Individual {name: "Cyril Ramaphosa", description: "President of South Africa"})
MERGE (individual3:Individual {name: "Gwarube", description: "Minister"})

MERGE (company1:Company {name: "Democratic Alliance", description: "Second-largest political party in the Government of National Unity"})
MERGE (company2:Company {name: "African National Congress", description: "Political party in the Government of National Unity"})

MERGE (organization1:Organization {name: "Government of National Unity", description: "Collaborative government negotiated between 11 political parties and the ANC"})

MERGE (outcome1:Outcome {name: "NHI Act Controversy", description: "Controversial National Health Insurance Act remains a sticking point in the GNU"})
MERGE (outcome2:Outcome {name: "Universal Healthcare Agreement", description: "All parties agreed on the need for universal healthcare"})
MERGE (outcome3:Outcome {name: "Funding Model Disagreement", description: "Differences on the funding model for universal healthcare"})
MERGE (outcome4:Outcome {name: "Legal Challenges", description: "Threats of legal action from various parties, healthcare organisations and business groups against the NHI Act"})

MERGE (event1)-[:INVOLVES]->(individual2)
MERGE (event1)-[:RESULTED_IN]->(outcome4)
MERGE (event2)-[:INVOLVES]->(company1)
MERGE (event2)-[:INVOLVES]->(company2)
MERGE (event2)-[:RESULTED_IN]->(organization1)
MERGE (event3)-[:INVOLVES]->(individual1)
MERGE (event3)-[:RESULTED_IN]->(outcome2)

MERGE (individual1)-[:BELONGS_TO]->(company1)
MERGE (individual2)-[:BELONGS_TO]->(company2)

MERGE (company1)-[:PART_OF]->(organization1)
MERGE (company2)-[:PART_OF]->(organization1)

MERGE (outcome1)-[:RELATED_TO]->(event1)
MERGE (outcome1)-[:RELATED_TO]->(event2)
MERGE (outcome1)-[:RELATED_TO]->(event3)
MERGE (outcome2)-[:RELATED_TO]->(event3)
MERGE (outcome3)-[:RELATED_TO]->(event3)
MERGE (outcome4)-[:RELATED_TO]->(event1)
```