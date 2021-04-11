# TrackMe - Version 1.0 still in progress
This is a real-time group tracking app. Users can easily share their current location with other people within the same group.

The app is being built with the principles of Clean Architecture. These principles help to get the codebase under control and in a more 
manageable state. The app ver 1.0 will contain several layers (modules) to handle principles of Clean Architecture:
- Domain
- Data 
- Remote (in progress)
- Cache (planned for version 2.0)
- UI (in progress)


There are implemented range of technologies and libraries to create the project, this includes (not all technologies and libraries are already 
implemented, the app ver. 1.0. is still in progress and all technologies and libraries are subsequently added):

Version 1.0
- Kotlin as a language to code in
- MVI, Coroutines, Flow, Android Jetpack
- Koin for Dependency Injection
- Design support library for our UI components
- Kotest for our tests
- Mockk for handling mock data in our tests
- Firestore/Firebase for real-time communication
