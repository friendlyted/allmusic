package pro.friendlyted.allmusic.servlet;

import pro.friendlyted.allmusic.model.Triad;
import pro.friendlyted.allmusic.tools.IntervalSupplier;
import pro.friendlyted.allmusic.tools.Quest;
import pro.friendlyted.allmusic.tools.TriadSupplier;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TriadQuestServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final Quest result = TriadSupplier.getTriadQuest();
        resp.getWriter().append("{\"answer\":\"" + result.getAnswer() + "\",\"question\":\"" + result.getQuestion() + "\"}");
    }
}
