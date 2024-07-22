```cypher
// Create Individuals
MERGE (thato:Individual {name: "Thato Moncho", description: "Cancer patient", age: 40})
MERGE (motalatale:Individual {name: "Motalatale Modiba", description: "Gauteng department of health spokesperson"})

// Create Organizations
MERGE (cancer_alliance:Organization {name: "Cancer Alliance", description: "Organization advocating for cancer patients"})
MERGE (section27:Organization {name: "Section27", description: "Organization representing Cancer Alliance"})
MERGE (tac:Organization {name: "Treatment Action Campaign", description: "Organization advocating for healthcare rights"})
MERGE (gauteng_health:Organization {name: "Gauteng Department of Health", description: "Government health department for Gauteng"})

// Create Events
MERGE (court_application:Event {name: "Court Application", description: "Cancer Alliance launching a court application", date: date("2024-04-30")})
MERGE (protest:Event {name: "Protest", description: "Protest held by Section27, Cancer Alliance, and TAC", date: date("2024-04-30")})
MERGE (media_statement:Event {name: "Media Statement", description: "Gauteng department of health media statement", date: date("2024-04-30")})

// Create Outcomes
MERGE (bad_news:Outcome {name: "Bad News", description: "Radiation no longer effective for Thato Moncho", date: date("2023-05-01")})
MERGE (diagnosis:Outcome {name: "Diagnosis", description: "Thato Moncho diagnosed with breast cancer", date: date("2020-09-01")})
MERGE (remission:Outcome {name: "Remission", description: "Thato Moncho in remission after surgery", date: date("2022-09-01")})
MERGE (recurrence:Outcome {name: "Recurrence", description: "Cancer returned for Thato Moncho", date: date("2022-10-01")})
MERGE (fund_allocation:Outcome {name: "Fund Allocation", description: "R250m allocated for outsourcing radiation oncology services", date: date("2024-04-30")})

// Create Relationships
MERGE (thato)-[:RECEIVED]->(bad_news)
MERGE (thato)-[:DIAGNOSED_WITH]->(diagnosis)
MERGE (thato)-[:UNDERWENT]->(remission)
MERGE (thato)-[:EXPERIENCED]->(recurrence)
MERGE (cancer_alliance)-[:LAUNCHED]->(court_application)
MERGE (section27)-[:REPRESENTS]->(cancer_alliance)
MERGE (section27)-[:ORGANIZED]->(protest)
MERGE (tac)-[:PARTICIPATED_IN]->(protest)
MERGE (gauteng_health)-[:ISSUED]->(media_statement)
MERGE (gauteng_health)-[:ALLOCATED_FUNDS]->(fund_allocation)
MERGE (motalatale)-[:SPOKESPERSON_FOR]->(gauteng_health)
MERGE (court_application)-[:TARGETS]->(gauteng_health)
MERGE (protest)-[:TARGETS]->(gauteng_health)
```