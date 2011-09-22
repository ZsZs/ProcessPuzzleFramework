
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" version="-//W3C//DTD XHTML 1.1//EN" xml:lang="en">
	<head>
  <title>Course List</title>  
      
  </head>
 
  <body>
  <h1>Upcoming Courses</h1>
   
  <?php
  
  // TODO: Schedule download and download of courses from Scrum Alliance page
  // http://www.scrumalliance.org/profiles/72-michael-j-vizdos/courses.xml
  
	// Load the XML source - assume the courses.xml file is in the local path
	$xml = new DOMDocument;
	$xml->load('http://www.implementingscrum.com/courses/courses.xml');

	// load the XSL style sheet - apply formatting guidelines there
	$xsl = new DOMDocument;
	$xsl->load('http://www.implementingscrum.com/courses/courses.xsl');
	
	// Configure the transformer
	$proc = new XSLTProcessor;
	$proc->importStyleSheet($xsl); // attach the xsl rules
	
	// Result output
	echo $proc->transformToDoc($xml)->saveXML();
	  
	?>
  </body>
</html>