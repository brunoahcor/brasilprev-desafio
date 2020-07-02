package br.com.brasilprev.desafio.controller;

/*@RestController*/
public class ViewErrorController /*implements ErrorController*/ {

    /*
    @RequestMapping("/error")
    @ResponseBody
    public String handleError(HttpServletRequest request) {

        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");

        StringBuilder sb = new StringBuilder("<html><body>");
        sb.append("<h1>Error Page</h1>");
        sb.append("<h2>Status Code: ");
        sb.append(statusCode);
        sb.append("</h2>");
        sb.append("<h2>Exception Message:<h2>");
        sb.append("<h3>");
        sb.append(exception==null ? "N/A" : exception.getMessage());
        sb.append("</h3>");
        sb.append("</body></html>");

        return sb.toString();
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
    */
    
}