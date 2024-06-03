# Lab 2.1 - Flight demonstration: Basic Coding with Copilot Assistance

This module demonstrates how to utilize GitHub Copilot's Chat Extension and its agents (@workspace, @terminal, @vscode) to understand and navigate a codebase, implement REST API methods, generate code from comments, and maintain coding style consistency, culminating in a comprehensive, productivity-enhancing coding experience.

## Prerequisites

- The prerequisites steps must be completed, see [Labs Prerequisites](../Lab%201.1%20-%20Pre-Flight%20Checklist/README.md)

## Estimated time to complete

- 20 minutes, times may vary with optional labs.

## Objectives

- Introduction to GitHub Copilot Chat and its agents for code completion and style adaptation.

  - Step 1 - Plane Inspection - Explain the Codebase with GitHub Copilot Chat
  - Step 2 - Airplane Docking - Add new Flight Model
  - Step 3 - Test Flight - Autocompletion and Suggestions
  - Step 4 - Flight Plan - Code Completion and Style Adaptation
  - Step 5 - Laying Down the Runway - Creating the AirfieldController from thin air (Optional)

> [!IMPORTANT]  
> Please note that Copilot's responses are generated based on a mix of curated data, algorithms, and machine learning models, which means they may not always be accurate or reflect the most current information available. Users are advised to verify Copilot's outputs with trusted sources before making decisions based on them.

### Step 1: Plane Inspection - Explain the Codebase with GitHub Copilot Chat

- Open GitHub Copilot Chat

- Type the following in the chat window:

```md
explain the WrightBrothers API
```

- Copilot will give a brief overview of the API. This is a good way to get a quick overview of the codebase.

Try the following request:

```md
what does the PlanesController do?
```

> [!NOTE]  
> While VSCode has the `@workspace` agent call, IntelliJ does not. Instead, it always creates answers from a subset of files in the opened workspace (usually a folder or a project), and use the file tree information to analyze each file briefly and see if it would be intesting context to send into Copilot. This analysis happens clientside and only the files that match (for example the file name indicates a match, or a piece of the file content looks like a match), then those files/parts are send in as extra context. This can be seen in the "Used x references" in the Chat interface that can be openened and reviewed for the file references.

- Request how to run the application:

    ```md
    how can I run the application?
    ```

Copilot will let you know you can run it using Maven and maybe even how to do it using Gradle. 

> [!NOTE]
> While VSCode has the `@terminal` agent that can be used to help navigate the terminal and to answer generic questions about how to do things in the terminal. IntelliJ does not have this capability. In fact, Copilot in IntelliJ will decline to provide information about your command history and associated details. 

- Next, try to ask about managing IntelliJ plugins by typing the following in the chat window:

```md
how can I install a plugin for IntelliJ?
```

- It will provide a corresponding instructions to install extensions.

### Step 2: Airplane Docking - Add new Flight Model

- Open GitHub Copilot Chat, click **+** to clear prompt history.

- Ask Copilot to explain the `PlanesController.ts` class

    ```md
    What does the PlanesController do?
    ```

> [!NOTE]
> GitHub Copilot will give a brief overview of the `PlanesController.java` class.

- Now that we know what the PlanesController does, open `WrightBrothersApi` folder.

- Open the `src/main/java/com/arctiq/wright/controller/PlanesController.java` file.

```java
...
@RestController
@RequestMapping("/planes")
public class PlanesController {

    private List<Plane> planes = new ArrayList<>();

    public PlanesController() {
        planes.add(new Plane(1, "Wright Flyer", 1903, "The first successful heavier-than-air powered aircraft.", 12));
        planes.add(new Plane(2, "Wright Flyer II", 1904, "A refinement of the original Flyer with better performance.", 24));
        planes.add(new Plane(3, "Wright Model A", 1908, "The first commercially successful airplane.", 40));
        <---- Place your cursor here.
    }
...
```

- Let's add a new plane to the list by placing your cursor at the end of the `Planes` list, after the `}` of `Plane` with `Id = 3`, type a `,` then press `Enter`.

- GitHub Copilot will automatically suggest a `new Plane`.

> [!NOTE]
> GitHub Copilot will suggest a new `Plane` object with the next available `Id`. Also notice how Copilot understood that the next Plane is the Wright Model B and it automatically suggested the `Name`, `Year`, `Description`, and `RangeInKm` properties. The underlying LLM also learned from Wikipedia and other sources to understand the history of the Wright Brothers.

- Accept the suggestion by pressing `Tab` to accept this suggestion.

### Step 3: Test Flight - Autocompletion and Suggestions

- Open `WrightBrothersApi` folder.

- Open the `src/main/java/com/arctiq/wright/controller/PlanesController.java` file.

- Place your cursor at the end of the file, after the `}` of the `createPlane` method.

```java
class PlanesController {
    /* Rest of the methods */

    @PostMapping("/")
    public ResponseEntity<Plane> createPlane(@RequestBody Plane plane) {
        planes.add(plane);
        return ResponseEntity.status(201).body(plane);
    }<---- Place your cursor here
}
```

