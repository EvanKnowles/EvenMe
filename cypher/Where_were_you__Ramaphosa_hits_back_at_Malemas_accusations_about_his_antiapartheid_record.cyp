```cypher
MERGE (ramaphosa:Individual {name: "Cyril Ramaphosa", description: "President of South Africa"})
MERGE (malema:Individual {name: "Julius Malema", description: "Leader of EFF"})
MERGE (mantashe:Individual {name: "Gwede Mantashe", description: "Miner and among the first members of NUM"})
MERGE (motlatsi:Individual {name: "James Motlatsi", description: "Former President of NUM"})
MERGE (matlala:Individual {name: "Director Matlala", description: "NUM member"})
MERGE (gungubele:Individual {name: "Mondli Gungubele", description: "NUM member"})
MERGE (num:Organization {name: "National Union of Mineworkers", description: "Union co-founded by Cyril Ramaphosa in the 1980s"})

MERGE (ramaphosa)-[:CO_FOUNDED {date: "1980s"}]->(num)
MERGE (mantashe)-[:MEMBER_OF]->(num)
MERGE (motlatsi)-[:PRESIDENT_OF]->(num)
MERGE (matlala)-[:MEMBER_OF]->(num)
MERGE (gungubele)-[:MEMBER_OF]->(num)

MERGE (event1:Event {name: "Formation of NUM", date: "December 1982", description: "Formation of the National Union of Mineworkers in Klerksdorp"})
MERGE (event2:Event {name: "21-day Strike", description: "NUM's 21-day strike that stopped the entire mining industry in South Africa", date: "1987"})
MERGE (event3:Event {name: "Opening of Parliament Debate", date: "Monday", description: "Debate where Ramaphosa replied to Malema"})
MERGE (event4:Event {name: "Malema's Speech", date: "Friday", description: "Malema's speech implying Ramaphosa was an apartheid collaborator"})

MERGE (num)-[:FORMED_DURING]->(event1)
MERGE (num)-[:ORGANIZED]->(event2)
MERGE (ramaphosa)-[:SPOKE_AT]->(event3)
MERGE (malema)-[:SPOKE_AT]->(event4)

MERGE (outcome1:Outcome {description: "Ramaphosa offers to sit with Malema to discuss political history"})
MERGE (outcome2:Outcome {description: "Malema implies Ramaphosa was an apartheid collaborator"})
MERGE (outcome3:Outcome {description: "Ramaphosa calls for mutual respect in political debates"})

MERGE (event3)-[:RESULTED_IN]->(outcome1)
MERGE (event4)-[:RESULTED_IN]->(outcome2)
MERGE (event3)-[:RESULTED_IN]->(outcome3)

MERGE (da:Party {name: "DA", description: "Democratic Alliance"})
MERGE (anc:Party {name: "ANC", description: "African National Congress"})

MERGE (ramaphosa)-[:MEMBER_OF]->(anc)
MERGE (malema)-[:MEMBER_OF]->(eff:Party {name: "EFF", description: "Economic Freedom Fighters"})
MERGE (ramaphosa)-[:GOVERNMENT_RELATIONSHIP_WITH]->(da)
```