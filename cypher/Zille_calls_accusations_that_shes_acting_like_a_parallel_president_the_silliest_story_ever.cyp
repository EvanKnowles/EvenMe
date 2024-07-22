```cypher
MERGE (event1:Event {name: "Interview with Sunday Times", date: "2023-10-05"})
  ON CREATE SET event1.description = "Gwede Mantashe's interview with the Sunday Times where he made remarks about Helen Zille."

MERGE (individual1:Individual {name: "Gwede Mantashe"})
  ON CREATE SET individual1.description = "ANC chairperson and Mineral and Petroleum Resources Minister"

MERGE (individual2:Individual {name: "Helen Zille"})
  ON CREATE SET individual2.description = "DA federal council chairperson"

MERGE (individual3:Individual {name: "Khumbudzo Ntshavheni"})
  ON CREATE SET individual3.description = "Minister in the Presidency"

MERGE (party1:Party {name: "ANC"})
  ON CREATE SET party1.description = "African National Congress"

MERGE (party2:Party {name: "DA"})
  ON CREATE SET party2.description = "Democratic Alliance"

MERGE (organization1:Organization {name: "GNU"})
  ON CREATE SET organization1.description = "Government of National Unity"

MERGE (company1:Company {name: "Sunday Times"})
  ON CREATE SET company1.description = "Publication that reported the interview"

MERGE (company2:Company {name: "Newzroom Afrika"})
  ON CREATE SET company2.description = "Media company where Helen Zille gave an interview"

MERGE (event2:Event {name: "Opening of Parliament Address", date: "2023-10-06"})
  ON CREATE SET event2.description = "Ramaphosa's address setting out government's programme for the term"

MERGE (event3:Event {name: "Debate on Opening of Parliament Address", date: "2023-10-07"})
  ON CREATE SET event3.description = "Debate where GNU parties expressed support for the programme and called for unity"

MERGE (individual1)-[:GAVE_INTERVIEW]->(event1)
MERGE (individual2)-[:MENTIONED_IN]->(event1)
MERGE (individual2)-[:GAVE_INTERVIEW]->(company2)
MERGE (individual3)-[:BRIEFED_MEDIA]->(event2)
MERGE (individual1)-[:MEMBER_OF]->(party1)
MERGE (individual2)-[:MEMBER_OF]->(party2)
MERGE (party1)-[:PART_OF]->(organization1)
MERGE (party2)-[:PART_OF]->(organization1)
MERGE (event1)-[:REPORTED_BY]->(company1)
MERGE (event2)-[:FOLLOWED_BY]->(event3)
```