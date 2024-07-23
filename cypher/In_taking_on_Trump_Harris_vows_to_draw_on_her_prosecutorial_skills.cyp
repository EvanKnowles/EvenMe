```cypher
MERGE (event1:Event {name: "Kamala Harris Presidential Campaign Announcement", date: "2024-08-12", description: "Kamala Harris announced her presidential run and rallied supporters."})
MERGE (individual1:Individual {name: "Kamala Harris", description: "US Vice President and former Attorney General of California and US Senator"})
MERGE (individual2:Individual {name: "Donald Trump", description: "Republican nominee and former President of the United States"})
MERGE (individual3:Individual {name: "Joe Biden", description: "Current President of the United States"})
MERGE (individual4:Individual {name: "Karoline Leavitt", description: "Trump campaign's national press secretary"})
MERGE (individual5:Individual {name: "Nancy Pelosi", description: "Former Speaker of the House of Representatives"})
MERGE (individual6:Individual {name: "Gretchen Whitmer", description: "Governor of Michigan"})
MERGE (individual7:Individual {name: "Gavin Newsom", description: "Governor of California"})
MERGE (individual8:Individual {name: "Andy Beshear", description: "Governor of Kentucky"})
MERGE (individual9:Individual {name: "Eric Holder", description: "Former US Attorney General"})
MERGE (individual10:Individual {name: "George Lang", description: "Ohio state senator"})
MERGE (individual11:Individual {name: "JD Vance", description: "Trump's running mate"})
MERGE (individual12:Individual {name: "Benjamin Netanyahu", description: "Israeli Prime Minister"})
MERGE (organization1:Organization {name: "AFL-CIO", description: "Labour union federation representing 12.5-million workers"})
MERGE (company1:Company {name: "Covington & Burling LLP", description: "Law firm"})
MERGE (party1:Party {name: "Democratic Party"})
MERGE (party2:Party {name: "Republican Party"})

MERGE (individual1)-[:MEMBER_OF]->(party1)
MERGE (individual2)-[:MEMBER_OF]->(party2)
MERGE (individual3)-[:MEMBER_OF]->(party1)
MERGE (individual5)-[:MEMBER_OF]->(party1)
MERGE (individual6)-[:MEMBER_OF]->(party1)
MERGE (individual7)-[:MEMBER_OF]->(party1)
MERGE (individual8)-[:MEMBER_OF]->(party1)
MERGE (individual10)-[:MEMBER_OF]->(party2)
MERGE (individual11)-[:MEMBER_OF]->(party2)

MERGE (event1)-[:INVOLVES]->(individual1)
MERGE (event1)-[:INVOLVES]->(individual2)
MERGE (event1)-[:INVOLVES]->(individual3)
MERGE (event1)-[:INVOLVES]->(individual5)
MERGE (event1)-[:INVOLVES]->(individual6)
MERGE (event1)-[:INVOLVES]->(individual7)
MERGE (event1)-[:INVOLVES]->(individual8)
MERGE (event1)-[:INVOLVES]->(individual10)
MERGE (event1)-[:INVOLVES]->(individual11)

MERGE (individual3)-[:ENDORSED]->(individual1)
MERGE (individual5)-[:ENDORSED]->(individual1)
MERGE (organization1)-[:ENDORSED]->(individual1)
MERGE (individual6)-[:ENDORSED]->(individual1)
MERGE (individual7)-[:ENDORSED]->(individual1)
MERGE (individual8)-[:ENDORSED]->(individual1)

MERGE (individual9)-[:WORKS_FOR]->(company1)
MERGE (individual9)-[:VETTING]->(individual1)

MERGE (individual1)-[:CRITICIZED]->(individual2)
MERGE (individual2)-[:CRITICIZED]->(individual1)

MERGE (event2:Event {name: "Biden's Withdrawal from 2024 Race", date: "2024-08-11", description: "Joe Biden announced his withdrawal from the 2024 presidential race."})
MERGE (event2)-[:INVOLVES]->(individual3)
MERGE (event2)-[:INVOLVES]->(individual1)

MERGE (event3:Event {name: "Trump's Sentencing", date: "2024-09", description: "Donald Trump is due to be sentenced for falsifying business records."})
MERGE (event3)-[:INVOLVES]->(individual2)

MERGE (event4:Event {name: "Trump's Criminal Charges", description: "Donald Trump faces criminal charges related to his efforts to overturn Biden's 2020 victory."})
MERGE (event4)-[:INVOLVES]->(individual2)

MERGE (event5:Event {name: "Trump's False Election Fraud Claims", description: "Donald Trump falsely claims he lost in 2020 because of election fraud."})
MERGE (event5)-[:INVOLVES]->(individual2)

MERGE (event6:Event {name: "Biden's Covid-19 Recovery", date: "2024-08-11", description: "Joe Biden recovering from Covid-19 at his home in Delaware."})
MERGE (event6)-[:INVOLVES]->(individual3)

MERGE (event7:Event {name: "Harris' Campaign Fundraising", date: "2024-08-11", description: "Kamala Harris' campaign raised $81m in 24 hours after Biden's exit."})
MERGE (event7)-[:INVOLVES]->(individual1)

MERGE (event8:Event {name: "Harris' Campaign Speech", date: "2024-08-12", description: "Kamala Harris delivered a campaign speech outlining her policies."})
MERGE (event8)-[:INVOLVES]->(individual1)

MERGE (event9:Event {name: "Trump's Near-Assassination", date: "2024-07-13", description: "Trump was nearly assassinated by a gunman during a campaign stop."})
MERGE (event9)-[:INVOLVES]->(individual2)

MERGE (event10:Event {name: "Biden's Debate Performance", date: "2024-06-27", description: "Joe Biden had a disastrous debate performance against Trump."})
MERGE (event10)-[:INVOLVES]->(individual3)
MERGE (event10)-[:INVOLVES]->(individual2)

MERGE (event11:Event {name: "Harris' Milwaukee Visit", date: "2024-08-13", description: "Kamala Harris will travel to Milwaukee, Wisconsin."})
MERGE (event11)-[:INVOLVES]->(individual1)

MERGE (event12:Event {name: "Republican National Convention", date: "2024-08-06", description: "Republican National Convention hosted in Wisconsin."})
MERGE (event12)-[:INVOLVES]->(individual2)

MERGE (event13:Event {name: "Trump's Critique of Harris", date: "2024-08-12", description: "Trump campaign sent out a detailed critique of Harris' record."})
MERGE (event13)-[:INVOLVES]->(individual2)
MERGE (event13)-[:INVOLVES]->(individual1)

MERGE (event14:Event {name: "Lang's Civil War Remarks", date: "2024-08-12", description: "George Lang called for civil war if Trump didn't win the November election."})
MERGE (event14)-[:INVOLVES]->(individual10)
MERGE (event14)-[:INVOLVES]->(individual2)

MERGE (event15:Event {name: "Biden-Netanyahu Meeting", date: "2024-08-13", description: "Biden plans to meet with Israeli Prime Minister Benjamin Netanyahu."})
MERGE (event15)-[:INVOLVES]->(individual3)
MERGE (event15)-[:INVOLVES]->(individual12)
```