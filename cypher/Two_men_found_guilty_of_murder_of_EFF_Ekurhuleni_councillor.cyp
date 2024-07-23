```cypher
MERGE (court:Organization {name: "Johannesburg high court", description: "High court in Johannesburg"})
MERGE (simon:Individual {name: "Simon Mahuse", age: 28, description: "Convicted of murder and other crimes"})
MERGE (frans:Individual {name: "Frans Ntshumayelo", age: 29, description: "Convicted of murder and other crimes"})
MERGE (koketso:Individual {name: "Koketso Mojatau", description: "EFF Ekurhuleni councillor, murder victim"})
MERGE (siboniso:Individual {name: "Siboniso Sokhele", description: "Friend of Koketso Mojatau, murder victim"})
MERGE (eff:Party {name: "EFF", description: "Economic Freedom Fighters"})
MERGE (empd:Organization {name: "Ekurhuleni Metro Police Department", description: "Police department in Ekurhuleni"})
MERGE (phindi:Individual {name: "Phindi Mjonondwane", description: "National Prosecuting Authority spokesperson"})
MERGE (matthews:Individual {name: "Matthews Rampyapedi", description: "Senior prosecutor"})
MERGE (event1:Event {name: "Murder of Koketso Mojatau and Siboniso Sokhele", date: "2023-04", description: "Murder of EFF councillor and his friend"})
MERGE (event2:Event {name: "Conviction of Simon Mahuse and Frans Ntshumayelo", date: "2023-09-25", description: "Conviction for multiple violent crimes"})
MERGE (event3:Event {name: "Arrest of Simon Mahuse and Frans Ntshumayelo", date: "2022-04-23", description: "Arrest following a tip-off and shoot-out"})
MERGE (event4:Event {name: "Series of violent crimes", date: "2022-01 to 2022-04", description: "Crimes committed by Mahuse and Ntshumayelo"})

MERGE (simon)-[:COMMITTED]->(event1)
MERGE (frans)-[:COMMITTED]->(event1)
MERGE (koketso)-[:VICTIM_OF]->(event1)
MERGE (siboniso)-[:VICTIM_OF]->(event1)
MERGE (court)-[:CONVICTED]->(simon)
MERGE (court)-[:CONVICTED]->(frans)
MERGE (event2)-[:OUTCOME_OF]->(event1)
MERGE (event3)-[:OUTCOME_OF]->(event4)
MERGE (empd)-[:ARRESTED]->(simon)
MERGE (empd)-[:ARRESTED]->(frans)
MERGE (phindi)-[:SPOKESPERSON_FOR]->(court)
MERGE (matthews)-[:PROSECUTED]->(simon)
MERGE (matthews)-[:PROSECUTED]->(frans)
MERGE (koketso)-[:MEMBER_OF]->(eff)
```