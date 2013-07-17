Github Company Fight
====================

Presentation
------------------

Github Company Fight is a Web application allowing companies to fight on Github.

Github Company Fight is made with the following technologies :

- HTML5, [AngularJS](http://angularjs.org/) and [Twitter Bootstrap](http://twitter.github.com/bootstrap/)
- [JBoss 7](http://www.jboss.org/jbossas)
- [PostgreSQL](http://www.postgresql.org/)

Github Company Fight is developped by [Ippon Technologies](http://www.ippon.fr)

Installation
----------------

- Install [PostgreSQL](http://www.postgresql.org/) and launch it
- Install [JBoss 7](http://www.jboss.org/jbossas) and launch it
- Clone, fork or download the source code from this Github page and go the application directory
- Install [Maven 3](http://maven.apache.org/)
- Install the datasource on JBoss, from Maven : `mvn install`
- Deploy the application on JBoss, from Maven : `mvn jboss-as:deploy`
- Connect to the application at http://127.0.0.1:8080/company-fight

The application uses [the GitHub API for Java](http://github-api.kohsuke.org/), if you want to do more than 60
requests per hour, you need to create the ~/.github property file, which should have the following two values:

`
login=jdubois

password=ippon
`

Alternatively, you can have just the OAuth token in this file:

`
oauth=4d98173f7c075427cb64878561d1fe70
`

License
-------

Copyright 2012 [Ippon Technologies](http://www.ippon.fr)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this application except in compliance with the License.
You may obtain a copy of the License at

[http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