- Then, press `Enter` twice.

- Github Copilot will automatically suggest the `updatePlane` method, press `Tab` to accept.

    ```java
    // * Suggested by Copilot
    @PutMapping("/{id}")
    public ResponseEntity<Plane> updatePlane(@PathVariable int id, @RequestBody Plane plane) {
        Optional<Plane> existingPlane = planes.stream().filter(p -> p.getId() == id).findFirst();
        if (existingPlane.isPresent()) {
            Plane updatedPlane = existingPlane.get();
            updatedPlane.setName(plane.getName());
            updatedPlane.setYear(plane.getYear());
            updatedPlane.setDescription(plane.getDescription());
            return ResponseEntity.ok(updatedPlane);
        } else {
            return ResponseEntity.status(404).build();
        }
    }
    // * Suggested by Copilot
    ```

> [!NOTE]
> The reason GitHub Copilot suggests the `updatePlane` method is because it understand that the `PlanesController.ts` class is a REST API controller and that the `updatePlane` is currently missing. The `updatePlane` method is the next logical step in the REST API for updating a resource.

> [!IMPORTANT]
> Be sure to proofread the suggestion. Sometimes Copilot can hallucinate additional data members or otherwise provide innacurate answers. Always read what it has suggested.

- Let's do it again, place your cursor at the end of the file, after the `}` of the `Put` method, press `Enter` twice.

- Copilot will automatically suggest the `deletePlane` method, press `Tab` to accept.

    ```java
    // * Suggested by Copilot
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlane(@PathVariable int id) {
        Optional<Plane> plane = planes.stream().filter(p -> p.getId() == id).findFirst();
        if (plane.isPresent()) {
            planes.remove(plane.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }
    // * Suggested by Copilot
    ```

### Step 4: Test Flight Accelerate - Comments to Code

- Open `WrightBrothersApi` folder.

- Open the `src/main/java/com/arctiq/wright/controller/PlanesController.java` file.

- Type `// Search planes by name` in the comment block. After the `}` of the `getAllPlanes` method, press `Enter`.

    ```java
    // Search planes by name
    ```

    ```java
    ...
    @RestController
    @RequestMapping("/planes")
    public class PlanesController {
    /* Rest of the methods */

        @GetMapping("/")
        public List<Plane> getAllPlanes() {
            return planes;
        }
    
        <---- Place your cursor here
        // Search Planes by name
    }
    ...
    ```

- GitHub Copilot will automatically suggest a search method, press `Tab` to accept.

    ```java
    // Search planes by name
    // * Suggested by Copilot
    @GetMapping("/search")
    public ResponseEntity<List<Plane>> searchPlanes(@RequestParam String name) {
        List<Plane> matchingPlanes = new ArrayList<>();
        for (Plane plane : planes) {
            if (plane.getName().toLowerCase().contains(name.toLowerCase())) {
                matchingPlanes.add(plane);
            }
        }
        return ResponseEntity.ok(matchingPlanes);
    }    // * Suggested by Copilot
    ```

> [!NOTE]
> The reason GitHub Copilot suggests the `searchPlanes` method is because it understands that the comment is a description of the method. It also understands that the method is a GET method and that it has a parameter `name` of type `String`.

It may be the case that your suggested method didn't come with a handler for the case where the plane is not found (as in the example above). If so, we can fix this by adding a comment after the main for loop.

```java
    ...
        for (Plane plane : planes) {
            if (plane.getName().toLowerCase().contains(name.toLowerCase())) {
                matchingPlanes.add(plane);
            }
            // return 404 if no matching planes found
    ...
```

Then, allow copilot to suggest an appropriate check for this case.

```java
...
        // return 404 if no matching planes found
        // * Suggested by Copilot
        if (matchingPlanes.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        // * Suggested by Copilot
        return ResponseEntity.ok(matchingPlanes);
```

- Let's do it again, place your cursor inside the and at the top of the createPlane method.

- Type `// Return BadRequest if plane already exists by name` in the comment block.

    ```java
    // Return BadRequest if plane already exists by name
    ```

    ```java
    @PostMapping("/")
    public ResponseEntity<Plane> createPlane(@RequestBody Plane plane) {
        // Check if the plane already exists

        planes.add(plane);
        return ResponseEntity.status(201).body(plane);
    }
    ```

- Copilot will automatically suggest the `if` statement and return `Plane already exists` if the plane already exists by name.

    ```java
    @PostMapping("/")
    public ResponseEntity<Plane> createPlane(@RequestBody Plane plane) {
        // Check if the plane already exists
        Optional<Plane> existingPlane = planes.stream().filter(p -> p.getId() == plane.getId()).findFirst();
        if (existingPlane.isPresent()) {
            return ResponseEntity.status(409).body(plane);
        } else {
            planes.add(plane);
            return ResponseEntity.status(201).body(plane);
        }
    }
   ```

