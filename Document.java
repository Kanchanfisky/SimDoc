import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import java.net.*;

public class Document
{
	ArrayList<Integer> hashCodes;
	ArrayList<Integer> shingles;
	static int[] randNums;
	static int maxShingles = 200;
	static int k = 5;
	static final int MAX_HASH_VALUE = 10000000;

	public static String readFile(String filename)
	{
		String text = ""; 
		try {
			FileInputStream fis = new FileInputStream (filename);
			byte[] bytes = new byte[512];
			int bytesRead = fis.read(bytes, 0, bytes.length);
			while (bytesRead != -1)
			{
				text += new String(bytes);
				bytesRead = fis.read(bytes, 0, bytes.length);
			}
		}
		catch (IOException e){
			System.out.println(e);
		}
		return text;
	}

	public String readFromURL(String urlString){
		String text = "";
		try{
		    URL obj = new URL(urlString);
		    InputStream is = obj.openConnection().getInputStream();
		    byte[] bytes = new byte[512];
			int bytesRead = is.read(bytes, 0, bytes.length);
			while (bytesRead != -1)
			{
				text += new String(bytes);
				bytesRead = is.read(bytes, 0, bytes.length);
			}
		}
		catch (Exception e){
		    System.out.println(e);
		}
		return text;
	}
	
	public Document(String input, int param)
	{	
		String text = "";
		if (param == 0) text = input;
		else if (param == 1) text = readFile(input);
		else if (param == 2) text = readFromURL(input);
		
		hashCodes = new ArrayList<Integer> ();
		String[] docWords = text.split(" ");
		
		for(int i = 0; i < ((docWords.length - k) + 1); i++)
		{
			String shingle = "";
			for(int j = 0; j < k; j++)
			{
				shingle += docWords[i + j];
			}
			//System.out.println(shingle);
			hashCodes.add(Math.abs(shingle.hashCode()) % MAX_HASH_VALUE);
		}
		//System.out.println(hashCodes);
		shingles = new ArrayList<Integer> ();
		for (int i = 0; i < maxShingles; i++)
		{
			shingles.add(getMin(newHashcode(i)));
		}
		//System.out.println(shingles);
	}

	public double jaccard(Document that)
	{
		ArrayList<Integer> intersection = new ArrayList<Integer>(this.shingles);
		intersection.retainAll(that.shingles);

		int size1 = this.shingles.size();
		int size2 = that.shingles.size();

		return ((double) intersection.size()) / (size1 + size2 - intersection.size());
	}

	public static void makeRand(){
		randNums = new int[maxShingles - 1];
		Random gen = new Random(111333777);
		for (int i = 0; i < randNums.length; i++)
		{
			randNums[i] = gen.nextInt(100000);
		}	
	}

	public ArrayList<Integer> newHashcode(int index)
	{
		if (index == 0) return hashCodes;
		ArrayList<Integer> newHashCodes = new ArrayList<Integer> ();

		index--;
		for (int h : hashCodes)
		{
			newHashCodes.add((h ^ randNums[index]) % MAX_HASH_VALUE);
		}
	//	System.out.println(newHashCodes);
		return newHashCodes;
	}

	public int getMin(ArrayList<Integer> hashShingles)
	{
		int min = hashShingles.get(0);
		for (int x : hashShingles)
		{
			if (x < min) min = x;
		}
		return min;
	}
}