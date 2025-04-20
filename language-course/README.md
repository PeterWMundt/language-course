# Introduction 

Vaadin Start project for demo purposes ..


# Styling 

.. see also [https://vaadin.com/docs/latest/styling](https://vaadin.com/docs/latest/styling)

## Setup 

Within the folder `src/main/frontend/themes` a subfolder `vaadin-start-demo` is created. 
This folder contains the **theme** of the application. 

### Subfolder `vaadin-start-demo`

	vaadin-start-demo
	├── components
	├── views
	├  └── chat-view.css
	├── main-layout.css
	├── styles.css
	└── theme.json
	
	
* The theme folder must contain a master stylesheet called, `styles.css`. 
* A theme configuration file called, `theme.json`  is optional.
* The master stylesheet, `styles.css` is loaded automatically into the UI. 
* All CSS, including Lumo style property values and custom component styles, can be added there.
* Additional stylesheets are loaded through @import directives at the top of the master stylesheet. They can be placed in sub-folders.

The master stylesheet, `styles.css` 

	@import url('./main-layout.css');
	@import url('./views/chat-view.css');
	
## Components Sub-Folder (Legacy Feature) 

*Stylesheets placed in a sub-folder called components in the application theme are loaded by default into the Shadow DOM of Vaadin components — if their file names match the root element name of a component.* 

*This is a legacy feature from earlier versions of Vaadin:* 


# Changes

## Logging 

Spring default logging replaced with log4j2! 

.. in `pom.xml`

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<exclusions>
				<exclusion>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-starter-logging</artifactId>
				</exclusion>
			</exclusions>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-log4j2</artifactId>
		</dependency>


## Spring Boot Application & Vaadin AppShellConfigurator - Separation! 

In the **Spring Boot Application** `ch.orcasys.vaadin.demo.Application` the annotations `@Theme..` and `@Push` have been removed and added in a new class `ch.orcasys.vaadin.demo.MyAppShellConfigurator` that implements the interface `com.vaadin.flow.component.page.AppShellConfigurator`. 

This way we get a separation of these two concerns! 

### Annotations in the \`AppShellConfiguration\` class! 

	@Theme(value = "vaadin-start-demo")
	@Push
	public class MyAppShellConfigurator implements AppShellConfigurator{
		
	}


----------
# OLD STUFF

# My App

This project can be used as a starting point to create your own Vaadin application with Spring Boot.
It contains all the necessary configuration and some placeholder files to get you started.

## Running the application

Open the project in an IDE. You can download the [IntelliJ community edition](https://www.jetbrains.com/idea/download) if you do not have a suitable IDE already.
Once opened in the IDE, locate the `Application` class and run the main method using "Debug".

For more information on installing in various IDEs, see [how to import Vaadin projects to different IDEs](https://vaadin.com/docs/latest/getting-started/import).

If you install the Vaadin plugin for IntelliJ, you should instead launch the `Application` class using "Debug using HotswapAgent" to see updates in the Java code immediately reflected in the browser.

## Deploying to Production

The project is a standard Maven project. To create a production build, call 

```
./mvnw clean package -Pproduction
```

If you have Maven globally installed, you can replace `./mvnw` with `mvn`.

This will build a JAR file with all the dependencies and front-end resources,ready to be run. The file can be found in the `target` folder after the build completes.
You then launch the application using 
```
java -jar target/vaadin-start-1.0-SNAPSHOT.jar
```

## Project structure

- `MainLayout.java` in `src/main/java` contains the navigation setup (i.e., the
  side/top bar and the main menu). This setup uses
  [App Layout](https://vaadin.com/docs/components/app-layout).
- `views` package in `src/main/java` contains the server-side Java views of your application.
- `views` folder in `src/main/frontend` contains the client-side JavaScript views of your application.
- `themes` folder in `src/main/frontend` contains the custom CSS styles.

## Useful links

- Read the documentation at [vaadin.com/docs](https://vaadin.com/docs).
- Follow the tutorial at [vaadin.com/docs/latest/tutorial/overview](https://vaadin.com/docs/latest/tutorial/overview).
- Create new projects at [start.vaadin.com](https://start.vaadin.com/).
- Search UI components and their usage examples at [vaadin.com/docs/latest/components](https://vaadin.com/docs/latest/components).
- View use case applications that demonstrate Vaadin capabilities at [vaadin.com/examples-and-demos](https://vaadin.com/examples-and-demos).
- Build any UI without custom CSS by discovering Vaadin's set of [CSS utility classes](https://vaadin.com/docs/styling/lumo/utility-classes). 
- Find a collection of solutions to common use cases at [cookbook.vaadin.com](https://cookbook.vaadin.com/).
- Find add-ons at [vaadin.com/directory](https://vaadin.com/directory).
- Ask questions on [Stack Overflow](https://stackoverflow.com/questions/tagged/vaadin) or join our [Forum](https://vaadin.com/forum).
- Report issues, create pull requests in [GitHub](https://github.com/vaadin).
