// Import Servlet Libraries
import javax.servlet.*;
import javax.servlet.http.*;

// Import Java Libraries
import java.io.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "AdditionalServlet", urlPatterns = {"/printPage"})
public class printPage extends HttpServlet
{
   public void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException
   {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<style>");
		out.println("button:hover{");
		out.println("background: #FAEBD7;");
		out.println("}");

		out.println("body {");
			out.println("height: 1000px;");
			out.println("background: linear-gradient(to top, #33ccff 0%, #666699 100%);");
		out.println("}");

		out.println("h1 {");
			out.println("color: linen;");
		out.println("}");
		out.println("p {");
			out.println("color: linen;");
		out.println("}");
		
		out.println("ul {");
			out.println("color: lightgray;");
		out.println("}");
		out.println("label {");
			out.println("color: linen;");
		out.println("}");
		out.println("div	{");
			out.println("color: lightgray;");
		out.println("}");
		out.println("</style>");
		out.println("</head>");


		out.println("<body>");
		out.println("<title>SWE 432 - Eric Schumacher, Arian Filipour, & Daniel Sinani</title>");
		out.println("<h1><b>SWE 432 A6 ~ <i><font size=5>Eric Schumacher, Arian Filipour, & Daniel Sinani</font></i></h1></b>");
		out.println("<p><font size=6>Instructions:</font></p>");
		out.println("<font size=5>");
		out.println("<ul>");
			out.println("<li><i>You will be asked to enter in an initial number (the number of characteristics)</i></li><br>");
			out.println("<li><i>You will then be asked to enter a list of numbers for each characteristic based the initial number you chose</i></li><br>");
			out.println("<li><i>Example list of numbers if intial number is 4: \"1 2 3 4\"</i></li><br>");
			out.println("<li><i>You will then be asked to enter a list of names for each characteristic based on the initial number</i></li><br>");
			out.println("<li><i>Example list of names if intial number is 3: \"name age major\"</i></li><br>");
		out.println("</ul>");
		out.println("</font>");

		out.println("<form method=\"post\" action=\"https://dsinaniwebapps.herokuapp.com/bussLogic\" id=\"fm\">");
		out.println("<label for=\"text\"><font size=5>Enter in an initial number:</font></label><br>");
		out.println("<input type=\"text\" name=\"N\" id=\"N\">");
		out.println("<br><label for=\"text\"><font size=5>Enter a list of numbers:</font></label><br>");
		out.println("<input type=\"text\" name=\"nums\" id=\"nums\">");
		out.println("<br><label for=\"text\"><font size=5>Enter a list of names:</font></label><br>");
		out.println("<input type=\"text\" name=\"names\" id=\"names\">");
		out.println("<button style=\"font-size:13px;\" id=\"b\" onclick=\"\"><font color=\"black\"><i>Enter</i></font></button><br>");
		out.println("</form>");

		out.println("<br>");
		out.println("<p><font size=6>Collaborative Summary:</p></font>");
		out.println("<ul><i><font size=5>");
		out.println("We all collaborated for creating the servlets for the doGet and doPost methods. Eric and Arian worked on the printPage servlet and Daniel worked on the bussLogic servlet. All of us took part in styling. We included the names for the characteristics and the each-choice and base-choice tests.");
		out.println("</ul></i></font><br>");
/*		out.println("<ul><i><font size=5>");
		out.println("In this assignment, Daniel and Arian collaberated on refactoring the previous web application by ensuring there is correct functionality. Arian worked with the team to ensure the web application is deployed on the home pages using github & Heroku. Eric reviewed the code refacotorization while making sure the web application follows all usability concepts taught in class.");
		out.println("</ul></i></font><br>");*/
		out.println("<p><b>The GMU CS Department Website: </b><a href=\"https://cs.gmu.edu/\">https://cs.gmu.edu</a></p>");

		out.println("</body>");
		out.println("</html>");

		out.close ();
    }
}