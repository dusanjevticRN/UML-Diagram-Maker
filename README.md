In the requirement specification for the ClassyCrafT UML Diagram Maker (Specifikacija_ClassyCraft.pdf), I provided a thorough introduction, outlining the app's concept, intended users, and a competitive analysis that highlights our strengths. The document also covers functional, optional, and advanced requirements, ensuring that all necessary features are well-defined. Non-functional requirements are detailed as well. A key component of the specification is the use case diagram, which visually represents the primary interactions within the system. This diagram is crucial for understanding the application's functionality and is included as a central part of the document.

In short, the idea behind this project was to create a tool for making UML diagrams similar to what you can do in Visual Paradigm.

In developing this project, several design patterns were utilized, including Singleton, Observer, EventBus, Composite, Simple Factory (which covers Factory Method), State, and Command patterns. Additionally, the project features serialization and deserialization capabilities.

In the ClassyCrafT application, users can create classes, interfaces, and enums, and establish connections between them. The workspace allows for easy manipulation of these elements; users can move them around the screen, add fields and methods, and adjust the zoom level for detailed or broad views. The application supports duplicating elements and offers undo and redo functionality to manage changes efficiently. Additionally, a "Code" button on the far right of the JMenuBar enables users to convert the UML diagram into code. Users can also save and load projects to maintain their work and continue editing as needed.

This entire project consists of approximately 10,000 to 12,000 lines of code, maybe slightly more, but no less.

![image](https://github.com/user-attachments/assets/7b647ee3-2304-449f-bfb8-06a853bab856)
