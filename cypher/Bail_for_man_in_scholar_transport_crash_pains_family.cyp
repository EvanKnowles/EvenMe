```cypher
MERGE (event:Event {description: "Accident involving a bakkie and a vehicle carrying schoolchildren", date: "Last Wednesday"})
MERGE (individual1:Individual {name: "Nkosinathi Maphukade", description: "Driver of the vehicle carrying schoolchildren"})
MERGE (individual2:Individual {name: "Gert van Emmenis", description: "Driver of the white Ford Ranger bakkie"})
MERGE (individual3:Individual {name: "Reneilwe Hlalele", age: 7, description: "Schoolchild"})
MERGE (individual4:Individual {name: "Hlompho Hlalele", age: 10, description: "Schoolchild"})
MERGE (individual5:Individual {name: "Thato Hlalele", age: 8, description: "Schoolchild"})
MERGE (individual6:Individual {name: "Sihle Hlalele", age: 12, description: "Schoolchild"})
MERGE (individual7:Individual {name: "Thandeka Mtyilibe", age: 9, description: "Schoolchild"})
MERGE (individual8:Individual {name: "Reabetswe Rabodiba", age: 7, description: "Schoolchild"})
MERGE (individual9:Individual {name: "Katlego Morebudi", age: 12, description: "Schoolchild"})
MERGE (individual10:Individual {name: "Junior Mankofu", age: 13, description: "Schoolchild"})
MERGE (individual11:Individual {name: "Tshiamo Makinta", age: 9, description: "Schoolchild"})
MERGE (individual12:Individual {name: "Khothatso Sesing", age: 9, description: "Schoolchild"})
MERGE (individual13:Individual {name: "Olesego Khesa", age: 12, description: "Schoolchild"})
MERGE (individual14:Individual {name: "Lungisa Mkrokrelwa", description: "Cousin of Nkosinathi Maphukade"})
MERGE (individual15:Individual {name: "Patrick Rampai", description: "Friend of Nkosinathi Maphukade"})
MERGE (individual16:Individual {name: "Adv Abrie van der Merwe", description: "Lawyer of Gert van Emmenis"})
MERGE (individual17:Individual {name: "Solomon Taueatsoala", description: "Prosecutor"})
MERGE (individual18:Individual {name: "Brother of Gert van Emmenis", description: "Brother of Gert van Emmenis"})
MERGE (individual19:Individual {name: "Magistrate", description: "Magistrate at Fochville magistrates court"})

MERGE (outcome1:Outcome {description: "Nkosinathi Maphukade and 11 schoolchildren burnt beyond recognition"})
MERGE (outcome2:Outcome {description: "Gert van Emmenis granted R20,000 bail"})
MERGE (outcome3:Outcome {description: "Gert van Emmenis facing 12 counts of culpable homicide and one of reckless and/or negligent driving"})

MERGE (organization1:Organization {name: "Fochville magistrates court"})

MERGE (event)-[:INVOLVED]->(individual1)
MERGE (event)-[:INVOLVED]->(individual2)
MERGE (event)-[:INVOLVED]->(individual3)
MERGE (event)-[:INVOLVED]->(individual4)
MERGE (event)-[:INVOLVED]->(individual5)
MERGE (event)-[:INVOLVED]->(individual6)
MERGE (event)-[:INVOLVED]->(individual7)
MERGE (event)-[:INVOLVED]->(individual8)
MERGE (event)-[:INVOLVED]->(individual9)
MERGE (event)-[:INVOLVED]->(individual10)
MERGE (event)-[:INVOLVED]->(individual11)
MERGE (event)-[:INVOLVED]->(individual12)
MERGE (event)-[:INVOLVED]->(individual13)

MERGE (individual1)-[:RESULTED_IN]->(outcome1)
MERGE (individual2)-[:RESULTED_IN]->(outcome1)
MERGE (individual2)-[:RESULTED_IN]->(outcome2)
MERGE (individual2)-[:FACING]->(outcome3)

MERGE (individual16)-[:REPRESENTS]->(individual2)
MERGE (individual17)-[:PROSECUTES]->(individual2)
MERGE (individual18)-[:SUPPORTS]->(individual2)
MERGE (individual19)-[:GRANTED_BAIL]->(individual2)

MERGE (organization1)-[:HELD]->(event)
MERGE (organization1)-[:EMPLOYS]->(individual19)
```