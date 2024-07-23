```cypher
MERGE (event1:Event {description: "Looting at VBS Mutual Bank", date: "October 2017"})
MERGE (individual1:Individual {name: "Andile Ramavhunga", description: "Former CEO of VBS Mutual Bank"})
MERGE (company1:Company {name: "VBS Mutual Bank", description: "Bank involved in looting scandal"})
MERGE (company2:Company {name: "Dambale Holdings", description: "Business account receiving looted funds"})
MERGE (company3:Company {name: "Munyai Investments", description: "Company receiving looted funds"})
MERGE (company4:Company {name: "Vele Petroport", description: "Company involved in transactions"})
MERGE (company5:Company {name: "Absa", description: "Bank holding account receiving looted funds"})
MERGE (individual2:Individual {name: "Phophi Mukhodobwane", description: "Former treasurer of VBS Mutual Bank"})
MERGE (company6:Company {name: "Lemawave", description: "Company receiving looted funds"})
MERGE (individual3:Individual {name: "Tshifhiwa Matodzi", description: "Convicted former VBS chair"})
MERGE (individual4:Individual {name: "Kabelo Matsepe", description: "Former ANC Youth League leader"})
MERGE (company7:Company {name: "Robvet", description: "Company involved in transactions"})
MERGE (company8:Company {name: "MRS Holdings", description: "Company involved in transactions"})
MERGE (individual5:Individual {name: "Shaun Abrahams", description: "Disgraced former NPA boss"})
MERGE (individual6:Individual {name: "Danny Msiza", description: "Former ANC Limpopo treasurer"})
MERGE (organization1:Organization {name: "National Prosecuting Authority", description: "Prosecuting authority"})
MERGE (individual7:Individual {name: "Hein van der Merve", description: "State prosecutor"})
MERGE (individual8:Individual {name: "Mthunzi Mhaga", description: "NPA spokesperson"})

MERGE (individual1)-[:RECEIVED_PAYMENT {amount: 15300000, date: "2017-10-05"}]->(company2)
MERGE (individual1)-[:RECEIVED_PAYMENT {amount: 300000, date: "2017-10-02"}]->(company2)
MERGE (individual1)-[:RECEIVED_PAYMENT {amount: 23400000, duration: "24 months"}]->(company1)
MERGE (individual1)-[:RECEIVED_PAYMENT {amount: 19000000}]->(company2)
MERGE (individual1)-[:RECEIVED_PAYMENT {amount: 300000, date: "2017-10-31"}]->(company2)
MERGE (individual1)-[:RECEIVED_PAYMENT {amount: 3700000}]->(company3)
MERGE (individual1)-[:RECEIVED_PAYMENT {amount: 180000, date: "2016-12-01"}]->(company4)
MERGE (individual1)-[:RECEIVED_PAYMENT {amount: 120000, date: "2016-12-01"}]->(company4)
MERGE (individual1)-[:RECEIVED_PAYMENT {amount: 800000, duration: "8 months"}]->(company5)
MERGE (individual1)-[:RECEIVED_PAYMENT {amount: 23495000, duration: "2016-02-09 to 2018-02-02"}]->(company1)
MERGE (event1)-[:LED_TO]->(company1)
MERGE (company1)-[:COLLAPSED {date: "2018-03"}]->(event1)
MERGE (individual2)-[:RECEIVED_PAYMENT {amount: 16000000, duration: "7 months"}]->(company1)
MERGE (individual2)-[:RECEIVED_PAYMENT {amount: 5600000, date: "2017-03-30"}]->(company6)
MERGE (individual2)-[:RECEIVED_PAYMENT {amount: 10000000, date: "2017-10-05"}]->(company6)
MERGE (individual2)-[:RECEIVED_PAYMENT {amount: 350000, date: "2017-04-13"}]->(company6)
MERGE (individual2)-[:RECEIVED_PAYMENT {amount: 150000, date: "2017-05-13"}]->(company6)
MERGE (individual3)-[:INSTRUCTED_PAYMENT {amount: 5600000, date: "2017-03-30"}]->(company6)
MERGE (individual4)-[:RECEIVED_PAYMENT {amount: 499999, date: "2016-10-15"}]->(company7)
MERGE (individual4)-[:RECEIVED_PAYMENT {amount: 499999, date: "2016-10-15"}]->(company8)
MERGE (individual4)-[:RECEIVED_PAYMENT {amount: 456000, date: "2016-10-17"}]->(company7)
MERGE (individual4)-[:RECEIVED_PAYMENT {amount: 4900000}]->(company1)
MERGE (individual4)-[:RECEIVED_PAYMENT {amount: 16000000}]->(company1)
MERGE (individual4)-[:RECEIVED_PAYMENT {amount: 7800000}]->(company1)
MERGE (individual5)-[:MADE_APPLICATION {date: "2023-08-14"}]->(event1)
MERGE (individual6)-[:MADE_APPLICATION {date: "2023-08-14"}]->(event1)
MERGE (individual7)-[:OBJECTED_TO]->(individual5)
MERGE (individual8)-[:COMMENTED_ON]->(event1)
```