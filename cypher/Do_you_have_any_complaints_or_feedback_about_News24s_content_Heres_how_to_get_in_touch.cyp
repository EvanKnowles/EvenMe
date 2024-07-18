```cypher
MERGE (george:Individual {name: "George Claassen", description: "Seasoned journalist and academic Professor"})
MERGE (news24:Organization {name: "News24", description: "News organization"})
MERGE (newsombuds:Organization {name: "Organisation of Newsombuds and Standards Editors", description: "International organization for news ombuds and standards editors"})
MERGE (media24:Company {name: "Media24", description: "Parent company of News24"})

MERGE (george)-[:ROLE {description: "Public Editor", since: "2023"}]->(news24)
MERGE (george)-[:BOARD_MEMBER {since: "2011"}]->(newsombuds)
MERGE (news24)-[:OWNED_BY]->(media24)

MERGE (complaints:Event {description: "Complaints about editorial content"})
MERGE (queries:Event {description: "Queries about editorial content"})
MERGE (suggestions:Event {description: "Suggestions about editorial content"})
MERGE (technical_issues:Event {description: "Technical issues or queries about subscriptions"})
MERGE (comments_queries:Event {description: "Queries about comments"})

MERGE (george)-[:HANDLES]->(complaints)
MERGE (george)-[:HANDLES]->(queries)
MERGE (george)-[:HANDLES]->(suggestions)
MERGE (news24)-[:HANDLES]->(technical_issues)
MERGE (news24)-[:HANDLES]->(comments_queries)

SET george.email = "george.claassen@media24.com"
SET george.phone = "021 851 3232"
SET news24.email_subs = "subs@news24.com"
SET news24.email_comments = "comments@news24.com"
```