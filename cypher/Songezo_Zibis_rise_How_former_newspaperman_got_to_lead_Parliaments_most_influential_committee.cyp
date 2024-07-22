```cypher
MERGE (event1:Event {name: "Election of Songezo Zibi as Chairperson of SCOPA", date: date("2024-07-11")})
  ON CREATE SET event1.description = "Songezo Zibi was elected as the Chairperson of the Standing Committee on Public Accounts (SCOPA)."

MERGE (individual1:Individual {name: "Songezo Zibi"})
  ON CREATE SET individual1.description = "Leader of Rise Mzansi and Chairperson of SCOPA."

MERGE (individual2:Individual {name: "Rebecca Oppenheimer"})
  ON CREATE SET individual2.description = "Daughter of Mary Slack and donor to Rise Mzansi and DA."

MERGE (individual3:Individual {name: "Mary Slack"})
  ON CREATE SET individual3.description = "Daughter of South African industrialist Harry Oppenheimer."

MERGE (individual4:Individual {name: "Harry Oppenheimer"})
  ON CREATE SET individual4.description = "South African industrialist and heir to the Anglo-American and De Beers fortune."

MERGE (individual5:Individual {name: "Makashule Gana"})
  ON CREATE SET individual5.description = "MP of Rise Mzansi."

MERGE (individual6:Individual {name: "Veronica Mente"})
  ON CREATE SET individual6.description = "Speaker of the National Assembly from EFF."

MERGE (individual7:Individual {name: "Cyril Ramaphosa"})
  ON CREATE SET individual7.description = "President of South Africa."

MERGE (individual8:Individual {name: "Athol Trollip"})
  ON CREATE SET individual8.description = "Member of ActionSA."

MERGE (individual9:Individual {name: "Mkhuleko Hlengwa"})
  ON CREATE SET individual9.description = "Former Chairperson of SCOPA and Deputy Minister."

MERGE (party1:Party {name: "Rise Mzansi"})
  ON CREATE SET party1.description = "Political party led by Songezo Zibi."

MERGE (party2:Party {name: "DA"})
  ON CREATE SET party2.description = "Democratic Alliance, a political party in South Africa."

MERGE (party3:Party {name: "EFF"})
  ON CREATE SET party3.description = "Economic Freedom Fighters, a political party in South Africa."

MERGE (party4:Party {name: "ANC"})
  ON CREATE SET party4.description = "African National Congress, a political party in South Africa."

MERGE (party5:Party {name: "ActionSA"})
  ON CREATE SET party5.description = "Political party in South Africa."

MERGE (organization1:Organization {name: "SCOPA"})
  ON CREATE SET organization1.description = "Standing Committee on Public Accounts."

MERGE (event2:Event {name: "2024 General Election", date: date("2024-07-16")})
  ON CREATE SET event2.description = "General election in South Africa."

MERGE (outcome1:Outcome {name: "Rise Mzansi secures 0.42% of national vote"})
  ON CREATE SET outcome1.description = "Rise Mzansi secured two seats in the National Assembly."

MERGE (outcome2:Outcome {name: "R15 million donation to Rise Mzansi"})
  ON CREATE SET outcome2.description = "Rebecca Oppenheimer donated R15 million to Rise Mzansi."

MERGE (outcome3:Outcome {name: "R15 million donation to DA"})
  ON CREATE SET outcome3.description = "Rebecca Oppenheimer donated R15 million to DA."

MERGE (outcome4:Outcome {name: "R10 million donation to ActionSA"})
  ON CREATE SET outcome4.description = "Mary Slack's daughters donated a combined R10 million to ActionSA in 2021."

MERGE (individual1)-[:LEADS]->(party1)
MERGE (individual1)-[:CHAIRS]->(organization1)
MERGE (individual2)-[:DONATED_TO {amount: 15000000, date: date("2024-07-16")}]->(party1)
MERGE (individual2)-[:DONATED_TO {amount: 15000000, date: date("2024-07-16")}]->(party2)
MERGE (individual2)-[:DONATED_TO {amount: 10000000, date: date("2021-06-30")}]->(party5)
MERGE (individual2)-[:CHILD_OF]->(individual3)
MERGE (individual3)-[:CHILD_OF]->(individual4)
MERGE (individual5)-[:MEMBER_OF]->(party1)
MERGE (individual1)-[:ATTENDED]->(event1)
MERGE (individual5)-[:ATTENDED]->(event1)
MERGE (individual6)-[:ELECTED_AS]->(organization1)
MERGE (individual7)-[:ELECTED_AS]->(organization1)
MERGE (individual8)-[:MEMBER_OF]->(party5)
MERGE (individual9)-[:FORMER_CHAIR_OF]->(organization1)
MERGE (party1)-[:PARTICIPATED_IN]->(event2)
MERGE (party1)-[:SECURED]->(outcome1)
MERGE (party4)-[:REACHED_OUT_TO]->(party1)
MERGE (party1)-[:VOTED_FOR]->(individual6)
MERGE (party1)-[:VOTED_FOR]->(individual7)
```