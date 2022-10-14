import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;


@WebServlet(urlPatterns = "/controller")
public class ControllerServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("dotJsons") == null) {
            req.getSession().setAttribute("dotJsons", new ArrayList<String>());
        }
        System.out.println(req.getSession().getAttribute("dotJsons"));
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();



        writer.write(GSON.toJson(req.getSession().getAttribute("dotJsons")));
        System.out.println(GSON.toJson(req.getSession().getAttribute("dotJsons")));
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String xValue = req.getParameter("x_value");
        String yValue = req.getParameter("y_value");
        String rValue = req.getParameter("r_value");

        log(xValue + " " +  yValue + " " + rValue);
        log("these values have just come");



        if (Stream.of(xValue, yValue, rValue)
                .allMatch(Objects::nonNull)) {
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/check");
            dispatcher.forward(req, resp);
        }

    }

    @Override
    public void destroy() {

    }
}
