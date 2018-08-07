package engine.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class OBJloader 
{	
	public OBJloader(Object o, String path) throws IOException
	{
		BufferedReader read = new BufferedReader(new FileReader(new File(path)));
		String line;
		while((line = read.readLine()) != null)
		{
			if(line.startsWith("v "))
			{
				float x = Float.valueOf(line.split(" ")[1]);
				float y = Float.valueOf(line.split(" ")[2]);
				float z = Float.valueOf(line.split(" ")[3]);
				Vector3f v = new Vector3f(x, y, z);
				//o.verts.add(v);
			}
			if(line.startsWith("vn "))
			{
				float x = Float.valueOf(line.split(" ")[1]);
				float y = Float.valueOf(line.split(" ")[2]);
				float z = Float.valueOf(line.split(" ")[3]);
				Vector3f v = new Vector3f(x, y, z);
				//o.norms.add(v);
			}
			if(line.startsWith("f "))
			{
				/*float x1 = Float.valueOf(line.split(" ")[1].split("/")[0]);
				float y1 = Float.valueOf(line.split(" ")[2].split("/")[0]);
				float z1 = Float.valueOf(line.split(" ")[3].split("/")[0]);
				float x2 = Float.valueOf(line.split(" ")[1].split("/")[2]);
				float y2 = Float.valueOf(line.split(" ")[2].split("/")[2]);
				float z2 = Float.valueOf(line.split(" ")[3].split("/")[2]);
				Vector2f f = new Vector2f(new Vector3f(x2, y2, z2), new Vector3f(x1, y1, z1));*/
			}
		}
	}
}
