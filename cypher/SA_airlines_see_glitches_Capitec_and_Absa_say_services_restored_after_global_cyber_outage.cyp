```cypher
MERGE (e:Event {name: "Global Software Update Disruption", description: "A software update caused disruptions in computer systems globally.", date: "2023-10-06"})
MERGE (c1:Company {name: "Capitec Bank", description: "A South African bank."})
MERGE (c2:Company {name: "Absa", description: "A South African bank operating in Africa."})
MERGE (c3:Company {name: "CrowdStrike", description: "A cybersecurity firm."})
MERGE (c4:Company {name: "SAA", description: "South African Airways."})
MERGE (c5:Company {name: "Airlink", description: "A privately-owned regional airline."})
MERGE (c6:Company {name: "FlySafair", description: "A low-cost airline."})

MERGE (o1:Outcome {description: "Services of Capitec Bank restored", date: "2023-10-06"})
MERGE (o2:Outcome {description: "Services of Absa restored", date: "2023-10-06"})
MERGE (o3:Outcome {description: "Intermittent technical outage at SAA contact centre", date: "2023-10-06"})
MERGE (o4:Outcome {description: "Airlink IT network down", date: "2023-10-06"})
MERGE (o5:Outcome {description: "FlySafair card payment issues", date: "2023-10-06"})

MERGE (c1)-[:EXPERIENCED]->(e)
MERGE (c2)-[:EXPERIENCED]->(e)
MERGE (c3)-[:INVOLVED_IN]->(e)
MERGE (c4)-[:EXPERIENCED]->(e)
MERGE (c5)-[:EXPERIENCED]->(e)
MERGE (c6)-[:EXPERIENCED]->(e)

MERGE (c1)-[:RESULTED_IN]->(o1)
MERGE (c2)-[:RESULTED_IN]->(o2)
MERGE (c4)-[:RESULTED_IN]->(o3)
MERGE (c5)-[:RESULTED_IN]->(o4)
MERGE (c6)-[:RESULTED_IN]->(o5)
```