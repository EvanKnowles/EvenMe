```cypher
MERGE (event1:Event {name: "Tension in GPU", description: "Tension is mounting in the government of provincial unity in KwaZulu-Natal."})
MERGE (individual1:Individual {name: "Thulasizwe Buthelezi", description: "IFP leader and Cogta MEC"})
MERGE (individual2:Individual {name: "Bheki Mtolo", description: "ANC provincial secretary"})
MERGE (individual3:Individual {name: "Thami Ntuli", description: "Premier"})
MERGE (individual4:Individual {name: "Bongiwe Sithole-Moloi", description: "Former Cogta MEC"})
MERGE (individual5:Individual {name: "AB Mnikathi", description: "Acting municipal manager"})
MERGE (individual6:Individual {name: "BW Ndlovu", description: "Acting financial officer"})
MERGE (organization1:Organization {name: "ANC", description: "African National Congress"})
MERGE (organization2:Organization {name: "IFP", description: "Inkatha Freedom Party"})
MERGE (organization3:Organization {name: "DA", description: "Democratic Alliance"})
MERGE (organization4:Organization {name: "NFP", description: "National Freedom Party"})
MERGE (organization5:Organization {name: "GPU", description: "Government of Provincial Unity"})
MERGE (organization6:Organization {name: "Cogta", description: "Department of Cooperative Governance and Traditional Affairs"})
MERGE (organization7:Organization {name: "Nongoma Local Municipality", description: "Local Municipality in KwaZulu-Natal"})
MERGE (outcome1:Outcome {name: "ANC lost power", description: "The ANC lost power in the general elections", date: "2023-05-29"})
MERGE (outcome2:Outcome {name: "Corruption and maladministration evidence", description: "Evidence of corruption and maladministration in the sixth administration"})
MERGE (outcome3:Outcome {name: "Forensic investigation", description: "Forensic investigation into procurement and employment violations between January 2023 to May 2024"})

MERGE (event1)-[:INVOLVES]->(individual1)
MERGE (event1)-[:INVOLVES]->(individual2)
MERGE (event1)-[:INVOLVES]->(organization1)
MERGE (event1)-[:INVOLVES]->(organization2)
MERGE (event1)-[:INVOLVES]->(organization5)
MERGE (event1)-[:INVOLVES]->(organization6)

MERGE (individual1)-[:MEMBER_OF]->(organization2)
MERGE (individual2)-[:MEMBER_OF]->(organization1)
MERGE (individual3)-[:MEMBER_OF]->(organization2)
MERGE (individual4)-[:MEMBER_OF]->(organization1)
MERGE (individual5)-[:WORKS_FOR]->(organization7)
MERGE (individual6)-[:WORKS_FOR]->(organization7)

MERGE (organization1)-[:PART_OF]->(organization5)
MERGE (organization2)-[:PART_OF]->(organization5)
MERGE (organization3)-[:PART_OF]->(organization5)
MERGE (organization4)-[:PART_OF]->(organization5)

MERGE (individual1)-[:ACCUSATION_BY]->(individual2)
MERGE (individual2)-[:ACCUSATION_AGAINST]->(individual1)
MERGE (individual1)-[:RESPONSE_TO]->(individual2)

MERGE (individual1)-[:APPOINTED]->(individual5)
MERGE (individual1)-[:APPOINTED]->(individual6)

MERGE (outcome1)-[:RESULT_OF]->(event1)
MERGE (outcome2)-[:RESULT_OF]->(event1)
MERGE (outcome3)-[:RESULT_OF]->(event1)

MERGE (individual1)-[:MENTIONED_IN]->(outcome2)
MERGE (individual1)-[:MENTIONED_IN]->(outcome3)
```