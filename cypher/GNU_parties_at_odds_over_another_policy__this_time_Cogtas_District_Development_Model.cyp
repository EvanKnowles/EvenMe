```cypher
// Create Individuals
MERGE (vh:Individual {name: "Velenkosini Hlabisa", description: "Cogta Minister"})
MERGE (zm:Individual {name: "Zweli Mkhize", description: "Portfolio Committee on Cogta chairperson, ANC MP"})
MERGE (mvz:Individual {name: "Marina Van Zyl", description: "DA MP"})
MERGE (sd:Individual {name: "Sharon Davids", description: "ANC MP"})
MERGE (mg:Individual {name: "Makashule Gana", description: "Rise Mzansi member"})
MERGE (am:Individual {name: "Aaron Motsoaledi", description: "Health Minister"})

// Create Organizations
MERGE (anc:Organization {name: "ANC", description: "African National Congress"})
MERGE (da:Organization {name: "DA", description: "Democratic Alliance"})
MERGE (ifp:Organization {name: "IFP", description: "Inkatha Freedom Party"})
MERGE (rm:Organization {name: "Rise Mzansi", description: "GNU partner"})
MERGE (ffp:Organization {name: "FF Plus", description: "Freedom Front Plus"})
MERGE (cogta:Organization {name: "Cogta", description: "Cooperative Governance and Traditional Affairs Department"})

// Create Events
MERGE (debate:Event {name: "Debate on Cogta Minister's budget vote speech", date: "2023-10-03", description: "Debate under the theme 'Every Municipality Must Work'"})
MERGE (health_debate:Event {name: "Health budget debate", description: "First debate of the seventh Parliament on the Health budget"})

// Create Outcomes
MERGE (ddm:Outcome {name: "District Development Model", description: "Plan to improve flailing local governance"})
MERGE (nhi:Outcome {name: "National Health Insurance", description: "Health insurance plan supported by Health Minister Aaron Motsoaledi"})

// Create Companies
MERGE (or_tambo:Company {name: "OR Tambo District Municipality", description: "Pilot site for DDM in the Eastern Cape"})
MERGE (waterberg:Company {name: "Waterberg District Municipality", description: "Pilot site for DDM in Limpopo"})
MERGE (ethekwini:Company {name: "eThekwini Metropolitan Municipality", description: "Pilot site for DDM in KZN"})

// Create Relationships
MERGE (vh)-[:SUPPORTS]->(ddm)
MERGE (zm)-[:SUPPORTS]->(ddm)
MERGE (sd)-[:SUPPORTS]->(ddm)
MERGE (mvz)-[:OPPOSES]->(ddm)
MERGE (mg)-[:SUPPORTS_CONDITIONALLY]->(ddm)
MERGE (vh)-[:LEADS]->(ifp)
MERGE (zm)-[:MEMBER_OF]->(anc)
MERGE (mvz)-[:MEMBER_OF]->(da)
MERGE (sd)-[:MEMBER_OF]->(anc)
MERGE (mg)-[:MEMBER_OF]->(rm)
MERGE (am)-[:SUPPORTS]->(nhi)
MERGE (am)-[:MEMBER_OF]->(anc)
MERGE (da)-[:OPPOSES]->(nhi)
MERGE (ffp)-[:OPPOSES]->(nhi)
MERGE (debate)-[:INVOLVES]->(vh)
MERGE (debate)-[:INVOLVES]->(zm)
MERGE (debate)-[:INVOLVES]->(mvz)
MERGE (debate)-[:INVOLVES]->(sd)
MERGE (debate)-[:INVOLVES]->(mg)
MERGE (health_debate)-[:INVOLVES]->(am)
MERGE (health_debate)-[:INVOLVES]->(da)
MERGE (health_debate)-[:INVOLVES]->(ffp)
MERGE (ddm)-[:IMPLEMENTED_AT]->(or_tambo)
MERGE (ddm)-[:IMPLEMENTED_AT]->(waterberg)
MERGE (ddm)-[:IMPLEMENTED_AT]->(ethekwini)
```