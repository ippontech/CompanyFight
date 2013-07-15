package fr.ippon.companyfight.web.servlet;

import fr.ippon.companyfight.model.Fight;
import fr.ippon.companyfight.service.FightService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(name= "LatestFights", urlPatterns = "/latest-fights")
public class LatestFightsServlet extends HttpServlet {

    @Inject
    private Logger log;

    @Inject
    private FightService fightService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Fight> fights = fightService.findLatestFights();
        req.setAttribute("fights", fights);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/pages/latest-fights.jsp");
        dispatcher.forward(req, resp);
    }
}
