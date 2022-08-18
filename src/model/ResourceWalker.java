package model;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.*;

public class ResourceWalker {
	public static ArrayList<String> getFileNames(String directory) throws URISyntaxException, IOException {
		ArrayList<String> result = new ArrayList<String>();
		
		URI uri = ResourceWalker.class.getResource(directory).toURI();
        Path myPath;
        if (uri.getScheme().equals("jar")) {
            FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.<String, Object>emptyMap());
            myPath = fileSystem.getPath(directory);
        } else {
            myPath = Paths.get(uri);
        }
        Stream<Path> walk = Files.walk(myPath, 1);
        for (Iterator<Path> it = walk.iterator(); it.hasNext();) {
        	String path = it.next().toString();
        	String[] tmp = path.split(Pattern.quote("\\"));
        	String filename = tmp[tmp.length-1];
            result.add(filename);
        }
        
        walk.close();
        
        result.remove(0);
        
        return result;
	}
}