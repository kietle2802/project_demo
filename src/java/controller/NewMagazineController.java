package controller;

import model.Magazine;
import model.MagazinesDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author MrEnd
 */
public class NewMagazineController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, 
                                  HttpServletResponse response)
                                  throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewMagazineController</title>"); 
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewMagazineController at " + 
                        request.getContextPath() + "</h1>");
            String id = request.getParameter("id");
            String title = request.getParameter("title");
            String publisher = request.getParameter("publisher");
            String p = request.getParameter("price"); 
            if (Validation(id, title, publisher, p).equals("")) {
                Magazine m = new Magazine(id, title, publisher, Double.parseDouble(p));
                try {
                    MagazinesDAO dao = new MagazinesDAO();
                    if (dao.newMagazine(m)) {
                        out.print("New Magazine Inserted!!!");
                        // forward back to index.jsp
                        out.print("<form action=\"index.jsp\" method=\"post\">\n<br><br>" +
                                  " <input type=\"submit\" value=\"List all magazines\"> \n" +
                                  " </form>");
                    } else {
                        out.print("FAIL");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    out.print("ERROR: " + e.getMessage());
                }
            } else {
                out.println(Validation(id, title, publisher, p));
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    public String Validation(String id, String title, String publisher, String price) {
        if (!id.matches("^M\\d{3}$"))
            return "Maz must follow M + 3 digits";
        MagazinesDAO dao = new MagazinesDAO();
        ArrayList<Magazine> list = dao.getAll(id);
        // check if maz id existed in database
        if (!list.isEmpty())
            return "This maz id existed";
        if (title.equals(""))
            return "Please input title";
        if (publisher.equals(""))
            return "Please input publisher";
        try {
            double p = Double.parseDouble(price);
        } catch (Exception e) {
            return "Price must be a number";
        }
        return "";
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the 
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse 
                         response)
                         throws ServletException, IOException {
      //  processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse 
                          response)
                          throws ServletException, IOException {
     //   processRequest(request, response);
     request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
    
}
