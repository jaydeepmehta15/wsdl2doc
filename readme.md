###Overview
This project is about to generate a documentation in different 
output formats (PDF, Word, HTML and Markdown) from a WSDL source (file or remote resource).

To use it, simply call it via CMD or use the libraries programatically. 

If you tend to use it via the CMD, provide the following options with arguments:
-i <input source> (can be file path or URL)
-o <output path> (file path where to save the generated documentation)
-f <output format> (e.g. PDF, HTML, Word or Markdown) (optional)
  
If you rather want to use it programatically, you can use WSDL2DocService.java, which provides an abstraction layer and high level nethods.

### Maven
You can download the sources via Maven
```
<dependency>
  <groupId>com.github.eriksattelmair</groupId>
  <artifactId>wsdl2doc</artifactId>
  <version>1.4</version>
</dependency>
```
