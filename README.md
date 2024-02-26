# OaktreePower Code Problem

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
 

### Dependencies
- Junit + parameters - parameterizing tests to speed up development and reduce
test code footprint
- Arrow, may be overkill but much preferred over the `Result` type in the std lib
and is used for railroad pattern style error handling (as 1st class citizens), while
being 'functional' in nature