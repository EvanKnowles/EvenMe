```cypher
MERGE (event:Event {name: "Newcastle By-Election", description: "By-election in the Newcastle Local Municipality", date: "2023-10-04"})
MERGE (ifp:Party {name: "IFP", description: "Inkatha Freedom Party"})
MERGE (tssa:Party {name: "TSSA", description: "Team Sugar South Africa"})
MERGE (mk:Party {name: "MK Party", description: "MK Party"})
MERGE (eff:Party {name: "EFF", description: "Economic Freedom Fighters"})
MERGE (anc:Party {name: "ANC", description: "African National Congress"})
MERGE (rise_mzansi:Party {name: "Rise Mzansi", description: "Rise Mzansi"})
MERGE (johan_zwane:Individual {name: "Johan Bhekinkosi Zwane", description: "IFP candidate"})
MERGE (mesuli_mabaso:Individual {name: "Mesuli Mhlengi Mabaso", description: "MK Party candidate"})
MERGE (shedrack_thwala:Individual {name: "Shedrack Thwala", description: "TSSA candidate"})
MERGE (praismon_maya:Individual {name: "Praismon Maya", description: "ANC candidate"})
MERGE (ntokozo_sibisi:Individual {name: "Ntokozo Sibisi", description: "Rise Mzansi candidate"})
MERGE (mongezi_twala:Individual {name: "Mongezi Twala", description: "EFF KwaZulu-Natal chairperson"})

MERGE (ifp)-[:PARTICIPATED_IN]->(event)
MERGE (tssa)-[:PARTICIPATED_IN]->(event)
MERGE (mk)-[:PARTICIPATED_IN]->(event)
MERGE (eff)-[:PARTICIPATED_IN]->(event)
MERGE (anc)-[:PARTICIPATED_IN]->(event)
MERGE (rise_mzansi)-[:PARTICIPATED_IN]->(event)

MERGE (johan_zwane)-[:REPRESENTS]->(ifp)
MERGE (mesuli_mabaso)-[:REPRESENTS]->(mk)
MERGE (shedrack_thwala)-[:REPRESENTS]->(tssa)
MERGE (praismon_maya)-[:REPRESENTS]->(anc)
MERGE (ntokozo_sibisi)-[:REPRESENTS]->(rise_mzansi)

MERGE (johan_zwane)-[:WON]->(event)
MERGE (mesuli_mabaso)-[:CONTESTED]->(event)
MERGE (shedrack_thwala)-[:CONTESTED]->(event)
MERGE (praismon_maya)-[:CONTESTED]->(event)
MERGE (ntokozo_sibisi)-[:CONTESTED]->(event)

MERGE (tssa)-[:LOST_WARD]->(ifp)
MERGE (mk)-[:LOST_WARD]->(ifp)

MERGE (tssa)-[:TERMINATED_COUNCILLOR]->(event)

MERGE (eff)-[:SUPPORTED]->(tssa)
MERGE (mongezi_twala)-[:EXPLAINED_SUPPORT]->(eff)

MERGE (outcome:Outcome {description: "IFP won the ward with 852 votes", votes: 852})
MERGE (johan_zwane)-[:ACHIEVED]->(outcome)

MERGE (outcome2:Outcome {description: "MK Party received 614 votes", votes: 614})
MERGE (mesuli_mabaso)-[:ACHIEVED]->(outcome2)

MERGE (outcome3:Outcome {description: "TSSA received 361 votes", votes: 361})
MERGE (shedrack_thwala)-[:ACHIEVED]->(outcome3)

MERGE (outcome4:Outcome {description: "ANC received 283 votes", votes: 283})
MERGE (praismon_maya)-[:ACHIEVED]->(outcome4)

MERGE (outcome5:Outcome {description: "Rise Mzansi received 27 votes", votes: 27})
MERGE (ntokozo_sibisi)-[:ACHIEVED]->(outcome5)

MERGE (outcome6:Outcome {description: "EFF received 4 votes", votes: 4})
MERGE (eff)-[:ACHIEVED]->(outcome6)

MERGE (outcome7:Outcome {description: "Total votes cast", votes: 2175})
MERGE (event)-[:TOTAL_VOTES]->(outcome7)
```