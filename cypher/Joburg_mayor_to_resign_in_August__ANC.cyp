```cypher
MERGE (event1:Event {name: "Kabelo Gwamanda Resignation", description: "City of Johannesburg executive mayor Kabelo Gwamanda is set to resign by August", date: "2023-08"})
MERGE (individual1:Individual {name: "Kabelo Gwamanda", description: "City of Johannesburg executive mayor"})
MERGE (party1:Party {name: "ANC", description: "African National Congress"})
MERGE (party2:Party {name: "ActionSA", description: "ActionSA party"})
MERGE (party3:Party {name: "Al Jama-ah", description: "Al Jama-ah party"})
MERGE (individual2:Individual {name: "Sasabona Manganye", description: "ANC Johannesburg regional secretary"})
MERGE (individual3:Individual {name: "Thembi Nkadimeng", description: "Former co-operative governance and traditional affairs minister"})
MERGE (individual4:Individual {name: "Ganief Hendricks", description: "Al Jama-ah president and social development deputy minister"})
MERGE (individual5:Individual {name: "Michael Beaumont", description: "ActionSA national chair"})
MERGE (individual6:Individual {name: "Mncedi Ndzwanana", description: "Tshwane council speaker and ATM councillor"})
MERGE (organization1:Organization {name: "Cosatu", description: "Labour federation"})
MERGE (company1:Company {name: "City Power", description: "Electricity provider for Johannesburg"})

MERGE (individual1)-[:MEMBER_OF]->(party3)
MERGE (event1)-[:INVOLVES]->(individual1)
MERGE (event1)-[:INVOLVES]->(party1)
MERGE (event1)-[:INVOLVES]->(party2)
MERGE (event1)-[:INVOLVES]->(party3)
MERGE (event1)-[:MENTIONED_BY]->(individual2)
MERGE (event1)-[:MENTIONED_BY]->(individual5)
MERGE (event1)-[:MENTIONED_BY]->(individual6)
MERGE (event1)-[:MENTIONED_BY]->(organization1)
MERGE (event1)-[:MENTIONED_BY]->(company1)

MERGE (individual1)-[:ELECTED_ON {date: "2023-06"}]->(event2:Event {name: "Kabelo Gwamanda Election", description: "Election of Kabelo Gwamanda as mayor of Johannesburg", date: "2023-06"})
MERGE (event2)-[:INVOLVES]->(individual1)
MERGE (event2)-[:INVOLVES]->(party3)

MERGE (individual3)-[:INTRODUCED {date: "2023-05"}]->(event3:Event {name: "Bill for Public Comment", description: "Introduction of a bill for public comment to rein in local government instability", date: "2023-05"})
MERGE (event3)-[:INVOLVES]->(individual3)

MERGE (individual1)-[:CALLED_TO_STEP_DOWN_BY {date: "2023-06"}]->(organization1)
MERGE (organization1)-[:CALLS_FOR]->(event1)

MERGE (individual5)-[:MAKES_STATEMENT_ABOUT]->(event1)
MERGE (individual5)-[:MAKES_STATEMENT_ABOUT]->(individual1)
MERGE (individual5)-[:MAKES_STATEMENT_ABOUT]->(party1)
MERGE (individual5)-[:MAKES_STATEMENT_ABOUT]->(party2)
MERGE (individual5)-[:MAKES_STATEMENT_ABOUT]->(party3)

MERGE (event4:Event {name: "ANC and ActionSA Agreement", description: "Agreement between ANC and ActionSA regarding Johannesburg governance"})
MERGE (event4)-[:INVOLVES]->(party1)
MERGE (event4)-[:INVOLVES]->(party2)
MERGE (event4)-[:MENTIONED_BY]->(individual5)

MERGE (individual5)-[:CONDITIONS_SUPPORT_FOR]->(event1)
MERGE (event1)-[:HAS_CONDITION]->(event5:Event {name: "Reversal of R200 Surcharge", description: "Immediate reversal of the R200 surcharge imposed by City Power on prepaid electricity users"})
MERGE (event1)-[:HAS_CONDITION]->(event6:Event {name: "Series of Motions by ActionSA", description: "ActionSA to produce a series of motions aimed at turning around Johannesburg in service delivery matters"})
MERGE (event5)-[:INVOLVES]->(company1)
MERGE (event6)-[:INVOLVES]->(party2)

MERGE (individual5)-[:MAKES_STATEMENT_ABOUT]->(individual6)
MERGE (individual6)-[:MENTIONED_IN]->(event4)

MERGE (individual1)-[:PRECEDED_BY]->(individual7:Individual {name: "Parks Tau", description: "Mayor of Johannesburg in 2016"})
MERGE (individual1)-[:PRECEDED_BY]->(individual8:Individual {name: "Herman Mashaba", description: "Mayor of Johannesburg from 2016 to 2019"})
MERGE (individual1)-[:PRECEDED_BY]->(individual9:Individual {name: "Geoff Makhubo", description: "Mayor of Johannesburg from 2020 to 2021"})
MERGE (individual1)-[:PRECEDED_BY]->(individual10:Individual {name: "Mpho Moerane", description: "Mayor of Johannesburg in 2021"})
MERGE (individual1)-[:PRECEDED_BY]->(individual11:Individual {name: "Jolidee Matongo", description: "Mayor of Johannesburg in 2021"})
MERGE (individual1)-[:PRECEDED_BY]->(individual12:Individual {name: "Mpho Phalatse", description: "Mayor of Johannesburg from 2021 to 2022"})
MERGE (individual1)-[:PRECEDED_BY]->(individual13:Individual {name: "Dada Morero", description: "Mayor of Johannesburg in 2022"})
MERGE (individual1)-[:PRECEDED_BY]->(individual14:Individual {name: "Thapelo Amad", description: "Mayor of Johannesburg in 2023"})
```