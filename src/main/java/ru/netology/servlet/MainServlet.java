package ru.netology.servlet;

import ru.netology.config.JavaConfig;
import ru.netology.controller.PostController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {
    private PostController controller;
    private final String BASE_PATH = "/api/posts";

    @Override
    public void init() {
        final var config = new JavaConfig();
        controller = config.postController();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        // если деплоились в root context, то достаточно этого
        try {
            final var path = req.getRequestURI();
            final var method = req.getMethod();
            // primitive routing
            if (method.equals(HttpMethod.GET.getMethod()) && path.equals(BASE_PATH)) {
                controller.all(resp);
                return;
            }
            if (method.equals(HttpMethod.GET.getMethod()) && path.matches(BASE_PATH + "/\\d+")) {
                final var id = Long.parseLong(path.substring(path.lastIndexOf("/") + 1));
                controller.getById(id, resp);
                return;
            }
            if (method.equals(HttpMethod.POST.getMethod()) && path.equals(BASE_PATH)) {
                controller.save(req.getReader(), resp);
                return;
            }
            if (method.equals(HttpMethod.DELETE.getMethod()) && path.matches(BASE_PATH + "/\\d+")) {
                // easy way
                final var id = Long.parseLong(path.substring(path.lastIndexOf("/") + 1));
                controller.removeById(id, resp);
                return;
            }
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}

