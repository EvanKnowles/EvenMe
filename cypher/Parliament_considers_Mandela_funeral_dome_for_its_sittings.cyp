```cypher
MERGE (event1:Event {name: "Nelson Mandela's Funeral", description: "Funeral of former president Nelson Mandela", date: "2013"})
MERGE (individual1:Individual {name: "Nelson Mandela", description: "Former President of South Africa"})
MERGE (individual2:Individual {name: "Thoko Didiza", description: "National Assembly speaker"})
MERGE (individual3:Individual {name: "Dean Macpherson", description: "Public works minister"})
MERGE (individual4:Individual {name: "Raymond Zondo", description: "Chief Justice"})
MERGE (individual5:Individual {name: "Cyril Ramaphosa", description: "President of South Africa"})
MERGE (individual6:Individual {name: "Angie Motshekga", description: "Defence minister"})
MERGE (individual7:Individual {name: "George Michalakis", description: "DA chief whip"})
MERGE (organization1:Organization {name: "Parliament of South Africa", description: "Legislative body of South Africa"})
MERGE (organization2:Organization {name: "Development Bank of Southern Africa", description: "Managing the rebuilding project"})
MERGE (organization3:Organization {name: "MK Party", description: "Political party"})
MERGE (organization4:Organization {name: "EFF", description: "Economic Freedom Fighters, political party"})
MERGE (organization5:Organization {name: "TimesLIVE", description: "News organization"})
MERGE (organization6:Organization {name: "Cape Town City Hall", description: "Venue for full sittings of parliament"})
MERGE (organization7:Organization {name: "Cape Town International Convention Centre", description: "Venue for first sitting of new parliament"})
MERGE (organization8:Organization {name: "Defence Department", description: "Department responsible for defence"})
MERGE (event2:Event {name: "Parliament's 2024/2025 Budget Vote", description: "Debate on parliament's 2024/2025 budget vote", date: "2023"})
MERGE (event3:Event {name: "January 2022 Fire", description: "Fire that destroyed the Assembly chamber", date: "2022"})
MERGE (event4:Event {name: "Opening of the Seventh Parliament", description: "Opening of the seventh parliament", date: "2023"})
MERGE (event5:Event {name: "Parliament's First Sitting", description: "First sitting of the new parliament", date: "2023"})
MERGE (event6:Event {name: "President's Reply to Debate", description: "President Cyril Ramaphosa's reply to debate on his opening of parliament address", date: "2023"})
MERGE (event7:Event {name: "Thursday Meeting", description: "Meeting to discuss transportation of the dome structure", date: "2023"})

MERGE (event1)-[:INVOLVED]->(individual1)
MERGE (event2)-[:INVOLVED]->(individual2)
MERGE (event2)-[:INVOLVED]->(individual3)
MERGE (event2)-[:INVOLVED]->(organization1)
MERGE (event3)-[:INVOLVED]->(organization1)
MERGE (event4)-[:INVOLVED]->(organization1)
MERGE (event5)-[:INVOLVED]->(organization1)
MERGE (event5)-[:INVOLVED]->(individual4)
MERGE (event6)-[:INVOLVED]->(individual5)
MERGE (event6)-[:INVOLVED]->(organization1)
MERGE (event7)-[:INVOLVED]->(individual3)
MERGE (event7)-[:INVOLVED]->(individual6)
MERGE (event7)-[:INVOLVED]->(organization1)
MERGE (event7)-[:INVOLVED]->(organization8)
MERGE (event7)-[:INVOLVED]->(organization2)
MERGE (event7)-[:INVOLVED]->(individual2)
MERGE (event7)-[:INVOLVED]->(individual7)
MERGE (event7)-[:INVOLVED]->(organization5)
MERGE (event7)-[:INVOLVED]->(organization3)
MERGE (event7)-[:INVOLVED]->(organization4)
MERGE (event7)-[:INVOLVED]->(organization6)
MERGE (event7)-[:INVOLVED]->(organization7)
```