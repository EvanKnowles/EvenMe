```cypher
MERGE (event:Event {name: "President Cyril Ramaphosa's Opening of Parliament Speech", description: "President Cyril Ramaphosa's opening of parliament speech", date: "2023-10-05"})
MERGE (individual1:Individual {name: "Cyril Ramaphosa", description: "President of South Africa"})
MERGE (individual2:Individual {name: "John Hlophe", description: "Leader of the opposition in parliament"})
MERGE (party:Party {name: "MK Party", description: "Political party in South Africa"})
MERGE (outcome:Outcome {description: "Debate on President Cyril Ramaphosa's opening of parliament speech", date: "2023-10-06"})

MERGE (individual1)-[:GAVE_SPEECH]->(event)
MERGE (individual2)-[:MEMBER_OF]->(party)
MERGE (individual2)-[:REACTED_TO {description: "Scathing reaction, calling Ramaphosa a sophisticated liar"}]->(event)
MERGE (event)-[:LED_TO]->(outcome)
```