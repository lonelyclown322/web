import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.HitResponse;
import utils.ValuesValidation;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;

@WebServlet(urlPatterns = "/check")
public class AreaCheckServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();
        long startTime = System.nanoTime();

        String xString = req.getParameter("x_value");
        String yString = req.getParameter("y_value");
        String rString = req.getParameter("r_value");

        if (!Stream.of(xString, yString, rString).allMatch(Objects::nonNull)) {
            writer.println("Not all parameters have been set!");
            writer.flush();
            return;
        }

        float xValue;
        float yValue;
        float rValue;

        try {
            xValue = ValuesValidation.validateX(xString);
            yValue = ValuesValidation.validateY(yString);
            rValue = ValuesValidation.validateR(rString);
        } catch (NumberFormatException e) {
            writer.println("Some of parameters is not valid!");
            return;
        }

        HitResponse hitResponse = new HitResponse();

        hitResponse.setHit(ValuesValidation.isHit(xValue, yValue, rValue));
        hitResponse.setCurrentTime(getCurrentDate());
        hitResponse.setExecutionTime(getExecutionTime(startTime));
        hitResponse.setX(xValue);
        hitResponse.setY(yValue);
        hitResponse.setR(rValue);

        writer.println(GSON.toJson(hitResponse));
        System.out.println(GSON.toJson(hitResponse));
        ArrayList<String> list = (ArrayList<String>) req.getSession().getAttribute("dotJsons");
        if (list == null) {
            System.out.println("asdasdasdasdasd");
            System.out.println(req.getSession());
        }
        list.add(GSON.toJson(hitResponse))  ;
        System.out.println(GSON.toJson(list));
        System.out.println(req.getSession());
        writer.flush();
    }

    private String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    private double getExecutionTime(long startTime) {
        return Math.round( (System.nanoTime() - startTime) / 1000000.0 * 100) / 100.0;
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
