```cypher
MERGE (e:Event {name: "Veldfire near Pietermaritzburg", date: "Last Sunday", description: "A raging veldfire near Pietermaritzburg resulted in multiple deaths and injuries."})
MERGE (o:Outcome {description: "Death toll rises to seven"})
MERGE (e)-[:RESULTED_IN]->(o)

MERGE (c:Company {name: "Farmusa", description: "An agricultural company contracted to Sappi Forestry"})
MERGE (e)-[:INVOLVED]->(c)

MERGE (c2:Company {name: "Sappi Forestry", description: "A forestry company"})
MERGE (e)-[:INVOLVED]->(c2)

MERGE (i1:Individual {name: "Ntombikhona Nxele", age: 39, description: "Burnt to death in the fire"})
MERGE (i2:Individual {name: "Khethiwe Ngcobo", age: 42, description: "Burnt to death in the fire"})
MERGE (i3:Individual {name: "Nonsikelelo Zuma", age: 37, description: "Burnt to death in the fire"})
MERGE (i4:Individual {name: "Nomfundo Nxele", age: 35, description: "Died later in hospital"})
MERGE (i5:Individual {name: "Bongiwe Shishane", age: 41, description: "Died later in hospital"})
MERGE (i6:Individual {name: "Vusi Hudula", age: 36, description: "Died later in hospital"})
MERGE (i7:Individual {name: "Richard Dlamini", description: "Died on Friday after being in critical condition"})
MERGE (i8:Individual {name: "Sphesihle Ndlovu", description: "Suffered burns on legs and back, discharged on Monday"})

MERGE (e)-[:CAUSED_DEATH_OF]->(i1)
MERGE (e)-[:CAUSED_DEATH_OF]->(i2)
MERGE (e)-[:CAUSED_DEATH_OF]->(i3)
MERGE (e)-[:CAUSED_DEATH_OF]->(i4)
MERGE (e)-[:CAUSED_DEATH_OF]->(i5)
MERGE (e)-[:CAUSED_DEATH_OF]->(i6)
MERGE (e)-[:CAUSED_DEATH_OF]->(i7)
MERGE (e)-[:INJURED]->(i8)

MERGE (i9:Individual {name: "Chris Pappas", description: "uMngeni mayor"})
MERGE (i9)-[:CONFIRMED]->(i7)

MERGE (i10:Individual {name: "Mark Bernardo", description: "Sappi KwaZulu-Natal GM"})
MERGE (i10)-[:CONFIRMED]->(i7)

MERGE (o2:Outcome {description: "Claims of employees being ill-equipped or lacking proper training"})
MERGE (e)-[:RESULTED_IN]->(o2)

MERGE (o3:Outcome {description: "Sappi's fire management programme"})
MERGE (c2)-[:HAS_PROGRAMME]->(o3)

MERGE (o4:Outcome {description: "Training and equipment details"})
MERGE (o3)-[:INCLUDES]->(o4)

MERGE (o5:Outcome {description: "Community awareness and fitness activities"})
MERGE (o3)-[:INCLUDES]->(o5)
```