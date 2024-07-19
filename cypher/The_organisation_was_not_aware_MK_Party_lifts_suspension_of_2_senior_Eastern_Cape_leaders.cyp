```cypher
MERGE (mkParty:Organization {name: "MK Party", description: "A political party"})
MERGE (easternCape:Organization {name: "MK Party Eastern Cape", description: "Eastern Cape branch of MK Party"})
MERGE (kubukeli:Individual {name: "Pumlani Kubukeli", description: "Provincial convener of MK Party Eastern Cape"})
MERGE (ndakisa:Individual {name: "Mawande Ndakisa", description: "Provincial coordinator of MK Party Eastern Cape"})
MERGE (simonNdzele:Individual {name: "Luleka Simon-Ndzele", description: "Provincial organiser and former Buffalo City metro speaker"})
MERGE (otola:Individual {name: "Nozizwe Otola", description: "Buffalo City convener"})
MERGE (ndhlela:Individual {name: "Nhlamulo Ndhlela", description: "MK Party spokesperson"})
MERGE (ramaphosa:Individual {name: "Cyril Ramaphosa", description: "ANC president"})
MERGE (zuma:Individual {name: "Jacob Zuma", description: "Former ANC president"})
MERGE (cope:Organization {name: "COPE", description: "Congress of the People, a South African political party"})
MERGE (news24:Organization {name: "News24", description: "News organization"})

MERGE (suspensionEvent:Event {name: "Suspension of Leaders", description: "Suspension of two senior leaders in the Eastern Cape", date: date("2023-07-10")})
MERGE (liftingSuspensionEvent:Event {name: "Lifting of Suspension", description: "Lifting of suspension of two senior leaders in the Eastern Cape", date: date("2023-07-11")})
MERGE (fraudCase:Event {name: "Nelson Mandela Funeral Fraud Case", description: "Fraud case involving R10 million", date: date("2023-10-14")})

MERGE (mkParty)-[:HAS_BRANCH]->(easternCape)
MERGE (easternCape)-[:HAS_CONVENER]->(kubukeli)
MERGE (easternCape)-[:HAS_COORDINATOR]->(ndakisa)
MERGE (easternCape)-[:HAS_ORGANISER]->(simonNdzele)
MERGE (easternCape)-[:HAS_CONVENER]->(otola)
MERGE (mkParty)-[:HAS_SPOKESPERSON]->(ndhlela)
MERGE (anc:Organization {name: "ANC", description: "African National Congress, a political party in South Africa"})
MERGE (anc)-[:HAS_PRESIDENT]->(ramaphosa)
MERGE (anc)-[:HAD_PRESIDENT]->(zuma)

MERGE (kubukeli)-[:AUTHORED]->(suspensionEvent)
MERGE (suspensionEvent)-[:TARGETED]->(simonNdzele)
MERGE (suspensionEvent)-[:TARGETED]->(otola)
MERGE (liftingSuspensionEvent)-[:TARGETED]->(simonNdzele)
MERGE (liftingSuspensionEvent)-[:TARGETED]->(otola)
MERGE (ndakisa)-[:AUTHORED]->(liftingSuspensionEvent)
MERGE (simonNdzele)-[:INVOLVED_IN]->(fraudCase)

MERGE (kubukeli)-[:REFERRED_TO {date: date("2023-07-10")}]->(news24)
MERGE (ndakisa)-[:COMMENTED_ON {date: date("2023-07-10")}]->(kubukeli)
MERGE (kubukeli)-[:SURROUNDED_BY]->(ramaphosa)
MERGE (kubukeli)-[:SURROUNDED_BY]->(cope)
MERGE (kubukeli)-[:OPPOSED_BY]->(zuma)
```