```cypher
MERGE (event1:Event {name: "Debate on the opening of parliament address", date: "2023-10-06"})
  ON CREATE SET event1.description = "Debate on the opening of parliament address on Friday"

MERGE (individual1:Individual {name: "Naledi Chirwa"})
  ON CREATE SET individual1.description = "EFF MP"

MERGE (individual2:Individual {name: "Cyril Ramaphosa"})
  ON CREATE SET individual2.description = "President of South Africa"

MERGE (party1:Party {name: "EFF"})
  ON CREATE SET party1.description = "Economic Freedom Fighters"

MERGE (party2:Party {name: "ANC"})
  ON CREATE SET party2.description = "African National Congress"

MERGE (organization1:Organization {name: "VBS Mutual Bank"})
  ON CREATE SET organization1.description = "VBS Mutual Bank involved in looting saga"

MERGE (individual3:Individual {name: "Tshifhiwa Matodzi"})
  ON CREATE SET individual3.description = "Former VBS Mutual Bank chairperson"

MERGE (event2:Event {name: "Jobs Summit", date: "2018"})
  ON CREATE SET event2.description = "Jobs summit launched by Cyril Ramaphosa"

MERGE (event3:Event {name: "Presidential Employment Stimulus Programme"})
  ON CREATE SET event3.description = "Programme to create work and livelihood opportunities"

MERGE (event4:Event {name: "Ramaphosa's speech", date: "2023-10-05"})
  ON CREATE SET event4.description = "Speech by Cyril Ramaphosa on Thursday"

MERGE (event5:Event {name: "Standing ovation for Naledi Chirwa"})
  ON CREATE SET event5.description = "EFF and MK Party MPs gave Naledi Chirwa a standing ovation"

MERGE (party3:Party {name: "MK Party"})
  ON CREATE SET party3.description = "MK Party"

MERGE (event6:Event {name: "Claims by Tshifhiwa Matodzi"})
  ON CREATE SET event6.description = "Claims made by Tshifhiwa Matodzi in his leaked affidavit"

MERGE (individual1)-[:PARTICIPATED_IN]->(event1)
MERGE (individual2)-[:MENTIONED_IN]->(event1)
MERGE (party1)-[:PARTICIPATED_IN]->(event1)
MERGE (individual1)-[:CRITICIZED]->(individual2)
MERGE (individual1)-[:MENTIONED]->(event2)
MERGE (individual1)-[:MENTIONED]->(event3)
MERGE (individual2)-[:LAUNCHED]->(event2)
MERGE (individual2)-[:MENTIONED]->(event3)
MERGE (individual2)-[:GAVE_SPEECH]->(event4)
MERGE (individual1)-[:RECEIVED_OVATION]->(event5)
MERGE (party1)-[:GAVE_OVATION]->(event5)
MERGE (party3)-[:GAVE_OVATION]->(event5)
MERGE (individual3)-[:MADE_CLAIMS]->(event6)
MERGE (organization1)-[:INVOLVED_IN]->(event6)
MERGE (party1)-[:RECEIVED_DONATIONS]->(event6)
MERGE (party1)-[:OPPOSES]->(party2)
MERGE (party1)-[:SUPPORTS]->(individual1)
MERGE (party2)-[:LEADS]->(individual2)
```