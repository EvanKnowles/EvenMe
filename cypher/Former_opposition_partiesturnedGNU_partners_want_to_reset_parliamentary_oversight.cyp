```cypher
MERGE (event1:Event {name: "Parliament Budget Debate", date: "2023-10-02", description: "Debate on Parliament's budget and oversight functions"})
MERGE (org1:Organization {name: "GNU", description: "Government of National Unity"})
MERGE (org2:Organization {name: "Progressive Caucus", description: "Includes MK Party, EFF, ATM, NCC, and UAT"})
MERGE (org3:Organization {name: "Parliament", description: "South African Parliament"})
MERGE (org4:Organization {name: "DA", description: "Democratic Alliance"})
MERGE (org5:Organization {name: "ANC", description: "African National Congress"})
MERGE (org6:Organization {name: "EFF", description: "Economic Freedom Fighters"})
MERGE (org7:Organization {name: "ATM", description: "African Transformation Movement"})
MERGE (org8:Organization {name: "FF Plus", description: "Freedom Front Plus"})
MERGE (org9:Organization {name: "Rise Mzansi", description: "Political Party"})
MERGE (org10:Organization {name: "BOSA", description: "Build One South Africa"})
MERGE (org11:Organization {name: "ActionSA", description: "Political Party"})
MERGE (org12:Organization {name: "ACDP", description: "African Christian Democratic Party"})
MERGE (org13:Organization {name: "MK Party", description: "Political Party"})
MERGE (org14:Organization {name: "NCC", description: "National Congress of the People"})
MERGE (org15:Organization {name: "UAT", description: "United African Transformation"})
MERGE (org16:Organization {name: "UDM", description: "United Democratic Movement"})
MERGE (org17:Organization {name: "National Treasury", description: "South African National Treasury"})
MERGE (org18:Organization {name: "Public Works and Infrastructure", description: "Department of Public Works and Infrastructure"})

MERGE (ind1:Individual {name: "Thoko Didiza", description: "Speaker of the National Assembly"})
MERGE (ind2:Individual {name: "George Michalakis", description: "DA Chief Whip"})
MERGE (ind3:Individual {name: "Baxolile Nodada", description: "DA Deputy Chief Whip"})
MERGE (ind4:Individual {name: "Wouter Wessels", description: "FF Plus Chief Whip"})
MERGE (ind5:Individual {name: "Makashule Gana", description: "Rise Mzansi MP"})
MERGE (ind6:Individual {name: "Doris Dlakude", description: "ANC Deputy Chief Whip"})
MERGE (ind7:Individual {name: "Cameron Dugmore", description: "ANC MP"})
MERGE (ind8:Individual {name: "Nobuntu Hlazo-Webster", description: "BOSA Deputy Leader"})
MERGE (ind9:Individual {name: "Lerato Ngobeni", description: "ActionSA Chief Whip"})
MERGE (ind10:Individual {name: "Steve Swart", description: "ACDP MP"})
MERGE (ind11:Individual {name: "Wesley Douglas", description: "MK Party MP"})
MERGE (ind12:Individual {name: "Omphile Maotwe", description: "EFF MP"})
MERGE (ind13:Individual {name: "Vuyolwethu Zungula", description: "ATM Leader"})
MERGE (ind14:Individual {name: "Fadiel Adams", description: "NCC Leader"})
MERGE (ind15:Individual {name: "Wonderboy Mahlatsi", description: "UAT Leader"})
MERGE (ind16:Individual {name: "Nqabayomzi Kwankwa", description: "UDM MP"})
MERGE (ind17:Individual {name: "Dean Macpherson", description: "Public Works and Infrastructure Minister"})
MERGE (ind18:Individual {name: "Annelie Lotriet", description: "Deputy Speaker of the National Assembly"})

MERGE (event1)-[:INVOLVES]->(org1)
MERGE (event1)-[:INVOLVES]->(org2)
MERGE (event1)-[:INVOLVES]->(org3)
MERGE (event1)-[:INVOLVES]->(org4)
MERGE (event1)-[:INVOLVES]->(org5)
MERGE (event1)-[:INVOLVES]->(org6)
MERGE (event1)-[:INVOLVES]->(org7)
MERGE (event1)-[:INVOLVES]->(org8)
MERGE (event1)-[:INVOLVES]->(org9)
MERGE (event1)-[:INVOLVES]->(org10)
MERGE (event1)-[:INVOLVES]->(org11)
MERGE (event1)-[:INVOLVES]->(org12)
MERGE (event1)-[:INVOLVES]->(org13)
MERGE (event1)-[:INVOLVES]->(org14)
MERGE (event1)-[:INVOLVES]->(org15)
MERGE (event1)-[:INVOLVES]->(org16)
MERGE (event1)-[:INVOLVES]->(org17)
MERGE (event1)-[:INVOLVES]->(org18)

MERGE (ind1)-[:PARTICIPATED_IN]->(event1)
MERGE (ind2)-[:PARTICIPATED_IN]->(event1)
MERGE (ind3)-[:PARTICIPATED_IN]->(event1)
MERGE (ind4)-[:PARTICIPATED_IN]->(event1)
MERGE (ind5)-[:PARTICIPATED_IN]->(event1)
MERGE (ind6)-[:PARTICIPATED_IN]->(event1)
MERGE (ind7)-[:PARTICIPATED_IN]->(event1)
MERGE (ind8)-[:PARTICIPATED_IN]->(event1)
MERGE (ind9)-[:PARTICIPATED_IN]->(event1)
MERGE (ind10)-[:PARTICIPATED_IN]->(event1)
MERGE (ind11)-[:PARTICIPATED_IN]->(event1)
MERGE (ind12)-[:PARTICIPATED_IN]->(event1)
MERGE (ind13)-[:PARTICIPATED_IN]->(event1)
MERGE (ind14)-[:PARTICIPATED_IN]->(event1)
MERGE (ind15)-[:PARTICIPATED_IN]->(event1)
MERGE (ind16)-[:PARTICIPATED_IN]->(event1)
MERGE (ind17)-[:PARTICIPATED_IN]->(event1)
MERGE (ind18)-[:PARTICIPATED_IN]->(event1)

MERGE (ind1)-[:MEMBER_OF]->(org3)
MERGE (ind2)-[:MEMBER_OF]->(org4)
MERGE (ind3)-[:MEMBER_OF]->(org4)
MERGE (ind4)-[:MEMBER_OF]->(org8)
MERGE (ind5)-[:MEMBER_OF]->(org9)
MERGE (ind6)-[:MEMBER_OF]->(org5)
MERGE (ind7)-[:MEMBER_OF]->(org5)
MERGE (ind8)-[:MEMBER_OF]->(org10)
MERGE (ind9)-[:MEMBER_OF]->(org11)
MERGE (ind10)-[:MEMBER_OF]->(org12)
MERGE (ind11)-[:MEMBER_OF]->(org13)
MERGE (ind12)-[:MEMBER_OF]->(org6)
MERGE (ind13)-[:MEMBER_OF]->(org7)
MERGE (ind14)-[:MEMBER_OF]->(org14)
MERGE (ind15)-[:MEMBER_OF]->(org15)
MERGE (ind16)-[:MEMBER_OF]->(org16)
MERGE (ind17)-[:MEMBER_OF]->(org18)
MERGE (ind18)-[:MEMBER_OF]->(org3)

MERGE (org2)-[:PART_OF]->(org1)
MERGE (org4)-[:PART_OF]->(org1)
MERGE (org5)-[:PART_OF]->(org1)
MERGE (org6)-[:PART_OF]->(org2)
MERGE (org7)-[:PART_OF]->(org2)
MERGE (org8)-[:PART_OF]->(org1)
MERGE (org9)-[:PART_OF]->(org1)
MERGE (org10)-[:PART_OF]->(org1)
MERGE (org11)-[:PART_OF]->(org1)
MERGE (org12)-[:PART_OF]->(org1)
MERGE (org13)-[:PART_OF]->(org2)
MERGE (org14)-[:PART_OF]->(org2)
MERGE (org15)-[:PART_OF]->(org2)
```