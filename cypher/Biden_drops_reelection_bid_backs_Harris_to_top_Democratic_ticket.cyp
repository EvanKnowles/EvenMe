```cypher
MERGE (biden:Individual {name: "Joe Biden", description: "U.S. President", birthYear: 1942})
SET biden:President, biden.endTerm = date("2025-01-20")

MERGE (harris:Individual {name: "Kamala Harris", description: "Vice President", birthYear: 1964})
SET harris:VicePresident

MERGE (trump:Individual {name: "Donald Trump", description: "Former President", birthYear: 1946})
SET trump:RepublicanCandidate

MERGE (democraticParty:Party {name: "Democratic Party"})
MERGE (republicanParty:Party {name: "Republican Party"})

MERGE (biden)-[:MEMBER_OF]->(democraticParty)
MERGE (harris)-[:MEMBER_OF]->(democraticParty)
MERGE (trump)-[:MEMBER_OF]->(republicanParty)

MERGE (bidenReelectionEnd:Event {name: "Biden Ends Re-election Campaign", date: date("2023-07-16"), description: "Joe Biden ends his re-election campaign"})
MERGE (biden)-[:ANNOUNCED]->(bidenReelectionEnd)

MERGE (harrisEndorsement:Event {name: "Biden Endorses Harris", date: date("2023-07-16"), description: "Joe Biden endorses Kamala Harris"})
MERGE (biden)-[:ENDORSED]->(harrisEndorsement)
MERGE (harrisEndorsement)-[:ENDORSES]->(harris)

MERGE (covidDiagnosis:Event {name: "Biden Diagnosed with COVID-19", date: date("2023-07-12"), description: "Joe Biden diagnosed with COVID-19 for the third time"})
MERGE (biden)-[:DIAGNOSED_WITH]->(covidDiagnosis)

MERGE (debate:Event {name: "June 27 Debate", date: date("2023-06-27"), description: "Televised debate between Joe Biden and Donald Trump"})
MERGE (biden)-[:PARTICIPATED_IN]->(debate)
MERGE (trump)-[:PARTICIPATED_IN]->(debate)

MERGE (natoSummit:Event {name: "NATO Summit", date: date("2023-07-10"), description: "NATO summit where Biden made gaffes"})
MERGE (biden)-[:PARTICIPATED_IN]->(natoSummit)

MERGE (pelosi:Individual {name: "Nancy Pelosi", description: "Former House Speaker"})
MERGE (pelosi)-[:ALLY_OF]->(biden)

MERGE (schiff:Individual {name: "Adam Schiff", description: "California Representative"})
MERGE (schiff)-[:CALLED_ON]->(biden)

MERGE (democraticConvention:Event {name: "Democratic National Convention", date: date("2023-08-20"), description: "Democratic National Convention in Chicago"})
MERGE (biden)-[:PARTICIPATED_IN]->(democraticConvention)

MERGE (bidenPrimaryWin:Outcome {name: "Biden Wins Primary", description: "Biden wins Democratic Party's primary race"})
MERGE (biden)-[:ACHIEVED]->(bidenPrimaryWin)

MERGE (biden2020Win:Outcome {name: "Biden Wins 2020 Election", description: "Biden wins 2020 Presidential Election"})
MERGE (biden)-[:ACHIEVED]->(biden2020Win)
MERGE (trump)-[:DEFEATED_IN]->(biden2020Win)

MERGE (trumpProsecution:Event {name: "Trump Criminal Prosecutions", description: "Trump faces criminal prosecutions related to 2020 election"})
MERGE (trump)-[:INVOLVED_IN]->(trumpProsecution)

MERGE (trumpSentencing:Event {name: "Trump Sentencing", date: date("2023-09-01"), description: "Trump to be sentenced for hush-money payment"})
MERGE (trump)-[:SENTENCED_IN]->(trumpSentencing)
```