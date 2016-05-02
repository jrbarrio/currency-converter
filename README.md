# currency-converter

Protected currency converter application using [openexchangerates.org](http://openexchangerates.org) public API. It has been built using:
- Spring Boot.
- Spring MVC.
- Thymeleaf.
- Gradle.
- H2 database.

This is the [application URL](http://serene-scrubland-62122.herokuapp.com) deployed in [heroku](https://www.heroku.com/home). The aplication has the following pages:
- "/": main page (secured).
- "/login": login page.
- "/register": register page.

The continuous integration server for this application is hosted at [Travis-CI sever](https://travis-ci.org/jrbarrio/currency-converter).

The application is deployed including Spring Boot Actuator to provide a monitoring REST interface with the following endpoints:
- "/health"
- "/env"
- "/mappings"
- "/beans"
- "/info"
- "/metrics"
- "/autoconfig"
- "/configprops"
- "/trace"
- "/dump"


