# PolytechJavaLabs
Labs for java cource in SPBSTU for 3rd semister by Artyom Petrov 3530904/80004

_______________________________________________________________________________

- Sources for Lab #1 are located at `src/ru/petrov/lab1`
- Tests for Lab #1 are located at `test/ru/petrov/lab1`

_______________________________________________________________________________

- Sources for Lab #2 are located at `src/ru/petrov/lab2`
- Tests for Lab #2 are not provided - all classes have no complicated functionality and main program is all about manipulating collections
- Input data (file) for  Lab #2 is located at `resources/plain/animals`
- Output of the program can be seen at `output/lab2`. There are three files, each for each task

_______________________________________________________________________________

- Sources for Lab #3 are located at `src/ru/petrov/lab3`
- Tests for Lab #3  are located at `test/ru/petrov/lab3`
- String builder than provides the possibility to unexecute actions is realised in `ImprovedStringBuilder` class
- Implementation uses pattern "Command" ("Transaction"). All classes, concerning this pattern are located at `src/ru/petrov/lab3/commands`

_______________________________________________________________________________

- Sources for Lab #4 are located at `src/ru/petrov/lab4`
- Tests for Lab #4 are located at `test/ru/petrov/lab4`
- Class `Main` creates all necessary classes and lauches app
- Class `Controller` implements logic of how to react on different user requests
- Class `Explorer` provides logic of accessing end editing file structure.
- Class `FileEditor` provides logic of editing files.
- Classes in `utils.io` are encapsulating logic of input/output needed by controller
- Classes in `utils.providers` are wrappers around `Explorer` and `FileEditor`, thus allowing easier usage without violating Single Responsibility Principle

_______________________________________________________________________________

- Sources for Lab #5 are located at `src/ru/petrov/lab5`
- Tests for Lab #5 are located at `test/ru/petrov/lab5`

_______________________________________________________________________________

- Sources for Lab #6 are located at `src/ru/petrov/lab6`
- Tests for Lab #6 are located at `test/ru/petrov/lab6`
- All data was generated using class `utils.DataGenerator`
- Data from XML is loaded using class `lab6.utils.XMLTractionLoader`
- Transactions can are executed using Transaction Managers in package `lab6.transactions.managers`
- Class `SynchronousTransactionManager` executes all transactions in one thread by the queue
- Class `AsynchronousTransactionManager` executes all transactions in multiple threads using `ExecutorService` thread
- Class `TransactionDemo` can show how `AsynchronousTransactionManager` works