```cypher
MERGE (event1:Event {name: "NSFAS Appeal", description: "NSFAS intends to appeal Western Cape High Court judgment interdicting its attempt to cancel a contract.", date: "2023-10-02"})
MERGE (event2:Event {name: "Western Cape High Court Ruling", description: "Western Cape High Court ruling on the Department of Higher Education and Training's attempt to get NSFAS out of a controversial contract.", date: "2023-10-02"})
MERGE (event3:Event {name: "eZaga Court Application", description: "eZaga headed to court with an urgent application for an interdict.", date: "2023-10-02"})
MERGE (event4:Event {name: "Special Tribunal Application", description: "SIU and NSFAS applied before the Special Tribunal to interdict the service-level agreements between the four companies and the scheme.", date: "2023-05-30"})
MERGE (event5:Event {name: "NSFAS Decision to Stop Using Service Providers", description: "NSFAS decided to stop using the service providers.", date: "2023-04-24"})
MERGE (event6:Event {name: "SIU Revelation to SCOPA", description: "SIU revealed to the Standing Committee on Public Accounts that more than 40,000 undeserving students had accessed NSFAS funds worth R5 billion.", date: "2022-04-24"})

MERGE (individual1:Individual {name: "Tembeka Ngcukaitobi", description: "Advocate involved in the report recommending the termination of the contracts."})
MERGE (individual2:Individual {name: "Ishmael Mnisi", description: "NSFAS spokesperson."})
MERGE (individual3:Individual {name: "Andile Nongogo", description: "Former NSFAS CEO with links to Coinvest Africa."})
MERGE (individual4:Individual {name: "Ernest Khosa", description: "Former NSFAS chairperson."})

MERGE (company1:Company {name: "eZaga", description: "Company awarded the contract for the direct payment of allowances to university students and TVET colleges."})
MERGE (company2:Company {name: "Coinvest Africa", description: "Company awarded the contract for the direct payment of allowances to university students and TVET colleges."})
MERGE (company3:Company {name: "Tenet Technology", description: "Company awarded the contract for the direct payment of allowances to university students and TVET colleges."})
MERGE (company4:Company {name: "Norraco Corporation", description: "Company awarded the contract for the direct payment of allowances to university students and TVET colleges."})

MERGE (organization1:Organization {name: "NSFAS", description: "National Student Financial Aid Scheme."})
MERGE (organization2:Organization {name: "Department of Higher Education and Training", description: "Government department involved in the attempt to get NSFAS out of the contract."})
MERGE (organization3:Organization {name: "Werksmans Attorneys", description: "Law firm involved in the report recommending the termination of the contracts."})
MERGE (organization4:Organization {name: "Special Investigating Unit", description: "Unit involved in the application to set aside the contract."})
MERGE (organization5:Organization {name: "Special Tribunal", description: "Tribunal where the application to set aside the contract is pending."})
MERGE (organization6:Organization {name: "Standing Committee on Public Accounts", description: "Committee that was informed about the undeserving students accessing NSFAS funds."})

MERGE (event1)-[:INVOLVES]->(organization1)
MERGE (event1)-[:INVOLVES]->(organization2)
MERGE (event1)-[:INVOLVES]->(organization3)
MERGE (event1)-[:INVOLVES]->(individual1)

MERGE (event2)-[:INVOLVES]->(organization1)
MERGE (event2)-[:INVOLVES]->(organization2)
MERGE (event2)-[:INVOLVES]->(company1)

MERGE (event3)-[:INVOLVES]->(company1)
MERGE (event3)-[:INVOLVES]->(organization1)

MERGE (event4)-[:INVOLVES]->(organization1)
MERGE (event4)-[:INVOLVES]->(organization4)
MERGE (event4)-[:INVOLVES]->(organization5)

MERGE (event5)-[:INVOLVES]->(organization1)
MERGE (event5)-[:INVOLVES]->(company1)
MERGE (event5)-[:INVOLVES]->(company2)
MERGE (event5)-[:INVOLVES]->(company3)
MERGE (event5)-[:INVOLVES]->(company4)

MERGE (event6)-[:INVOLVES]->(organization4)
MERGE (event6)-[:INVOLVES]->(organization6)

MERGE (individual1)-[:AUTHORED]->(organization3)
MERGE (individual2)-[:SPOKESPERSON_FOR]->(organization1)
MERGE (individual3)-[:LINKED_TO]->(company2)
MERGE (individual4)-[:FORMER_CHAIRPERSON_OF]->(organization1)
```