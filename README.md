# OaktreePower Code Problem

## Design
- Mainly functional with some struct like data classes where necessary
- Gradle as a build tool simply to speed up testing and bring in arrow
- Use of context receivers to \'give them a spin'
 

### Dependencies
- Junit + parameters - parameterizing tests to speed up development and reduce
test code footprint
- Arrow, may be overkill but much preferred over the `Result` type in the std lib
and is used for railroad pattern style error handling (as 1st class citizens), while
being 'functional' in nature