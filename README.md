# OaktreePower Code Problem

## Prerequisites

- Gradle
- jdk17 or greater

## Usage

```shell
./gradlew run 
```

### Available Commands

| Command | Arguments                                              | Description                                                                      | Example     |
|---------|--------------------------------------------------------|----------------------------------------------------------------------------------|-------------|
| C       | x (integer), y (integer)                               | creates a canvas with size x and y                                               | C 20 4      |
| L       | x1 (integer), y1 (integer), x2 (integer), y2 (integer) | draws a line between two points (x1,y1) and (x2, y2)                             | L 6 3 6 4   |
| R       | x1 (integer), y1 (integer), x2 (integer), y2 (integer) | draws a rectangle with upper left corner (x1,y1) and lower right corner (x2, y2) | R 16 1 20 3 |
| B       | x (integer), y (integer), c (character)                | performs a flood fill with starting point (x,y)                                  | B 10 3 o    |
| Q       |                                                        | Quits the applications                                                           | Q           |

or simply run the application and input `help`

## Design

- Mainly functional with some struct like data classes where necessary
- Gradle as a build tool simply to speed up testing and bring in arrow
- Use of context receivers to \'give them a spin'
- Rather than executing a do while or breaking for directly in the main method.
  I thought it would be interesting to tie a simple state machine to the context
  via the experimental API.
- Using a CharArray for the actual canvas, for mutation. Certainly a room splitter
  to 'opt in' to mutation rather than producing a new canvas via pure functions, which
  would be enforced with a `List` implementation.
- to add to the above it will mean tests need to have a prepared state, it'd be be nice
  to have something in tests to streamline this... Will likely skip due to time constraints
- opted into recursive flood fill instead of utilising function stack frames rather than
an explicit queue or other.

### Structure

```shell
├── context -> contains the execution logic for how the app behaves
├── ports -> contains logic to perform operations to and form domain
│   ├── in  
│   └── out
└── domain -> contains domain models and mappings
    ├── canvas
    ├── commands
    ├── errors
    └── mapping -> utilities to map commands to the domain
        └── parser -> command parsing behaviours
```

### Dependencies

- Junit + parameters - parameterizing tests to speed up development and reduce
  test code footprint
- Arrow, may be overkill but much preferred over the `Result` type in the std lib
  and is used for railroad pattern style error handling (as 1st class citizens), while
  being 'functional' in nature
