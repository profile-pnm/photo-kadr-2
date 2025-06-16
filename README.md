# photo-kadr-2
Multi-user web-application, which store photos in albums. Another word, this is photoalbum website.

### How deployment
Requirements:
1. Eclipse IDE (2024-12) with include packages (software):

   | Package  							 | Link															   	   |
   |-----------------------------------  |---------------------------------------------------------------------|
   | Eclipse IDE integration for Maven   | https://download.eclipse.org/technology/m2e/releases/latest 		   |
   | EGit							     | https://download.eclipse.org/egit/updates						   |
   | JustJ JRE Updates for IDE Packages  | https://download.eclipse.org/justj/epp/release/latest			   |
   | Latest Eclipse IDE Packages Release | https://download.eclipse.org/technology/epp/packages/latest/ 	   |
   | Latest Eclipse Simultaneous Release | https://download.eclipse.org/releases/latest						   |
   | Lombok								 | https://projectlombok.org/p2										   |
   | Spring Tool Suite 4				 | https://cdn.spring.io/spring-tools/release/TOOLS/sts4/update/e4.34/ |
   | The Eclipse Project Updates		 | https://download.eclipse.org/eclipse/updates/4.34				   |
   | The Eclipse Web Tools Platform 	 | https://download.eclipse.org/webtools/repository/latest			   |
   
2. To the Spring Tool Suite 4 add some dependencies:
   - spring-boot-starter-jdbc
   - spring-boot-starter-web
   - spring-boot-starter-security
   - spring-boot-devtools
   - spring-boot-starter-thymeleaf
   - spring-boot-starter-validation
   - h2 (db inside project)
   - tika-core (check type file)
   - commons-io

Import this project how maven project and run in IDE. Then open page in browser on `localhost:8080` 
 