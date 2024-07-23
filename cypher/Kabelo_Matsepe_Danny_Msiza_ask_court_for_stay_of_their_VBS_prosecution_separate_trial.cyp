```cypher
// Create Individuals
MERGE (kabelo:Individual {name: "Kabelo Matsepe", description: "Former ANCYL leader"})
MERGE (danny:Individual {name: "Danny Msiza", description: "Former provincial ANC treasurer"})
MERGE (shaun:Individual {name: "Shaun Abrahams", description: "Former NPA boss advocate"})
MERGE (hein:Individual {name: "Hein van der Merwe", description: "State prosecutor"})
MERGE (peter:Individual {name: "Peter Mabuse", description: "Presiding judge"})
MERGE (tshifhiwa:Individual {name: "Tshifhiwa Matodzi", description: "Now convicted chairperson of VBS Mutual Bank"})
MERGE (zweli:Individual {name: "Zweli Mkhize", description: "Former health minister"})

// Create Organizations
MERGE (anc:Organization {name: "ANC", description: "African National Congress"})
MERGE (ancyl:Organization {name: "ANCYL", description: "African National Congress Youth League"})
MERGE (npa:Organization {name: "NPA", description: "National Prosecuting Authority"})
MERGE (eff:Organization {name: "EFF", description: "Economic Freedom Fighters"})
MERGE (sacp:Organization {name: "SACP", description: "South African Communist Party"})
MERGE (vbs:Company {name: "VBS Mutual Bank", description: "Bank involved in looting scandal"})

// Create Events
MERGE (courtEvent:Event {name: "Court Application", description: "Application for stay of prosecution and separation of trial", date: "2023-08-14"})
MERGE (affidavitEvent:Event {name: "Leaked Affidavit", description: "Leaked affidavit implicating senior politicians", date: "2023"})

// Create Outcomes
MERGE (stayOutcome:Outcome {name: "Stay of Prosecution", description: "Pending finalisation of application at the Supreme Court of Appeal"})
MERGE (separationOutcome:Outcome {name: "Separation of Trial", description: "Separation of trial from co-accused"})
MERGE (donationOutcome:Outcome {name: "Donations to Politicians", description: "Donations to politicians to avoid public scrutiny"})

// Create Relationships
MERGE (kabelo)-[:MEMBER_OF]->(ancyl)
MERGE (danny)-[:MEMBER_OF]->(anc)
MERGE (shaun)-[:REPRESENTS]->(kabelo)
MERGE (shaun)-[:REPRESENTS]->(danny)
MERGE (hein)-[:REPRESENTS]->(npa)
MERGE (peter)-[:PRESIDES_OVER]->(courtEvent)
MERGE (courtEvent)-[:INVOLVES]->(kabelo)
MERGE (courtEvent)-[:INVOLVES]->(danny)
MERGE (courtEvent)-[:INVOLVES]->(hein)
MERGE (courtEvent)-[:INVOLVES]->(shaun)
MERGE (courtEvent)-[:INVOLVES]->(peter)
MERGE (courtEvent)-[:SEEKS]->(stayOutcome)
MERGE (courtEvent)-[:SEEKS]->(separationOutcome)
MERGE (affidavitEvent)-[:INVOLVES]->(tshifhiwa)
MERGE (affidavitEvent)-[:IMPLICATES]->(zweli)
MERGE (affidavitEvent)-[:IMPLICATES]->(eff)
MERGE (affidavitEvent)-[:IMPLICATES]->(sacp)
MERGE (affidavitEvent)-[:IMPLICATES]->(anc)
MERGE (affidavitEvent)-[:RESULTS_IN]->(donationOutcome)
MERGE (donationOutcome)-[:BENEFITS]->(eff)
MERGE (donationOutcome)-[:BENEFITS]->(zweli)
MERGE (donationOutcome)-[:BENEFITS]->(sacp)
```