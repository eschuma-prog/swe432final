//Import Servlet Libraries
import javax.servlet.*;
import javax.servlet.http.*;

//Import Java Libraries
import java.io.*;
import java.util.*;
import java.lang.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "AnotherServlet", urlPatterns = {"/bussLogic"})
public class bussLogic extends HttpServlet
{ 
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, java.io.IOException
   {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();

      String para = "";
      Enumeration paraNames = request.getParameterNames();

      int numCharacteristics = 1;
      String[] values;
      Vector<characteristic> Characs = new Vector<>();

      out.print("<html>\n<head>\n\n");
      out.print("<title>SWE 432: Output</title>\n");
      out.print("</head>\n");
      out.print("<body>\n");
      out.print("<center><h2>Output</h2></center>\n");
      out.print("<hr>\n");

      if (paraNames.hasMoreElements()){
         para = (String)paraNames.nextElement();
         values = request.getParameterValues(para);
         if (values != null && !values[0].equals("")){
            numCharacteristics = Integer.parseInt(values[0]);
         }
      }
      out.print("Your initial number is: <font color=blue>");
      out.print(numCharacteristics);
      out.print("</font>\n");
      
      String[] charName;
      String[] numBlocks;
      String temp = "";
      //String[] numbers;
      characteristic C;

      if (paraNames.hasMoreElements()){
         para = (String)paraNames.nextElement();
         values = request.getParameterValues(para);
         if (values != null && !values[0].equals("")){
            temp = values[0];
         }
         
      }
      numBlocks = temp.split(" ");

      if (paraNames.hasMoreElements()){
         para = (String)paraNames.nextElement();
         values = request.getParameterValues(para);
         if (values != null && !values[0].equals("")){
            temp = values[0];
         }
         
      }
      charName = temp.split(" ");
      
      for (int i=0; i<numCharacteristics; i++) {
         C = new characteristic(charName[i], Integer.parseInt(numBlocks[i]));
         Characs.add(C);
         //charName++;
      }

      out.print("<hr>\n");
      out.print("<p>");
      out.print("\nCharacteristics and their blocks: <font color=blue>");
      out.print("<br>");
      for (int i=0; i<numCharacteristics; i++) {
         out.print("Characteristic " + (Characs.get(i)).getName() + ": ");
         out.print((Characs.get(i)).getBlocks());
         out.print("<br>");
      }
      out.print("</p>");

      out.print("<p>");
      out.print("<br>");
      //out.print("\nEach choice tests: <font color=blue>");
      EC(numCharacteristics, Characs, out);
      out.print("<br>");
      //out.print("\nBase choice tests: <font color=blue>");
      BC(numCharacteristics, Characs, out);
      out.print("</p>");

      out.print("</font>\n");
      out.print("</body>\n");
      out.print("</html>\n");
      out.close();
   }

   // Each-choice--print the blocks in each-choice order
private static void EC(int numCharacteristics, Vector<characteristic> Characs, PrintWriter out)
{
   int maxCharacteristic=0;
   String block;
   characteristic C;
   for (int charNum=0; charNum<numCharacteristics; charNum++)
   {  // Find the maximum # blocks among the characteristics
      C = Characs.get(charNum);
      if (C.getNumBlocks()>maxCharacteristic)
         maxCharacteristic= C.getNumBlocks();
   }
   out.print("\n" + maxCharacteristic + " each-choice abstract tests ");
   out.print("<br>");
   for (int testNum=1; testNum<=maxCharacteristic; testNum++)
   {
      out.print("Abstract test " + testNum + ": [");
      for (int charNum=0; charNum<numCharacteristics; charNum++)
      {
         C=Characs.get(charNum);
         block=C.getName();
         out.print(block);
         if (testNum<=C.getNumBlocks())
            out.print(testNum);
         else // no more blocks, use wild card
            out.print("*");
         if (charNum<numCharacteristics-1)
            out.print(", ");
      }
      out.println("]");
      out.print("<br>");
   }
} // end EC

// Base-choice--print the blocks in base-choice order
// Assume base blocks are all '1'
private static void BC(int numCharacteristics, Vector<characteristic> Characs, PrintWriter out)
{
   String block;
   characteristic C;
   int numTests = 1; // start at 1 for the base test
   for (int CNum=0; CNum<numCharacteristics; CNum++)
   {  // Find the maximum # blocks among the characteristics
      C = Characs.get(CNum);
      numTests = numTests + (C.getNumBlocks()-1);
   }
   out.println("\n" + numTests + " base-choice abstract tests");
   out.print("<br>");

   // Create base test
   Vector<String> baseTest = new Vector<>();
   for (int CNum=0; CNum<numCharacteristics; CNum++)
   {
      C = Characs.get(CNum);
      baseTest.add(C.getName()+"1");
   }
   out.print("Abstract test 1 (base): ");
   out.println(baseTest);
   out.print("<br>");

   // non-base tests
   Vector<String> nextTest = new Vector<>(baseTest);
   int testNum = 2;
   for (int CNum=0; CNum<numCharacteristics; CNum++)
   { // for (int BNum=2; BNum<=3; BNum++)
      C = Characs.get(CNum);
      for (int BNum=2; BNum<=C.getNumBlocks(); BNum++)
      {
         nextTest.set(CNum, C.getName()+String.valueOf(BNum));
         out.println("Abstract test " + testNum + " = " + nextTest);
         testNum++;
         nextTest.set(CNum, baseTest.get(CNum));
         out.print("<br>");
      }
   }
} // end BC()


   public class characteristic
   {
      private String name;
      private int numBlocks;

   // Default constructor has 2 blocks and is named "A"
      public characteristic()
      {
         name = "A";
         numBlocks = 2;
      }

   // Constructor that takes a num and number of blocks
      public characteristic(String name, int blocks)
      {
         this.name = name;
         this.numBlocks = blocks;
      }

   // Getter and setter for name
      public String getName ()
      {
         return this.name;
      }
      public void setName (String name)
      {
         this.name = name;
      }

   // Getter and setter for number of blocks
      public int getNumBlocks ()
      {
         return numBlocks;
      }
      public void setNumBlocks (int numBlocks)
      {
         this.numBlocks = numBlocks;
      }

      public String toString()
      {
         return ("[" + name + ", " + numBlocks + "]");
      }

      public String getBlocks()
      {
         String returnVal = "[ ";
         for (int j=0; j<numBlocks; j++)
         {
            returnVal += name + (j+1);
            if (j<numBlocks-1)
               returnVal += ",";
            returnVal += " ";
         }
         returnVal += "]";
         return returnVal;
      }
   } // end class characteristic

}//end class bussLogic
