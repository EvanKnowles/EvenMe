```cypher
// Create Individuals
MERGE (bheki:Individual {name: "Bheki Mtolo", description: "ANC KwaZulu-Natal secretary"})
MERGE (solly:Individual {name: "Solly Mapaila", description: "SACP boss"})
MERGE (mike:Individual {name: "Mike Shingange", description: "Nehawu president"})
MERGE (ayanda:Individual {name: "Ayanda Zulu", description: "Nehawu KwaZulu-Natal secretary"})
MERGE (cyril:Individual {name: "Cyril Ramaphosa", description: "President of South Africa"})

// Create Organizations
MERGE (anc:Organization {name: "ANC", description: "African National Congress"})
MERGE (nehawu:Organization {name: "Nehawu", description: "National Education Health and Allied Workers' Union"})
MERGE (sacp:Organization {name: "SACP", description: "South African Communist Party"})
MERGE (cosatu:Organization {name: "Cosatu", description: "Congress of South African Trade Unions"})
MERGE (sanationalcivic:Organization {name: "SA National Civic Organisation", description: "South African National Civic Organisation"})
MERGE (samwu:Organization {name: "Samwu", description: "South African Municipal Workers' Union"})
MERGE (sadtu:Organization {name: "Sadtu", description: "South African Democratic Teachers Union"})
MERGE (numsa:Organization {name: "Numsa", description: "National Union of Metalworkers of South Africa"})

// Create Events
MERGE (political_school:Event {name: "Political School Gathering", date: "2023-09", description: "Nehawu-organized event where SACP boss Solly Mapaila criticized the ANC"})
MERGE (address:Event {name: "Address to ANC supporters", date: "2023-10", description: "Bheki Mtolo's address to ANC supporters in Shayamoya, Kokstad"})
MERGE (covid19:Event {name: "Covid-19 Pandemic", date: "2020", description: "Global pandemic affecting South Africa"})

// Create Outcomes
MERGE (apology_demand:Outcome {name: "Apology Demand", description: "Nehawu demands public apology from Bheki Mtolo"})
MERGE (court_threat:Outcome {name: "Court Threat", description: "Nehawu threatens to take Bheki Mtolo to court"})
MERGE (dissatisfaction:Outcome {name: "Dissatisfaction", description: "SACP, SA National Civic Organisation, and Cosatu express dissatisfaction with ANC leaders"})
MERGE (criticism:Outcome {name: "Criticism", description: "Solly Mapaila and Mike Shingange criticize the government of national unity"})
MERGE (insults:Outcome {name: "Insults", description: "Ayanda Zulu insults Bheki Mtolo"})

// Create Relationships
MERGE (bheki)-[:MEMBER_OF]->(anc)
MERGE (solly)-[:MEMBER_OF]->(sacp)
MERGE (mike)-[:MEMBER_OF]->(nehawu)
MERGE (ayanda)-[:MEMBER_OF]->(nehawu)
MERGE (cyril)-[:MEMBER_OF]->(anc)

MERGE (nehawu)-[:AFFILIATED_WITH]->(cosatu)
MERGE (samwu)-[:AFFILIATED_WITH]->(cosatu)
MERGE (sadtu)-[:AFFILIATED_WITH]->(cosatu)
MERGE (numsa)-[:AFFILIATED_WITH]->(cosatu)

MERGE (political_school)-[:ORGANIZED_BY]->(nehawu)
MERGE (political_school)-[:ATTENDED_BY]->(solly)
MERGE (political_school)-[:ATTENDED_BY]->(mike)
MERGE (address)-[:GIVEN_BY]->(bheki)
MERGE (address)-[:MENTIONS]->(cyril)
MERGE (address)-[:MENTIONS]->(nehawu)
MERGE (address)-[:MENTIONS]->(gnu:Event {name: "Government of National Unity", description: "Political arrangement in South Africa"})

MERGE (apology_demand)-[:DEMANDED_BY]->(nehawu)
MERGE (apology_demand)-[:TARGETS]->(bheki)
MERGE (court_threat)-[:THREATENED_BY]->(nehawu)
MERGE (court_threat)-[:TARGETS]->(bheki)
MERGE (dissatisfaction)-[:EXPRESSED_BY]->(sacp)
MERGE (dissatisfaction)-[:EXPRESSED_BY]->(sanationalcivic)
MERGE (dissatisfaction)-[:EXPRESSED_BY]->(cosatu)
MERGE (dissatisfaction)-[:TARGETS]->(anc)
MERGE (criticism)-[:EXPRESSED_BY]->(solly)
MERGE (criticism)-[:EXPRESSED_BY]->(mike)
MERGE (criticism)-[:TARGETS]->(gnu)
MERGE (insults)-[:EXPRESSED_BY]->(ayanda)
MERGE (insults)-[:TARGETS]->(bheki)

MERGE (covid19)-[:AFFECTED]->(southafrica:Organization {name: "South Africa", description: "Country in Southern Africa"})
MERGE (nehawu)-[:FOUGHT_AGAINST]->(covid19)
```