- this will return an error in the API if the ID of the new plane is already present in the collection. If we want a more robust check we'd need to provide more field checks here.

### Step 5: Laying Down the Runway - Creating the AirfieldController from thin air (Optional)

- Open the `WrightBrothersApi` folder in Visual Studio Code.

- Open the `src/main/java/com/arctiq/wright/model/Airfield.java` file.

- Open GitHub Copilot Chat, click **+** to clear prompt history.

- Ask the following question:

    ```md
    Using the Airfield class, create a new ApiController class with all the CRUD operations and add test data for the first 3 airfields used by the Wright Brothers as well as the router with all the routing handlers attached.
    ```

- Copilot will give a suggestion to create an `AirfieldController` class based on the `Airfield` class.

    ```java
    package com.arctiq.wright.controller;

    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Optional;
    import com.arctiq.wright.model.Airfield;

    @RestController
    @RequestMapping("/airfields")
    public class AirfieldController {

        private List<Airfield> airfields = new ArrayList<>();

        public AirfieldController() {
            airfields.add(new Airfield(1, "Huffman Prairie", "Dayton, Ohio", "1904-1910", "The Wright Brothers' primary testing and development site."));
            airfields.add(new Airfield(2, "Kill Devil Hills", "North Carolina", "1900-1903", "Site of the Wright Brothers' first controlled, sustained flight."));
            airfields.add(new Airfield(3, "Simms Station", "Dayton, Ohio", "1910-1916", "Location of the Wright Brothers' first civilian training school."));
        }

        @GetMapping("/")
        public List<Airfield> getAllAirfields() {
            return airfields;
        }

        @GetMapping("/{id}")
        public ResponseEntity<Airfield> getAirfieldById(@PathVariable int id) {
            Optional<Airfield> airfield = airfields.stream().filter(a -> a.getId() == id).findFirst();
            if (airfield.isPresent()) {
                return ResponseEntity.ok(airfield.get());
            } else {
                return ResponseEntity.status(404).build();
            }
        }

        @PostMapping("/")
        public ResponseEntity<Airfield> createAirfield(@RequestBody Airfield airfield) {
            Optional<Airfield> existingAirfield = airfields.stream().filter(a -> a.getId() == airfield.getId()).findFirst();
            if (existingAirfield.isPresent()) {
                return ResponseEntity.status(409).body(airfield);
            } else {
                airfields.add(airfield);
                return ResponseEntity.status(201).body(airfield);
            }
        }

        @PutMapping("/{id}")
        public ResponseEntity<Airfield> updateAirfield(@PathVariable int id, @RequestBody Airfield airfield) {
            Optional<Airfield> existingAirfield = airfields.stream().filter(a -> a.getId() == id).findFirst();
            if (existingAirfield.isPresent()) {
                Airfield updatedAirfield = existingAirfield.get();
                updatedAirfield.setName(airfield.getName());
                updatedAirfield.setLocation(airfield.getLocation());
                updatedAirfield.setDatesOfUse(airfield.getDatesOfUse());
                updatedAirfield.setSignificance(airfield.getSignificance());
                return ResponseEntity.ok(updatedAirfield);
            } else {
                return ResponseEntity.status(404).build();
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteAirfield(@PathVariable int id) {
            Optional<Airfield> airfield = airfields.stream().filter(a -> a.getId() == id).findFirst();
            if (airfield.isPresent()) {
                airfields.remove(airfield.get());
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(404).build();
            }
        }
    }
    ```

- Create a new class file called AirfieldController.java in the java/com/arctiq/wright/controller folder. 
- In GitHub Copilot Chat, click the "Insert Code Block at cursor" with the new class file open (ensure the default contents are cleared or highlighted first.)
- Save the file by clicking pressing `Ctrl + S` or `Cmd + S`.

> [!NOTE]
> Copilot is not only context aware, knows you need a list of items and knows the `Air Fields` used by the Wright Brothers, the `Huffman Prairie`, which is the first one used by the Wright Brothers.

- Now that you have created the `AirfieldController` with CRUD operations, it's time to check that it's working as expected.

- Run the application by typing the following commands in the terminal:

    ```sh
    mvn spring-boot:run 
    ```

- Open the browser to `http://localhost:3000/swagger-ui/index.html` 
- Find the `airfiled-controller` group of actions.
- Expand the '`GET /airfields/` action.
- Click the `Try it out` button on the right side of this action.
- Click `Execute` to get all of the airfields.

- You will see that the list of airfields (likely) includes three airfields as:
  - Huffman Prairie
  - Kill Devil Hill
  - Simms Station

- Response will look like:

![Screenshot of Airfields List](../../Images/Screenshot-Lab2.1-Airfields-list.png)

- Stop the application by pressing `Ctrl+C` in the terminal window.

### Congratulations you've made it to the end! &#9992; &#9992; &#9992;

#### And with that, you've now concluded this module. We hope you enjoyed it! &#x1F60A;
