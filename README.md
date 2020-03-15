# easy_files
Easy upload and download over http. Simple file server on top of spring boot. 

Run 
---
**java -jar easy-files-0.0.1-SNAPSHOT.jar**

### Features
* To download a file : 
curl -O http://localhost:8091/download/note.txt

* To upload a file : 
curl -F "file=@Documents/note.txt" http://localhost:8091/upload 

* List all files : curl http://localhost:8091/list
