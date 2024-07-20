```cypher
// Create Individuals
MERGE (cyril:Individual {name: "Cyril Ramaphosa", description: "President of South Africa"})
MERGE (cilliers:Individual {name: "Cilliers Brink", description: "DA Mayor of Tshwane"})
MERGE (george:Individual {name: "George Matjila", description: "ANC Tshwane regional secretary"})
MERGE (eugene:Individual {name: "Eugene 'Bonzo' Modise", description: "ANC regional chairperson and Tshwane caucus leader"})
MERGE (jacqui:Individual {name: "Jacqui Uys", description: "Finance MMC and DA councillor"})

// Create Parties
MERGE (anc:Party {name: "ANC", description: "African National Congress"})
MERGE (da:Party {name: "DA", description: "Democratic Alliance"})

// Create Event
MERGE (opening_parliament:Event {name: "Opening of Parliament", date: date("2023-10-05"), description: "Opening of Parliament under the government of national unity (GNU)"})
MERGE (motion_confidence:Event {name: "Motion of No Confidence", date: date("2023-10-06"), description: "Motion of no confidence in DA Mayor Cilliers Brink at the next council sitting"})

// Create Outcomes
MERGE (service_disruption:Outcome {name: "Service Disruption", description: "Service disruption in Tshwane due to waste removal contractors closing waste dumps and threatening City drivers"})

// Create Companies
MERGE (waste_contractors:Company {name: "Waste Removal Contractors", description: "Group of waste removal truck contractors and sub-contractors"})

// Create Organizations
MERGE (tshwane_city:Organization {name: "City of Tshwane", description: "Municipal government of Tshwane"})

// Create Relationships
MERGE (anc)-[:PLANS_TO_REMOVE]->(cilliers)
MERGE (da)-[:CLAIMS]->(anc)
MERGE (da)-[:ACCUSES]->(anc)
MERGE (anc)-[:SAID]->(da)
MERGE (da)-[:CLAPPED_FOR]->(cyril)
MERGE (anc)-[:GEARING_UP_FOR]->(motion_confidence)
MERGE (george)-[:SAID]->(anc)
MERGE (eugene)-[:ADDED]->(motion_confidence)
MERGE (anc)-[:POINTED_TO]->(service_disruption)
MERGE (tshwane_city)-[:ANNOUNCED]->(service_disruption)
MERGE (jacqui)-[:SAID]->(motion_confidence)
MERGE (jacqui)-[:CLAIMED]->(george)
MERGE (waste_contractors)-[:WAITING_TO_SPEAK_TO]->(cilliers)
```