# REST API Design

## Get the current date
GET http://localhost:4567/DAI/current/date
## Get the current month
GET http://localhost:4567/DAI/current/month
## Get the current year
GET http://localhost:4567/DAI/current/year
## Get event(s) for a given date
GET http://localhost:4567/DAI/allEvents/year/{yyyy}/month/{mm}/date/{dd}
## Add event
POST http://localhost:4567/DAI/event?month={mm}&date={dd}&year={yyyy}&patient={p}&doctor{d}}&comment={c}
## Modify event
PUT http://localhost:4567/DAI/event/{id}?month={mm}&patient={p}
## Delete event
DELETE http://localhost:4567/DAI/event/{id}
