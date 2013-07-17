package fr.ippon.companyfight.web.servlet;

import fr.ippon.companyfight.model.Score;
import fr.ippon.companyfight.repository.ScoreRepository;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HighScoresServlet", urlPatterns = "/high-scores")
public class HighScoresServlet extends HttpServlet {

    @Inject
    private ScoreRepository scoreRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Score> scores = scoreRepository.findHighScores();
        req.setAttribute("scores", scores);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/pages/high-scores.jsp");
        dispatcher.forward(req, resp);
    }
}
